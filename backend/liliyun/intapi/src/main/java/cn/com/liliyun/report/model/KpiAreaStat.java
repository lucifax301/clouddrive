package cn.com.liliyun.report.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class KpiAreaStat extends BaseModel implements Serializable{

	private String area;
	
	private int areaid;
	
	private List<KpiAreaStatRecord> data;
	
	public KpiAreaStat(){
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

	public List<KpiAreaStatRecord> getData() {
		return data;
	}

	public void setData(List<KpiAreaStatRecord> data) {
		this.data = data;
	}
	
	
}
