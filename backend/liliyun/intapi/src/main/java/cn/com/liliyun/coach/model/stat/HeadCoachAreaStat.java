package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HeadCoachAreaStat implements Serializable{
	
	private String area;
	
	private int areaid;
	
	private String coachname;
	
	
	
	public String getCoachname() {
		return coachname;
	}


	public void setCoachname(String coachname) {
		this.coachname = coachname;
	}

	private List<HeadCoachStat> data;
	
	public HeadCoachAreaStat(){
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

	public List<HeadCoachStat> getData() {
		return data;
	}

	public void setData(List<HeadCoachStat> data) {
		this.data = data;
	}
}
