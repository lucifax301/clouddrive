package cn.com.liliyun.report.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class KpiAreaStatParam extends BaseModel implements Serializable{

	private Integer areaid;
	
	private Integer subjectid;
	
	private Date begindate;
	
	private Date enddate;
	
	private Integer type;
	
	private Integer sorttype;

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(Integer subjectid) {
		this.subjectid = subjectid;
	}

	public Date getBegindate() {
		return begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSorttype() {
		return sorttype;
	}

	public void setSorttype(Integer sorttype) {
		this.sorttype = sorttype;
	}

	
	
	
}
