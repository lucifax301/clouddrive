package cn.com.liliyun.report.model;

import java.io.Serializable;

public class KpiClassStatRecord extends KpiStatRecord implements Serializable{

	private Integer classid;
	
	private String classname;
	
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

	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	
}
