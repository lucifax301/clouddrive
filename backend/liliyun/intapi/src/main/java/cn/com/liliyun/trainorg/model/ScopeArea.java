package cn.com.liliyun.trainorg.model;

import cn.com.liliyun.common.model.BaseModel;
import java.io.Serializable;

public class ScopeArea extends BaseModel implements Serializable {
	private Integer scopeId;

	private String scopeName;

	private Integer scopeStatus;

	private String scopeDescription;

	private Integer proxyCount;
	
	private static final long serialVersionUID = 1L;

	public Integer getScopeId() {
		return scopeId;
	}

	public void setScopeId(Integer scopeId) {
		this.scopeId = scopeId ;
	}

	public String getScopeName() {
		return scopeName;
	}

	public void setScopeName(String scopeName) {
		this.scopeName = scopeName == null ? null : scopeName.trim();
	}

	public Integer getScopeStatus() {
		return scopeStatus;
	}

	public void setScopeStatus(Integer scopeStatus) {
		this.scopeStatus = scopeStatus;
	}

	public String getScopeDescription() {
		return scopeDescription;
	}

	public void setScopeDescription(String scopeDescription) {
		this.scopeDescription = scopeDescription == null ? null : scopeDescription.trim();
	}

	public Integer getProxyCount() {
		return proxyCount;
	}

	public void setProxyCount(Integer proxyCount) {
		this.proxyCount = proxyCount;
	}
	
	
}