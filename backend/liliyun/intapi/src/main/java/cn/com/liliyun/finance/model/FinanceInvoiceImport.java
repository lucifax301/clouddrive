package cn.com.liliyun.finance.model;

import java.math.BigDecimal;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class FinanceInvoiceImport extends BaseModel{
	private static final long serialVersionUID = 1L;

	@Excel(name = "发票号码", isImportField = "true")
	private String invoicenum;
	
	@Excel(name = "备注", isImportField = "true")
	private String remark;
	
	@Excel(name = "开票日期", isImportField = "true")
	private Date invoicedate;
	
	@Excel(name = "金额", isImportField = "true")
	private BigDecimal invoicemoney;
	
	private String receiptnum;

	public String getInvoicenum() {
		return invoicenum;
	}

	public void setInvoicenum(String invoicenum) {
		this.invoicenum = invoicenum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(Date invoicedate) {
		this.invoicedate = invoicedate;
	}

	public BigDecimal getInvoicemoney() {
		return invoicemoney;
	}

	public void setInvoicemoney(BigDecimal invoicemoney) {
		this.invoicemoney = invoicemoney;
	}

	public String getReceiptnum() {
		return receiptnum;
	}

	public void setReceiptnum(String receiptnum) {
		this.receiptnum = receiptnum;
	}
	
}