package cn.com.liliyun.trainorg.model;

public class ScopeProxy  implements  java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer scopeId;
	private Integer proxyId;

	
	public Integer getScopeId() {
		return scopeId;
	}

	public void setScopeId(Integer scopeId) {
		this.scopeId = scopeId;
	}

	public Integer getProxyId() {
		return proxyId;
	}

	public void setProxyId(Integer proxyId) {
		this.proxyId = proxyId;
	}

}
