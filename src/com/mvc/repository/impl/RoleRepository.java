package com.mvc.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.entity.Role;
import com.mvc.repository.IRoleRepository;
@Repository
public class RoleRepository implements IRoleRepository{

	@Override
	public List<Role> findByUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
