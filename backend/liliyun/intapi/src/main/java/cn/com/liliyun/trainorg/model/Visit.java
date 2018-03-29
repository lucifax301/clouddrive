package cn.com.liliyun.trainorg.model;

import java.io.Serializable;
import java.sql.Timestamp;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 营销管理
 * 
 */
public class Visit extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private long visitid;
	private long marketid;
	private String content;
	private String visitPersonnel;//回访人员
	private String operatorPersonnel;//操作人员
	
	private int operate;
	private int errorcode;
	private String message;
	private Timestamp  utime;
	private Timestamp ltime;
	
	private Timestamp ctime;
	private Timestamp mtime;
	
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
	public long getVisitid() {
		return visitid;
	}
	public void setVisitid(long visitid) {
		this.visitid = visitid;
	}
	public long getMarketid() {
		return marketid;
	}
	public void setMarketid(long marketid) {
		this.marketid = marketid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getVisitPersonnel() {
		return visitPersonnel;
	}
	public void setVisitPersonnel(String visitPersonnel) {
		this.visitPersonnel = visitPersonnel;
	}
	public String getOperatorPersonnel() {
		return operatorPersonnel;
	}
	public void setOperatorPersonnel(String operatorPersonnel) {
		this.operatorPersonnel = operatorPersonnel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
