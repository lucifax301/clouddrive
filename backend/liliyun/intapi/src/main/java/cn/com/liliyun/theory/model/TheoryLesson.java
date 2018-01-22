package cn.com.liliyun.theory.model;

import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class TheoryLesson extends BaseModel{

	private static final long serialVersionUID = 1L;

	private Integer theoryid;

    private Integer areaid;

    private String areaname;

    private String lessonname;

    private Date starttime;

    private Date endtime;

    private String place;

    private String contactname;

    private String contactphone;

    private Byte cardtype;

    private String idcard;

    private Integer recomnum;

    private Integer arrangenum;

    private Integer state;

    private Byte isdel;

    private Integer reviewerid;

    private Integer reviewstate;

    private Date reviewtime;

    private Integer cuid;

    private Date ctime;

    private Date mtime;
    
    private String businessid;
    
    private String transactionid;

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

	public Integer getTheoryid() {
        return theoryid;
    }

    public void setTheoryid(Integer theoryid) {
        this.theoryid = theoryid;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public String getLessonname() {
        return lessonname;
    }

    public void setLessonname(String lessonname) {
        this.lessonname = lessonname == null ? null : lessonname.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname == null ? null : contactname.trim();
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone == null ? null : contactphone.trim();
    }

    public Byte getCardtype() {
        return cardtype;
    }

    public void setCardtype(Byte cardtype) {
        this.cardtype = cardtype;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public Integer getRecomnum() {
        return recomnum;
    }

    public void setRecomnum(Integer recomnum) {
        this.recomnum = recomnum;
    }

    public Integer getArrangenum() {
        return arrangenum;
    }

    public void setArrangenum(Integer arrangenum) {
        this.arrangenum = arrangenum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Byte getIsdel() {
        return isdel;
    }

    public void setIsdel(Byte isdel) {
        this.isdel = isdel;
    }

    public Integer getReviewerid() {
        return reviewerid;
    }

    public void setReviewerid(Integer reviewerid) {
        this.reviewerid = reviewerid;
    }

    public Integer getReviewstate() {
        return reviewstate;
    }

    public void setReviewstate(Integer reviewstate) {
        this.reviewstate = reviewstate;
    }

    public Date getReviewtime() {
        return reviewtime;
    }

    public void setReviewtime(Date reviewtime) {
        this.reviewtime = reviewtime;
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
}