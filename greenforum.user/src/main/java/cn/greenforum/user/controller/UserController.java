package cn.greenforum.user.controller;

//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.common.pojo.User;
import com.jt.common.utils.CookieUtils;
import com.jt.common.vo.SysResult;

import cn.greenforum.user.service.UserService;

@RestController
@RequestMapping("user/manage")
public class UserController {
	@Autowired
	private UserService userService;
	//注册时,检查用户名是否重复
	@RequestMapping("checkUserName")
	public SysResult checkUserName(String userName){
		Boolean exist= userService.checkUserName(userName);
		//判断返回的sysResult值
		if(exist){//不可用 status=201
			return SysResult.build(201, "已经存在", null);
		}else{//可用
			return SysResult.ok();
		}
	}
	//注册页面,注册表单提交
	@RequestMapping("save")
	public SysResult saveUser(User user){
		try{
			userService.saveUser(user);//业务层封装数据完整
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, "注册失败", null);
		}
	}
	//登录校验
	@RequestMapping("login")
	public SysResult doLogin(User user,HttpServletRequest req,HttpServletResponse res){
		//在业务层中实现登录逻辑,存储redis过程
		//返回给控制层一个生成的redis存储ticket值
		//登录成功,ticket是非空,登录失败返回空null/""
		String ticket=userService.doLogin(user);
		if(StringUtils.isNotEmpty(ticket)){
			return SysResult.build(200, "ok", ticket);
		}else{
			//ticket为空登录失败
			return SysResult.build(201, "登录失败", null);
		}
	}
	//通过js发送的参数ticket获取userJson封装返回给ajax使用
	@RequestMapping("/query/{ticket}")
	public SysResult checkLoginUserData(@PathVariable String ticket){
		String userJson=
			userService.checkLoginUserData(ticket);
		if(StringUtils.isNotEmpty(userJson)){
			//说明userJson非空 说明登录状态正在生效
			return SysResult.build
					(200, "登录正常", userJson);
		}else{//登录失效超时
			return SysResult.build
					(201, "登录失效", null);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
