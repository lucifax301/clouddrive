package cn.com.liliyun.importexcel.model;

import java.io.Serializable;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

public class CarOilwearImport implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private Integer carId;
    
    private String carNo;

	@Excel(name="卡号")
	private String oilCard;
	
	@Excel(name="油品")
    private String oilType_str;
    
	private Integer oilType;
	
	@Excel(name="加油升数")
    private Integer oilMl;
    
	@Excel(name="单价")
    private Integer cost;
    
	@Excel(name="加油金额")
    private Integer sumCost;
    
	@Excel(name="加油时间")
    private Date oilDate;
    
	@Excel(name="折让")
    private Double discount;
    
	@Excel(name="油站")
    private String oilstatName;
    
	@Excel(name="折让率")
    private Double discountRate;
    
	@Excel(name="实际折让率")
    private Double actualDiscountRate;	
    
	@Excel(name="挂牌价")
    private Double hangBrandPrice;
    
	@Excel(name="剩余抵扣")
    private Double surplusDiscount;	
    
	@Excel(name="上期抵扣")
    private Double previousDiscount;
    
	@Excel(name="油站代码")
    private String oilStationCode;
	
	@Excel(name="油品代码")
    private String oilTypeCode;

	public String getOilCard() {
		return oilCard;
	}

	public void setOilCard(String oilCard) {
		this.oilCard = oilCard;
	}

	public Integer getOilType() {
		return oilType;
	}

	public void setOilType(Integer oilType) {
		this.oilType = oilType;
	}

	public Integer getOilMl() {
		return oilMl;
	}

	public void setOilMl(Integer oilMl) {
		this.oilMl = oilMl;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getSumCost() {
		return sumCost;
	}

	public void setSumCost(Integer sumCost) {
		this.sumCost = sumCost;
	}

	public Date getOilDate() {
		return oilDate;
	}

	public void setOilDate(Date oilDate) {
		this.oilDate = oilDate;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getOilstatName() {
		return oilstatName;
	}

	public void setOilstatName(String oilstatName) {
		this.oilstatName = oilstatName;
	}

	public Double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}

	public Double getActualDiscountRate() {
		return actualDiscountRate;
	}

	public void setActualDiscountRate(Double actualDiscountRate) {
		this.actualDiscountRate = actualDiscountRate;
	}

	public Double getHangBrandPrice() {
		return hangBrandPrice;
	}

	public void setHangBrandPrice(Double hangBrandPrice) {
		this.hangBrandPrice = hangBrandPrice;
	}

	public Double getSurplusDiscount() {
		return surplusDiscount;
	}

	public void setSurplusDiscount(Double surplusDiscount) {
		this.surplusDiscount = surplusDiscount;
	}

	public Double getPreviousDiscount() {
		return previousDiscount;
	}

	public void setPreviousDiscount(Double previousDiscount) {
		this.previousDiscount = previousDiscount;
	}

	public String getOilStationCode() {
		return oilStationCode;
	}

	public void setOilStationCode(String oilStationCode) {
		this.oilStationCode = oilStationCode;
	}

	public String getOilTypeCode() {
		return oilTypeCode;
	}

	public void setOilTypeCode(String oilTypeCode) {
		this.oilTypeCode = oilTypeCode;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getOilType_str() {
		return oilType_str;
	}

	public void setOilType_str(String oilType_str) {
		this.oilType_str = oilType_str;
	}

	@Override
	public String toString() {
		return "CarOilwearImport [carId=" + carId + ", carNo=" + carNo
				+ ", oilCard=" + oilCard + ", oilType_str=" + oilType_str
				+ ", oilType=" + oilType + ", oilMl=" + oilMl + ", cost="
				+ cost + ", sumCost=" + sumCost + ", oilDate=" + oilDate
				+ ", discount=" + discount + ", oilstatName=" + oilstatName
				+ ", discountRate=" + discountRate + ", actualDiscountRate="
				+ actualDiscountRate + ", hangBrandPrice=" + hangBrandPrice
				+ ", surplusDiscount=" + surplusDiscount
				+ ", previousDiscount=" + previousDiscount
				+ ", oilStationCode=" + oilStationCode + ", oilTypeCode="
				+ oilTypeCode + "]";
	}


}
