package cn.com.liliyun.market.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

public class SalesActivityClassinfo extends BaseModel implements Serializable {

	private static final long serialVersionUID = -3398465110659049824L;

	private Integer id;
	
	private Integer activityid;
	
	private Integer classinfoid;
	
	private Integer c1price;
	
	private Integer c2price;

	
	///////////////////////
	
	private Integer c1flag;
	
	private Integer c2flag;
	
	private Integer c1amount;
	
	private Integer c2amount;
	
	
	private String classtype;
	
	private String name;
	
	

	public Integer getC1flag() {
		return c1flag;
	}

	public void setC1flag(Integer c1flag) {
		this.c1flag = c1flag;
	}

	public Integer getC2flag() {
		return c2flag;
	}

	public void setC2flag(Integer c2flag) {
		this.c2flag = c2flag;
	}

	public Integer getC1amount() {
		return c1amount;
	}

	public void setC1amount(Integer c1amount) {
		this.c1amount = c1amount;
	}

	public Integer getC2amount() {
		return c2amount;
	}

	public void setC2amount(Integer c2amount) {
		this.c2amount = c2amount;
	}

	public String getClasstype() {
		return classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getC1price() {
		return c1price;
	}

	public void setC1price(Integer c1price) {
		this.c1price = c1price;
	}

	public Integer getC2price() {
		return c2price;
	}

	public void setC2price(Integer c2price) {
		this.c2price = c2price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityid() {
		return activityid;
	}

	public void setActivityid(Integer activityid) {
		this.activityid = activityid;
	}

	public Integer getClassinfoid() {
		return classinfoid;
	}

	public void setClassinfoid(Integer classinfoid) {
		this.classinfoid = classinfoid;
	}
	
	
}
