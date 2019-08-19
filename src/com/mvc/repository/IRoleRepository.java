package com.mvc.repository;

import java.util.List;

import com.mvc.entity.Role;

public interface IRoleRepository {
	public List<Role> findByUser(String username);
}
