package cn.com.liliyun.common.model;

import java.io.Serializable;

import cn.com.liliyun.common.util.HttpConstant;

/**
 * 业务与接入层之间传递的实体
 * 返回给前端信息的封装
 */
public class ResultBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Object result;
	private int code;
	private String msg;
	
	private Object extend;
	
	private String stack;
	
	
	
	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public Object getExtend() {
		return extend;
	}

	public void setExtend(Object extend) {
		this.extend = extend;
	}

	public int getCode() {
		return code;
	}

	public ResultBean(){
		this.code = HttpConstant.SUCCESS_CODE;
		this.msg = HttpConstant.SUCCESS_MSG;
	}
	
	public ResultBean(Object result){
		this();
		this.result=result;
	}
	
	public ResultBean(String msg){
		this.code = HttpConstant.ERROR_CODE;
		this.msg = msg;
	}

	public ResultBean(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
