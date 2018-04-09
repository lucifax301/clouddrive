package cn.com.liliyun.trainorg.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.OrderExam;
import cn.com.liliyun.trainorg.model.OrderExamItem;

/**
 * 考试预约
 *
 */
public interface OrderExamService {
	
	List<OrderExam> list(OrderExam orderExam);
	
	List<OrderExamItem> listOfCoach(OrderExamItem orderExamItem);

	OrderExamItem selectOne(OrderExamItem orderExamItem);
	
	List<OrderExamItem> listItem(OrderExamItem orderExamItem);
	
	ResultBean add(List <OrderExamItem> list);
	
	Map<String,Object> importData(List <OrderExamItem> list);
}
