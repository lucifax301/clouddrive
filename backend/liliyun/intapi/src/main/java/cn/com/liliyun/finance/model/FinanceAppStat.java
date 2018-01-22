package cn.com.liliyun.finance.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class FinanceAppStat extends BaseModel{
	private static final long serialVersionUID = 1L;

	private BigDecimal money;
	
	private Integer areaid;
	
	private Integer storeid;
	
	private Date date;
	
	private Date startdate;
	
	private Date enddate;

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
}