package cn.com.liliyun.student.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.student.model.StudentStatusLog;

public interface StudentStatusLogMapper {

	int deleteByPrimaryKey(StudentStatusLog record);

    int insert(StudentStatusLog record);

    int insertSelective(StudentStatusLog record);

    StudentStatusLog selectByPrimaryKey(StudentStatusLog record);

    int updateByPrimaryKeySelective(StudentStatusLog record);

    int updateByPrimaryKey(StudentStatusLog record);
    
    List <StudentStatusLog> selectList(StudentStatusLog record);
    
    int insertBatch(Map <String,Object> params);
}