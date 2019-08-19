package com.mvc.service;

import java.io.Serializable;
import java.util.List;

import com.mvc.entity.Service;

public interface IServiceService {
    public Serializable saveService(Service service);
    public List<Service> serviceList();
    public Service findById(long id);
    public List<Service> findMyService(String userId);
}
