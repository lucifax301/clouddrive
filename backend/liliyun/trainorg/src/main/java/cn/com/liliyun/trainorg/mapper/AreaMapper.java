package cn.com.liliyun.trainorg.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.trainorg.model.Area;

public interface AreaMapper {
    int deleteByPrimaryKey(Area area);

    int insert(Area area);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Area area);

    int updateByPrimaryKeySelective(Area area);

    int updateByPrimaryKey(Area area);
    
    List <Area> selectList(Area area);
    
    @MapKey("id")
    Map<Integer, MapDTO> getMap(Area area);
}