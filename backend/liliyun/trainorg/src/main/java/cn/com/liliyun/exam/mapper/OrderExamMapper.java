package cn.com.liliyun.exam.mapper;

import java.util.List;

import cn.com.liliyun.trainorg.model.OrderExam;

public interface OrderExamMapper {
    int deleteByPrimaryKey(OrderExam orderExam);

    int insert(OrderExam record);

    int insertSelective(OrderExam record);

    OrderExam selectByPrimaryKey(OrderExam orderExam);

    int updateByPrimaryKeySelective(OrderExam record);

    int updateByPrimaryKey(OrderExam record);
    
    List <OrderExam> selectList(OrderExam orderExam);
    
    //List <OrderExam> selectCoachStuList(OrderExam orderExam);
}