package com.mvc.repository.impl;
import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.repository.impl.BaseRepository;
import com.mvc.entity.Work;
import com.mvc.repository.IWorkRepository;
@Repository
public class WorkRepository  extends BaseRepository<Work> implements IWorkRepository{

	@Override
	public Serializable saveWork(Work work) {
		return save(work);
	}

	@Override
	public List<Work> workList() {
		StringBuffer hql=new StringBuffer();
		hql.append("from Work");
		return find(hql.toString());
	}

	@Override
	public Work findById(Serializable id) {
		return get(Work.class, id);
	}

	@Override
	public List<Work> findWorkByUserId(String userId) {
		StringBuffer hql=new StringBuffer();
		hql.append("from Work where userId='");
		hql.append(userId);
		hql.append("'");
		List<Work> list= find(hql.toString());
		return list;
	}
        
}
