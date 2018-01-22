package cn.com.liliyun.finance.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class FinanceIncome extends BaseModel implements Serializable {

	private static final long serialVersionUID = -3048031040144922402L;

	private String batchnum;
	
	private Integer count;

    private Integer receiptmoney;

    private Date receiptdate;
    
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

	public String getBatchnum() {
		return batchnum;
	}

	public void setBatchnum(String batchnum) {
		this.batchnum = batchnum;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getReceiptmoney() {
		return receiptmoney;
	}

	public void setReceiptmoney(Integer receiptmoney) {
		this.receiptmoney = receiptmoney;
	}

	public Date getReceiptdate() {
		return receiptdate;
	}

	public void setReceiptdate(Date receiptdate) {
		this.receiptdate = receiptdate;
	}

}