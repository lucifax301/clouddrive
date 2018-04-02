package cn.com.liliyun.common.trace;

public class InnerTrace {

	private String traceId;
	
	private InnerTrace parent;
	
	private long nanoStartTime;
	
	private long nanoEndTime;
	
	private long duration;
	
	private String methodName;
	
	

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getNanoStartTime() {
		return nanoStartTime;
	}

	public void setNanoStartTime(long nanoStartTime) {
		this.nanoStartTime = nanoStartTime;
	}

	public long getNanoEndTime() {
		return nanoEndTime;
	}

	public void setNanoEndTime(long nanoEndTime) {
		this.nanoEndTime = nanoEndTime;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public InnerTrace getParent() {
		return parent;
	}

	public void setParent(InnerTrace parent) {
		this.parent = parent;
	}
	
	public void close(){
		/**
		 * send to queue
		 */
	}
}
