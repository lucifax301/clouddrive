package cn.com.liliyun.trainorg.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

public class TrainExamItemYearVo extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8555678291955097923L;

	private String month;

	private Integer indexcat;
    
    private Integer subject;
    
    private String year;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getIndexcat() {
		return indexcat;
	}

	public void setIndexcat(Integer indexcat) {
		this.indexcat = indexcat;
	}

	public Integer getSubject() {
		return subject;
	}

	public void setSubject(Integer subject) {
		this.subject = subject;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}


}