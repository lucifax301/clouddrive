package cn.com.liliyun.school.model;

import java.io.Serializable;
import java.sql.Timestamp;

import cn.com.liliyun.common.model.BaseModel;

/**
 * 
 * 驾校管理：交易账号
 * @author Administrator
 *
 */
public class AccountBalanceDTO extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId; //驾校id
	private String userType; //驾校为5
	private String remark;
	private String orderId; //订单号
	private String timestamp;
	private String price; //价格 单位分
	private String payWay; //支付方式 1 微信 2支付宝
	//app返回
	private String v;
	private String data;
	
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTimestamp() {
		return timestamp = String.valueOf(System.currentTimeMillis()/1000L);
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	
	
	
	
}
