package cn.com.liliyun.theory.model;

import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class TheoryStudent extends BaseModel {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer theoryid;

    private Integer storeid;

    private Integer studentid;

    private String remark;

    private Date ctime;

    private String extra;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTheoryid() {
        return theoryid;
    }

    public void setTheoryid(Integer theoryid) {
        this.theoryid = theoryid;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }
}