package cn.com.liliyun.coach.model;

import cn.com.liliyun.common.model.BaseModel;

public class CoachClassinfo extends BaseModel{

	private Integer id;
	
	private Integer coachid;
	
	private Integer classinfoid;
	
	//班别名字
	private String className;
	
	//带教班别ids
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCoachid() {
		return coachid;
	}

	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
	}

	public Integer getClassinfoid() {
		return classinfoid;
	}

	public void setClassinfoid(Integer classinfoid) {
		this.classinfoid = classinfoid;
	}

	
	
	
}
