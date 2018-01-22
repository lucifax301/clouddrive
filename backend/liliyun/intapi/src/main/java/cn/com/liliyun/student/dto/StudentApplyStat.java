package cn.com.liliyun.student.dto;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class StudentApplyStat extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Integer areaid;
	
	private String area;
	
	private Integer storeid;
	
	private String store;
	
	private Integer classid;
	
	private String classinfo;
	
	private BigDecimal contractmoney;
	
	private BigDecimal applymoney;
	
	private Integer count;
	
	private Date applydatelow;
	
	private Date applydatetop;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public void setCountPlus(Integer count) {
		if (count == null)
			return;
		if (this.count == null)
			this.count = 0;
		this.count += count;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public String getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(String classinfo) {
		this.classinfo = classinfo;
	}

	public BigDecimal getContractmoney() {
		return contractmoney;
	}

	public void setContractmoney(BigDecimal contractmoney) {
		this.contractmoney = contractmoney;
	}
	
	public void setContractmoneyPlus(BigDecimal contractmoney) {
		if (contractmoney == null)
			return;
		if (this.contractmoney == null)
			this.contractmoney = BigDecimal.ZERO;
		this.contractmoney.add(contractmoney);
	}

	public BigDecimal getApplymoney() {
		return applymoney;
	}

	public void setApplymoney(BigDecimal applymoney) {
		this.applymoney = applymoney;
	}
	
	public void setApplymoneyPlus(BigDecimal applymoney) {
		if (applymoney == null)
			return;
		if (this.applymoney == null)
			this.applymoney = BigDecimal.ZERO;
		this.applymoney.add(applymoney);
	}

	public Date getApplydatelow() {
		return applydatelow;
	}

	public void setApplydatelow(Date applydatelow) {
		this.applydatelow = applydatelow;
	}

	public Date getApplydatetop() {
		return applydatetop;
	}

	public void setApplydatetop(Date applydatetop) {
		this.applydatetop = applydatetop;
	}
	
}
