package cn.com.liliyun.student.mapper;

import java.util.List;

import cn.com.liliyun.student.model.Transfertable;

public interface TransfertableMapper {

	int deleteByPrimaryKey(Transfertable record);

    int insert(Transfertable record);

    int insertSelective(Transfertable record);

    Transfertable selectByPrimaryKey(Transfertable record);

    int updateByPrimaryKeySelective(Transfertable record);

    int updateByPrimaryKey(Transfertable record);
    
    List<Transfertable> selectList(Transfertable record);
    
    int updateAreaReturnCount(Transfertable transfertable);
    
    int updateStoreReturnCount(Transfertable transfertable);
}