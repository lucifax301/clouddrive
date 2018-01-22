package cn.com.liliyun.user.mapper;

import java.util.List;

import cn.com.liliyun.user.model.Dic;

public interface DicMapper {
    int deleteByPrimaryKey(Dic dic);

    int insert(Dic record);

    int insertSelective(Dic record);

    Dic selectByPrimaryKey(Dic dic);

    int updateByPrimaryKeySelective(Dic record);

    int updateByPrimaryKey(Dic record);
    
    List <Dic> selectList(Dic dic);
}