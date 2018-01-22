package cn.com.liliyun.user.model;


import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class TopContacts extends BaseModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -302431915954633863L;

	private Integer id;

    private Long userId;

    private Integer schoolId;

    private Integer contsStaffId;

    private Integer contsSchoolId;

    private Integer isDel;

    private Date ctime;

    private Date mtime;

    private String ext;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getContsSchoolId() {
        return contsSchoolId;
    }

    public void setContsSchoolId(Integer contsSchoolId) {
        this.contsSchoolId = contsSchoolId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

	public Integer getContsStaffId() {
		return contsStaffId;
	}

	public void setContsStaffId(Integer contsStaffId) {
		this.contsStaffId = contsStaffId;
	}
}