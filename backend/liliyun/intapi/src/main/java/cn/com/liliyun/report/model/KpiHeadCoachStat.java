package cn.com.liliyun.report.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class KpiHeadCoachStat extends BaseModel implements Serializable{

	private String coachname;
	
	private Integer coachid;
	
	private String area;
	
	private Integer areaid;
	
	private List<KpiHeadCoachStatRecord> data;
	
	public KpiHeadCoachStat(){
		this.data=new ArrayList();
	}

	public String getCoachname() {
		return coachname;
	}

	public void setCoachname(String coachname) {
		this.coachname = coachname;
	}

	public Integer getCoachid() {
		return coachid;
	}

	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public List<KpiHeadCoachStatRecord> getData() {
		return data;
	}

	public void setData(List<KpiHeadCoachStatRecord> data) {
		this.data = data;
	}
	
	
}
