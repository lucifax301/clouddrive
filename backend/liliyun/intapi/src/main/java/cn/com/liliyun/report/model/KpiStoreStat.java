package cn.com.liliyun.report.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class KpiStoreStat extends BaseModel implements Serializable{

	private String store;
	
	private Integer areaid;
	
	private String area;
	
	public KpiStoreStat(){
		this.data=new ArrayList();
	}
	
	private List<KpiStoreStatRecord> data;

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public List<KpiStoreStatRecord> getData() {
		return data;
	}

	public void setData(List<KpiStoreStatRecord> data) {
		this.data = data;
	}
	
	
}
