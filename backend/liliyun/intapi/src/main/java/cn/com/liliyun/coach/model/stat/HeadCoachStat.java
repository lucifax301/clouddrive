package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HeadCoachStat implements Serializable{

	private String coachname;
	
	private Integer coachid;
	
	private List<CoachStat> data;
	
	


	public HeadCoachStat(){
		this.data=new ArrayList();
	}
	
	public HeadCoachStat(Integer coachid){
		this.coachid=coachid;
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



	public List<CoachStat> getData() {
		return data;
	}

	public void setData(List<CoachStat> data) {
		this.data = data;
	}
	
	
}
