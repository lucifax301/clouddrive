package cn.com.liliyun.market.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

public class EnrolClassStatItem extends BaseModel implements Serializable{

	private Integer areaid;
	
	private String traintype;
	
	private Integer classinfoid;
	
	private Integer count;
	
	private Integer storeid;

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getTraintype() {
		return traintype;
	}

	public void setTraintype(String traintype) {
		this.traintype = traintype;
	}

	public Integer getClassinfoid() {
		return classinfoid;
	}

	public void setClassinfoid(Integer classinfoid) {
		this.classinfoid = classinfoid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	
	
}
