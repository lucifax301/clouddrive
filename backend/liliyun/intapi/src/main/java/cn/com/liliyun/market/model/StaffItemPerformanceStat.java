package cn.com.liliyun.market.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

public class StaffItemPerformanceStat extends PerformanceStat implements Serializable{

	
	private Integer staffid;
	
	
	public Integer getStaffid() {
		return staffid;
	}

	public void setStaffid(Integer staffid) {
		this.staffid = staffid;
	}
	
	
	
}
