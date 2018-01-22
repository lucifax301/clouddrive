package cn.com.liliyun.common.util.pay.wxpay;

public class PayConfigUtil {
	//统一下单
	public static String APP_ID = "wxd183626a0a4188f9";
	public static String MCH_ID = "1381390202";
	public static String API_KEY = "clz11111111111111111111111111111";//商户密钥
	public static String PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static String spbill_create_ip = "192.168.0.1"; //218.17.39.228  客户端ip
	//public static String NOTIFY_URL = "http://218.17.39.227:9741/httpaccess/open/notify_wx";
	
	
	//下载订单
	public static String orderQuery_url = "https://api.mch.weixin.qq.com/pay/downloadbill";
}
