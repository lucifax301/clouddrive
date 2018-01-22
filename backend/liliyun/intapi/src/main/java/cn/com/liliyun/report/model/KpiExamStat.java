package cn.com.liliyun.report.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

public class KpiExamStat extends BaseModel implements Serializable{

	private String exam;
	
	private Integer areaid;
	
	private String area;
	
	private List<KpiExamStatRecord> data;
	
	public KpiExamStat(){
		this.data=new ArrayList();
	}

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public List<KpiExamStatRecord> getData() {
		return data;
	}

	public void setData(List<KpiExamStatRecord> data) {
		this.data = data;
	}
	
	
}
