package cn.com.liliyun.user.model;

import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class Register extends BaseModel {

	private Integer id;

	private String contact;

	private String qq;

	private String email;

	private String applyname;

	private String acquire;

	private Date ctime;

	private Date mtime;

	private Integer status;

	private String invitecode;
	
	private String password;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact == null ? null : contact.trim();
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getApplyname() {
		return applyname;
	}

	public void setApplyname(String applyname) {
		this.applyname = applyname == null ? null : applyname.trim();
	}

	public String getAcquire() {
		return acquire;
	}

	public void setAcquire(String acquire) {
		this.acquire = acquire == null ? null : acquire.trim();
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getInvitecode() {
		return invitecode;
	}

	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode == null ? null : invitecode.trim();
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}