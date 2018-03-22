package cn.com.liliyun.student.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

@SuppressWarnings("serial")
public class StudentStatus extends BaseModel implements Serializable {

	private Integer id;

	private String name;

	private int sort;

	private Date ctime;

	private Date mtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
}