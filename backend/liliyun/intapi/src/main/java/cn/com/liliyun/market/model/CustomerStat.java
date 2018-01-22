package cn.com.liliyun.market.model;

import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class CustomerStat extends BaseModel{

	private static final long serialVersionUID = 1L;

	private Integer areaid;
	
	private Integer storeid;
	
	private Integer channel;
	
	private Date date;
	
	/**
	 * 是否潜在客户，1是，0否
	 */
	private Integer ispotential;
	
	private Integer stucount;
	
	/**
	 * 统计开始日期（含）
	 */
	private Date startdate;
	
	/**
	 * 统计结束日期（含）
	 */
	private Date enddate;

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
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

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getIspotential() {
		return ispotential;
	}

	public void setIspotential(Integer ispotential) {
		this.ispotential = ispotential;
	}

	public Integer getStucount() {
		return stucount;
	}

	public void setStucount(Integer stucount) {
		this.stucount = stucount;
	}
	
}
