package cn.com.liliyun.coach.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.coach.model.CoachLoadStudentInfo;
import cn.com.liliyun.coach.model.CoachStudentDTO;
import cn.com.liliyun.common.model.UploadImage;
import cn.com.liliyun.trainorg.model.CoachCoachCar;

public interface CoachMapper {
    int deleteByPrimaryKey(Coach record);

    int insert(Coach record);

    int insertSelective(Coach record);

    Coach selectByPrimaryKey(Coach record);

    int updateByPrimaryKeySelective(Coach record);
    
    int updateTeachState(Coach record);
    
    int updateCarno(Coach record);
    
    int updateHeadcoach(Map data);
    
    int getCoachcarcount(Map data);
    
    int batchUpdateTeachType(Map data);
    
    int batchUpdateStudentLoad(Map data);
    
    int incrementCoachStudent(Coach record);
    
    int decrementCoachStudent(Coach record);
    
    int updateEmploystatus(Coach record);

    int updateByPrimaryKey(Coach record);
    
    List<Coach> selectByCondition(Coach coach);
    
    List<Coach> selectNoassign(Coach coach);
    
    int getCount(Coach coach);
    
    List selectByterm(Coach coach); 
    
    //插入图片
  	public int saveImage(UploadImage uploadImage);
  	//一辆车绑定的教练列表
  	List <CoachCoachCar> selectCoachListOfcar(Coach coach);
  	
  	//查询教练-学员绑定列表
  	List<CoachStudentDTO> getNotStuListOfCoach(CoachStudentDTO coachStudentDTO);
  	List<CoachStudentDTO> getStuListOfCoach(CoachStudentDTO coachStudentDTO);
  	
  	public List<CoachLoadStudentInfo> getCoachLoadStudentInfo(CoachLoadStudentInfo info);
}