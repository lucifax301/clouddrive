package cn.com.liliyun.market.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class CoachItemPerformanceStat extends PerformanceStat implements Serializable{

	private Integer coachid;
	
	
	public Integer getCoachid() {
		return coachid;
	}



	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
	}

	
	
}
