package com.zjf.cms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * ¡Ÿ ±Œƒ’¬±Ì
 * @author zjf
 *
 */
@Entity
public class TempArticle {
	//±‡∫≈
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="content",length=9999999)
	private String content;
	@Column(name="updateDate")
	private Date updateDate =  new Date();
	private Integer menuId;
	
	public TempArticle(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public TempArticle() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	
}
