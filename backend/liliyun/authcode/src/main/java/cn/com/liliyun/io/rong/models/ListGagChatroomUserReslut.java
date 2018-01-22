package cn.com.liliyun.io.rong.models;

import java.util.List;

import cn.com.liliyun.io.rong.util.GsonUtil;

/**
 * listGagChatroomUser返回结果
 */
public class ListGagChatroomUserReslut {
	// 返回码，200 为正常。
	Integer code;
	// 聊天室被禁言用户列表。
	List<GagChatRoomUser> users;
	// 错误信息。
	String errorMessage;
	
	public ListGagChatroomUserReslut(Integer code, List<GagChatRoomUser> users, String errorMessage) {
		this.code = code;
		this.users = users;
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
	 * 设置users
	 *
	 */	
	public void setUsers(List<GagChatRoomUser> users) {
		this.users = users;
	}
	
	/**
	 * 获取users
	 *
	 * @return List<GagChatRoomUser>
	 */
	public List<GagChatRoomUser> getUsers() {
		return users;
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
		return GsonUtil.toJson(this, ListGagChatroomUserReslut.class);
	}
}
