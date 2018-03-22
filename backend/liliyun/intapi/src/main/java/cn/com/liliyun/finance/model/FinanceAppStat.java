package cn.com.liliyun.finance.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.com.liliyun.common.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FinanceAppStat extends BaseModel{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private List<BigDecimal> data;
	@JsonIgnore
	private Date date;
	@JsonIgnore
	private Date startdate;
	@JsonIgnore
	private Date enddate;
	
	@JsonIgnore
	private BigDecimal money;
	private BigDecimal total;

	
	
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BigDecimal> getData() {
		return data;
	}

	public void setData(List<BigDecimal> data) {
		this.data = data;
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
	
	public void setTotalPlus(BigDecimal total)
	  {
	    if (total == null) {
	      return;
	    }
	    if (this.total == null) {
	      this.total = BigDecimal.ZERO;
	    }
	    this.total = this.total.add(total);
	  }
}