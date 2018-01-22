package cn.com.liliyun.market.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.market.model.CustomerRecord;
import cn.com.liliyun.market.model.CustomerStat;
import cn.com.liliyun.market.model.PotentialCustomer;
import cn.com.liliyun.user.model.User;

public interface CustomerService {
	
	public ResultBean addCustomerRecord(CustomerRecord customerRecord, User user);
	
	public ResultBean getCustomerRecord(CustomerRecord customerRecord, User user);
	
	public ResultBean getCustomerRecordList(CustomerRecord customerRecord, User user);
	
	public ResultBean editCustomerRecord(CustomerRecord customerRecord, User user);
	
	public List<CustomerRecord> getCustomerRecordByStuID(Integer studentid, User user);
	
	public ResultBean handleCustomerRecord(CustomerRecord customerRecord, User user);

	public List<CustomerRecord> getCustomerRecordExport(CustomerRecord customerRecord, User user);
	
	public ResultBean addPotentialCustomer(PotentialCustomer potentialCustomer, User user);
	
	public ResultBean getPotentialCustomerList(PotentialCustomer potentialCustomer, User user);
	
	public ResultBean editPotentialCustomer(PotentialCustomer potentialCustomer, User user);
	
	public List<PotentialCustomer> getPotentialCustomerExport(PotentialCustomer potentialCustomer, User user);
	
	/**
	 * ��ȡ������������ѧԱͳ��
	 * @param customerStat
	 * @param user
	 * @return
	 */
	public List<CustomerStat> getChannelNewStuStat(CustomerStat customerStat, User user);
	
	/**
	 * ��ȡǱ�ڿͻ�ת��ѧԱͳ��
	 * @param customerStat
	 * @param user
	 * @return
	 */
	public List<CustomerStat> getPotentialNewStuStat(CustomerStat customerStat, User user);
	
	/**
	 * ��ȡ��ѧԱ����Ǳ�ڷ�Ǳ�ڣ�ͳ��
	 * @param customerStat
	 * @param user
	 * @return
	 */
	public List<CustomerStat> getNewStuStat(CustomerStat customerStat, User user);
}
