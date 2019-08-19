package com.mvc.repository;


import java.util.List;

import com.mvc.entity.Permission;



public interface IPermissionRepository {
	public void savePermission(Permission permission);
	public void updatePermission(Permission function);
	public Permission findPermission(String userid);
	public List<Permission> findAllPermission(String userid);
	public List<Permission> findPermissionByName(String userid);
	public void deletePermission(String id);
}
