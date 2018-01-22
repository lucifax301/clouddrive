package cn.com.liliyun.market.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

public class CoachPerformanceStatItem extends BaseModel implements Serializable{

	private Integer areaid;
	
	private Integer storeid;
	
	private Integer coachid;
	
	private String name;
	
	private Integer count;
	
	

	public Integer getCoachid() {
		return coachid;
	}

	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
