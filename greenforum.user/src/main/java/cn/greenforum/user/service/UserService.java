package cn.greenforum.user.service;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.pojo.User;
import com.jt.common.utils.MapperUtil;

import cn.greenforum.user.mapper.UserMapper;
import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.ShardedJedis;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	public Boolean checkUserName(String userName) {
		int result=userMapper
				.selectUserCountByUserName(userName);
		//result不是1就是0
		return result==1;
	}

	public void saveUser(User user) {
		//封装userId
		String userId=UUID.randomUUID().toString();
		user.setUserId(userId);
		//加密password
		String md5Password=DigestUtils.md5Hex(user.getUserPassword());
		System.out.println("密码密文:"+md5Password);
		user.setUserPassword(md5Password);
		//userType 数据库在接收到null 默认值0
		userMapper.insertUser(user);
	}

	@Autowired
	private JedisCluster jedis;
	public String doLogin(User user) {
		//依然要进行user校验工作
		user.setUserPassword(DigestUtils.md5Hex(user.getUserPassword()));
		User exist=userMapper.selectUserByUserNameAndPassword(user);
		if(exist==null){//登录失败
			return "";
		}else{//要引入顶替的逻辑
			//准备2个key值
			try{
			String userLoginIdent= "user_login_"+exist.getUserId();
			String ticket="EM_TICKET"+ System.currentTimeMillis()+exist.getUserId();
			String userJson= MapperUtil.MP.writeValueAsString(exist);
				if(jedis.exists(userLoginIdent)){
					//删除上次登录的ticket并且删除
					jedis.del(jedis.get(userLoginIdent));
				}
				//设置登录顶替相关的2个key-数据value
				jedis.setex(userLoginIdent, 60*60*3, ticket);
				jedis.setex(ticket, 60*60*2, userJson);
				return ticket;
			}catch(Exception e){
				e.printStackTrace();
				return "";
			}
		}
	}
	public String checkLoginUserData(String ticket) {
		String userLoginIdent="user_login_"+ticket.substring(22);
		//加入剩余时间续约的判断
		//判断剩余时间
		Long leftTime = jedis.pttl(ticket);
		//判断是否小于30分钟 1000*60*30
		if(leftTime<1000*60*30){
			//进行续约,将超时时间加长1小时
			Long expireTime=leftTime+1000*60*60;
			jedis.pexpire(ticket, expireTime);
			//对userLoginIdent做续约保证
			//userId才能对userLoginIdent 对ticket值作截取 操作
			jedis.pexpire(userLoginIdent,
			jedis.pttl(userLoginIdent)+1000*60*60);
		}
		return jedis.get(ticket);
		
	}
}
