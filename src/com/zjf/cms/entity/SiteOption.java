package com.zjf.cms.entity;
/**
 * 网站配置
 * @author 赵俊夫
 *
 * Aug 26, 2012
 */
public class SiteOption {
	//网站名称
	private String siteName;
	//上传类型
	private String uploadType;
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	
	
}
