package cn.com.liliyun.trainorg.mapper;

import java.util.List;

import cn.com.liliyun.trainorg.model.Trainarea;

public interface TrainareaMapper {
    int deleteByPrimaryKey(Trainarea trainarea);

    int insert(Trainarea trainarea);

    int insertSelective(Trainarea trainarea);

    Trainarea selectByPrimaryKey(Trainarea trainarea);

    int updateByPrimaryKeySelective(Trainarea trainarea);

    int updateByPrimaryKey(Trainarea trainarea);
    
    List<Trainarea> selectList(Trainarea trainarea);
    
    int getCount(Trainarea trainarea);
}