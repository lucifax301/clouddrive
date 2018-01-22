package cn.com.liliyun.report.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

public class KpiStatRecord extends BaseModel implements Serializable{

	private Integer subjectid;
	
	private String subject;
	
	private Integer result;
	
	private int count;
	
	private int reachcount;
	
	private int passcount;
	
	private int unreachcount;
	
	private int cancelcount;
	
	private int othercount;
	
	private int failedcount;
	//及格率
	private String passrate;
	
	private String cancelrate;
	
	private String unreachrate;
	
	//合格率
	private String qualifiedrate;
	
	

	public int getFailedcount() {
		return failedcount;
	}

	public void setFailedcount(int failedcount) {
		this.failedcount = failedcount;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Integer getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(Integer subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getReachcount() {
		return reachcount;
	}

	public void setReachcount(int reachcount) {
		this.reachcount = reachcount;
	}

	public int getPasscount() {
		return passcount;
	}

	public void setPasscount(int passcount) {
		this.passcount = passcount;
	}

	public int getUnreachcount() {
		return unreachcount;
	}

	public void setUnreachcount(int unreachcount) {
		this.unreachcount = unreachcount;
	}

	public int getCancelcount() {
		return cancelcount;
	}

	public void setCancelcount(int cancelcount) {
		this.cancelcount = cancelcount;
	}

	public int getOthercount() {
		return othercount;
	}

	public void setOthercount(int othercount) {
		this.othercount = othercount;
	}

	public String getPassrate() {
		return passrate;
	}

	public void setPassrate(String passrate) {
		this.passrate = passrate;
	}

	public String getCancelrate() {
		return cancelrate;
	}

	public void setCancelrate(String cancelrate) {
		this.cancelrate = cancelrate;
	}

	public String getUnreachrate() {
		return unreachrate;
	}

	public void setUnreachrate(String unreachrate) {
		this.unreachrate = unreachrate;
	}

	public String getQualifiedrate() {
		return qualifiedrate;
	}

	public void setQualifiedrate(String qualifiedrate) {
		this.qualifiedrate = qualifiedrate;
	}

	
	
	
}
