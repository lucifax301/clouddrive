package cn.com.liliyun.trainorg.service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.ProxyDealer;

public interface ProxyDealerService {

	ResultBean deleteByPrimaryKey(ProxyDealer record);

	ResultBean insertSelective(ProxyDealer record);

	ResultBean selectByPrimaryKey(ProxyDealer record);

	ResultBean updateByPrimaryKeySelective(ProxyDealer record);

	ResultBean getList(ProxyDealer proxyDealer);

	ResultBean checkMutliName(ProxyDealer proxyDealer);
	
	ResultBean onlyUpdateStatus(ProxyDealer record);

	ResultBean getAllByScopeId(ProxyDealer proxyDealer);
}
