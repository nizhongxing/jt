package com.jt.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class TestRedis {
	//如果需要从容器获取数据时，才使用spring的测试方式
	
	//1.实现字符串操作
	@Test
	public void testString() throws Exception {
		Jedis jedis = new Jedis("192.168.161.133", 6379);
		jedis.set("1812", "tomcat猫!!!");
		System.out.println(jedis.get("1812"));
		jedis.del("aa");
		
		//设置超时时间
		jedis.expire("1812", 30);
		Thread.sleep(2000);
		System.out.println("key还能存活多久:"+jedis.ttl("1812"));
		
	}
	
	@Test
	public void testHash() {
		Jedis jedis = new Jedis("192.168.161.133", 6379);
		//保存数据
		jedis.hset("user", "username", "abc");
		Boolean hexists = jedis.hexists("user", "username");
		System.out.println(hexists);
		Map<String, String> values = jedis.hgetAll("user");
		System.out.println(values);
		Set<String> set = jedis.hkeys("user");
		System.out.println(set);
		List<String> hvals = jedis.hvals("user");
		System.out.println(hvals);
		
	}
	
	
	@Test
	public void testList() {
		Jedis jedis = new Jedis("192.168.161.133", 6379);
		//当做队列使用
		//jedis.lpush("list", "1,2,3,4,5,6");
		jedis.lpush("list", "1","2","3","4","5","6");
		//先进先出
		String rpop = jedis.rpop("list");
		System.out.println(rpop);
		
	}
	
	@Test
	public void testTx() {
		Jedis jedis = new Jedis("192.168.161.133", 6379);
		//开启事务
		Transaction transaction = jedis.multi();
		//set操作
		try {
			transaction.set("aaa", "8999");
			transaction.set("bbb", "19011");
			
			transaction.exec();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.discard();
		}
	}
	
	
	
	
	
	
}
