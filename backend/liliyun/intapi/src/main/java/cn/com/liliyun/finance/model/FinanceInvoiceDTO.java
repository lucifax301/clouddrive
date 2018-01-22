package cn.com.liliyun.finance.model;

import java.math.BigDecimal;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class FinanceInvoiceDTO extends BaseModel{
	private static final long serialVersionUID = 1L;

	@Excel(name = "单据号")
    private String billnum;
	
	@Excel(name = "批号")
    private String batchnum;
	
	@Excel(name = "商品行数")
	private String goodsnum;
	
	@Excel(name = "购方名称")
	private String invoicename;
	
	@Excel(name = "购方税号")
	private String buyernum;
	
	@Excel(name = "购方地址电话")
	private String buyaddr;
	
	@Excel(name = "购方银行帐号")
	private String buybankaccount;
	
	@Excel(name = "备注")
	private String receiptremark;
	
	@Excel(name = "复核人")
	private String checker;
	
	@Excel(name = "收款人")
	private String payee;
	
	@Excel(name = "清单行商品名称")
	private String goodsname;
	
	@Excel(name = "单据日期")
	private Date billdate;
	
	@Excel(name = "销方银行帐号")
	private String sellerbankaccount;
	
	@Excel(name = "销方地址电话")
	private String selleraddr;
	
	@Excel(name = "货物名称")
	private String typestr;
	
	@Excel(name = "计量单位")
	private String unit;
	
	@Excel(name = "规格")
	private String standard;
	
	@Excel(name = "数量")
	private String amount;
	
	@Excel(name = "金额")
	private BigDecimal receiptmoney;
	
	@Excel(name = "税率")
	private Float  taxrate;
	
	@Excel(name = "商品税目")
	private String  taxname;
	
	@Excel(name = "折扣金额")
	private String  discount;
	
	@Excel(name = "税额")
	private String  taxamount;
	
	@Excel(name = "折扣税额")
	private String  discounttax;
	
	@Excel(name = "折扣率")
	private String  discountrate;
	
	@Excel(name = "单价")
	private String  unitprice;
	
	@Excel(name = "价格方式")
	private String  pricetype;
	
	@Excel(name = "商品编码库版本号")
	private String  goodscodeversion;
	
	@Excel(name = "商品编码")
	private String  goodscode;
	
	@Excel(name = "企业商品编码")
	private String  goodsecode;
	
	@Excel(name = "使用优惠政策标识")
	private String  isdiscount;
	
	@Excel(name = "零税率标识")
	private String  iszerotax;
	
	@Excel(name = "优惠政策说明")
	private String  discountnote;
	
	private Integer type;
	
	private Integer areaid;
	
	private String areastr;
	
	private Integer storeid;
	
	private String storestr;
	
	private String stuname;
	
	private String stuidcard;
	
	private String classinfo;

    private String traintype;
    
    private String receiptnum;

	public String getBillnum() {
		return billnum;
	}

	public void setBillnum(String billnum) {
		this.billnum = billnum;
	}

	public String getBatchnum() {
		return batchnum;
	}

	public void setBatchnum(String batchnum) {
		this.batchnum = batchnum;
	}

	public String getGoodsnum() {
		return goodsnum;
	}

	public void setGoodsnum(String goodsnum) {
		this.goodsnum = goodsnum;
	}

	public String getInvoicename() {
		return invoicename;
	}

	public void setInvoicename(String invoicename) {
		this.invoicename = invoicename;
	}

	public String getBuyernum() {
		return buyernum;
	}

	public void setBuyernum(String buyernum) {
		this.buyernum = buyernum;
	}

	public String getBuyaddr() {
		return buyaddr;
	}

	public void setBuyaddr(String buyaddr) {
		this.buyaddr = buyaddr;
	}

	public String getBuybankaccount() {
		return buybankaccount;
	}

	public void setBuybankaccount(String buybankaccount) {
		this.buybankaccount = buybankaccount;
	}

	public String getReceiptremark() {
		receiptremark = areastr!=null?areastr:"";
		receiptremark += "/";
		receiptremark += storestr!=null?storestr:"";
		receiptremark += "/";
		receiptremark += stuname!=null?stuname:"";
		receiptremark += "/";
		receiptremark += stuidcard!=null?stuidcard:"";
		receiptremark += "/";
		receiptremark += classinfo!=null?classinfo:"";
		receiptremark += "/";
		receiptremark += traintype!=null?traintype:"";
		receiptremark += "/";
		receiptremark += typestr!=null?typestr:"";
		receiptremark += "/";
		receiptremark += receiptnum!=null?receiptnum:"";
		return receiptremark;
	}

	public void setReceiptremark(String receiptremark) {
		this.receiptremark = receiptremark;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public Date getBilldate() {
		return billdate;
	}

	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}

	public String getSellerbankaccount() {
		return sellerbankaccount;
	}

	public void setSellerbankaccount(String sellerbankaccount) {
		this.sellerbankaccount = sellerbankaccount;
	}

	public String getSelleraddr() {
		return selleraddr;
	}

	public void setSelleraddr(String selleraddr) {
		this.selleraddr = selleraddr;
	}

	public String getTypestr() {
		return typestr;
	}

	public void setTypestr(String typestr) {
		this.typestr = typestr;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Float getTaxrate() {
		return taxrate;
	}

	public void setTaxrate(Float taxrate) {
		this.taxrate = taxrate;
	}

	public String getTaxname() {
		return taxname;
	}

	public void setTaxname(String taxname) {
		this.taxname = taxname;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getTaxamount() {
		return taxamount;
	}

	public void setTaxamount(String taxamount) {
		this.taxamount = taxamount;
	}

	public String getDiscounttax() {
		return discounttax;
	}

	public void setDiscounttax(String discounttax) {
		this.discounttax = discounttax;
	}

	public String getDiscountrate() {
		return discountrate;
	}

	public void setDiscountrate(String discountrate) {
		this.discountrate = discountrate;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getPricetype() {
		return pricetype;
	}

	public void setPricetype(String pricetype) {
		this.pricetype = pricetype;
	}

	public String getGoodscodeversion() {
		return goodscodeversion;
	}

	public void setGoodscodeversion(String goodscodeversion) {
		this.goodscodeversion = goodscodeversion;
	}

	public String getGoodscode() {
		return goodscode;
	}

	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
	}

	public String getGoodsecode() {
		return goodsecode;
	}

	public void setGoodsecode(String goodsecode) {
		this.goodsecode = goodsecode;
	}

	public String getIsdiscount() {
		return isdiscount;
	}

	public void setIsdiscount(String isdiscount) {
		this.isdiscount = isdiscount;
	}

	public String getIszerotax() {
		return iszerotax;
	}

	public void setIszerotax(String iszerotax) {
		this.iszerotax = iszerotax;
	}

	public String getDiscountnote() {
		return discountnote;
	}

	public void setDiscountnote(String discountnote) {
		this.discountnote = discountnote;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getReceiptmoney() {
		return receiptmoney;
	}

	public void setReceiptmoney(BigDecimal receiptmoney) {
		this.receiptmoney = receiptmoney;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getAreastr() {
		return areastr;
	}

	public void setAreastr(String areastr) {
		this.areastr = areastr;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public String getStorestr() {
		return storestr;
	}

	public void setStorestr(String storestr) {
		this.storestr = storestr;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getStuidcard() {
		return stuidcard;
	}

	public void setStuidcard(String stuidcard) {
		this.stuidcard = stuidcard;
	}

	public String getClassinfo() {
		return classinfo;
	}

	public void setClassinfo(String classinfo) {
		this.classinfo = classinfo;
	}

	public String getTraintype() {
		return traintype;
	}

	public void setTraintype(String traintype) {
		this.traintype = traintype;
	}

	public String getReceiptnum() {
		return receiptnum;
	}

	public void setReceiptnum(String receiptnum) {
		this.receiptnum = receiptnum;
	}
    
}