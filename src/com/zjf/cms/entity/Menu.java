package com.zjf.cms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 菜单实体
 * @author 赵俊夫
 *
 * Aug 15, 2012
 */
@Entity
public class Menu {
	//编号
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	//名称
	@Column(name="name")
	private String name;
	//是否为根菜单
	@Column(name="isRoot")
	private boolean isRoot;
	//是否可见
	@Column(name="visible")
	private boolean visible;
	//显示次序
	@Column(name="level")
	private int level;
	//子菜单
	@OneToMany(cascade=CascadeType.ALL,mappedBy="parent")
	private List<Menu> childs;
	//父菜单
	@ManyToOne
	@JoinColumn   
	private Menu parent;
	//菜单中的文章
	@OneToMany(mappedBy="menu")
	@OrderBy("pubDate desc")
	private List<Article> articles;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@NotEmpty(message="不能为空")
	@Size(max=30,message="最大长度为30")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public List<Menu> getChilds() {
		return childs;
	}
	public void setChilds(List<Menu> childs) {
		this.childs = childs;
	}
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	@Override
	public String toString() {
		return this.isRoot?this.name:("---"+this.name);
	}
	
}
