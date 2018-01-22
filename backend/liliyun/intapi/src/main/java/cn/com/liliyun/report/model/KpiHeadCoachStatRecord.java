package cn.com.liliyun.report.model;

import java.io.Serializable;

public class KpiHeadCoachStatRecord extends KpiStatRecord implements Serializable{

	private Integer areaid;
	
	private String coachname;
	
	private Integer coachid;
	
	

	public Integer getCoachid() {
		return coachid;
	}

	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
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
	
	
}
