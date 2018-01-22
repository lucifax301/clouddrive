package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;

public class AreaStatRecord implements Serializable{

	private int areaid;
	
	private String teachcartype;
	
	private int teachtypeid;
	
	private int studentcount;
	
	private int coachcount;
	
	private int carcount;

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public String getTeachcartype() {
		return teachcartype;
	}

	public void setTeachcartype(String teachcartype) {
		this.teachcartype = teachcartype;
	}

	public int getTeachtypeid() {
		return teachtypeid;
	}

	public void setTeachtypeid(int teachtypeid) {
		this.teachtypeid = teachtypeid;
	}

	public int getStudentcount() {
		return studentcount;
	}

	public void setStudentcount(int studentcount) {
		this.studentcount = studentcount;
	}

	public int getCoachcount() {
		return coachcount;
	}

	public void setCoachcount(int coachcount) {
		this.coachcount = coachcount;
	}

	public int getCarcount() {
		return carcount;
	}

	public void setCarcount(int carcount) {
		this.carcount = carcount;
	}
	
	
}
