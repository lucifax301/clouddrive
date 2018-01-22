package cn.com.liliyun.market.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class CoachPerformanceStat extends BaseModel implements Serializable{

	private Integer areaid;
	
	private String area;
	
	private List<CoachStorePerformanceStat> data;

	public CoachPerformanceStat(){
		this.data=new ArrayList();
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

	public List<CoachStorePerformanceStat> getData() {
		return data;
	}

	public void setData(List<CoachStorePerformanceStat> data) {
		this.data = data;
	}
	
	
}
