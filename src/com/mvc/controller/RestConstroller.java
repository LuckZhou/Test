package com.mvc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvc.entity.User;
import com.mvc.form.UserForm;
import com.mvc.service.IUserService;

@Controller
public class RestConstroller {
	@Resource
	private IUserService testService;
	@RequestMapping(value = "/welcome", method = RequestMethod.POST)
	public String welcome(UserForm userform, HttpSession session) {
		List<User> list = testService.getUserList(userform.getUserName(), userform.getPassword());
		if (list != null && list.size() > 0) {
			session.setAttribute("user", userform.getUserName());
			return "/welcome";
		}
		session.setAttribute("errorInfo", "用户名或密码不正确！");
		return "/login";
	}

	@RequestMapping(value = "/api/welcome2", method = RequestMethod.GET)
	public String welcome() {
		return "/welcome";
	}

	@RequestMapping(value = "/login")
	public String tologin() {
		return "/login";
	}

}
