package cn.com.liliyun.exam.mapper;



import cn.com.liliyun.trainorg.model.ResultExam;

import java.util.List;

public interface ResultExamMapper {

	int deleteByPrimaryKey(ResultExam record);

	int insert(ResultExam record);

	int insertSelective(ResultExam record);

	ResultExam selectByPrimaryKey(ResultExam record);

	int updateByPrimaryKeySelective(ResultExam record);

	int updateByPrimaryKey(ResultExam record);

    List <ResultExam> selectList(ResultExam record);
    
}