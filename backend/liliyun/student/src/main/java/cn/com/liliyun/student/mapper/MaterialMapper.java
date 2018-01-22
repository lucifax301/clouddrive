package cn.com.liliyun.student.mapper;

import java.util.List;

import cn.com.liliyun.student.model.Material;

public interface MaterialMapper {

	int deleteByPrimaryKey(Material material);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(Material material);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);
    
    List <Material> selectList(Material material);
    
}