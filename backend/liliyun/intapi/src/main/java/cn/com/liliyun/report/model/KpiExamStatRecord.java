package cn.com.liliyun.report.model;

import java.io.Serializable;

public class KpiExamStatRecord extends KpiStatRecord implements Serializable{

	private String exam;
	
	private Integer areaid;

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	
	
}
