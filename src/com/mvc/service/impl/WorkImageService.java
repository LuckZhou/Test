package com.mvc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mvc.entity.WorkImage;
import com.mvc.repository.IWorkImageRepository;
import com.mvc.service.IWorkImageService;
@Service
public class WorkImageService implements IWorkImageService{
	@Resource
    private IWorkImageRepository workImageRepository;
	@Override
	public void addWorkImage(WorkImage workImage) {
		workImageRepository.addWorkImage(workImage);
	}

}
