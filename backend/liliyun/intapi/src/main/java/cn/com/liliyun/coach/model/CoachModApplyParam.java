package cn.com.liliyun.coach.model;

import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class CoachModApplyParam extends BaseModel{

	private Integer areaid;
	
	private Integer status;
	
	private Date begindate;
	
	private Date enddate;
	
	//coach name
	private String name;

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getBegindate() {
		return begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
