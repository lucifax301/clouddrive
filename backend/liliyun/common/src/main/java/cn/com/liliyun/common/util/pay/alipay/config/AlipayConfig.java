package cn.com.liliyun.common.util.pay.alipay.config;

/* *
 * 支付宝支付接口参数
 */

public class AlipayConfig {
	public static String partner = "2088111797257831";
	public static String service = "create_direct_pay_by_user";
	public static String _input_charset = "utf-8";
	public static String sign_type = "MD5"; 
	public static String sign = ""; 
	//public static String out_trade_no = "1120aab"; 
	//public static String subject = "asdfas"; //标题
	public static String payment_type = "1";//只支持取值为1（商品购买）。
	//public static String total_fee = "0.01";
	public static String seller_email = "sz-chelizi@qq.com";//seller_email是支付宝登录账号，格式一般是邮箱或手机号。
	
	public static String alipayUrl = "https://mapi.alipay.com/gateway.do";//支付宝网关
	//public static String notify_url = "http://218.17.39.227:9741/httpaccess/open/notify_alipay";//可空 异步返回的页面
	public static String qr_pay_mode = "4"; //可空  扫码支付方式 
	public static int qrcode_width = 200; //可空  商户自定义的二维码宽度。当qr_pay_mode=4时，该参数生效
	public static String key ="5jtf6w5qiacd9xnqxpdae95au1hlqsbx"; //MD5密钥
	
	
	
	
}

