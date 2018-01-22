package cn.com.liliyun.user.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.user.model.User;

public interface UserService {
	
	public ResultBean saveAndInitSchoolInfo(User user);
	
	public ResultBean saveUser(User user);
	
	public ResultBean checkLogin(User user);
	
	public User checkUserLogin(User user) throws Exception;
	
	public ResultBean getList(User user);
	
	public ResultBean updateUser(User user);
	
	public ResultBean importUser(User user,List<User> list);
	
	public ResultBean updatePassowrd(User user);
	
	public ResultBean deleteUser(User user);
	
	public User getUser(User user);

	//public User getUserWithRole(User user);

	public ResultBean getUserListWithRole(User user);
	
	public List<User> selectRoleUser(User user);
	
	public List<User> selectSchoolUser(User user);
	
	public List<User> selectMutiUser(List<Integer> ids);
	
	public ResultBean getListsome(User user);
	
	public ResultBean changepwd(User newone, User user);
	
	
}