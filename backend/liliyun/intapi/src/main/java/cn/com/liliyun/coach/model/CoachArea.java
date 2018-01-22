package cn.com.liliyun.coach.model;

import cn.com.liliyun.common.model.BaseModel;

public class CoachArea extends BaseModel{

	private Integer id;
	
	private Integer coachid;
	
	private Integer areaid;

	private Integer step;

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

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}
	
	
	
	
	
	
}
