package cn.com.liliyun.car.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class Car extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 5943450672801245489L;

	private Integer carId;

    private Integer motorId;

    private String motorCode;

    private Date motorDate;

    private String motorFcode;

    private String carNo;

    private Integer carnoColor;

    private String formerCarno;

    private String frameNum;

    private String engineModel;

    private String engineNum;

    private String carVin;

    private String carModel;

    private String carType;

    private String carBrand;

    private String carOwner;

    private String assetNum;

    private String regOffice;

    private String fileNo;

    private Integer areaId;

    private Integer trainareaId;

    private String area;

    private Integer status;

    private Integer driveType;

    private Integer brandId;

    private String brandName;

    private String driveNum;

    private String drivePhoto;

    private Integer schoolId;

    private Integer cityId;

    private Integer carColor;

    private Integer fuel;

    private String carImgId;

    private String carImg;

    private String manufacturer;

    private Date leaveDate;

    private Date buyDate;

    private Integer buyFee;

    private Date regDate;

    private Date verifyEnd;

    private Date acceptDate;

    private Date retireDate;

    private Date scrapDate;

    private Date insuranceEnd;

    private Integer coachId;

    private String coachName;

    private Integer cuid;

    private Integer muid;

    private Date ctime;

    private Date mtime;

    private Integer power;

    private Integer wheelRear;

    private Integer wheelFront;

    private Integer tyreSpec;

    private Integer tyreNum;

    private Integer wheelbase;

    private Integer height;

    private Integer width;

    private Integer length;

    private Integer displacement;
    
    private Integer property;
    
    private String parkAddr;

    private String oilCard;
    
    private Integer delayScrap;
    
    private Integer teachSubject;
    
    private Integer org;
    
    
    private String carYear;//计算车龄用到
    
    
    
    public Integer getOrg() {
		return org;
	}

	public void setOrg(Integer org) {
		this.org = org;
	}

	public Integer getTeachSubject() {
		return teachSubject;
	}

	public void setTeachSubject(Integer teachSubject) {
		this.teachSubject = teachSubject;
	}

	public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getMotorId() {
        return motorId;
    }

    public void setMotorId(Integer motorId) {
        this.motorId = motorId;
    }

    public String getMotorCode() {
        return motorCode;
    }

    public void setMotorCode(String motorCode) {
        this.motorCode = motorCode == null ? null : motorCode.trim();
    }

    public Date getMotorDate() {
        return motorDate;
    }

    public void setMotorDate(Date motorDate) {
        this.motorDate = motorDate;
    }

    public String getMotorFcode() {
        return motorFcode;
    }

    public void setMotorFcode(String motorFcode) {
        this.motorFcode = motorFcode == null ? null : motorFcode.trim();
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public Integer getCarnoColor() {
        return carnoColor;
    }

    public void setCarnoColor(Integer carnoColor) {
        this.carnoColor = carnoColor;
    }

    public String getFormerCarno() {
        return formerCarno;
    }

    public void setFormerCarno(String formerCarno) {
        this.formerCarno = formerCarno == null ? null : formerCarno.trim();
    }

    public String getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(String frameNum) {
        this.frameNum = frameNum == null ? null : frameNum.trim();
    }

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel == null ? null : engineModel.trim();
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum == null ? null : engineNum.trim();
    }

    public String getCarVin() {
        return carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin == null ? null : carVin.trim();
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel == null ? null : carModel.trim();
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand == null ? null : carBrand.trim();
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

    public String getRegOffice() {
        return regOffice;
    }

    public void setRegOffice(String regOffice) {
        this.regOffice = regOffice == null ? null : regOffice.trim();
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo == null ? null : fileNo.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getTrainareaId() {
        return trainareaId;
    }

    public void setTrainareaId(Integer trainareaId) {
        this.trainareaId = trainareaId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDriveType() {
        return driveType;
    }

    public void setDriveType(Integer driveType) {
        this.driveType = driveType;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getDriveNum() {
        return driveNum;
    }

    public void setDriveNum(String driveNum) {
        this.driveNum = driveNum == null ? null : driveNum.trim();
    }

    public String getDrivePhoto() {
        return drivePhoto;
    }

    public void setDrivePhoto(String drivePhoto) {
        this.drivePhoto = drivePhoto == null ? null : drivePhoto.trim();
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCarColor() {
        return carColor;
    }

    public void setCarColor(Integer carColor) {
        this.carColor = carColor;
    }

    public Integer getFuel() {
        return fuel;
    }

    public void setFuel(Integer fuel) {
        this.fuel = fuel;
    }

    public String getCarImgId() {
        return carImgId;
    }

    public void setCarImgId(String carImgId) {
        this.carImgId = carImgId == null ? null : carImgId.trim();
    }

    public String getCarImg() {
        return carImg;
    }

    public void setCarImg(String carImg) {
        this.carImg = carImg == null ? null : carImg.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Integer getBuyFee() {
        return buyFee;
    }

    public void setBuyFee(Integer buyFee) {
        this.buyFee = buyFee;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getVerifyEnd() {
        return verifyEnd;
    }

    public void setVerifyEnd(Date verifyEnd) {
        this.verifyEnd = verifyEnd;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Date getRetireDate() {
        return retireDate;
    }

    public void setRetireDate(Date retireDate) {
        this.retireDate = retireDate;
    }

    public Date getScrapDate() {
        return scrapDate;
    }

    public void setScrapDate(Date scrapDate) {
        this.scrapDate = scrapDate;
    }

    public Date getInsuranceEnd() {
        return insuranceEnd;
    }

    public void setInsuranceEnd(Date insuranceEnd) {
        this.insuranceEnd = insuranceEnd;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName == null ? null : coachName.trim();
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }

    public Integer getMuid() {
        return muid;
    }

    public void setMuid(Integer muid) {
        this.muid = muid;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getWheelRear() {
        return wheelRear;
    }

    public void setWheelRear(Integer wheelRear) {
        this.wheelRear = wheelRear;
    }

    public Integer getWheelFront() {
        return wheelFront;
    }

    public void setWheelFront(Integer wheelFront) {
        this.wheelFront = wheelFront;
    }



    public Integer getWheelbase() {
        return wheelbase;
    }

    public void setWheelbase(Integer wheelbase) {
        this.wheelbase = wheelbase;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Integer displacement) {
        this.displacement = displacement;
    }

	public Integer getTyreSpec() {
		return tyreSpec;
	}

	public void setTyreSpec(Integer tyreSpec) {
		this.tyreSpec = tyreSpec;
	}

	public Integer getTyreNum() {
		return tyreNum;
	}

	public void setTyreNum(Integer tyreNum) {
		this.tyreNum = tyreNum;
	}

	public String getParkAddr() {
		return parkAddr;
	}

	public void setParkAddr(String parkAddr) {
		this.parkAddr = parkAddr;
	}

	public String getOilCard() {
		return oilCard;
	}

	public void setOilCard(String oilCard) {
		this.oilCard = oilCard;
	}

	public Integer getDelayScrap() {
		return delayScrap;
	}

	public void setDelayScrap(Integer delayScrap) {
		this.delayScrap = delayScrap;
	}

	public String getCarYear() {
		return carYear;
	}

	public void setCarYear(String carYear) {
		this.carYear = carYear;
	}
	
	
	
	
}