package cn.com.liliyun.report.model;

import java.io.Serializable;

public class KpiCoachStatRecord extends KpiStatRecord implements Serializable{

	private Integer coachid;
	
	private Integer storeid;
	
	private String coachname;
	
	private Integer areaid;
	
	private Integer teachtypeid;
	
	

	public Integer getTeachtypeid() {
		return teachtypeid;
	}

	public void setTeachtypeid(Integer teachtypeid) {
		this.teachtypeid = teachtypeid;
	}

	public String getCoachname() {
		return coachname;
	}

	public void setCoachname(String coachname) {
		this.coachname = coachname;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getCoachid() {
		return coachid;
	}

	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	
	
}
