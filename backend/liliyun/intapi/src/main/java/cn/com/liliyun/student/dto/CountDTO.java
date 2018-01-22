package cn.com.liliyun.student.dto;

import java.io.Serializable;

public class CountDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String date;
	private int count;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
