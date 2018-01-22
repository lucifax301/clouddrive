package cn.com.liliyun.common.model;

import java.io.Serializable;

public class Domain implements Serializable{

	private Integer areaid;
	
	private Integer storeid;

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
	
	
}
