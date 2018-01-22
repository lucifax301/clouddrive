package cn.com.liliyun.school.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.school.model.CoachAccount;
import cn.com.liliyun.school.model.Recharge;

public interface CoachAccountMapper {

	int insertAccount(Map map);

    int updateAccount(CoachAccount coachAccount);
    
    int deleteAccount(CoachAccount coachAccount);
    
    List <CoachAccount> selectAllAccount(CoachAccount coachAccount);
    
    int insertRecharge(Recharge recharge);
    
    List<Recharge> selectAllRecord(Recharge recharge);
    
    //没有分配交易账号的教练
    List<Coach> selectNotAccountCoach(Coach coach);
}