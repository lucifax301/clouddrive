package cn.com.liliyun.student.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.student.model.FileItem;

public interface FileItemMapper {

	int deleteByPrimaryKey(String tableid);

    int insert(FileItem record);

    int insertSelective(FileItem record);

    FileItem selectByPrimaryKey(String tableid);

    int updateByPrimaryKeySelective(FileItem record);

    int updateByPrimaryKey(FileItem record);
    
    List <FileItem> selectList(FileItem record);
    
    int insertBatch(Map <String,Object> params);
    
    String selectMaxFilenum(FileItem fileItem);
}