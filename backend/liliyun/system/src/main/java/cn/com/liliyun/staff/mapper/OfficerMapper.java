package cn.com.liliyun.staff.mapper;

import java.util.List;

import cn.com.liliyun.staff.model.Officer;

public interface OfficerMapper {
    
    int insert(Officer officer);
   
    Officer selectByPrimaryKey(Officer officer);
    
    Officer selectByStaffid(Officer officer);
    

    int update(Officer officer);
    
    int delete(Officer officer);
    
    
    List<Officer> selectByCondition(Officer officer);
    
   
}