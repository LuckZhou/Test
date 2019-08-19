package com.mvc.repository;

import java.io.Serializable;
import java.util.List;

import com.mvc.entity.Work;

public interface IWorkRepository {
       public Serializable saveWork(Work work);
       public List<Work> workList();
       public Work findById(Serializable id);
       public List<Work> findWorkByUserId(String userId);
}
