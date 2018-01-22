package cn.com.liliyun.coach.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class CoachStudentDTO extends BaseModel implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private Long studentid;
	private String name;
	private String sex;
	private String phone;
	private String cardtype;
	private String idcard;//证件号码
	private String traintype;//培训车型
	private String applydate;//报名时间
	private int classid;//所属班级
	private int coachid;//教练
	
	private String coachName;//教练姓名
	private String className;//班级名称
	private String proxyName;
	private String scopeName;
	private String storename;
	
	private String coachNames;//教练姓名组合
	
	private String applyexam;
	private int proxyid;
	private int scopeid;
	private int storeid;
	
	public String getProxyName() {
		return proxyName;
	}
	public void setProxyName(String proxyName) {
		this.proxyName = proxyName;
	}
	public String getScopeName() {
		return scopeName;
	}
	public void setScopeName(String scopeName) {
		this.scopeName = scopeName;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public int getCoachid() {
		return coachid;
	}
	public void setCoachid(int coachid) {
		this.coachid = coachid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public Long getStudentid() {
		return studentid;
	}
	public void setStudentid(Long studentid) {
		this.studentid = studentid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getTraintype() {
		return traintype;
	}
	public void setTraintype(String traintype) {
		this.traintype = traintype;
	}
	public String getApplydate() {
		return applydate;
	}
	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}
	public int getClassid() {
		return classid;
	}
	public void setClassid(int classid) {
		this.classid = classid;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getCoachNames() {
		return coachNames;
	}
	public void setCoachNames(String coachNames) {
		this.coachNames = coachNames;
	}
	public String getApplyexam() {
		return applyexam;
	}
	public void setApplyexam(String applyexam) {
		this.applyexam = applyexam;
	}
	public int getProxyid() {
		return proxyid;
	}
	public void setProxyid(int proxyid) {
		this.proxyid = proxyid;
	}
	public int getScopeid() {
		return scopeid;
	}
	public void setScopeid(int scopeid) {
		this.scopeid = scopeid;
	}
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	
	
}