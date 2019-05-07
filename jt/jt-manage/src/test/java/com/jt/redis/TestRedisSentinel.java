package com.jt.redis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class TestRedisSentinel {

	@Test
	public void test01() {
		
		//String msg = new HostAndPort("192.168.161.133", 26379);
		System.out.println("工具API");
		Set<String> sentinels = new HashSet<>();
		sentinels.add("192.168.161.133:26379");
		JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
		//只能操作主机
		Jedis jedis = pool.getResource();
		jedis.set("1812", "哨兵测试成功！！！");
		System.out.println("获取结果："+jedis.get("1812"));
		jedis.close();
		
		
		
		
		
		
	}
}
