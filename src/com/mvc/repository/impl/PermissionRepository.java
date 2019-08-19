package com.mvc.repository.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.core.repository.impl.BaseRepository;
import com.mvc.entity.Permission;
import com.mvc.repository.IPermissionRepository;
@Repository
public class PermissionRepository extends BaseRepository<Permission> implements IPermissionRepository{

	@Override
	public void savePermission(Permission permission) {
		add(permission);
	}

	@Override
	public void updatePermission(Permission permission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Permission findPermission(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permission> findAllPermission(String userid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Permission> findPermissionByName(String userName) {
		System.out.println("查数据库了===========");
		String hql="from Permission where userName='"+userName+"'";
		return find(hql);
	}

	@Override
	public void deletePermission(String id) {
		// TODO Auto-generated method stub
		
	}


}
