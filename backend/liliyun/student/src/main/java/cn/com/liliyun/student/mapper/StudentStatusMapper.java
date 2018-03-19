package cn.com.liliyun.student.mapper;

import cn.com.liliyun.student.model.StudentStatus;

import java.util.List;

public interface StudentStatusMapper {
    int deleteByPrimaryKey(StudentStatus studentStatus);

    int insert(StudentStatus studentStatus);

    int insertSelective(StudentStatus studentStatus);

    StudentStatus selectByPrimaryKey(StudentStatus studentStatus);

    int updateByPrimaryKeySelective(StudentStatus studentStatus);

    int updateByPrimaryKey(StudentStatus studentStatus);
    
    List<StudentStatus> selectList(StudentStatus studentStatus);
    
    int getCount(StudentStatus studentStatus);
}