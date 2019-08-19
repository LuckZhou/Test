package com.mvc.repository.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.repository.impl.BaseRepository;
import com.mvc.entity.Service;
import com.mvc.repository.IServiceRepository;

@Repository
public class ServiceRepository extends BaseRepository<Service> implements IServiceRepository{

	@Override
	public Serializable saveService(Service service) {
		return save(service);
	}

	@Override
	public List<Service> serviceList() {
		StringBuffer hql=new StringBuffer();
		hql.append("from Service");
		return find(hql.toString());
	}

	@Override
	public Service findById(long id) {
		return get(Service.class, id);
	}

	@Override
	public List<Service> getMyService(String userId) {
		StringBuffer hql=new StringBuffer();
		hql.append("from Service where userId='");
		hql.append(userId);
		hql.append("'");
		return find(hql.toString());
	}
   
}
