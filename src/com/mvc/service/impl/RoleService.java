package com.mvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mvc.entity.Role;
import com.mvc.repository.IRoleRepository;
import com.mvc.service.IRoleService;
@Service
public class RoleService implements IRoleService{
	@Resource
	private IRoleRepository roleRepository;

	@Override
	public List<Role> findByUser(String username) {
		return roleRepository.findByUser(username);
	}

}
