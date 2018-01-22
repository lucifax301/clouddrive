package cn.com.liliyun.school.service;


import java.io.InputStream;
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
	public ResultBean addCoachAccount(Map map);
	
	public ResultBean deleteCoachAccount(CoachAccount coachAccount);
	
	public ResultBean updateCoachAccount(CoachAccount coachAccount);
	
	public ResultBean getList(CoachAccount coachAccount);
	
	public ResultBean getCoachAccountByid(CoachAccount coachAccount);
	
	//充值部分
	public ResultBean addRecharge(Recharge recharge);
	//查询充值记录
	public ResultBean selectAllRecord(Recharge recharge);
	
	public ResultBean selectNotAccountCoach(Coach coach);
	
	//处理微信回调
	public String wxNotify(Map<String, String> m);
	//处理微信回调
	public void alipayNotify(HttpServletRequest request);
	//查询app余额
	public ResultBean getBalance(AccountBalanceDTO accountBalanceDTO);

	
}
