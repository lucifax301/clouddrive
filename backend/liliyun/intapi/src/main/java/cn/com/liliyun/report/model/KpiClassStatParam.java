package cn.com.liliyun.report.model;

import java.io.Serializable;

public class KpiClassStatParam extends KpiAreaStatParam implements Serializable{

	private Integer classinfoid;

	public Integer getClassinfoid() {
		return classinfoid;
	}

	public void setClassinfoid(Integer classinfoid) {
		this.classinfoid = classinfoid;
	}
	
	
}
