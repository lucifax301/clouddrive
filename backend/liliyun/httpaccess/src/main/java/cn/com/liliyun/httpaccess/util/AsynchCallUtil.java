package cn.com.liliyun.httpaccess.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import cn.com.liliyun.common.util.SMSUtil;

public class AsynchCallUtil {

	private static Logger logger = Logger.getLogger(AsynchCallUtil.class);
	
	private static Executor executor = Executors.newCachedThreadPool();
	
	public static void sendSMS(final String phoneNum, final String code, final String [] datas) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				logger.info("发送手机短息:" + phoneNum);
				SMSUtil.send(phoneNum, code, datas);
			}
		});
	}
}
