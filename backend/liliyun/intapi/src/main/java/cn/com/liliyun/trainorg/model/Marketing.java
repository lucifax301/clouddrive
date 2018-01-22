package cn.com.liliyun.trainorg.model;

import java.io.Serializable;
import java.sql.Timestamp;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 营销管理
 * 
 */
public class Marketing extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private long marketid;
	private String name;
	private String phone;
	private String traintype;
	private String address;
	private String source;//来源
	private String remarks;//备注
	private String trackePersonnel;//跟踪人员
	private String progress;//进度
	private int number;//回访次数
    
	private int operate;
	private int errorcode;
	private String message;
	private Timestamp  utime;
	private Timestamp ltime;
	private long cuid ;
	private long muid  ;
	private Timestamp ctime;
	private Timestamp mtime;
	
	public String getTrackePersonnel() {
		return trackePersonnel;
	}
	public void setTrackePersonnel(String trackePersonnel) {
		this.trackePersonnel = trackePersonnel;
	}
	public long getMarketid() {
		return marketid;
	}
	public void setMarketid(long marketid) {
		this.marketid = marketid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTraintype() {
		return traintype;
	}
	public void setTraintype(String traintype) {
		this.traintype = traintype;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getOperate() {
		return operate;
	}
	public void setOperate(int operate) {
		this.operate = operate;
	}
	public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getUtime() {
		return utime;
	}
	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}
	public Timestamp getLtime() {
		return ltime;
	}
	public void setLtime(Timestamp ltime) {
		this.ltime = ltime;
	}
	public long getCuid() {
		return cuid;
	}
	public void setCuid(long cuid) {
		this.cuid = cuid;
	}
	public long getMuid() {
		return muid;
	}
	public void setMuid(long muid) {
		this.muid = muid;
	}
	public Timestamp getCtime() {
		return ctime;
	}
	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
	public Timestamp getMtime() {
		return mtime;
	}
	public void setMtime(Timestamp mtime) {
		this.mtime = mtime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
