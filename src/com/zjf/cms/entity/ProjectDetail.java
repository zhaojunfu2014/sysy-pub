package com.zjf.cms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 项目进度明细
 * @author 赵俊夫
 *
 * Aug 28, 2012
 */
@Entity
public class ProjectDetail {
	//编号
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	//项目id
	@Column(name="projectId")
	private int projectId;
	/*
	 * 明细类别
	 * 1：质监手续
	 * 2：项目划分
	 * 3：监督计划（交底）
	 * 4：质量监督报告
	 */
	@Column(name="type")
	private int type;
	//明细描述
	@Column(name="detail")
	private String detail;
	//具体内容
	@Column(name="info",length=999999999)
	private String info;
	//是否完成
	@Column(name="finish")
	private boolean finish;
	//更新时间
	@Column(name="updateDate")
	private Date updateDate = new Date();
	//是否可见
	@Column(name="visible")
	private Boolean visible=true;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
}
