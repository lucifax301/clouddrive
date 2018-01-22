package cn.com.liliyun.coach.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

public class CoachLoadStudentInfo extends BaseModel implements Serializable{

	private Integer applyexam;
	
	private Integer count;
	
	private Integer coachid;
	
	

	public Integer getCoachid() {
		return coachid;
	}

	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
	}

	public Integer getApplyexam() {
		return applyexam;
	}

	public void setApplyexam(Integer applyexam) {
		this.applyexam = applyexam;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
