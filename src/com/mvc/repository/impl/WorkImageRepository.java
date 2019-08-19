package com.mvc.repository.impl;

import org.springframework.stereotype.Repository;

import com.core.repository.impl.BaseRepository;
import com.mvc.entity.WorkImage;
import com.mvc.repository.IWorkImageRepository;
@Repository
public class WorkImageRepository  extends BaseRepository<WorkImage> implements IWorkImageRepository{

	@Override
	public void addWorkImage(WorkImage workImage) {
		add(workImage);
	}

}
