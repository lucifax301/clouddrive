package cn.com.liliyun.common.util.pay.wxpay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


//微信扫码支付
public class Weixin_pay {
	/*public void fun(){
		Weixin_pay wp = new Weixin_pay();
		System.out.println(wp.getUrl());
	} */
	
	
	public String getUrl(String body,String attach,String order_price,String notifyUrl){
	 // 账号信息  
    String appid = PayConfigUtil.APP_ID;  // appid  
    String mch_id = PayConfigUtil.MCH_ID; // 商业号  
    String key = PayConfigUtil.API_KEY; // key  

    String currTime = PayCommonUtil.getCurrTime();  
    String strTime = currTime.substring(8, currTime.length());  
    String strRandom = PayCommonUtil.buildRandom(4) + "";  
    String nonce_str = strTime + strRandom;  
    
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    String out_trade_no = sdf.format(date); // 订单号  
      
    // 获取发起电脑 ip  
    String spbill_create_ip = PayConfigUtil.spbill_create_ip;  
    // 回调接口   
    //String notify_url = PayConfigUtil.NOTIFY_URL;  
    String trade_type = "NATIVE";
    try {
	    SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();
	    packageParams.put("appid", appid);
	    packageParams.put("mch_id", mch_id);
	    packageParams.put("nonce_str", nonce_str);
	    packageParams.put("body", body);
	    packageParams.put("attach", attach);
	    packageParams.put("out_trade_no", out_trade_no);
	    packageParams.put("total_fee", order_price);
	    packageParams.put("spbill_create_ip", spbill_create_ip);
	    packageParams.put("notify_url", notifyUrl);
	    packageParams.put("trade_type", trade_type); 
	    String sign = PayCommonUtil.createSign("UTF-8", packageParams,key);  
	    packageParams.put("sign", sign);
	    String requestXML = PayCommonUtil.getRequestXml(packageParams);
	    System.out.println(requestXML);
	    String resXml = HttpUtil.postData(PayConfigUtil.PAY_URL, requestXML); 
	    System.out.println(resXml);
	    Map map = XMLUtil.doXMLParse(resXml);  
	    String urlCode = (String) map.get("code_url");
	    return urlCode; 
    } catch (Exception e) {
	}
    return null;
	}
}
