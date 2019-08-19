package com.mvc.service.impl;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mvc.repository.IServiceRepository;
import com.mvc.service.IServiceService;
@Service
public class ServiceService implements IServiceService{
	@Resource
    private IServiceRepository serviceRepository;
	@Override
	public Serializable saveService(com.mvc.entity.Service service) {
		return serviceRepository.saveService(service);
	}
	@Override
	public List<com.mvc.entity.Service> serviceList() {
		return serviceRepository.serviceList();
	}
	@Override
	public com.mvc.entity.Service findById(long id) {
		return serviceRepository.findById(id);
	}
	@Override
	public List<com.mvc.entity.Service> findMyService(String userId) {
		return serviceRepository.getMyService(userId);
	}


}
