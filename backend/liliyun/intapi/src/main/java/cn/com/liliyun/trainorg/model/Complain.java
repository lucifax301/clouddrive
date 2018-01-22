package cn.com.liliyun.trainorg.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class Complain extends BaseModel implements Serializable {
    private Integer complainid;

    private String summary;

    private String content;

    private String complainuser;

    private String complainmobile;

    private String remark;

    private String manageuser;

    private String managetype;

    private Integer status;

    private String manageremark;

    private Long cuid;

    private Long muid;

    private Date ctime;

    private Date mtime;

    private Integer returncount;

    private String cusername;

    private String musername;

    private Integer returnnum;

    private static final long serialVersionUID = 1L;

    public Integer getComplainid() {
        return complainid;
    }

    public void setComplainid(Integer complainid) {
        this.complainid = complainid;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getComplainuser() {
        return complainuser;
    }

    public void setComplainuser(String complainuser) {
        this.complainuser = complainuser == null ? null : complainuser.trim();
    }

    public String getComplainmobile() {
        return complainmobile;
    }

    public void setComplainmobile(String complainmobile) {
        this.complainmobile = complainmobile == null ? null : complainmobile.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getManageuser() {
        return manageuser;
    }

    public void setManageuser(String manageuser) {
        this.manageuser = manageuser == null ? null : manageuser.trim();
    }

    public String getManagetype() {
        return managetype;
    }

    public void setManagetype(String managetype) {
        this.managetype = managetype == null ? null : managetype.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getManageremark() {
        return manageremark;
    }

    public void setManageremark(String manageremark) {
        this.manageremark = manageremark == null ? null : manageremark.trim();
    }

    public Long getCuid() {
        return cuid;
    }

    public void setCuid(Long cuid) {
        this.cuid = cuid;
    }

    public Long getMuid() {
        return muid;
    }

    public void setMuid(Long muid) {
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

    public Integer getReturncount() {
        return returncount;
    }

    public void setReturncount(Integer returncount) {
        this.returncount = returncount;
    }

    public String getCusername() {
        return cusername;
    }

    public void setCusername(String cusername) {
        this.cusername = cusername == null ? null : cusername.trim();
    }

    public String getMusername() {
        return musername;
    }

    public void setMusername(String musername) {
        this.musername = musername == null ? null : musername.trim();
    }

	public Integer getReturnnum() {
		return returnnum;
	}

	public void setReturnnum(Integer returnnum) {
		this.returnnum = returnnum;
	}

}