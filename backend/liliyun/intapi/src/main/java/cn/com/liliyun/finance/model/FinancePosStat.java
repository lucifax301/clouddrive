package cn.com.liliyun.finance.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import cn.com.liliyun.common.model.BaseModel;

public class FinancePosStat extends BaseModel {
	private static final long serialVersionUID = 1L;

	private String bank;
	
	private String posnum;
	
	private String poscompany;
	
	private String area;
	
	private String store;
	
	private Integer areaid;
	
	private Integer storeid;
	
	private BigDecimal posmoney;
	
	private BigDecimal rposmoney;
	
	private BigDecimal diffmoney;
	
	private BigDecimal confirmmoney;
	
	public void setIdsPlus(String ids) {
		if (!StringUtils.isBlank(ids)) {
			if (StringUtils.isBlank(getIds())) {
				setIds(ids);;
			} else {
				setIds(getIds() + "," + ids);
			}
		}
	}

	public String getPoscompany() {
		return poscompany;
	}

	public void setPoscompany(String poscompany) {
		this.poscompany = poscompany;
	}

	public String getPosnum() {
		return posnum;
	}

	public void setPosnum(String posnum) {
		this.posnum = posnum;
	}

	public BigDecimal getPosmoney() {
		return posmoney;
	}

	public void setPosmoney(BigDecimal posmoney) {
		this.posmoney = posmoney;
	}
	
	public void setPosmoneyPlus(BigDecimal posmoney) {
		if (posmoney == null)
			return;
		if (this.posmoney == null)
			this.posmoney = BigDecimal.ZERO;
		this.posmoney = this.posmoney.add(posmoney);
	}

	public BigDecimal getConfirmmoney() {
		return confirmmoney;
	}

	public void setConfirmmoney(BigDecimal confirmmoney) {
		this.confirmmoney = confirmmoney;
	}
	
	public void setConfirmmoneyPlus(BigDecimal confirmmoney) {
		if (confirmmoney == null) 
			return;
		if (this.confirmmoney == null)
			this.confirmmoney = BigDecimal.ZERO;
		this.confirmmoney = this.confirmmoney.add(confirmmoney);
	}

	public BigDecimal getRposmoney() {
		return rposmoney;
	}

	public void setRposmoney(BigDecimal rposmoney) {
		this.rposmoney = rposmoney;
	}
	
	public void setRposmoneyPlus(BigDecimal rposmoney) {
		if (rposmoney == null)
			return;
		if (this.rposmoney == null)
			this.rposmoney = BigDecimal.ZERO;
		this.rposmoney = this.rposmoney.add(rposmoney);
	}

	public BigDecimal getDiffmoney() {
		diffmoney = (posmoney==null?BigDecimal.ZERO:posmoney).subtract((rposmoney==null?BigDecimal.ZERO:rposmoney));
		return diffmoney;
	}

	public void setDiffmoney(BigDecimal diffmoney) {
		this.diffmoney = diffmoney;
	}
	
	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

}