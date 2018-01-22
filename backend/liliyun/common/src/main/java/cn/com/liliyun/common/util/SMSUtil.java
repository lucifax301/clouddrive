package cn.com.liliyun.common.util;

import java.util.HashMap;

import com.cloopen.rest.sdk.CCPRestSDK;

/**
 * 短信发送
 * 
 * @author yu.y
 *
 */
public class SMSUtil {
	
	private static final String App_ID = "8aaf0708582eefe901584304fcd30e68"; // 应用ID

	private static final String SERVER_IP = "app.cloopen.com"; // 服务器IP

	private static final String SERVER_PORT = "8883"; // 服务器端口

	private static final String ACCOUNT_SID = "8a48b551505b4af001505fe1a2ca0ce8";
	
	private static final String ACCOUNT_TOKEN = "bdfb63826c2740b68a780c8c35441007"; // 开发者账号
	
	/**
	 * 联容云通讯
	 * 
	 * @param phoneNum
	 *            手机号码，多个的时候中间用逗号隔开 每批发送的手机号数量不得超过100个  http://www.yuntongxun.com/doc/rest/sms/3_2_2_2.html
	 * @param templateId
	 *            模版id，没有模版则填写1
	 * @param datas
	 *            需要替换的内容
	 */
	public static void send(String phoneNum, String code, String[] datas) {
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init(SERVER_IP, SERVER_PORT); // 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(ACCOUNT_SID, ACCOUNT_TOKEN); // 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(App_ID); // 初始化应用ID
		result = restAPI.sendTemplateSMS(phoneNum, code , datas);
		System.out.println("sendTemplateSMS result=" + result);
	}
	
	public static void main(String[] args) {
		String [] s = {"aa","aa"};
		SMSUtil.send("18628308513", "131296", s);
	}

}
