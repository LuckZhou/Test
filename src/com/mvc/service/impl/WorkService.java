package com.mvc.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.mvc.entity.Work;
import com.mvc.repository.IWorkRepository;
import com.mvc.service.IWorkService;
@Service
public class WorkService implements IWorkService{
	@Resource
	private IWorkRepository workRepository;
	@Override
	public Serializable saveWork(Work work) {
		return workRepository.saveWork(work);
	}
	@Override
	public List<Work> workList() {
		return workRepository.workList();
	}
	@Override
	public Work findById(Serializable id) {
		return workRepository.findById(id);
	}
	@Override
	public List<Work> findWorkByUserId(String userId) {
		return workRepository.findWorkByUserId(userId);
	}
	

}
