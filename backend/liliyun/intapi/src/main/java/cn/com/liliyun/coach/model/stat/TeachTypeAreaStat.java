package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;

public class TeachTypeAreaStat implements Serializable{

	private int areaid;
	
	private String area;
	
	private int studentcount;
	
	private int coachcount;
	
	private int carcount;
	
	private int avgcoachstudentcount;
	
	private int avgcarstudentcount;

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

	public int getAvgcoachstudentcount() {
		return avgcoachstudentcount;
	}

	public void setAvgcoachstudentcount(int avgcoachstudentcount) {
		this.avgcoachstudentcount = avgcoachstudentcount;
	}

	public int getAvgcarstudentcount() {
		return avgcarstudentcount;
	}

	public void setAvgcarstudentcount(int avgcarstudentcount) {
		this.avgcarstudentcount = avgcarstudentcount;
	}
	
	
}
