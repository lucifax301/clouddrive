package cn.com.liliyun.user.mapper;

import cn.com.liliyun.user.model.DbConfig;

public interface DbConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DbConfig record);

    int insertSelective(DbConfig record);

    DbConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DbConfig record);

    int updateByPrimaryKey(DbConfig record);
    
    DbConfig selectDB(DbConfig record);
    
    int selectDBCount(DbConfig record);
}