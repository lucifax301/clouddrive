package cn.com.liliyun.coach.model.stat;

import java.io.Serializable;

/**
 * 带教类型统计数据
 * @author devil
 *
 */
public class AreaTeachTypeStat implements Serializable{

	private String teachtype;
	
	private int studentcount;
	
	private int coachcount;
	
	private int carcount;
	
	private int avgcoachstudentcount;
	
	private int avgcarstudentcount;

	public String getTeachtype() {
		return teachtype;
	}

	public void setTeachtype(String teachtype) {
		this.teachtype = teachtype;
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
