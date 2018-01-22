package cn.com.liliyun.finance.model;

import cn.com.liliyun.common.model.BaseModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FinanceFeeItem extends BaseModel implements Serializable {
    private Integer itemid;

	private String tableid;

	private Integer studentid;

	private String idcard;

	private String name;

	private Integer subject1;

	private Integer subject2;

	private Integer applyexam;

	private BigDecimal money;

	private Integer billtype;

	private Date billdate;

	private String billnum;

	private Integer status;

	private String notenum;

	private Integer source;

	private Integer checkstatus;

	private Integer checkuid;

	private Date checktime;

	private String checkname;

	private String feenum;

	private Integer feeuid;

	private String feename;

	private Date feetime;

	private Integer areaid;

	private Integer storeid;

	private String remark;

	private String checkremark;

	private Integer cuid;

	private Date ctime;

	private String cname;

	private Integer muid;

	private Date mtime;

	private String mname;
	
	private Date stime;
	
	private Date etime;

	private String tableids;

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

	private static final long serialVersionUID = 1L;

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid == null ? null : tableid.trim();
	}

	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard == null ? null : idcard.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getSubject1() {
		return subject1;
	}

	public void setSubject1(Integer subject1) {
		this.subject1 = subject1;
	}

	public Integer getSubject2() {
		return subject2;
	}

	public void setSubject2(Integer subject2) {
		this.subject2 = subject2;
	}

	public Integer getApplyexam() {
		return applyexam;
	}

	public void setApplyexam(Integer applyexam) {
		this.applyexam = applyexam;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getBilltype() {
		return billtype;
	}

	public void setBilltype(Integer billtype) {
		this.billtype = billtype;
	}

	public Date getBilldate() {
		return billdate;
	}

	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}

	public String getBillnum() {
		return billnum;
	}

	public void setBillnum(String billnum) {
		this.billnum = billnum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNotenum() {
		return notenum;
	}

	public void setNotenum(String notenum) {
		this.notenum = notenum;
	}

	public Integer getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(Integer checkstatus) {
		this.checkstatus = checkstatus;
	}

	public Integer getCheckuid() {
		return checkuid;
	}

	public void setCheckuid(Integer checkuid) {
		this.checkuid = checkuid;
	}

	public Date getChecktime() {
		return checktime;
	}

	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}

	public String getCheckname() {
		return checkname;
	}

	public void setCheckname(String checkname) {
		this.checkname = checkname == null ? null : checkname.trim();
	}

	public String getFeenum() {
		return feenum;
	}

	public void setFeenum(String feenum) {
		this.feenum = feenum;
	}

	public Integer getFeeuid() {
		return feeuid;
	}

	public void setFeeuid(Integer feeuid) {
		this.feeuid = feeuid;
	}

	public String getFeename() {
		return feename;
	}

	public void setFeename(String feename) {
		this.feename = feename == null ? null : feename.trim();
	}

	public Date getFeetime() {
		return feetime;
	}

	public void setFeetime(Date feetime) {
		this.feetime = feetime;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getCheckremark() {
		return checkremark;
	}

	public void setCheckremark(String checkremark) {
		this.checkremark = checkremark == null ? null : checkremark.trim();
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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname == null ? null : cname.trim();
	}

	public Integer getMuid() {
		return muid;
	}

	public void setMuid(Integer muid) {
		this.muid = muid;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname == null ? null : mname.trim();
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getTableids() {
		return tableids;
	}

	public void setTableids(String tableids) {
		this.tableids = tableids;
	}
}