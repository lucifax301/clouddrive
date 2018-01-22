package cn.com.liliyun.report.model;

import java.io.Serializable;

public class KpiStoreStatParam extends KpiAreaStatParam implements Serializable{

	private Integer storeid;

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	
	
}
