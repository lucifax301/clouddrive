package cn.com.liliyun.market.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.HttpConstant;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.market.mapper.CustomerRecordMapper;
//import cn.com.liliyun.market.mapper.CustomerStatMapper;
import cn.com.liliyun.market.mapper.PotentialCustomerMapper;
import cn.com.liliyun.market.model.CustomerRecord;
import cn.com.liliyun.market.model.CustomerStat;
import cn.com.liliyun.market.model.PotentialCustomer;
import cn.com.liliyun.market.service.CustomerService;
import cn.com.liliyun.user.model.User;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRecordMapper customerRecordMapper;
	
	@Autowired
	private PotentialCustomerMapper potentialCustomerMapper;
	
//	@Autowired
//	private CustomerStatMapper customerStatMapper;

	@Override
	public ResultBean addCustomerRecord(CustomerRecord customerRecord, User user) {
		ResultBean r = new ResultBean();
		
		customerRecord.setDblink(user.getDblink());
		customerRecord.setCuid(user.getId());
		customerRecord.setCname(user.getRealname());
		customerRecordMapper.insertSelective(customerRecord);
		
		return r;
	}

	@Override
	public ResultBean getCustomerRecord(CustomerRecord customerRecord, User user) {
		ResultBean r = new ResultBean();
		
		Map<String, Object> map = new HashMap<>();
		map.put("dblink", user.getDblink());
		CustomerRecord cr = customerRecordMapper.selectByPrimaryKey(map);
		List<CustomerRecord> crList = null;
		if (cr != null)
			crList = getCustomerRecordByStuID(cr.getStudentid(), user);
		map.clear();
		map.put("record", cr);
		map.put("history", crList);
		r.setResult(map);
		
		return r;
	}

	@Override
	public ResultBean getCustomerRecordList(CustomerRecord customerRecord, User user) {
		ResultBean r = new ResultBean();
		
		PageUtil.startPage(customerRecord);
		customerRecord.setDblink(user.getDblink());
		List<CustomerRecord> list = customerRecordMapper.selectlist(customerRecord);
		r.setResult(new PageInfo<CustomerRecord>(list));
		
		return r;
	}

	@Override
	public ResultBean editCustomerRecord(CustomerRecord customerRecord, User user) {
		ResultBean r = new ResultBean();
		
		customerRecord.setDblink(user.getDblink());
		customerRecordMapper.updateByPrimaryKeySelective(customerRecord);
		
		return r;
	}

	@Override
	public List<CustomerRecord> getCustomerRecordByStuID(Integer studentid, User user) {
		List<CustomerRecord> r = null;
		
		if (studentid != null) {
			CustomerRecord customerRecord = new CustomerRecord();
			customerRecord.setStudentid(studentid);
			customerRecord.setDblink(user.getDblink());
			r = customerRecordMapper.selectlist(customerRecord);
		}
		
		return r;
	}

	@Override
	public ResultBean handleCustomerRecord(CustomerRecord customerRecord, User user) {
		ResultBean r = new ResultBean();
		
		String [] ids = customerRecord.getIds().split(",");
		List<String> list = new ArrayList<>();
		for (String id : ids) {
			list.add(id);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("dblink", user.getDblink());
		customerRecordMapper.handleRecordBatch(map);
		
		return r;
	}

	@Override
	public List<CustomerRecord> getCustomerRecordExport(CustomerRecord customerRecord, User user) {
		List<CustomerRecord> r = null;
		
		if (customerRecord != null) {
			customerRecord.setDblink(user.getDblink());
			r = customerRecordMapper.selectlist(customerRecord);
		}
		
		return r;
	}

	@Override
	public ResultBean addPotentialCustomer(PotentialCustomer potentialCustomer, User user) {
		ResultBean r = new ResultBean();
		
		potentialCustomer.setDblink(user.getDblink());
		potentialCustomer.setCuid(user.getId());
		potentialCustomer.setCname(user.getRealname());
		potentialCustomerMapper.insertSelective(potentialCustomer);
		
		return r;
	}

	@Override
	public ResultBean getPotentialCustomerList(PotentialCustomer potentialCustomer, User user) {
		ResultBean r = new ResultBean();
		
		PageUtil.startPage(potentialCustomer);
		potentialCustomer.setDblink(user.getDblink());
		List<PotentialCustomer> list = potentialCustomerMapper.selectList(potentialCustomer);
		r.setResult(new PageInfo<PotentialCustomer>(list));
		
		return r;
	}

	@Override
	public ResultBean editPotentialCustomer(PotentialCustomer potentialCustomer, User user) {
		ResultBean r = new ResultBean();
		
		potentialCustomer.setDblink(user.getDblink());
		potentialCustomerMapper.updateByPrimaryKeySelective(potentialCustomer);
		
		return r;
	}

	@Override
	public List<PotentialCustomer> getPotentialCustomerExport(PotentialCustomer potentialCustomer, User user) {
		List<PotentialCustomer> list = null;
		
		potentialCustomer.setDblink(user.getDblink());
		list = potentialCustomerMapper.selectList(potentialCustomer);
		
		return list;
	}

	@Override
	public List<CustomerStat> getChannelNewStuStat(CustomerStat customerStat, User user) {
		return new ArrayList<>();
//		customerStat.setDblink(user.getDblink());
//		List<CustomerStat> list = customerStatMapper.selectChannelNewStuStat(customerStat);
//		Map<String, CustomerStat> map = new HashMap<>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		if (list != null && list.size() > 0) {
//			for (CustomerStat cs : list) {
//				String key = sdf.format(cs.getDate()) + "_" + cs.getIspotential().toString();
//				map.put(key, cs);
//			}
//		}
//		Calendar calendar = Calendar.getInstance();
//		Date cur = customerStat.getStartdate();
//		calendar.setTime(customerStat.getEnddate());
//		calendar.add(Calendar.DATE, 1);
//		Date end = calendar.getTime();
//		while (cur.before(end)) {
//			String key0 = sdf.format(cur) + "_0";
//			String key1 = sdf.format(cur) + "_1";
//			if (map.get(key0) == null) {
//				CustomerStat cs = new CustomerStat();
//			}
//		}
//		return list;
	}

	@Override
	public List<CustomerStat> getPotentialNewStuStat(CustomerStat customerStat, User user) {
		return new ArrayList<>();
	}

	@Override
	public List<CustomerStat> getNewStuStat(CustomerStat customerStat, User user) {
		return new ArrayList<>();
	}
}
