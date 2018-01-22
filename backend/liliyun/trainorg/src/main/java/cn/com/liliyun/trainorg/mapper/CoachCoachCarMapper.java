package cn.com.liliyun.trainorg.mapper;

import java.util.List;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.trainorg.model.CoachCoachCar;

public interface CoachCoachCarMapper {

	CoachCoachCar selectOne(CoachCoachCar coachCoachCar);

	List <CoachCoachCar> selectList(CoachCoachCar coachCoachCar);
	
	int insert(CoachCoachCar record);

    int insertSelective(CoachCoachCar record);
    
    int deleteSelective(CoachCoachCar record);
    
    List <Coach> selectCoachByCarid(CoachCoachCar coachCoachCar);
}