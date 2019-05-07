package com.jt.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jt.anno.CacheAnnotation;
import com.jt.util.ObjectMapperUtil;

import redis.clients.jedis.JedisCluster;


@Component
@Aspect
public class CacheAspect {

	@Autowired
	private JedisCluster jedis;
	
	
	//@Autowired
	//private ShardedJedis jedis;
	
	//实现redis哨兵高可用操作
	//@Autowired(required=false)
	//private RedisService jedis;
	
	
	@Around(value="@annotation(cacheAnno)")
	public Object around(ProceedingJoinPoint joinPoint,CacheAnnotation cacheAnno) {
		
		String key = getKey(joinPoint,cacheAnno);
		Object object = getObject(joinPoint,cacheAnno,key);
		return object;
		
	}

	//类名.方法名.参数值.值
	private String getKey(ProceedingJoinPoint joinPoint, CacheAnnotation cacheAnno) {
		//获得类名
		String targetClassName = joinPoint.getSignature().getDeclaringTypeName();
		//获得方法名
		String methodName = joinPoint.getSignature().getName();
		//转化为方法对象
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		//获得参数名
		String[] parameterNames = methodSignature.getParameterNames();
		String parameter = parameterNames[cacheAnno.index()];
		Object arg = joinPoint.getArgs()[cacheAnno.index()];
		String key = targetClassName+"."+methodName+"."+parameter+"."+arg;
		return key;
	}

	private Object getObject(ProceedingJoinPoint joinPoint, CacheAnnotation cacheAnno, String key) {
		Object object =null;
		//判断用户缓存  读/更新
		switch(cacheAnno.cacheType()) {
		case FIND:
			object = findCache(joinPoint,cacheAnno,key);
			break;
			
		case UPDATE:
			object = updateCache(joinPoint,key);
			break;
		}
		return object;
	}

	private Object findCache(ProceedingJoinPoint joinPoint, CacheAnnotation cacheAnno, String key) {
		Object object = null;
		//根据key查询缓存信息
		String result = jedis.get(key);
		try {
			if(StringUtils.isEmpty(result)) {
				//表示缓存中没有数据，从业务层数据库取数据
				object = joinPoint.proceed();
				//将数据转化成字符串
				String json = ObjectMapperUtil.toJSON(object);
				//将字符串存储到redis中
				jedis.set(key, json);
				System.out.println("AOP查询业务层获得数据");
			}else {
				//表示缓存中有数据，可以直接返回数据
				//需要获取目标方法的方法类型
				MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
				Class<?> returnType = methodSignature.getReturnType();
				object = ObjectMapperUtil.toObject(result, returnType);
				System.out.println("AOP缓存查询数据");
			}
			
			
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
		return object;
	}

	private Object updateCache(ProceedingJoinPoint joinPoint, String key) {
		Object object = null;
		try {
			jedis.del(key);	//如果是更新操作,则直接删除缓存
			joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return object;
	}
	
	
	
	
	
	
}
