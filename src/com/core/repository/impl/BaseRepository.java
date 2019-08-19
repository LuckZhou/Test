package com.core.repository.impl;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.core.repository.IBaseRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * dao层基础实现类
 *
 * @param <T>
 * @author Administrator
 */
@Repository
public class BaseRepository<T> implements IBaseRepository<T> {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    public Serializable save(T o) {
        return this.openSession().save(o);
    }

    public void add(T o) {
       this.openSession().save(o);
    }

    public void saveList(List<T> list) {
        Session session = this.openSession();
        for (int i = 0, len = list.size(); i < len; i++) {
            session.save(list.get(i));
            if (i % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    public void delete(T o) {
        this.openSession().delete(o);
    }

    public void update(T o) {
        this.openSession().update(o);
    }

    @Override
    public void update(String hql, Map<String, Object> param) {
        Query q = this.openSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (String o : param.keySet()) {
                q.setParameter(o, param.get(o));
            }
        }
        q.executeUpdate();
    }

    public void saveOrUpdate(T o) {
        this.openSession().saveOrUpdate(o);
    }

    @SuppressWarnings("unchecked")
	public List<T> find(String hql) {
        return this.openSession().createQuery(hql).list();
    }


    public List<T> find(String hql, Map<String, Object> param) {
        Query q = this.openSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (String o : param.keySet()) {
                if (o.endsWith("List")) {
                    q.setParameterList(o, (Collection) param.get(o));
                    continue;
                }
                q.setParameter(o, param.get(o));
            }
        }
        return q.list();
    }

    @Override
    public List<T> findByList(String hql, Map<String, Object> param) {
        Query q = this.openSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (String o : param.keySet()) {
                q.setParameterList(o, (List) param.get(o));
            }
        }
        return q.list();
    }

    public List<T> find(String hql, Map<String, Object> param, Integer page,
                        Integer rows, String sort, String order) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            hql += " order by " + sort + " " + order;
        }
        Query q = this.openSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (String o : param.keySet()) {
                q.setParameter(o, param.get(o));
            }
        }
        rows = rows > 0 ? rows : 10;

        return q.setFirstResult((page > 0 ? (page - 1) : 0) * rows)
                .setMaxResults(rows).list();
    }

    public List<T> find(String hql, Map<String, Object> param, Integer page,
                        Integer rows) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = this.openSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (String o : param.keySet()) {
                q.setParameter(o, param.get(o));
            }
        }
        rows = rows > 0 ? rows : 10;
        return q.setFirstResult((page > 0 ? (page - 1) : 0) * rows)
                .setMaxResults(rows).list();
    }

    public List<Map<String, Object>> findSql(String sql) {
        return (List<Map<String, Object>>) this.openSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    public List<Object[]> findSqlList(String sql) {
        return this.openSession().createSQLQuery(sql).list();
    }

    public List<Object> findListBySql(String sql) {
        return this.openSession().createSQLQuery(sql).list();
    }

    public Map<String, Object> findSqlUnique(String sql) {
        return (Map<String, Object>) this.openSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
    }

    public T get(Class<T> c, Serializable id) {
        return (T) this.openSession().get(c, id);
    }

    public T get(String hql, Map<String, Object> param) {
        List<T> l = this.find(hql, param);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    public Long count(String hql) {
        return (Long) this.openSession().createQuery(hql).uniqueResult();
    }

    public Long count1(String hql) {
        String listSize="0";
        try{
            listSize=this.openSession().createQuery(hql).list().size()+"";
        }catch (Exception e){

        }

        return Long.parseLong(listSize);
    }



    public Long count(String hql, Map<String, Object> param) {
        Query q = this.openSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (String o : param.keySet()) {
                q.setParameter(o, param.get(o));
            }
        }
        return (Long) q.uniqueResult();
    }

    public long sum(String hql, Map<String, Object> param) {
        Query q = this.openSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (String o : param.keySet()) {
                q.setParameter(o, param.get(o));
            }
        }
        Long result = (Long) q.uniqueResult();
        if (result == null) {
            return 0;
        }
        return result;
    }

    public double sumDouble(String hql, Map<String, Object> param) {
        Query q = this.openSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (String o : param.keySet()) {
                q.setParameter(o, param.get(o));
            }
        }
        Double result = (Double) q.uniqueResult();
        if (result == null) {
            return 0;
        }
        return result;
    }

    public Integer executeHql(String hql) {
        return this.openSession().createQuery(hql).executeUpdate();
    }

    public Integer executeHql(String hql, Map<String, Object> param) {
        Query q = this.openSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (String o : param.keySet()) {
                q.setParameter(o, param.get(o));
            }
        }
        return q.executeUpdate();
    }

    public List executeSql(String sql) {
        SQLQuery sq = this.openSession().createSQLQuery(sql);

        return sq.list();
    }

    public List executeSql(String sql, List<Object> param) {
        SQLQuery sq = this.openSession().createSQLQuery(sql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                sq.setParameter(i, param.get(i));
            }
        }
        return sq.list();
    }

    @Override
    public List executeSql(String sql, Map<String, Object> param) {
        SQLQuery sq = this.openSession().createSQLQuery(sql);
        if (param != null && param.size() > 0) {
            for (String o : param.keySet()) {
                sq.setParameter(o, param.get(o));
            }
        }
        return sq.list();
    }

    public List executeSql(String sql, List<Object> param, Integer page,
                           Integer rows) {
        SQLQuery sq = this.openSession().createSQLQuery(sql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                sq.setParameter(i, param.get(i));
            }
        }
        rows = rows > 0 ? rows : 10;
        return sq.setFirstResult((page > 0 ? (page - 1) : 0) * rows)
                .setMaxResults(rows).list();
    }

    public Integer countSql(String hql) {
        Integer n = 0;
        Object o = this.openSession().createSQLQuery(hql).uniqueResult();
        if (o != null) {
            n = ((Number) o).intValue();
        }
        return n;
    }

    public Integer executeSQL(String sql) {
        return this.openSession().createSQLQuery(sql).executeUpdate();
    }

    @Override
    public void updateSql(String sql) {
        this.openSession().createSQLQuery(sql).executeUpdate();
    }

    @Override
    public void updateSql(Object a) {
        //BUZHIDAO XINGBUXING
        this.openSession().save(a);
    }
}
