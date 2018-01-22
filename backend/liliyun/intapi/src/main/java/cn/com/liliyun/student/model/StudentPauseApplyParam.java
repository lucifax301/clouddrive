package cn.com.liliyun.student.model;

import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class StudentPauseApplyParam extends BaseModel{

	private Integer areaid;
	
	private Integer type;
	
	private Integer storeid;
	
	private Integer status;
	
	private Date stime;
	
	private Date etime;
	
	private String studentname;

	private String name;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	

	

	public Integer getStatus() {
		return status;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public Date getEtime() {
		return etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	
	
}
