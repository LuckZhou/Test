package com.mvc.service;

import java.util.List;

import com.mvc.entity.User;

public interface IUserService {
	 public List<User> getUserList(String userName,String password);
	 public void addUser(User user);
	 public void updateUser(User user);
	 public User findById(String userId);
	 public User findUser(String username);
}
