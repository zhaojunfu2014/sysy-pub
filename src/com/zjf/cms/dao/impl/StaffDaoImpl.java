package com.zjf.cms.dao.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zjf.cms.dao.StaffDao;
import com.zjf.cms.entity.Staff;
/**
 * 员工数据访问实现
 * @author 赵俊夫
 *
 * Aug 24, 2012
 */
@Repository(value="staffDao")
public class StaffDaoImpl extends BaseDaoImpl implements StaffDao {
	
	/**
	 * 用用户名查找
	 */
	public Staff fetchByUsername(String username) {
		Set<Criterion> criterions = new LinkedHashSet<Criterion>();
		criterions.add(Restrictions.eq("username", username));
		Criteria cri = sf.getCurrentSession().createCriteria(Staff.class);
		for(Criterion c: criterions){
			cri.add(c);
		}
		List<Staff> datas = cri.list();
		if(datas.size()>0){
			return datas.get(0);
		}else{
			return null;
		}
	}
}
