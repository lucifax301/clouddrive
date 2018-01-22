package cn.com.liliyun.car.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CarCostRemind extends BaseModel implements Serializable {
    private Integer tax;

    private Integer insurance;

    private Integer annualcheck;

    private Integer retire;
    
    private Integer type;
    
    private Integer areaid;
    @JsonIgnore
    private Integer status;

    private static final long serialVersionUID = 1L;

    
    
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getInsurance() {
        return insurance;
    }

    public void setInsurance(Integer insurance) {
        this.insurance = insurance;
    }

    public Integer getAnnualcheck() {
        return annualcheck;
    }

    public void setAnnualcheck(Integer annualcheck) {
        this.annualcheck = annualcheck;
    }

    public Integer getRetire() {
        return retire;
    }

    public void setRetire(Integer retire) {
        this.retire = retire;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
    
}