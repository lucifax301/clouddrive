package cn.com.liliyun.student.model;

import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class TransferStudent extends BaseModel {
   
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer studentid;

    private String name;

    private String studentnum;

    private String idcard;

    private Integer fromareaid;

    private Integer fromstoreid;

    private String fromstorenum;

    private Integer targetareaid;

    private Integer targetstoreid;

    private String targetstorenum;

    private Byte isoutarea;

    private Byte filetransfertype;

    private String transferno;

    private Integer state;

    private String reason;

    private Integer cuid;
    
    private String cname;

    private Date ctime;

    private Date mtime;

    private Integer reviewid;

    private String reviewname;
    
    private Date reviewtime;
    
    private String reviewremark;
    
    private String businessid;
    
    private String transactionid;
    
    //列表筛选
    private Integer isChosen;
    
    public String getBusinessid() {
		return businessid;
	}

	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getReviewname() {
		return reviewname;
	}

	public void setReviewname(String reviewname) {
		this.reviewname = reviewname;
	}

	public Integer getIsChosen() {
		return isChosen;
	}

	public void setIsChosen(Integer isChosen) {
		this.isChosen = isChosen;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(String studentnum) {
        this.studentnum = studentnum == null ? null : studentnum.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public Integer getFromareaid() {
        return fromareaid;
    }

    public void setFromareaid(Integer fromareaid) {
        this.fromareaid = fromareaid;
    }

    public Integer getFromstoreid() {
        return fromstoreid;
    }

    public void setFromstoreid(Integer fromstoreid) {
        this.fromstoreid = fromstoreid;
    }

    public String getFromstorenum() {
        return fromstorenum;
    }

    public void setFromstorenum(String fromstorenum) {
        this.fromstorenum = fromstorenum == null ? null : fromstorenum.trim();
    }

    public Integer getTargetareaid() {
        return targetareaid;
    }

    public void setTargetareaid(Integer targetareaid) {
        this.targetareaid = targetareaid;
    }

    public Integer getTargetstoreid() {
        return targetstoreid;
    }

    public void setTargetstoreid(Integer targetstoreid) {
        this.targetstoreid = targetstoreid;
    }

    public String getTargetstorenum() {
        return targetstorenum;
    }

    public void setTargetstorenum(String targetstorenum) {
        this.targetstorenum = targetstorenum == null ? null : targetstorenum.trim();
    }

    public Byte getIsoutarea() {
        return isoutarea;
    }

    public void setIsoutarea(Byte isoutarea) {
        this.isoutarea = isoutarea;
    }

    public Byte getFiletransfertype() {
        return filetransfertype;
    }

    public void setFiletransfertype(Byte filetransfertype) {
        this.filetransfertype = filetransfertype;
    }

    public String getTransferno() {
        return transferno;
    }

    public void setTransferno(String transferno) {
        this.transferno = transferno == null ? null : transferno.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
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

    public Integer getReviewid() {
        return reviewid;
    }

    public void setReviewid(Integer reviewid) {
        this.reviewid = reviewid;
    }

    public Date getReviewtime() {
        return reviewtime;
    }

    public void setReviewtime(Date reviewtime) {
        this.reviewtime = reviewtime;
    }

    public String getReviewremark() {
        return reviewremark;
    }

    public void setReviewremark(String reviewremark) {
        this.reviewremark = reviewremark == null ? null : reviewremark.trim();
    }
}