package com.jt.redis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class TestRedisCluster {

	@Test
	public void test01() {
		
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.161.133",7000));
		nodes.add(new HostAndPort("192.168.161.133",7001));
		nodes.add(new HostAndPort("192.168.161.133",7002));
		nodes.add(new HostAndPort("192.168.161.133",7003));
		nodes.add(new HostAndPort("192.168.161.133",7004));
		nodes.add(new HostAndPort("192.168.161.133",7005));
		nodes.add(new HostAndPort("192.168.161.133",7006));
		nodes.add(new HostAndPort("192.168.161.133",7007));
		nodes.add(new HostAndPort("192.168.161.133",7008));
		
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("1813", "666");
		System.out.println(cluster.get("1813"));
	}
	
}
