package com.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ShiroAction {
    @RequestMapping("loginin")
    public String login(HttpServletRequest request){
		 //当前Subject  
		 Subject currentUser = SecurityUtils.getSubject();  
		 //加密（md5+盐），返回一个32位的字符串小写
		 String salt="("+request.getParameter("username")+")";  
		 String md5Pwd=new Md5Hash(request.getParameter("password"),salt).toString();
		 //传递token给shiro的realm
		 UsernamePasswordToken token = new UsernamePasswordToken(request.getParameter("username"),md5Pwd);  
		 try {  
		     currentUser.login(token); 
		     return "welcome";
		 
		 } catch (AuthenticationException e) {//登录失败  
		     request.setAttribute("msg", "用户名和密码错误");  
		 } 
		    return "login";
    }
    @RequestMapping("toLogin")
    public String toLogin(){
    	 return "login";
    }
}
