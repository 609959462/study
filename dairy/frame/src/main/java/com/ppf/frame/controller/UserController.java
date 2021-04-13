package com.ppf.frame.controller;

import com.ppf.frame.common.AjaxResult;
import com.ppf.frame.common.RedisCache;
import com.ppf.frame.pojo.User;
import com.ppf.frame.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/frame/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    RedisCache redisCache;

    //登录方法
    @GetMapping("/login")
    public AjaxResult login(@Validated User user) {
    	//shiro登录验证
		try{
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
				user.getUserName(),
				user.getUserPassword()
		);
		subject.login(usernamePasswordToken);
			Map<String,User> map = new HashMap<String, User>();
			redisCache.setCacheMap("user",map);
		return new AjaxResult(500,"登陆成功","good");
		}catch (UnknownAccountException e) {
			System.out.println("UnknownAccountException");
			return new AjaxResult();
		} catch (DisabledAccountException e) {
			System.out.println("DisabledAccountException");
			return new AjaxResult();
		} catch (IncorrectCredentialsException e) {
			System.out.println("IncorrectCredentialsException");
			return new AjaxResult(500,"登陆失败，密码错误","bad");
		} catch (ExcessiveAttemptsException e) {
			System.out.println("ExcessiveAttemptsException");
			return new AjaxResult();
		} catch (RuntimeException e) {
			System.out.println("RuntimeException");
			return new AjaxResult();
		}
    }
}
