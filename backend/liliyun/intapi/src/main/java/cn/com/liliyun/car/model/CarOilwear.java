package cn.com.liliyun.car.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CarOilwear extends BaseModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3896434928228775123L;

	private Integer id;

    private Integer carId;
    
    private Integer areaId;
    
    private String carNo;
    
    private String carOwner;

    private String oilSupplier;

    private Date oilDate;

    private Integer oilMl;

    private Integer oilType;

    private BigDecimal cost;

    private Integer sumCost;
    
    private Integer realCost;

    private Integer costType;

    private String oilstatName;
    
    private Double discount;
    
    private Double discountRate;
    
    private Double actualDiscountRate;
    
    private Double hangBrandPrice;
    
    private Double surplusDiscount;
    
    private Double previousDiscount;
    
    private String oilStationCode;
    
    private String oilTypeCode;
    
    private Double oilCardPrice;
    
    private String oilCard;

    private String cname;

    private Date ctime;

    private String remark;

    private Integer isDel;

    @JsonIgnore
    private Date stime;
    @JsonIgnore
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

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	public String getOilSupplier() {
        return oilSupplier;
    }

    public void setOilSupplier(String oilSupplier) {
        this.oilSupplier = oilSupplier == null ? null : oilSupplier.trim();
    }

    public Date getOilDate() {
        return oilDate;
    }

    public void setOilDate(Date oilDate) {
        this.oilDate = oilDate;
    }

    public Integer getOilMl() {
        return oilMl;
    }

    public void setOilMl(Integer oilMl) {
        this.oilMl = oilMl;
    }

    public Integer getOilType() {
        return oilType;
    }

    public void setOilType(Integer oilType) {
        this.oilType = oilType;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getSumCost() {
        return sumCost;
    }

    public void setSumCost(Integer sumCost) {
        this.sumCost = sumCost;
    }

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    public String getOilstatName() {
        return oilstatName;
    }

    public void setOilstatName(String oilstatName) {
        this.oilstatName = oilstatName == null ? null : oilstatName.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Integer getRealCost() {
		return realCost;
	}

	public void setRealCost(Integer realCost) {
		this.realCost = realCost;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
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

	public Double getOilCardPrice() {
		return oilCardPrice;
	}

	public void setOilCardPrice(Double oilCardPrice) {
		this.oilCardPrice = oilCardPrice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getOilCard() {
		return oilCard;
	}

	public void setOilCard(String oilCard) {
		this.oilCard = oilCard;
	}
    
	
	
}