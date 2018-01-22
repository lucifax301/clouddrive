package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TeachTypeStat implements Serializable{

	private int teachtypeid;
	
	private String teachtype;
	
	private List<TeachTypeCarTypeStat> data;
	
	public TeachTypeStat(){
		this.data=new ArrayList();
	}
	
	public TeachTypeStat(String teachtype){
		this.teachtype=teachtype;
		this.data=new ArrayList();
	}

	public int getTeachtypeid() {
		return teachtypeid;
	}

	public void setTeachtypeid(int teachtypeid) {
		this.teachtypeid = teachtypeid;
	}

	public String getTeachtype() {
		return teachtype;
	}

	public void setTeachtype(String teachtype) {
		this.teachtype = teachtype;
	}

	public List<TeachTypeCarTypeStat> getData() {
		return data;
	}

	public void setData(List<TeachTypeCarTypeStat> data) {
		this.data = data;
	}
	
	
}
