package com.mvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mvc.entity.User;
import com.mvc.repository.IUserRepository;
import com.mvc.service.IUserService;
@Service
public class UserService implements IUserService{
	@Resource
	private IUserRepository userRepository;

	@Override
	public List<User> getUserList(String userName,String password) {
		List<User> list=userRepository.getList(userName,password);
		return list;
	}

	@Override
	public void addUser(User user) {
		userRepository.addUser(user);
	}

	@Override
	public void updateUser(User user) {
		userRepository.updateUser(user);
	}

	@Override
	public User findById(String userId) {
		return userRepository.findById(userId);
	}

	@Override
	public User findUser(String username) {
		return userRepository.findUser(username);
	}
}
