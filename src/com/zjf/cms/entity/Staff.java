package com.zjf.cms.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 员工
 * @author 赵俊夫
 *
 * Aug 24, 2012
 */
@Entity
public class Staff {
	//编号
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	//用户名
	@Column(name="username",unique = true,nullable = false)
	@NotEmpty(message="不能为空")
	@Size(max=30,message="最大长度为30")
	private String username;
	//密码
	@Column(name="password")
	@NotEmpty(message="不能为空")
	@Size(max=30,min=6,message="最小长度为6，最大长度为30")
	private String password;
	//权限值
	@Column(name="privilege")
	@NotEmpty(message="不能为空")
	private String privilege;
	//是否启用账号
	@Column(name="enable")
	private boolean enable;
	//注册日期
	@Column(name="regDate")
	private Date regDate = new Date();
	//临时文章
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="tempId")
	private TempArticle temp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Staff other = (Staff) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	public TempArticle getTemp() {
		return temp;
	}
	public void setTemp(TempArticle temp) {
		this.temp = temp;
	}
	
	
}
