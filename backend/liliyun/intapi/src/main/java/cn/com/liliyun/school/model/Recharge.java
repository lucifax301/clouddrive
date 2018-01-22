package cn.com.liliyun.school.model;

import java.io.Serializable;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 
 * 充值记录
 * @author chelizi
 *
 */
public class Recharge extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String serialNumber; //流水号
	private String rtime; //充值时间
	private String rtype; //充值方式 1微信 2支付宝
	private String amount; //充值金额
	private String balance;//余额
	private String rstatus;//充值状态  1成功 2 失败
	private String message;//充值信息
	private String username;//驾校注册账号
	
	//传值用
	private String attach;//数据包
	private String notifyUrl;//支付完成后回调url
	private String stime;//开始时间 查询
	private String etime;//结束时间 查询
	
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getRtime() {
		return rtime;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	public String getRtype() {
		return rtype;
	}
	public void setRtype(String rtype) {
		this.rtype = rtype;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getRstatus() {
		return rstatus;
	}
	public void setRstatus(String rstatus) {
		this.rstatus = rstatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
