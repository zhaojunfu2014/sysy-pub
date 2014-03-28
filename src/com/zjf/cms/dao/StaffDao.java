package com.zjf.cms.dao;

import com.zjf.cms.entity.Staff;

/**
 * 员工数据访问接口
 * @author 赵俊夫
 *
 * Aug 24, 2012
 */
public interface StaffDao extends BaseDao {
	public Staff fetchByUsername(String username);
}
