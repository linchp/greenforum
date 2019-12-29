package cn.greenforum.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConfigurationProperties(prefix="redis.cluster")
public class ClusterConfig {
	//节点信息
	private List<String> nodes;
	//最大连接数,最大空闲,最小空闲
	private Integer maxTotal;
	private Integer maxIdle;
	private Integer minIdle;
	//初始化的方法
	@Bean
	public JedisCluster initCluster(){
		Set<HostAndPort> set=new HashSet<HostAndPort>();
		for(String node:nodes){
			//10.9.39.13:8000 8001 8002
			String host=node.split(":")[0];
			int port=Integer.parseInt(node.split(":")[1]);
			set.add(new HostAndPort(host,port));
		}
		//连接池的属性
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMinIdle(minIdle);
		config.setMaxIdle(maxIdle);
		return new JedisCluster(set,config);
	}
	public List<String> getNodes() {
		return nodes;
	}
	public void setNodes(List<String> nodes) {
		this.nodes = nodes;
	}
	public Integer getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}
	public Integer getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}
	public Integer getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}
	
}










