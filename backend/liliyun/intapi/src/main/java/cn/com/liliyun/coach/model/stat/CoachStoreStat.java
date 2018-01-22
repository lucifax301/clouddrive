package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CoachStoreStat implements Serializable{

	private String storename;
	
	private Integer storeid;
	
	private List<CoachStat> data;
	
	

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public CoachStoreStat(){
		this.data=new ArrayList();
	}
	
	public CoachStoreStat(String storename){
		this.storename=storename;
		this.data=new ArrayList();
	}
	
	public CoachStoreStat(Integer storeid){
		this.storeid=storeid;
		this.data=new ArrayList();
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public List<CoachStat> getData() {
		return data;
	}

	public void setData(List<CoachStat> data) {
		this.data = data;
	}
	
	
}
