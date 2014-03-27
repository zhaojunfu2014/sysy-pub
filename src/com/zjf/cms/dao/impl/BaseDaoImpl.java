package com.zjf.cms.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zjf.cms.dao.BaseDao;
@Repository(value="baseDao")
@Transactional
public  class BaseDaoImpl implements BaseDao{
	@Resource protected SessionFactory sf;
	private Log log = LogFactory.getLog(getClass());

	@SuppressWarnings("unchecked")
	public <T> T fetch(Class<T> className,Serializable id) {
		return (T) sf.getCurrentSession().get(className, id);
	}

	public <T> List<T> fetchAll(Class<T> className) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Object t) {
		sf.getCurrentSession().save(t);
		log.debug(t.getClass());
	}

	public void update(Object t) {
		sf.getCurrentSession().update(t);
		
	}
	@SuppressWarnings("unchecked")
	public <T> List<T> getScrollData(Class<T> className, Integer firstIndex,
			Integer maxResult, Set<Criterion> criterions, Set<Order> orders) {
		Criteria cri = sf.getCurrentSession().createCriteria(className);
		if(firstIndex>=0){
			cri.setFirstResult(firstIndex);
		}
		if(maxResult>=0){
			cri.setMaxResults(maxResult);
		}
		if(criterions!=null){
			for(Criterion c:criterions){
				cri.add(c);
			}
		}
		if(orders!=null){
			for(Order o:orders){
				cri.addOrder(o);
			}
		}
		return cri.list();
	}

	@SuppressWarnings("unchecked")
	public Integer getTableSize(Class className, Set<Criterion> criterions) {
		Criteria cri = sf.getCurrentSession().createCriteria(className);
		if(criterions!=null){
			for(Criterion c: criterions){
				cri.add(c);
			}
		}
		return cri.list().size();
	}
	public <T> List<T> getScrollData(Class<T> className, Integer firstIndex,
			Integer maxResult, Set<Criterion> criterions) {
		return getScrollData(className, firstIndex, maxResult, criterions, null);
	}
	
	public <T> List<T> getScrollData(Class<T> className,
			Set<Criterion> criterions) {
		return getScrollData(className, -1, -1, criterions, null);
	}
	public void delete(Object object) {
		sf.getCurrentSession().delete(object);
	}

	public void deletes(Object[] objects) {
		for(Object obj : objects){
			sf.getCurrentSession().delete(obj);
		}

	}
	@SuppressWarnings("unchecked")
	public <T> T load(Class<T> className, Serializable id) {
		return (T) sf.getCurrentSession().load(className, id);
	}
}
