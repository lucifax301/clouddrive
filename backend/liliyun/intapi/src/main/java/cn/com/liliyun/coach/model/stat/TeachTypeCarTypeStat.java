package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TeachTypeCarTypeStat implements Serializable{

	private String cartype;
	
	private List<TeachTypeAreaStat> data;

	public TeachTypeCarTypeStat(){
		this.data=new ArrayList();
	}
	
	public TeachTypeCarTypeStat(String cartype){
		this.cartype=cartype;
		this.data=new ArrayList();
	}
	
	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public List<TeachTypeAreaStat> getData() {
		return data;
	}

	public void setData(List<TeachTypeAreaStat> data) {
		this.data = data;
	}
	
	
}
