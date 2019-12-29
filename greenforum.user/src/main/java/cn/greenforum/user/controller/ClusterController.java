package cn.greenforum.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisCluster;

@RestController
public class ClusterController {
	@Autowired
	private JedisCluster jedis;
	@RequestMapping("cluster")
	public String setAndGet(String key,String value){
		jedis.set(key, value);
		return jedis.get(key);
	}
}

