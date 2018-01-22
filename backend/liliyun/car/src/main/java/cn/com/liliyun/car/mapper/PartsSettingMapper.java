package cn.com.liliyun.car.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.car.model.PartsSetting;

public interface PartsSettingMapper {
    int deleteByPrimaryKey(PartsSetting partsSetting);

    int insert(PartsSetting partsSetting);

    int insertSelective(PartsSetting partsSetting);

    PartsSetting selectByPrimaryKey(PartsSetting partsSetting);

    int updateByPrimaryKeySelective(PartsSetting partsSetting);

    int updateByPrimaryKey(PartsSetting partsSetting);
    
    List<PartsSetting> selectAll(PartsSetting partsSetting);
    
    int updateByPrimaryKeySelectiveBatch(Map<String, Object> map);
    
    int batchInsertSelective(Map<String, Object> map);
}