package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CoachAreaStat implements Serializable{

	private String area;
	
	private int areaid;
	
	private List<CoachStoreStat> data;
	
	public CoachAreaStat(){
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

	public List<CoachStoreStat> getData() {
		return data;
	}

	public void setData(List<CoachStoreStat> data) {
		this.data = data;
	}
	
	
}
