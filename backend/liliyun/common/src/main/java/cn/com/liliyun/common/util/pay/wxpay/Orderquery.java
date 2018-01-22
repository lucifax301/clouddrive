package cn.com.liliyun.common.util.pay.wxpay;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;


public class Orderquery {
	private static String url = "https://api.mch.weixin.qq.com/pay/downloadbill";
	private static String appid = PayConfigUtil.APP_ID;  // appid  
	private static String mch_id = PayConfigUtil.MCH_ID; // 商业号    
	private static String nonce_str = ""; //随机数  
	private static String sign = "";
	private static String bill_date = "20161025";//下载日期  格式：20140603
	private static String bill_type = "ALL";
	
	public void fun(){
		 SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();
		 packageParams.put("appid", Orderquery.appid);
		 packageParams.put("mch_id", Orderquery.mch_id);
		 nonce_str = UUID.randomUUID().toString().trim().replaceAll("-", "");
		 packageParams.put("nonce_str", Orderquery.nonce_str);
		 packageParams.put("bill_date", Orderquery.bill_date);
		 packageParams.put("bill_type", Orderquery.bill_type);
		 String sign = PayCommonUtil.createSign("UTF-8", packageParams,PayConfigUtil.API_KEY);  
		 packageParams.put("sign", sign);
		 
		 try {
			String requestXML = PayCommonUtil.getRequestXml(packageParams);
		    //System.out.println(requestXML);
		    String resXml = HttpUtil.postData(PayConfigUtil.orderQuery_url, requestXML); 
		    
		    ByteArrayInputStream inputStringStream = new ByteArrayInputStream(resXml.getBytes());
		    BufferedReader br = new BufferedReader(new InputStreamReader(inputStringStream));
		    
		    String lineData = null;  
	        while (true) {  
	        	lineData = br.readLine();  
	            if(lineData!=null){
	            	String[] title = lineData.split(",");
	            	List<String> list = Arrays.asList(title);
	                System.out.println(lineData+"==="); 
	            }  
	            	 
	            else{
	            	break;  
	            }  
	                
	        }  
	          
	        inputStringStream.close();
		    
		    //System.out.println(resXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 
	}
	
	public void fun1(){
		
		        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");  
		        System.out.println(uuid);
	}
}
