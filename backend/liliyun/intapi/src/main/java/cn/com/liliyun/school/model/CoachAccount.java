package cn.com.liliyun.school.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 
 * 驾校管理：交易账号
 * @author Administrator
 *
 */
public class CoachAccount extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private int coachid;
	private String isLimit;
	private String limitQuantity;//额度
	private String status;//是否有效
	private String auth;//是否授权
	
	//查询用
	private String name;
	private String mobile;
	private String workType;
	private String coachids;//新增交易账号教练id集合
	private Integer coachNum;
	
	
	
	public Integer getCoachNum() {
		return coachNum;
	}
	public void setCoachNum(Integer coachNum) {
		this.coachNum = coachNum;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getCoachids() {
		return coachids;
	}
	public void setCoachids(String coachids) {
		this.coachids = coachids;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCoachid() {
		return coachid;
	}
	public void setCoachid(int coachid) {
		this.coachid = coachid;
	}
	public String getIsLimit() {
		return isLimit;
	}
	public void setIsLimit(String isLimit) {
		this.isLimit = isLimit;
	}
	public String getLimitQuantity() {
		return limitQuantity;
	}
	public void setLimitQuantity(String limitQuantity) {
		this.limitQuantity = limitQuantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
