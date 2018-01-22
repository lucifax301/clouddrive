package cn.com.liliyun.report.model;

import java.io.Serializable;

public class KpiStoreStatRecord extends KpiStatRecord implements Serializable{

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
