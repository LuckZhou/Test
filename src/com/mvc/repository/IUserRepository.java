package com.mvc.repository;

import java.util.List;

import com.mvc.entity.User;

public interface IUserRepository {
       public List<User> getList(String username,String password);
       public void addUser(User user);
       public void updateUser(User user);
       public User findById(String userId);
       public User findUser(String username);
}
