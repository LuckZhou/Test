package com.mvc.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.repository.impl.BaseRepository;
import com.mvc.entity.User;
import com.mvc.repository.IUserRepository;
@Repository
public class UserRepository  extends BaseRepository<User> implements IUserRepository{
	@Override
	public List<User> getList(String username,String password) {
		StringBuffer hql=new StringBuffer(20);
		hql.append("from User where 1=1");
		hql.append(" and userName='");
		hql.append(username);
		hql.append("' and password='");
		hql.append(password);
		hql.append("'");
		return find(hql.toString());
	}

	@Override
	public void addUser(User user) {
		add(user);
	}

	@Override
	public void updateUser(User user) {
		update(user);
	}

	@Override
	public User findById(String userId) {
		return (User)get(User.class,userId);
	}
	public User findUser(String username) {
		String hql="from User where userName='"+username+"'";
		List<User> list=find(hql);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}   
}
