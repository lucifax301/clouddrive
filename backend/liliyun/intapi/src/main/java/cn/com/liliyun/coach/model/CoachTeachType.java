package cn.com.liliyun.coach.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;
import cn.com.liliyun.trainorg.model.Classinfo;

public class CoachTeachType extends BaseModel implements Serializable{

	private Integer id;
	
	private String type;
	
	private Integer userId;
	
	private String cuser;
	
	private Integer status;
	
	private String remark;
	
	private Date createDate;
	
	private String subject;
	
	private String ids;
	
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCuser() {
		return cuser;
	}

	public void setCuser(String cuser) {
		this.cuser = cuser;
	}

	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
