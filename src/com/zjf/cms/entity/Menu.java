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
 * �˵�ʵ��
 * @author �Կ���
 *
 * Aug 15, 2012
 */
@Entity
public class Menu {
	//���
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	//����
	@Column(name="name")
	private String name;
	//�Ƿ�Ϊ���˵�
	@Column(name="isRoot")
	private boolean isRoot;
	//�Ƿ�ɼ�
	@Column(name="visible")
	private boolean visible;
	//��ʾ����
	@Column(name="level")
	private int level;
	//�Ӳ˵�
	@OneToMany(cascade=CascadeType.ALL,mappedBy="parent")
	private List<Menu> childs;
	//���˵�
	@ManyToOne
	@JoinColumn   
	private Menu parent;
	//�˵��е�����
	@OneToMany(mappedBy="menu")
	@OrderBy("pubDate desc")
	private List<Article> articles;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@NotEmpty(message="����Ϊ��")
	@Size(max=30,message="��󳤶�Ϊ30")
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
