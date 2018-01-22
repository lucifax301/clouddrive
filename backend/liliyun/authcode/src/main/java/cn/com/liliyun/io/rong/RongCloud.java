/**
 * 融云 Server API java 客户端
 * create by kitName
 * create datetime : 2016-10-20 
 * 
 * v2.0.1
 */
package cn.com.liliyun.io.rong;

import java.util.concurrent.ConcurrentHashMap;

import cn.com.liliyun.io.rong.methods.Chatroom;
import cn.com.liliyun.io.rong.methods.Group;
import cn.com.liliyun.io.rong.methods.Message;
import cn.com.liliyun.io.rong.methods.Push;
import cn.com.liliyun.io.rong.methods.SMS;
import cn.com.liliyun.io.rong.methods.User;
import cn.com.liliyun.io.rong.methods.Wordfilter;

public class RongCloud {

	private static ConcurrentHashMap<String, RongCloud> rongCloud = new ConcurrentHashMap<String,RongCloud>();
	
	public User user;
	public Message message;
	public Wordfilter wordfilter;
	public Group group;
	public Chatroom chatroom;
	public Push push;
	public SMS sms;

	private RongCloud(String appKey, String appSecret) {
		user = new User(appKey, appSecret);
		message = new Message(appKey, appSecret);
		wordfilter = new Wordfilter(appKey, appSecret);
		group = new Group(appKey, appSecret);
		chatroom = new Chatroom(appKey, appSecret);
		push = new Push(appKey, appSecret);
		sms = new SMS(appKey, appSecret);

	}

	public static RongCloud getInstance(String appKey, String appSecret) {
		if (null == rongCloud.get(appKey)) {
			rongCloud.putIfAbsent(appKey, new RongCloud(appKey, appSecret));
		}
		return rongCloud.get(appKey);
	}
	 
}