package cn.com.liliyun.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.liliyun.student.model.TransferStudent;

public interface TransferStudentMapper {
    int deleteByPrimaryKey(TransferStudent record);

    int insert(TransferStudent record);

    int insertSelective(TransferStudent record);

    TransferStudent selectByPrimaryKey(TransferStudent record);

    int updateByPrimaryKeySelective(TransferStudent record);

    int updateByPrimaryKeyWithBLOBs(TransferStudent record);

    int updateByPrimaryKey(TransferStudent record);
    
    List<TransferStudent> selectByModel(TransferStudent transferStudent);
    
    /*
     * 来源属于当前用户或者目的属于当前用户，target和from必填
     */
    List<TransferStudent> selectTransferList(TransferStudent transferStudent);
}