package cn.com.liliyun.finance.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class FinanceFeeDTO implements Serializable {
	
	private static final long serialVersionUID = 139118712509265769L;

	private String tableid;
	
	private Integer subject;

    private Integer applyexam;

    private BigDecimal money;

	public Integer getSubject() {
		return subject;
	}

	public void setSubject(Integer subject) {
		this.subject = subject;
	}

	public Integer getApplyexam() {
		return applyexam;
	}

	public void setApplyexam(Integer applyexam) {
		this.applyexam = applyexam;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid;
	}

}