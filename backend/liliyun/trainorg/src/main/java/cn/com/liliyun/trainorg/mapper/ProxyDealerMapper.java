package cn.com.liliyun.trainorg.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.trainorg.model.ProxyDealer;

public interface ProxyDealerMapper {
	
    int deleteByPrimaryKey(ProxyDealer proxyDealer);

    int insertSelective(ProxyDealer record);

    ProxyDealer selectByPrimaryKey(ProxyDealer proxyDealer);

    int updateByPrimaryKeySelective(ProxyDealer record);

    List<ProxyDealer> getList(ProxyDealer proxyDealer);

	void insertScopeProxy(@SuppressWarnings("rawtypes") Map map);

	void deleteAllScopeProxy(ProxyDealer proxyDealer);

	int checkMutliName(ProxyDealer proxyDealer);
	
	int onlyUpdateStatus(ProxyDealer record);
	
	int getCount(ProxyDealer record);

	List<ProxyDealer> getAllByScopeId(ProxyDealer proxyDealer);
}