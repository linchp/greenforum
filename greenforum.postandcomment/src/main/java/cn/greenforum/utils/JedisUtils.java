package cn.greenforum.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
/*
@Component
public class JedisUtils {
	@Autowired
	private ShardedJedisPool pool;
	
	//常用的方法,做重写封装
	public void updateOrAdd(String key,String value){
		ShardedJedis jedis = pool.getResource();
		try{
			jedis.set(key, value);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			pool.returnResource(jedis);
		}
	}
}





*/