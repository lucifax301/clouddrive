package cn.com.liliyun.car.model;

import java.io.Serializable;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class CarExport extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5057758384850729056L;
	
    private Integer areaId;
	
	@Excel(name="资产编号")
	private String assetNum;

	@Excel(name="所属片区")
	private String area;
	
	@Excel(name="车牌号")
	private String carNo;
	
	@Excel(name="车辆型号")
	private String carModel;
	
	@Excel(name="车辆品牌",replace={"捷达_1","神州者_2","迈腾_3","无_null"})
	private Integer brandId;
	
	@Excel(name="登记日期",exportFormat="yyyy-MM-dd")
	private Date regDate;
	
	@Excel(name="使用性质",replace={"专职教练车_0","强化教练车_1","测试车_2","组长车_3","其他_null"})
	private Integer property;
	
	@Excel(name="车辆类型",replace={"自有车_1","加盟商用车_2"})
	private String carType;
	
	@Excel(name="发动机号")
	private String engineNum;
	
	@Excel(name="车架号")
	private String frameNum;
	
	@Excel(name="机动车登记证书编号") //码值转换
	private String motorCode;
	
	@Excel(name="车辆所有人")
	private String carOwner;
	
	@Excel(name="车龄")
	private String carYear;
	
	public String getMotorCode() {
		return motorCode;
	}

	public void setMotorCode(String motorCode) {
		this.motorCode = motorCode == null ? null : motorCode.trim();
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo == null ? null : carNo.trim();
	}

	public String getFrameNum() {
		return frameNum;
	}

	public void setFrameNum(String frameNum) {
		this.frameNum = frameNum == null ? null : frameNum.trim();
	}

	public String getEngineNum() {
		return engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum == null ? null : engineNum.trim();
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner == null ? null : carOwner.trim();
	}

	public String getAssetNum() {
		return assetNum;
	}

	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum == null ? null : assetNum.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public String getCarYear() {
		return carYear;
	}

	public void setCarYear(String carYear) {
		this.carYear = carYear;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	
	
}