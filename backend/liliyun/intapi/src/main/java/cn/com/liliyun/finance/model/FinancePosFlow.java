package cn.com.liliyun.finance.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class FinancePosFlow extends BaseModel{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer areaid;
	
	private Integer storeid;

    private String poscompany;

    private Date tradedate;

    private String tradenum;

    private String posnum;
    
    private String posbankname;

    private BigDecimal trademoney;
    
    private BigDecimal settlemoney;
    
    private BigDecimal poundage;
    
    private String bank;

    private Date ctime;

    private Integer cuid;

    private String cname;
    
    //筛选字段
    private Date tradedatelow;
    
    private Date tradedatetop;

    public String getPosbankname() {
		return posbankname;
	}

	public void setPosbankname(String posbankname) {
		this.posbankname = posbankname;
	}

	public Date getTradedatelow() {
		return tradedatelow;
	}

	public void setTradedatelow(Date tradedatelow) {
		this.tradedatelow = tradedatelow;
	}

	public Date getTradedatetop() {
		return tradedatetop;
	}

	public void setTradedatetop(Date tradedatetop) {
		this.tradedatetop = tradedatetop;
	}

	public BigDecimal getSettlemoney() {
		return settlemoney;
	}

	public void setSettlemoney(BigDecimal settlemoney) {
		this.settlemoney = settlemoney;
	}
	
	public void setSettlemoneyPlus(BigDecimal settlemoney) {
		if (settlemoney == null)
			return;
		if (this.settlemoney == null)
			this.settlemoney = BigDecimal.ZERO;
		this.settlemoney = this.settlemoney.add(settlemoney);
	}

	public BigDecimal getPoundage() {
		return poundage;
	}

	public void setPoundage(BigDecimal poundage) {
		this.poundage = poundage;
	}
	
	public void setPoundagePlus(BigDecimal poundage) {
		if (poundage == null)
			return;
		if (this.poundage == null)
			this.poundage = BigDecimal.ZERO;
		this.poundage = this.poundage.add(poundage);
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
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

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoscompany() {
        return poscompany;
    }

    public void setPoscompany(String poscompany) {
        this.poscompany = poscompany == null ? null : poscompany.trim();
    }

    public Date getTradedate() {
        return tradedate;
    }

    public void setTradedate(Date tradedate) {
        this.tradedate = tradedate;
    }

    public String getTradenum() {
        return tradenum;
    }

    public void setTradenum(String tradenum) {
        this.tradenum = tradenum == null ? null : tradenum.trim();
    }

    public String getPosnum() {
        return posnum;
    }

    public void setPosnum(String posnum) {
        this.posnum = posnum == null ? null : posnum.trim();
    }

    public BigDecimal getTrademoney() {
        return trademoney;
    }

    public void setTrademoney(BigDecimal trademoney) {
        this.trademoney = trademoney;
    }
    
    public void setTrademoneyPlus(BigDecimal trademoney) {
    	if (trademoney == null)
    		return;
    	if(this.trademoney == null)
    		this.trademoney = BigDecimal.ZERO;
    	this.trademoney = this.trademoney.add(trademoney);
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
}