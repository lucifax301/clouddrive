package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;

public class CoachStatRecord implements Serializable{

	private int areaid;
	
	private Integer storeid;
	
	private int coachid;
	
	private int applyexam;
	
	private int count;
	
	private String coachname;
	
	

	public String getCoachname() {
		return coachname;
	}

	public void setCoachname(String coachname) {
		this.coachname = coachname;
	}

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public int getCoachid() {
		return coachid;
	}

	public void setCoachid(int coachid) {
		this.coachid = coachid;
	}

	public int getApplyexam() {
		return applyexam;
	}

	public void setApplyexam(int applyexam) {
		this.applyexam = applyexam;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
