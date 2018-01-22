package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AreaTeachCarTypeStat implements Serializable{

	private String cartype;
	
	private List<AreaTeachTypeStat> data;
	
	public AreaTeachCarTypeStat(){
		this.data=new ArrayList();
	}
	
	public AreaTeachCarTypeStat(String cartype){
		this.cartype=cartype;
		this.data=new ArrayList();
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public List<AreaTeachTypeStat> getData() {
		return data;
	}

	public void setData(List<AreaTeachTypeStat> data) {
		this.data = data;
	}
	
	
}
