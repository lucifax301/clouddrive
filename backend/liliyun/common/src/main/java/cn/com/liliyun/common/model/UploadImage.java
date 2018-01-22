package cn.com.liliyun.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UploadImage extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String module;
	private long businessId;
	private String imgeType;
	private String imgeKey;
	private String imgeSize;
	private Timestamp ctime;
	private Timestamp mtime;
	private Timestamp utime;
	private String ids;//id集合
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public String getImgeType() {
		return imgeType;
	}
	public void setImgeType(String imgeType) {
		this.imgeType = imgeType;
	}
	public String getImgeKey() {
		return imgeKey;
	}
	public void setImgeKey(String imgeKey) {
		this.imgeKey = imgeKey;
	}
	public String getImgeSize() {
		return imgeSize;
	}
	public void setImgeSize(String imgeSize) {
		this.imgeSize = imgeSize;
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
	public Timestamp getUtime() {
		return utime;
	}
	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}
	
	
}
