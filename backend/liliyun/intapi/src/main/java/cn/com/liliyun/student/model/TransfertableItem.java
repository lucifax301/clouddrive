package cn.com.liliyun.student.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

public class TransfertableItem extends BaseModel implements Serializable {
    
	private String tableid;
	
	private String areatableid;
	
	private Integer studentid;

	private String idcard;

	private String name;

	private String photoback;

	private Integer status;

	private String rtnreason;

	private Integer islocal;

	private static final long serialVersionUID = 1L;

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid == null ? null : tableid.trim();
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard == null ? null : idcard.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPhotoback() {
		return photoback;
	}

	public void setPhotoback(String photoback) {
		this.photoback = photoback == null ? null : photoback.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRtnreason() {
		return rtnreason;
	}

	public void setRtnreason(String rtnreason) {
		this.rtnreason = rtnreason == null ? null : rtnreason.trim();
	}

	public Integer getIslocal() {
		return islocal;
	}

	public void setIslocal(Integer islocal) {
		this.islocal = islocal;
	}

	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	public String getAreatableid() {
		return areatableid;
	}

	public void setAreatableid(String areatableid) {
		this.areatableid = areatableid;
	}
	
}