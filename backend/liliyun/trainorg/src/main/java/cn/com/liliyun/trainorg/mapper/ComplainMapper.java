package cn.com.liliyun.trainorg.mapper;

import java.util.List;

import cn.com.liliyun.trainorg.model.Complain;

public interface ComplainMapper {
    int deleteByPrimaryKey(Complain record);

    int insert(Complain record);

    int insertSelective(Complain record);

    Complain selectByPrimaryKey(Complain record);

    int updateByPrimaryKeySelective(Complain record);

    int updateByPrimaryKey(Complain record);
    
	List<Complain> selectByCondition(Complain complain);
}