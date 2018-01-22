package cn.com.liliyun.car.model;

import cn.com.liliyun.common.model.BaseModel;

public class CoachCar extends BaseModel {

	private Integer coachid;
	
	private String carno;

	public Integer getCoachid() {
		return coachid;
	}

	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno;
	}
	
	
}
