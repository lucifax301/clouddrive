package cn.com.liliyun.trainorg.mapper;

import java.util.List;

import cn.com.liliyun.trainorg.model.Examarea;
import cn.com.liliyun.trainorg.model.Trainarea;

public interface ExamareaMapper {
    int deleteByPrimaryKey(Examarea trainarea);

    int insert(Examarea trainarea);
    

    Trainarea selectByPrimaryKey(Examarea trainarea);
    

    int updateByPrimaryKey(Examarea trainarea);
    
    List<Examarea> selectList(Examarea trainarea);
    
    
}