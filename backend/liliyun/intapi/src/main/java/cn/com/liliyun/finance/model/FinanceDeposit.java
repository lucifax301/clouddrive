package cn.com.liliyun.finance.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class FinanceDeposit extends BaseModel{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer areaid;

    private Integer storeid;

    @Excel(name = "存款人")
    private String depositor;

    private Integer accountid;

    @Excel(name = "存款帐号")
    private String account;

    private String bankname;

    private String orderid;

    @Excel(name = "存款金额")
    private BigDecimal amount;

    @Excel(name = "回款说明")
    private String receipt;

    @Excel(name = "当日现金余额")
    private BigDecimal remaincash;

    private Date receiptdate;

    private Date depositdate;

    @Excel(name = "是否结转", replace = {"已结转_1", "未结转_0"})
    private Byte isconfirm;

    private Byte isdel;

    @Excel(name = "录入时间", exportFormat = "yyyy-MM-dd HH:mm")
    private Date ctime;

    private Integer cuid;

    @Excel(name = "录入人")
    private String cname;

    private Date mtime;

    private Integer muid;

    private String mname;
    
    //导出字段
    @Excel(name = "片区")
    private String areastr;
    
    @Excel(name = "门店")
    private String storestr;
    
    //筛选字段
    private Date depositdatetop;
    
    private Date depositdatelow;
    
    private Boolean updateable;
    
    public Boolean getUpdateable() {
		return updateable;
	}

	public void setUpdateable(Boolean updateable) {
		this.updateable = updateable;
	}

	public String getAreastr() {
		return areastr;
	}

	public void setAreastr(String areastr) {
		this.areastr = areastr;
	}

	public String getStorestr() {
		return storestr;
	}

	public void setStorestr(String storestr) {
		this.storestr = storestr;
	}

	public Date getDepositdatetop() {
		return depositdatetop;
	}

	public void setDepositdatetop(Date depositdatetop) {
		this.depositdatetop = depositdatetop;
	}

	public Date getDepositdatelow() {
		return depositdatelow;
	}

	public void setDepositdatelow(Date depositdatelow) {
		this.depositdatelow = depositdatelow;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor == null ? null : depositor.trim();
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt == null ? null : receipt.trim();
    }

    public BigDecimal getRemaincash() {
        return remaincash;
    }

    public void setRemaincash(BigDecimal remaincash) {
        this.remaincash = remaincash;
    }

    public Date getReceiptdate() {
        return receiptdate;
    }

    public void setReceiptdate(Date receiptdate) {
        this.receiptdate = receiptdate;
    }

    public Date getDepositdate() {
        return depositdate;
    }

    public void setDepositdate(Date depositdate) {
        this.depositdate = depositdate;
    }

    public Byte getIsconfirm() {
        return isconfirm;
    }

    public void setIsconfirm(Byte isconfirm) {
        this.isconfirm = isconfirm;
    }

    public Byte getIsdel() {
        return isdel;
    }

    public void setIsdel(Byte isdel) {
        this.isdel = isdel;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
        if (ctime != null) {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	String ctimestr = sdf.format(ctime);
        	String curstr = sdf.format(new Date());
        	updateable = ctimestr.equals(curstr);
        } else {
        	updateable = null;
        }
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Integer getMuid() {
        return muid;
    }

    public void setMuid(Integer muid) {
        this.muid = muid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname == null ? null : mname.trim();
    }
}