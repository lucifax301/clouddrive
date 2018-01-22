package cn.com.liliyun.report.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class KpiClassAreaStat extends BaseModel implements Serializable{

	private String area;
	
	private int areaid;
	
	private List<KpiClassStatRecord> data;
	
	public KpiClassAreaStat(){
		this.data=new ArrayList();
	}
	
	public KpiClassAreaStat(int areaid){
		this.areaid=areaid;
		this.data=new ArrayList();
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public List<KpiClassStatRecord> getData() {
		return data;
	}

	public void setData(List<KpiClassStatRecord> data) {
		this.data = data;
	}
	
	
}
