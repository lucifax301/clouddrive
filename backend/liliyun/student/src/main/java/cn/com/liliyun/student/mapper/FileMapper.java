package cn.com.liliyun.student.mapper;

import java.util.List;

import cn.com.liliyun.student.model.File;

public interface FileMapper {
    int deleteByPrimaryKey(String tableid);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(String tableid);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
    
    List <File> selectList(File file);
}