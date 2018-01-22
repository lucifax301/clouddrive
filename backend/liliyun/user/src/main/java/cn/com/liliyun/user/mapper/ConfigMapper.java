package cn.com.liliyun.user.mapper;


import java.util.List;

import cn.com.liliyun.user.model.Config;

public interface ConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);
    
    List<Config> getConfigList(Config config);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}