package com.mvc.repository;

import java.io.Serializable;
import java.util.List;

import com.mvc.entity.Service;

public interface IServiceRepository {
	public Serializable saveService(Service service);
	public List<Service> serviceList();
    public Service findById(long id);
    public List<Service> getMyService(String userId);
}
