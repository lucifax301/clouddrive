package cn.com.liliyun.common.model;

import java.io.Serializable;

public class WdInvokeContext implements Serializable {

	private RequestContext requestContext;

	public RequestContext getRequestContext() {
		return requestContext;
	}

	public void setRequestContext(RequestContext requestContext) {
		this.requestContext = requestContext;
	}
	
	
}
