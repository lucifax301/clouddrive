package cn.com.liliyun.report.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KpiCoachStat extends KpiStatRecord implements Serializable{

	private Integer coachid;
	
	private String area;
	
	private String teachtype;
	
	private String coachname;
	
	private Integer teachtypeid;
	
	
	
	public Integer getTeachtypeid() {
		return teachtypeid;
	}

	public void setTeachtypeid(Integer teachtypeid) {
		this.teachtypeid = teachtypeid;
	}

	public String getCoachname() {
		return coachname;
	}

	public void setCoachname(String coachname) {
		this.coachname = coachname;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTeachtype() {
		return teachtype;
	}

	public void setTeachtype(String teachtype) {
		this.teachtype = teachtype;
	}

	private List<KpiCoachStoreStat> data;
	
	public KpiCoachStat(){
		this.data=new ArrayList();
	}
	
	public KpiCoachStat(Integer coachid){
		this.coachid=coachid;
		this.data=new ArrayList();
	}

	public Integer getCoachid() {
		return coachid;
	}

	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
	}

	public List<KpiCoachStoreStat> getData() {
		return data;
	}

	public void setData(List<KpiCoachStoreStat> data) {
		this.data = data;
	}
	
	
}
