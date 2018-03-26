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
	
	List <FinanceFee> selectList(FinanceFee financeFee);
	
	List <FinanceFeeItem> selectItemList(FinanceFeeItem financeFeeItem);

	List <FinanceFeeItem> selectAllItemList(FinanceFeeItem FinanceFeeItem);

	List <FinancePay> selectPay(FinancePay financePay);
	
	ResultBean save(FinanceFeeDTO dto,List <FinanceFeeItem> list);
	
	ResultBean edit(FinanceFeeDTO dto,List <FinanceFeeItem> list);
	
	ResultBean initData(Student student);
	
	ResultBean saveItem(FinanceFeeItem financeFeeItem);
	
	ResultBean editItem(FinanceFeeItem financeFeeItem);
	
	ResultBean check(FinanceFeeItem financeFeeItem);
	
	ResultBean checkBatch(FinanceFeeItem financeFeeItem);
	
	ResultBean deleteItem(FinanceFeeItem financeFeeItem);

	ResultBean saveFinanceItem( FinanceFeeItem feeItem,Student student);
}
