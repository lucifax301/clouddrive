package cn.com.liliyun.finance.model;

import java.math.BigDecimal;

import cn.com.liliyun.common.model.BaseModel;

public class FinanceStat extends BaseModel{
	private static final long serialVersionUID = 1L;

	private String bank;
	
	private String area;
	
	private String store;
	
	private BigDecimal price;
	
	private Integer areaid;
	
	private Integer storeid;
	
	private Integer type; //票据类型
	
	private String typestr;

	public String getTypestr() {
		return typestr;
	}

	public void setTypestr(String typestr) {
		this.typestr = typestr;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public void setPricePlus(BigDecimal price) {
		if (price == null) 
			return;
		if (this.price == null) {
			this.price = BigDecimal.ZERO;
		}
		this.price = this.price.add(price);
	}
}