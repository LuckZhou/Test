package com.mvc.service;

import java.util.List;

import com.mvc.entity.Role;

public interface IRoleService {
	public List<Role> findByUser(String username);
}
