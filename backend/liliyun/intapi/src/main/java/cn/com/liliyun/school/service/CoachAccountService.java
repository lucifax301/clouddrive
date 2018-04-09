package cn.com.liliyun.school.service;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.school.model.AccountBalanceDTO;
import cn.com.liliyun.school.model.CoachAccount;
import cn.com.liliyun.school.model.Recharge;
/**
 * 
 * 驾校管理
 * @author Administrator
 *
 */
public interface CoachAccountService {
	
	//集团账号部分
	ResultBean addCoachAccount(Map map);
	
	ResultBean deleteCoachAccount(CoachAccount coachAccount);
	
	ResultBean updateCoachAccount(CoachAccount coachAccount);
	
	ResultBean getList(CoachAccount coachAccount);
	
	ResultBean getCoachAccountByid(CoachAccount coachAccount);
	
	//充值部分
	ResultBean addRecharge(Recharge recharge);
	//查询充值记录
	ResultBean selectAllRecord(Recharge recharge);
	
	ResultBean selectNotAccountCoach(Coach coach);
	
	//处理微信回调
	String wxNotify(Map<String, String> m);
	//处理微信回调
	void alipayNotify(HttpServletRequest request);
	//查询app余额
	ResultBean getBalance(AccountBalanceDTO accountBalanceDTO);

	
}
