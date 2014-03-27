package com.zjf.cms.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 * ����dao�ӿ�
 * @author �Կ���
 *
 * Aug 15, 2012
 */
@Repository
public interface BaseDao {
	/**
	 * �־û�����
	 */
	public void save(Object t);
	/**
	 * �����ض���
	 * @param <T>
	 * @param className
	 * @param id
	 * @return
	 */
	public <T> T load(Class<T> className,Serializable id);
	/**
	 * ���¶���
	 */
	public void update(Object t);
	/**
	 * ɾ����������
	 * @param object
	 */
	public void delete(Object object);
	/**
	 * ɾ���������
	 * @param objects
	 */
	public void deletes(Object[] objects);
	/**
	 * ��ѯ��Ŀ-by id
	 */
	public <T> T fetch(Class<T> className,Serializable id);
	/**
	 * ��ѯ���е���Ŀ
	 */
	public <T> List<T> fetchAll(Class<T> className); 
	/**
	 * ��ȡĳ����
	 * @param className
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer getTableSize(Class className,Set<Criterion> criterions);
	
	/**
	 * ��ȡ���ݿ��ҳԼ������
	 * @param <T>
	 * @param className
	 * @param firstIndex
	 * @param maxResult
	 * @param criterions
	 * @return
	 */
	public <T> List<T> getScrollData(Class<T> className,Integer firstIndex,Integer maxResult,Set<Criterion> criterions);
	/**
	 * ��ȡ���ݿ�Լ������
	 * @param <T>
	 * @param className
	 * @param criterions
	 * @return
	 */
	public <T> List<T> getScrollData(Class<T> className,Set<Criterion> criterions);
	/**
	 * ��ȡ���ݿ�Լ������
	 * @param <T>
	 * @param className
	 * @param firstIndex
	 * @param maxResult
	 * @param criterions
	 * @return
	 */
	public <T> List<T> getScrollData(Class<T> className,Integer firstIndex,Integer maxResult,Set<Criterion> criterions,Set<Order> orders);
}
