package cn.com.liliyun.report.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class KpiCoachStoreStat extends BaseModel implements Serializable{

	private Integer storeid;
	
	private String store;
	
	private List<KpiCoachStatRecord> data;
	
	public KpiCoachStoreStat(){
		this.data=new ArrayList();
	}
	
	public KpiCoachStoreStat(Integer storeid){
		this.storeid=storeid;
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

	public List<KpiCoachStatRecord> getData() {
		return data;
	}

	public void setData(List<KpiCoachStatRecord> data) {
		this.data = data;
	}
	
	
}
