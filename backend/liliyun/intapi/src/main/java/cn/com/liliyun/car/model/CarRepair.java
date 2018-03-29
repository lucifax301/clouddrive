package cn.com.liliyun.car.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class CarRepair extends BaseModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5471531804186988303L;

	private Integer id;

    private Integer carId;

    private String reason;

    private String state;

    private Integer itemNum;

    private String hwu;

    private Date stime;

    private Date etime;

    private String parts;

    private String partsId;

    private Integer money;

    private String repairman;

    private String repairmanId;

    private String cname;

    
    private String remark;

    private Integer isDel;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public String getHwu() {
        return hwu;
    }

    public void setHwu(String hwu) {
        this.hwu = hwu == null ? null : hwu.trim();
    }

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

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts == null ? null : parts.trim();
    }

    public String getPartsId() {
        return partsId;
    }

    public void setPartsId(String partsId) {
        this.partsId = partsId == null ? null : partsId.trim();
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getRepairman() {
        return repairman;
    }

    public void setRepairman(String repairman) {
        this.repairman = repairman == null ? null : repairman.trim();
    }

    public String getRepairmanId() {
        return repairmanId;
    }

    public void setRepairmanId(String repairmanId) {
        this.repairmanId = repairmanId == null ? null : repairmanId.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
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
}