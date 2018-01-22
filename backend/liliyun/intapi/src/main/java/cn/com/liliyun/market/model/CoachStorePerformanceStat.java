package cn.com.liliyun.market.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class CoachStorePerformanceStat extends BaseModel implements Serializable{

	private Integer storeid;
	
	private String store;
	
	private List<CoachItemPerformanceStat> data;
	
	public CoachStorePerformanceStat(){
		this.data=new ArrayList();
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public List<CoachItemPerformanceStat> getData() {
		return data;
	}

	public void setData(List<CoachItemPerformanceStat> data) {
		this.data = data;
	}

	
	
	
}
