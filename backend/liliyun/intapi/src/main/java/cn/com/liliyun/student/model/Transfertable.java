package cn.com.liliyun.student.model;

import cn.com.liliyun.common.model.BaseModel;
import java.io.Serializable;
import java.util.Date;

public class Transfertable extends BaseModel implements Serializable {
	private String tableid;

	private Integer itemcount;

	private Integer localnum;

	private Integer nolocalnum;

	private Integer transferid;

	private String transfername;

	private Date transfertime;

	private Integer transferstoreid;

	private Integer transferareaid;

	private Integer receiveid;

	private String receivename;

	private Date receivetime;

	private Integer receivestoreid;

	private Integer receiveareaid;

	private Integer correctnum;

	private Integer incorrectnum;

	private Integer returnnum;

	private Integer storeid;

	private Integer areaid;

	private Integer status;

	private Date ctime;

	private Date mtime;

	private static final long serialVersionUID = 1L;

	public String getTableid() {
		return tableid;
	}

	public void setTableid(String tableid) {
		this.tableid = tableid == null ? null : tableid.trim();
	}

	public Integer getItemcount() {
		return itemcount;
	}

	public void setItemcount(Integer itemcount) {
		this.itemcount = itemcount;
	}

	public Integer getLocalnum() {
		return localnum;
	}

	public void setLocalnum(Integer localnum) {
		this.localnum = localnum;
	}

	public Integer getNolocalnum() {
		return nolocalnum;
	}

	public void setNolocalnum(Integer nolocalnum) {
		this.nolocalnum = nolocalnum;
	}

	public Integer getTransferid() {
		return transferid;
	}

	public void setTransferid(Integer transferid) {
		this.transferid = transferid;
	}

	public String getTransfername() {
		return transfername;
	}

	public void setTransfername(String transfername) {
		this.transfername = transfername == null ? null : transfername.trim();
	}

	public Date getTransfertime() {
		return transfertime;
	}

	public void setTransfertime(Date transfertime) {
		this.transfertime = transfertime;
	}

	public Integer getTransferstoreid() {
		return transferstoreid;
	}

	public void setTransferstoreid(Integer transferstoreid) {
		this.transferstoreid = transferstoreid;
	}

	public Integer getTransferareaid() {
		return transferareaid;
	}

	public void setTransferareaid(Integer transferareaid) {
		this.transferareaid = transferareaid;
	}

	public Integer getReceiveid() {
		return receiveid;
	}

	public void setReceiveid(Integer receiveid) {
		this.receiveid = receiveid;
	}

	public String getReceivename() {
		return receivename;
	}

	public void setReceivename(String receivename) {
		this.receivename = receivename == null ? null : receivename.trim();
	}

	public Date getReceivetime() {
		return receivetime;
	}

	public void setReceivetime(Date receivetime) {
		this.receivetime = receivetime;
	}

	public Integer getReceivestoreid() {
		return receivestoreid;
	}

	public void setReceivestoreid(Integer receivestoreid) {
		this.receivestoreid = receivestoreid;
	}

	public Integer getReceiveareaid() {
		return receiveareaid;
	}

	public void setReceiveareaid(Integer receiveareaid) {
		this.receiveareaid = receiveareaid;
	}

	public Integer getCorrectnum() {
		return correctnum;
	}

	public void setCorrectnum(Integer correctnum) {
		this.correctnum = correctnum;
	}

	public Integer getIncorrectnum() {
		return incorrectnum;
	}

	public void setIncorrectnum(Integer incorrectnum) {
		this.incorrectnum = incorrectnum;
	}

	public Integer getReturnnum() {
		return returnnum;
	}

	public void setReturnnum(Integer returnnum) {
		this.returnnum = returnnum;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
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