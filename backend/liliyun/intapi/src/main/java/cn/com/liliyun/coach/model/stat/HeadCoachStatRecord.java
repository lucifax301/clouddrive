package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;

public class HeadCoachStatRecord implements Serializable{

	private int areaid;
	
	private Integer headcoachid;
	
	private int coachid;
	
	private String coachname;
	
	private int applyexam;
	
	private int count;

	
	
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
	
	

	public Integer getHeadcoachid() {
		return headcoachid;
	}

	public void setHeadcoachid(Integer headcoachid) {
		this.headcoachid = headcoachid;
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
