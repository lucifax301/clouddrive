package cn.com.liliyun.io.rong.models;

import java.util.List;

import cn.com.liliyun.io.rong.util.GsonUtil;

/**
 *  chatroomQuery 返回结果
 */
public class ChatroomQueryReslut {
	// 返回码，200 为正常。
	Integer code;
	// 聊天室信息数组。
	List<ChatRoom> chatRooms;
	// 错误信息。
	String errorMessage;
	
	public ChatroomQueryReslut(Integer code, List<ChatRoom> chatRooms, String errorMessage) {
		this.code = code;
		this.chatRooms = chatRooms;
		this.errorMessage = errorMessage;
	}
	
	/**
	 * 设置code
	 *
	 */	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	/**
	 * 获取code
	 *
	 * @return Integer
	 */
	public Integer getCode() {
		return code;
	}
	
	/**
	 * 设置chatRooms
	 *
	 */	
	public void setChatRooms(List<ChatRoom> chatRooms) {
		this.chatRooms = chatRooms;
	}
	
	/**
	 * 获取chatRooms
	 *
	 * @return List<ChatRoom>
	 */
	public List<ChatRoom> getChatRooms() {
		return chatRooms;
	}
	
	/**
	 * 设置errorMessage
	 *
	 */	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * 获取errorMessage
	 *
	 * @return String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	@Override
	public String toString() {
		return GsonUtil.toJson(this, ChatroomQueryReslut.class);
	}
}
