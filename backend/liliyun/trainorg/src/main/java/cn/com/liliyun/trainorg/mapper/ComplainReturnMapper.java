package cn.com.liliyun.trainorg.mapper;

import java.util.List;

import cn.com.liliyun.trainorg.model.ComplainReturn;

public interface ComplainReturnMapper {
    int deleteByPrimaryKey(ComplainReturn record);

    int insert(ComplainReturn record);

    int insertSelective(ComplainReturn record);

    ComplainReturn selectByPrimaryKey(ComplainReturn record);

    int updateByPrimaryKeySelective(ComplainReturn record);

    int updateByPrimaryKey(ComplainReturn record);
    
    List<ComplainReturn> selectByCondition(ComplainReturn complainReturn);
}