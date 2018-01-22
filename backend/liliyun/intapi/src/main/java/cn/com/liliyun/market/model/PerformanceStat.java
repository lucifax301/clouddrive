package cn.com.liliyun.market.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

public class PerformanceStat extends BaseModel implements Serializable{

	private String name;
	
	private int index;
	
	private int enrolcount;
	
	private int exceedcount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getEnrolcount() {
		return enrolcount;
	}

	public void setEnrolcount(int enrolcount) {
		this.enrolcount = enrolcount;
	}

	public int getExceedcount() {
		return exceedcount;
	}

	public void setExceedcount(int exceedcount) {
		this.exceedcount = exceedcount;
	}

	
	
}
