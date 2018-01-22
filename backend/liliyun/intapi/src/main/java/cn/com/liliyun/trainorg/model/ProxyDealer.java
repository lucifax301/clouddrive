package cn.com.liliyun.trainorg.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.com.liliyun.common.model.BaseModel;

public class ProxyDealer extends BaseModel implements Serializable {

	private Integer proxyId;

	private String proxyName;

	private String dealerPerson;

	private String dealerPhone;

	private String dealerEmail;

	private List<ScopeProxy> scopeAreaList;

	@JsonIgnore
	private String scopeAreas;

	@JsonIgnore
	private Integer startRow;

	@JsonIgnore
	private Integer scopeId;

	private String dealerAddr;

	private Integer dealerStatus;

	private String dealerDescription;

	private Date addTime;

	private Date modifyTime;

	private String modifyUser;

	private static final long serialVersionUID = 1L;

	public Integer getProxyId() {
		return proxyId;
	}

	public void setProxyId(Integer proxyId) {
		this.proxyId = proxyId;
	}

	public String getProxyName() {
		return proxyName;
	}

	public void setProxyName(String proxyName) {
		this.proxyName = proxyName;
	}

	public String getDealerPerson() {
		return dealerPerson;
	}

	public void setDealerPerson(String dealerPerson) {
		this.dealerPerson = dealerPerson;
	}

	public String getDealerPhone() {
		return dealerPhone;
	}

	public void setDealerPhone(String dealerPhone) {
		this.dealerPhone = dealerPhone;
	}

	public String getDealerEmail() {
		return dealerEmail;
	}

	public void setDealerEmail(String dealerEmail) {
		this.dealerEmail = dealerEmail;
	}

	public List<ScopeProxy> getScopeAreaList() {
		return scopeAreaList;
	}

	public void setScopeAreaList(List<ScopeProxy> scopeAreaList) {
		this.scopeAreaList = scopeAreaList;
	}

	public String getScopeAreas() {
		return scopeAreas;
	}

	public void setScopeAreas(String scopeAreas) {
		this.scopeAreas = scopeAreas;
	}

	public String getDealerAddr() {
		return dealerAddr;
	}

	public void setDealerAddr(String dealerAddr) {
		this.dealerAddr = dealerAddr;
	}

	public Integer getDealerStatus() {
		return dealerStatus;
	}

	public void setDealerStatus(Integer dealerStatus) {
		this.dealerStatus = dealerStatus;
	}

	public String getDealerDescription() {
		return dealerDescription;
	}

	public void setDealerDescription(String dealerDescription) {
		this.dealerDescription = dealerDescription;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getScopeId() {
		return scopeId;
	}

	public void setScopeId(Integer scopeId) {
		this.scopeId = scopeId;
	}

}