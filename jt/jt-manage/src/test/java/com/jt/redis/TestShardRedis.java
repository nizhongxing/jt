package com.jt.redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

//测试redis分片技术

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestShardRedis {

	//@Autowired
	private ShardedJedis shardedJedis;
	
	@Test
	public void test01() {
		//准备list集合封装多态redis
		
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("192.168.161.133",6379));
		shards.add(new JedisShardInfo("192.168.161.133",6380));
		shards.add(new JedisShardInfo("192.168.161.133",6381));
		ShardedJedis jedis = new ShardedJedis(shards);
		
		//当前操作的是redis分片对象，操作3台redis
		jedis.set("1812", "分片机制成功！！！");
		System.out.println(jedis.get("1812"));
		
		
	}
	
	
	@Test
	public void test02() {
		shardedJedis.set("1812", "分片1812机制成功！！！");
		System.out.println(shardedJedis.get("1812"));
	}
	
	
}
