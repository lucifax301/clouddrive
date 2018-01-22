package cn.com.liliyun.school.model;

import java.io.Serializable;
import java.sql.Timestamp;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 
 * 驾校管理：交易账号
 * @author Administrator
 *
 */
public class CoachAccountDTO extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String mobile;
	private String workType;
	private String employstatus;
	private int coachid;
	private int coachSum; //查询数量
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
	public String getEmploystatus() {
		return employstatus;
	}
	public void setEmploystatus(String employstatus) {
		this.employstatus = employstatus;
	}
	public int getCoachid() {
		return coachid;
	}
	public void setCoachid(int coachid) {
		this.coachid = coachid;
	}
	public int getCoachSum() {
		return coachSum;
	}
	public void setCoachSum(int coachSum) {
		this.coachSum = coachSum;
	}
	
	
	
	
	
}
