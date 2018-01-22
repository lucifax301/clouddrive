package cn.com.liliyun.report.model;

import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class CertificateCollectDto extends BaseModel {
	private static final long serialVersionUID = 1L;

	private Integer areaid;
	
	private Integer storeid;
	
	private Integer applyexam;
	
	private Integer applystatus;
	
	private Integer subject;
	
	private Integer classid;
	
	private Integer channel;
	
	//办证日期，传后台展示使用
	private Date datetime;
	
	//报名日期
	private Date applydatelow;
	
	private Date applydatetop;
	
	//办证日期
	private Date ctimelow; 
	
	private Date ctimetop;

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public Integer getApplyexam() {
		return applyexam;
	}

	public void setApplyexam(Integer applyexam) {
		this.applyexam = applyexam;
	}

	public Integer getApplystatus() {
		return applystatus;
	}

	public void setApplystatus(Integer applystatus) {
		this.applystatus = applystatus;
	}

	public Integer getSubject() {
		return subject;
	}

	public void setSubject(Integer subject) {
		this.subject = subject;
	}

	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Date getApplydatelow() {
		return applydatelow;
	}

	public void setApplydatelow(Date applydatelow) {
		this.applydatelow = applydatelow;
	}

	public Date getApplydatetop() {
		return applydatetop;
	}

	public void setApplydatetop(Date applydatetop) {
		this.applydatetop = applydatetop;
	}

	public Date getCtimelow() {
		return ctimelow;
	}

	public void setCtimelow(Date ctimelow) {
		this.ctimelow = ctimelow;
	}

	public Date getCtimetop() {
		return ctimetop;
	}

	public void setCtimetop(Date ctimetop) {
		this.ctimetop = ctimetop;
	}
	
}
