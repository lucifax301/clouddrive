package cn.com.liliyun.finance.model;

import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class FinancePos extends BaseModel{
	private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer areaid;

    private Integer storeid;

    @Excel(name = "Pos机号")
    private String posnum;

    @Excel(name = "Pos类型", replace = {"移动_1","固定_2"})
    private Byte postype;

    private Integer accountid;

    @Excel(name = "Pos银行")
    private String bankname;

    @Excel(name = "Pos帐号")
    private String bankaccount;

    @Excel(name = "Pos公司")
    private String poscompany;

    private Date ctime;

    private Integer cuid;

    private String cname;

    private Date mtime;

    private Integer muid;

    private String mname;
    
    @Excel(name = "片区")
    private String areastr;
    
    @Excel(name = "门店")
    private String storestr;
    
    private String posnumequal;
    
    public String getPosnumequal() {
		return posnumequal;
	}

	public void setPosnumequal(String posnumequal) {
		this.posnumequal = posnumequal;
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

    public String getPosnum() {
        return posnum;
    }

    public void setPosnum(String posnum) {
        this.posnum = posnum == null ? null : posnum.trim();
    }

    public Byte getPostype() {
        return postype;
    }

    public void setPostype(Byte postype) {
        this.postype = postype;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount == null ? null : bankaccount.trim();
    }

    public String getPoscompany() {
        return poscompany;
    }

    public void setPoscompany(String poscompany) {
        this.poscompany = poscompany == null ? null : poscompany.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
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