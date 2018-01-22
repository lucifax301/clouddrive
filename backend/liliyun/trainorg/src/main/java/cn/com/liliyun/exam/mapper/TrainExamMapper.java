package cn.com.liliyun.exam.mapper;

import java.util.List;

import cn.com.liliyun.trainorg.model.TrainExam;

public interface TrainExamMapper {

	int deleteByPrimaryKey(TrainExam record);

	int insert(TrainExam record);

	int insertSelective(TrainExam record);

	TrainExam selectByPrimaryKey(TrainExam record);

	int updateByPrimaryKeySelective(TrainExam record);

	int updateByPrimaryKey(TrainExam record);

    List <TrainExam> selectList(TrainExam record);
    
    //List <TrainExam> selectCoachStuList(TrainExam record);
    
}