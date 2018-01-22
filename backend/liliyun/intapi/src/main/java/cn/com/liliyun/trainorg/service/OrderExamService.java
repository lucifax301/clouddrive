package cn.com.liliyun.trainorg.service;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.OrderExam;
import cn.com.liliyun.trainorg.model.OrderExamItem;
import cn.com.liliyun.user.model.User;

/**
 * 考试预约
 *
 */
public interface OrderExamService {
	
	public List<OrderExam> list(OrderExam orderExam);
	
	public List<OrderExamItem> listOfCoach(OrderExamItem orderExamItem);

	public OrderExamItem selectOne(OrderExamItem orderExamItem);
	
	public List<OrderExamItem> listItem(OrderExamItem orderExamItem);
	
	public ResultBean add(User user, List <OrderExamItem> list);
	
	public Map<String,Object> importData(User user, List <OrderExamItem> list);
}
