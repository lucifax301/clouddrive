package cn.com.liliyun.coach.mapper;

import java.util.List;

import cn.com.liliyun.coach.model.ClassManage;

public interface ClassManageMapper {
    int deleteByPrimaryKey(ClassManage record);

    int insert(ClassManage record);

    int insertSelective(ClassManage record);

    ClassManage selectByPrimaryKey(ClassManage record);

    int updateByPrimaryKeySelective(ClassManage record);

    int updateByPrimaryKey(ClassManage record);
    
    List<ClassManage> selectByCondition(ClassManage record);
}