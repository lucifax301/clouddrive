package cn.com.liliyun.staff.mapper;

import java.util.List;

import cn.com.liliyun.staff.model.Assessor;

public interface AssessorMapper {
    
    int insert(Assessor assessor);
   
    Assessor selectByPrimaryKey(Assessor assessor);
    
    Assessor selectByStaffid(Assessor assessor);

    int update(Assessor assessor);
    
    int delete(Assessor assessor);
    
    
    List<Assessor> selectByCondition(Assessor assessor);
    
   
}