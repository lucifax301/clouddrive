package cn.com.liliyun.user.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.user.model.User;

public interface UserService {
	
	ResultBean saveAndInitSchoolInfo(User user);
	
	ResultBean saveUser(User user);
	
	ResultBean checkLogin(User user);
	
	User checkUserLogin(User user) throws Exception;
	
	ResultBean getList(User user);
	
	ResultBean updateUser(User user);
	
	ResultBean importUser(User user,List<User> list);
	
	ResultBean updatePassowrd(User user);
	
	ResultBean deleteUser(User user);
	
	User getUser(User user);

	//User getUserWithRole(User user);

	ResultBean getUserListWithRole(User user);
	
	List<User> selectRoleUser(User user);
	
	List<User> selectSchoolUser(User user);
	
	List<User> selectMutiUser(List<Integer> ids);
	
	ResultBean getListsome(User user);
	
	ResultBean changepwd(User newone, User user);
	
	
}