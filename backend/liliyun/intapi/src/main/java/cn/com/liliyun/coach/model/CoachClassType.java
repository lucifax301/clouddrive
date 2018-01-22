package cn.com.liliyun.coach.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;
import cn.com.liliyun.trainorg.model.Classinfo;

public class CoachClassType extends BaseModel implements Serializable{

	private int id;
	
	private String type;
	
	private Integer userId;
	
	private String cuser;
	
	private Integer status;
	
	private String remark;
	
	private Date createDate;

	private String ids;
	
	private List<Classinfo> classinfo;
	
	
	
	public List<Classinfo> getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(List<Classinfo> classinfo) {
		this.classinfo = classinfo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCuser() {
		return cuser;
	}

	public void setCuser(String cuser) {
		this.cuser = cuser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
