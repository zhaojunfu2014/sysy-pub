package com.zjf.cms.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 * 基础dao接口
 * @author 赵俊夫
 *
 * Aug 15, 2012
 */
@Repository
public interface BaseDao {
	/**
	 * 持久化对象
	 */
	public void save(Object t);
	/**
	 * 懒加载对象
	 * @param <T>
	 * @param className
	 * @param id
	 * @return
	 */
	public <T> T load(Class<T> className,Serializable id);
	/**
	 * 更新对象
	 */
	public void update(Object t);
	/**
	 * 删除单个对象
	 * @param object
	 */
	public void delete(Object object);
	/**
	 * 删除多个对象
	 * @param objects
	 */
	public void deletes(Object[] objects);
	/**
	 * 查询条目-by id
	 */
	public <T> T fetch(Class<T> className,Serializable id);
	/**
	 * 查询所有的条目
	 */
	public <T> List<T> fetchAll(Class<T> className); 
	/**
	 * 获取某表长度
	 * @param className
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer getTableSize(Class className,Set<Criterion> criterions);
	
	/**
	 * 获取数据库分页约束数据
	 * @param <T>
	 * @param className
	 * @param firstIndex
	 * @param maxResult
	 * @param criterions
	 * @return
	 */
	public <T> List<T> getScrollData(Class<T> className,Integer firstIndex,Integer maxResult,Set<Criterion> criterions);
	/**
	 * 获取数据库约束数据
	 * @param <T>
	 * @param className
	 * @param criterions
	 * @return
	 */
	public <T> List<T> getScrollData(Class<T> className,Set<Criterion> criterions);
	/**
	 * 获取数据库约束排序
	 * @param <T>
	 * @param className
	 * @param firstIndex
	 * @param maxResult
	 * @param criterions
	 * @return
	 */
	public <T> List<T> getScrollData(Class<T> className,Integer firstIndex,Integer maxResult,Set<Criterion> criterions,Set<Order> orders);
}
