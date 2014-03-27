package com.zjf.cms.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 文章实体
 * @author 赵俊夫
 *
 * Aug 17, 2012
 */
@Entity
public class Article {
	//编号
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	//标题
	@Column
	private String title;
	//内容
	@Column(length=99999999)
	private String content;
	//下载类型（文章作为资料下载时有意义）
	@Column
	private String downloadType;
	@Column
	private Integer count = 0 ;
	//发表时间
	@Column
	private Date pubDate = new Date();
	//所属菜单
	@ManyToOne
	@JoinColumn
	private Menu menu;
	//所上传的附件
	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="article")
	private List<Attachment> attachments;
	//可见性
	@Column
	private Boolean visible = true;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public String getDownloadType() {
		return downloadType;
	}
	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}
	@Override
	public String toString() {
		return this.getTitle()+this.getContent();
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	public String getPubDateString(){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(this.pubDate);
	}
	public Integer getCount() {
		return count==null?0:count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
}
