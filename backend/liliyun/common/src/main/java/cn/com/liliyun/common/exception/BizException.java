package cn.com.liliyun.common.exception;

public class BizException extends RuntimeException {

	public BizException() {
		super();
	}

	public BizException(String arg0) {
		super(arg0);
	}
	
	public BizException(String arg0,Throwable t){
		super(arg0,t);
	}
	
	public BizException(Throwable t){
		super(t);
	}

	private static final long serialVersionUID = 1L;

}
