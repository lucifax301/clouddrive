package cn.com.liliyun.common.trace;

import cn.com.liliyun.common.model.RequestContext;

public class Trace {

	private static ThreadLocal<InnerTrace> current = new ThreadLocal<InnerTrace>();
	
	public static InnerTrace createTrace(){
		long start = System.nanoTime();
		InnerTrace innerTrace = current.get();
		if(innerTrace==null){
			innerTrace = new InnerTrace();
			current.set(innerTrace);
		}else{
			InnerTrace parent = innerTrace;
			innerTrace = new InnerTrace();
			innerTrace.setParent(parent);
			current.set(innerTrace);
		}
		String traceId = RequestContext.get().getTraceId();
		innerTrace.setTraceId(traceId);
		innerTrace.setNanoStartTime(start);
		return innerTrace;
	}
	
	public static InnerTrace endTrace(){
		InnerTrace innerTrace = current.get();
		if(innerTrace!=null){
			InnerTrace parent = innerTrace.getParent();
			current.set(parent);
			innerTrace.setNanoEndTime(System.nanoTime());
			innerTrace.setDuration(innerTrace.getNanoStartTime()-innerTrace.getNanoEndTime());
			innerTrace.close();
			return innerTrace;
		}
		return null;
	}
}
