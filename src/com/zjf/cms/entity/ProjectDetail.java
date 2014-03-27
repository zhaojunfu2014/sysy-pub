package com.zjf.cms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * ��Ŀ������ϸ
 * @author �Կ���
 *
 * Aug 28, 2012
 */
@Entity
public class ProjectDetail {
	//���
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	//��Ŀid
	@Column(name="projectId")
	private int projectId;
	/*
	 * ��ϸ���
	 * 1���ʼ�����
	 * 2����Ŀ����
	 * 3���ල�ƻ������ף�
	 * 4�������ල����
	 */
	@Column(name="type")
	private int type;
	//��ϸ����
	@Column(name="detail")
	private String detail;
	//��������
	@Column(name="info",length=999999999)
	private String info;
	//�Ƿ����
	@Column(name="finish")
	private boolean finish;
	//����ʱ��
	@Column(name="updateDate")
	private Date updateDate = new Date();
	//�Ƿ�ɼ�
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
