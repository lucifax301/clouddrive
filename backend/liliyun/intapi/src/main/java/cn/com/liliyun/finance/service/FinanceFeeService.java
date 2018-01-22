package cn.com.liliyun.finance.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.finance.model.FinanceFee;
import cn.com.liliyun.finance.model.FinanceFeeDTO;
import cn.com.liliyun.finance.model.FinanceFeeItem;
import cn.com.liliyun.finance.model.FinancePay;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.user.model.User;

public interface FinanceFeeService {
	
	List <FinanceFee> selectList(User user,FinanceFee financeFee);
	
	List <FinanceFeeItem> selectItemList(User user,FinanceFeeItem financeFeeItem);

	List <FinanceFeeItem> selectAllItemList(User user,FinanceFeeItem FinanceFeeItem);

	List <FinancePay> selectPay(User user,FinancePay financePay);
	
	ResultBean save(User user,FinanceFeeDTO dto,List <FinanceFeeItem> list);
	
	ResultBean edit(User user,FinanceFeeDTO dto,List <FinanceFeeItem> list);
	
	ResultBean initData(User user,Student student);
	
	ResultBean saveItem(User user,FinanceFeeItem financeFeeItem);
	
	ResultBean editItem(User user,FinanceFeeItem financeFeeItem);
	
	ResultBean check(User user,FinanceFeeItem financeFeeItem);
	
	ResultBean checkBatch(User user,FinanceFeeItem financeFeeItem);
	
	ResultBean deleteItem(User user,FinanceFeeItem financeFeeItem);

	ResultBean saveFinanceItem(User user, FinanceFeeItem feeItem,Student student);
}
