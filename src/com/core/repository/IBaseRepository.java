package com.core.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * dao层基础接口
 *
 * @param <T>
 * @author Administrator
 */
public interface IBaseRepository<T> {
    /**
     * 保存一个对象
     *
     * @param o
     * @return
     */
    public Serializable save(T o);

    /**
     * 批量新增
     *
     * @param list
     */
    public void saveList(List<T> list);

    /**
     * 删除一个对象
     *
     * @param o
     */
    public void delete(T o);

    /**
     * 更新一个对象
     *
     * @param o
     */
    public void update(T o);

    public void update(String hql, Map<String, Object> param);

    /**
     * 保存或更新对象
     *
     * @param o
     */
    public void saveOrUpdate(T o);

    /**
     * 查询
     *
     * @param hql
     * @return
     */
    public List<T> find(String hql);

    /**
     * 查询集合
     *
     * @param hql
     * @param param
     * @return
     */
    public List<T> find(String hql, Map<String, Object> param);

    public List<T> findByList(String hql, Map<String, Object> param);

    /**
     * 查询集合(带分页)
     *
     * @param hql
     * @param param
     * @param page  查询第几页
     * @param rows  每页显示几条记录
     * @return
     */
    public List<T> find(String hql, Map<String, Object> param, Integer page,
                        Integer rows, String sort, String order);

    public List<T> find(String hql, Map<String, Object> param, Integer page,
                        Integer rows);

    /**
     * 通过sql查询，返回多条数据
     *
     * @param sql
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> findSql(String sql);

    /**
     * 通过sql查询,返回多条数据
     *
     * @param sql
     * @return List<Object>
     */
    public List<Object[]> findSqlList(String sql);

    /**
     * 通过sql查询,返回多条数据
     *
     * @param sql
     * @return List<Object>
     */
    public List<Object> findListBySql(String sql);

    /**
     * 通过sql查询，返回一条数据
     *
     * @param sql
     * @return
     */
    public Map<String, Object> findSqlUnique(String sql);

    /**
     * 获得一个对象
     *
     * @param c  对象类型
     * @param id
     * @return Object
     */
    public T get(Class<T> c, Serializable id);

    /**
     * 获得一个对象
     *
     * @param hql
     * @param param
     * @return Object
     */
    public T get(String hql, Map<String, Object> param);

    /**
     * select count(*) from 类
     *
     * @param hql
     * @return
     */
    public Long count(String hql);

    /**
     * select count(*) from 类
     *
     * @param hql
     * @param param
     * @return
     */
    public Long count(String hql, Map<String, Object> param);

    /**
     * 求和
     *
     * @param hql
     * @param param
     * @return
     */
    public long sum(String hql, Map<String, Object> param);

    public double sumDouble(String hql, Map<String, Object> param);

    /**
     * 执行HQL语句
     *
     * @param hql
     * @return 响应数目
     */
    public Integer executeHql(String hql);

    /**
     * 执行HQL语句
     *
     * @param hql
     * @param param
     * @return 响应数目
     */
    public Integer executeHql(String hql, Map<String, Object> param);

    /**
     * 执行SQL语句
     *
     * @param hql
     * @return 响应数目
     */
    public List executeSql(String sql);

    /**
     * 执行SQL语句
     *
     * @param hql
     * @param param
     * @return 响应数目
     */
    public List executeSql(String sql, List<Object> param);

    public List executeSql(String sql, Map<String, Object> param);

    /**
     * 执行SQL语句
     *
     * @param hql
     * @param param
     * @param page  查询第几页
     * @param rows  每页显示几条记录
     * @return 响应数目
     */
    public List executeSql(String sql, List<Object> param, Integer page,
                           Integer rows);

    /**
     * 总记录数
     *
     * @param hql
     * @return
     */
    public Integer countSql(String hql);

    /**
     * 执行SQL语句
     *
     * @param sql
     * @return
     */
    public Integer executeSQL(String sql);

    /**
     * 执行update语句
     *
     * @param sql
     */
    public void updateSql(String sql);

    public void updateSql(Object a);

    public void add(T o);
}
