package cn.com.liliyun.finance.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class FinancePay extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 849126925888347014L;

	private String tableid;
	
	private Integer count;

    private Integer totalmoney;

    private Date billdate;
    
    private Date stime;
    
    private Date etime;

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	public Date getEtime() {
		return etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

	public Integer getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(Integer totalmoney) {
		this.totalmoney = totalmoney;
	}

	public Date getBilldate() {
		return billdate;
	}

	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}
	
}