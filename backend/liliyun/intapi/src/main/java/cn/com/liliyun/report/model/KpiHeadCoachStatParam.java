package cn.com.liliyun.report.model;

import java.io.Serializable;

public class KpiHeadCoachStatParam extends KpiAreaStatParam implements Serializable{

	private Integer coachid;

	public Integer getCoachid() {
		return coachid;
	}

	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
	}
	
	
}
