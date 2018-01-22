package cn.com.liliyun.coach.model;

import cn.com.liliyun.common.model.BaseModel;

public class CoachStore extends BaseModel{

	private Integer id;
	
	private Integer coachid;
	
	private Integer storeid;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
