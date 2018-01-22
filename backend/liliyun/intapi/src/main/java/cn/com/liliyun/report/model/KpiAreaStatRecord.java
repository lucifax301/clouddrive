package cn.com.liliyun.report.model;

import java.io.Serializable;

public class KpiAreaStatRecord extends KpiStatRecord implements Serializable{

	private Integer areaid;
	
	private String area;

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
	
	
}
