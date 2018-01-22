package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AreaStat implements Serializable{

	private int areaid;
	
	private String area;
	
	private List<AreaTeachCarTypeStat> data;
	
	public AreaStat(){
		this.data=new ArrayList();
	}

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public List<AreaTeachCarTypeStat> getData() {
		return data;
	}

	public void setData(List<AreaTeachCarTypeStat> data) {
		this.data = data;
	}
	
	
}
