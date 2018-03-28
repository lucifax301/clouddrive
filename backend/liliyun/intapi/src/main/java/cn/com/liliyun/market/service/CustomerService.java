package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.market.model.CustomerRecord;
import cn.com.liliyun.market.model.CustomerStat;
import cn.com.liliyun.market.model.PotentialCustomer;

public interface CustomerService {
	
	public ResultBean addCustomerRecord(CustomerRecord customerRecord);
	
	public ResultBean getCustomerRecord(CustomerRecord customerRecord);
	
	public ResultBean getCustomerRecordList(CustomerRecord customerRecord);
	
	public ResultBean editCustomerRecord(CustomerRecord customerRecord);
	
	public List<CustomerRecord> getCustomerRecordByStuID(Integer studentid);
	
	public ResultBean handleCustomerRecord(CustomerRecord customerRecord);

	public List<CustomerRecord> getCustomerRecordExport(CustomerRecord customerRecord);
	
	public ResultBean addPotentialCustomer(PotentialCustomer potentialCustomer);
	
	public ResultBean getPotentialCustomerList(PotentialCustomer potentialCustomer);
	
	public ResultBean editPotentialCustomer(PotentialCustomer potentialCustomer);
	
	public List<PotentialCustomer> getPotentialCustomerExport(PotentialCustomer potentialCustomer);
	
	/**
	 * ��ȡ������������ѧԱͳ��
	 * @param customerStat
	 * @param user
	 * @return
	 */
	public List<CustomerStat> getChannelNewStuStat(CustomerStat customerStat);
	
	/**
	 * ��ȡǱ�ڿͻ�ת��ѧԱͳ��
	 * @param customerStat
	 * @param user
	 * @return
	 */
	public List<CustomerStat> getPotentialNewStuStat(CustomerStat customerStat);
	
	/**
	 * ��ȡ��ѧԱ����Ǳ�ڷ�Ǳ�ڣ�ͳ��
	 * @param customerStat
	 * @param user
	 * @return
	 */
	public List<CustomerStat> getNewStuStat(CustomerStat customerStat);
}
