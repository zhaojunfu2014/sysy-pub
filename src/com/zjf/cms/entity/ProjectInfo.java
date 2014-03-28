package com.zjf.cms.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 项目监督表
 * @author 赵俊夫
 *
 * Aug 28, 2012
 */
@Entity
public class ProjectInfo {
	//编号
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	//项目编码
	@Column(name="code")
	private String code;
	//项目名称
	@Column(name="name")
	private String name;
	//重要单元核备
	@Column(name="zydyhb")
	private int zydyhb;
	//分部核备
	@Column(name="fbhb")
	private int fbhb;
	//单位工程外观质量核定
	@Column(name="dwgcwgzlhd")
	private int dwgcwgzlhd;
	//单位核定
	@Column(name="dwhd")
	private int dwhd;
	//质量监督通报
	@Column(name="zljdtb")
	private int zljdtb;
	//开始时间
	@Column(name="startDate")
	private Date startDate =  new Date();
	//结束时间
	@Column(name="endDate")
	private Date endDate;
	/*
	 * 项目明细的完成情况
	 */
	private Boolean type1 =false;
	private Boolean type2=false;
	private Boolean type3=false;
	private Boolean type4=false;
	private Boolean type5=false;
	private Boolean type6=false;
	private Boolean type7=false;
	private Boolean type8=false;
	private Boolean type9=false;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getZydyhb() {
		return zydyhb;
	}
	public void setZydyhb(int zydyhb) {
		this.zydyhb = zydyhb;
	}
	public int getFbhb() {
		return fbhb;
	}
	public void setFbhb(int fbhb) {
		this.fbhb = fbhb;
	}
	public int getDwgcwgzlhd() {
		return dwgcwgzlhd;
	}
	public void setDwgcwgzlhd(int dwgcwgzlhd) {
		this.dwgcwgzlhd = dwgcwgzlhd;
	}
	public int getDwhd() {
		return dwhd;
	}
	public void setDwhd(int dwhd) {
		this.dwhd = dwhd;
	}
	public int getZljdtb() {
		return zljdtb;
	}
	public void setZljdtb(int zljdtb) {
		this.zljdtb = zljdtb;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isType1() {
		return type1;
	}
	public void setType1(boolean type1) {
		this.type1 = type1;
	}
	public boolean isType2() {
		return type2;
	}
	public void setType2(boolean type2) {
		this.type2 = type2;
	}
	public boolean isType3() {
		return type3;
	}
	public void setType3(boolean type3) {
		this.type3 = type3;
	}
	public boolean isType4() {
		return type4;
	}
	public void setType4(boolean type4) {
		this.type4 = type4;
	}
	
	public void setType5(Boolean type5) {
		this.type5 = type5;
	}
	public void setType6(Boolean type6) {
		this.type6 = type6;
	}
	public void setType7(Boolean type7) {
		this.type7 = type7;
	}
	public void setType8(Boolean type8) {
		this.type8 = type8;
	}
	public void setType9(Boolean type9) {
		this.type9 = type9;
	}
	
	public Boolean getType5() {
		return type5;
	}
	public Boolean getType6() {
		return type6;
	}
	public Boolean getType7() {
		return type7;
	}
	public Boolean getType8() {
		return type8;
	}
	public Boolean getType9() {
		return type9;
	}
	public void setTypeFinish(int type,boolean value){
		switch (type) {
		case 1:
			setType1(value);
			break;
		case 2:
			setType2(value);
			break;
		case 3:
			setType3(value);
			break;
		case 4:
			setType4(value);
			break;
		case 5:
			setType4(value);
			break;
		case 6:
			setType4(value);
			break;
		case 7:
			setType4(value);
			break;
		case 8:
			setType4(value);
			break;
		case 9:
			setType4(value);
			break;
		default:
			break;
		}
	}
	public String getStartDateString(){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(this.startDate);
	}
}
