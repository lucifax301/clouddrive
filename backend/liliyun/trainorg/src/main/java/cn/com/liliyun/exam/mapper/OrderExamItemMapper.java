package cn.com.liliyun.exam.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.trainorg.model.OrderExam;
import cn.com.liliyun.trainorg.model.OrderExamItem;

public interface OrderExamItemMapper {
    int deleteByPrimaryKey(OrderExamItem record);

    int insert(OrderExamItem record);

    int insertSelective(OrderExamItem record);

    OrderExamItem selectByPrimaryKey(OrderExamItem record);

    int updateByPrimaryKeySelective(OrderExamItem record);

    int updateByPrimaryKey(OrderExamItem record);
    
    int insertBatch(Map <String,Object> params);
    
    List <OrderExamItem> selectList(OrderExamItem record);
    
    OrderExamItem selectChangedate(OrderExamItem record);
    
    List <OrderExamItem> selectCoachStuList(OrderExamItem orderExam);

    OrderExamItem selectOne(OrderExamItem record);
}