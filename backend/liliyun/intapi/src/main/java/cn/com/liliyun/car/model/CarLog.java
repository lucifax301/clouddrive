package cn.com.liliyun.car.model;

import cn.com.liliyun.common.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class CarLog extends BaseModel implements Serializable {
    private Integer id;
    
    private Integer carid;

    private String carno;

    private Integer areaid;//原片区id
    
    private String areaName;//原片区名
    
    private Integer curAreaid;//调入片区

    private Integer status;

    private String name;//现保管人

    private String prename;//原保管人
    
    private String parkingPlace;//停车地点
    
    private Date leadDate;//领用时间
    
    private Integer property;//使用类型
    
    private String remark;//备注
    
    private String reason;//调动原因
    
    private Integer changeOrNot;//是否换车
    
    private String changeCarNo;//换车牌号
    
    private Integer changeAreaId;//换车原片区
    
    private String changeName;//换车原保管人
    
    private Integer curChangeAreaId;//换车调入片区
    
    private String curChangeName;//换车现保管人
    
    private String changePackingPlace;//换车停车地点
    
    private Date changeLeadDate;//换车领用时间
    
    private Integer changeProperty;//换车领用性质
    
    private String changeRemark;//换车备注
       
    private Integer curAuditId;//当前审批人id
    
    private Integer submitId;//提交人id
    
    private Integer auditStatus;//办理状态
    
    private String transactionId;
    
    private Integer type;

    private Date ctime;
    
    private String cstartTime;
    
    private String cendTime;

    private Integer cuid;

    private String cname;
    
	private Integer modapplystat;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno == null ? null : carno.trim();
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename == null ? null : prename.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCstartTime() {
		return cstartTime;
	}

	public void setCstartTime(String cstartTime) {
		this.cstartTime = cstartTime;
	}

	public String getCendTime() {
		return cendTime;
	}

	public void setCendTime(String cendTime) {
		this.cendTime = cendTime;
	}

	public Integer getCurAreaid() {
		return curAreaid;
	}

	public void setCurAreaid(Integer curAreaid) {
		this.curAreaid = curAreaid;
	}

	public String getParkingPlace() {
		return parkingPlace;
	}

	public void setParkingPlace(String parkingPlace) {
		this.parkingPlace = parkingPlace;
	}

	public Date getLeadDate() {
		return leadDate;
	}

	public void setLeadDate(Date leadDate) {
		this.leadDate = leadDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getChangeOrNot() {
		return changeOrNot;
	}

	public void setChangeOrNot(Integer changeOrNot) {
		this.changeOrNot = changeOrNot;
	}

	public String getChangeCarNo() {
		return changeCarNo;
	}

	public void setChangeCarNo(String changeCarNo) {
		this.changeCarNo = changeCarNo;
	}

	public Integer getChangeAreaId() {
		return changeAreaId;
	}

	public void setChangeAreaId(Integer changeAreaId) {
		this.changeAreaId = changeAreaId;
	}


	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public Integer getCurChangeAreaId() {
		return curChangeAreaId;
	}

	public void setCurChangeAreaId(Integer curChangeAreaId) {
		this.curChangeAreaId = curChangeAreaId;
	}

	public String getCurChangeName() {
		return curChangeName;
	}

	public void setCurChangeName(String curChangeName) {
		this.curChangeName = curChangeName;
	}

	public String getChangePackingPlace() {
		return changePackingPlace;
	}

	public void setChangePackingPlace(String changePackingPlace) {
		this.changePackingPlace = changePackingPlace;
	}

	public Date getChangeLeadDate() {
		return changeLeadDate;
	}

	public void setChangeLeadDate(Date changeLeadDate) {
		this.changeLeadDate = changeLeadDate;
	}

	public String getChangeRemark() {
		return changeRemark;
	}

	public void setChangeRemark(String changeRemark) {
		this.changeRemark = changeRemark;
	}

	public Integer getCurAuditId() {
		return curAuditId;
	}

	public void setCurAuditId(Integer curAuditId) {
		this.curAuditId = curAuditId;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public Integer getChangeProperty() {
		return changeProperty;
	}

	public void setChangeProperty(Integer changeProperty) {
		this.changeProperty = changeProperty;
	}

	public Integer getSubmitId() {
		return submitId;
	}

	public void setSubmitId(Integer submitId) {
		this.submitId = submitId;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getModapplystat() {
		return modapplystat;
	}

	public void setModapplystat(Integer modapplystat) {
		this.modapplystat = modapplystat;
	}
	
	

}