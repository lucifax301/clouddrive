package cn.com.liliyun.trainorg.service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.ProxyDealer;

public interface ProxyDealerService {

	public ResultBean deleteByPrimaryKey(ProxyDealer record);

	public ResultBean insertSelective(ProxyDealer record);

	public ResultBean selectByPrimaryKey(ProxyDealer record);

	public ResultBean updateByPrimaryKeySelective(ProxyDealer record);

	public ResultBean getList(ProxyDealer proxyDealer);

	public ResultBean checkMutliName(ProxyDealer proxyDealer);
	
	public ResultBean onlyUpdateStatus(ProxyDealer record);

	public ResultBean getAllByScopeId(ProxyDealer proxyDealer);
}
