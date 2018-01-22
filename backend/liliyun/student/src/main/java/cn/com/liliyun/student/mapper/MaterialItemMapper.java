package cn.com.liliyun.student.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.student.model.MaterialItem;

public interface MaterialItemMapper {
    int deleteByPrimaryKey(MaterialItem materialItem);

    int insert(MaterialItem record);

    int insertSelective(MaterialItem record);

    MaterialItem selectByPrimaryKey(MaterialItem materialItem);

    int updateByPrimaryKeySelective(MaterialItem record);

    int updateByPrimaryKey(MaterialItem record);
    
    List <MaterialItem> selectList(MaterialItem materialItem);
    
    int insertBatch(Map <String,Object> params);
}