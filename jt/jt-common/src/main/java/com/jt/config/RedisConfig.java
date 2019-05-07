package com.jt.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;


//该配置为配置类 代替之前配置文件web.xml 和spring配置文件
//@ImportResource({"classpath:/spring/application-redis.xml"})	//导入第三方配置文件
@Configuration  //表示配置类 当springboot启动时，会加载配置类信息
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
	
	@Value("${redis.nodes}")
	private String nodes;
	
	
	@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> nodesSet = new HashSet<>();
		String[] node = nodes.split(",");
		for (String h_pNode : node) {
			String[] args = h_pNode.split(":");
			int port = Integer.parseInt(args[1]);
			HostAndPort hostAndPort = new HostAndPort(args[0], port);
			nodesSet.add(hostAndPort);
		}
		
		
		return new JedisCluster(nodesSet);
	}
	
	
	
	
	/*
	@Value("${redis.nodes}")
	private String nodes;
	@Value("${redis.masterName}")
	private String masterName;
	
	//获取的是哨兵的链接池
	@Bean
	public JedisSentinelPool jedisSentinelPool() {
		Set<String> sentinels = new HashSet<>();
		sentinels.add(nodes);
		return new JedisSentinelPool(masterName, sentinels);
	}
	
	*/
	
	/*
	 * @Value("${redis.host}") 
	 * private String host;
	 * 
	 * @Value("${redis.port}") 
	 * private Integer port;
	 */
	
	/*
	 * @Bean //将方法返回值对象交给spring管理 
	 * public Jedis jedis() { 
	 * return newJedis(host,port);
	 *  }
	 */
	
	
	/*
	@Value("${redis.shards}")
	private String redisShards;
	
	
	
	
	@Bean	//将方法返回值对象交给spring管理
	public ShardedJedis shardedJedis() {
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		String[] nodes = redisShards.split(",");
		for (String node : nodes) {
			String[] host_port = node.split(":");
			JedisShardInfo info = new JedisShardInfo(host_port[0],host_port[1]);
			shards.add(info);
		}
		
		return new ShardedJedis(shards);
	}
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
