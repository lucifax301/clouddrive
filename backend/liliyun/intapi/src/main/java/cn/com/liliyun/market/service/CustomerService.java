package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.market.model.CustomerRecord;
import cn.com.liliyun.market.model.CustomerStat;
import cn.com.liliyun.market.model.PotentialCustomer;

public interface CustomerService {
	
	ResultBean addCustomerRecord(CustomerRecord customerRecord);
	
	ResultBean getCustomerRecord(CustomerRecord customerRecord);
	
	ResultBean getCustomerRecordList(CustomerRecord customerRecord);
	
	ResultBean editCustomerRecord(CustomerRecord customerRecord);
	
	List<CustomerRecord> getCustomerRecordByStuID(Integer studentid);
	
	ResultBean handleCustomerRecord(CustomerRecord customerRecord);

	List<CustomerRecord> getCustomerRecordExport(CustomerRecord customerRecord);
	
	ResultBean addPotentialCustomer(PotentialCustomer potentialCustomer);
	
	ResultBean getPotentialCustomerList(PotentialCustomer potentialCustomer);
	
	ResultBean editPotentialCustomer(PotentialCustomer potentialCustomer);
	
	List<PotentialCustomer> getPotentialCustomerExport(PotentialCustomer potentialCustomer);
	
	/**
	 * ��ȡ������������ѧԱͳ��
	 * @param customerStat
	 * @param user
	 * @return
	 */
	List<CustomerStat> getChannelNewStuStat(CustomerStat customerStat);
	
	/**
	 * ��ȡǱ�ڿͻ�ת��ѧԱͳ��
	 * @param customerStat
	 * @param user
	 * @return
	 */
	List<CustomerStat> getPotentialNewStuStat(CustomerStat customerStat);
	
	/**
	 * ��ȡ��ѧԱ����Ǳ�ڷ�Ǳ�ڣ�ͳ��
	 * @param customerStat
	 * @param user
	 * @return
	 */
	List<CustomerStat> getNewStuStat(CustomerStat customerStat);
}
