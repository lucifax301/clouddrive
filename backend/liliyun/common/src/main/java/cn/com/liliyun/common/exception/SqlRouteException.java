package cn.com.liliyun.common.exception;

public class SqlRouteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SqlRouteException() {
		super("dblink error!!!");
	}
	
	public SqlRouteException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
