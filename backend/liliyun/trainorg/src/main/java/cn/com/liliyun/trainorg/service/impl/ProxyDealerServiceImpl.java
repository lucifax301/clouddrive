package cn.com.liliyun.trainorg.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.StringUtil;
import cn.com.liliyun.trainorg.mapper.ProxyDealerMapper;
import cn.com.liliyun.trainorg.model.ProxyDealer;
import cn.com.liliyun.trainorg.model.ScopeProxy;
import cn.com.liliyun.trainorg.service.ProxyDealerService;

@Service
public class ProxyDealerServiceImpl implements  ProxyDealerService{

	@Autowired
	private ProxyDealerMapper proxyDealerMapper;
	
	@Override
	public ResultBean deleteByPrimaryKey(ProxyDealer record) {
			proxyDealerMapper.deleteByPrimaryKey(record);
			proxyDealerMapper.deleteAllScopeProxy(record);
			return new ResultBean();
	}

	@Override
	public ResultBean insertSelective(ProxyDealer record) {
			ResultBean resultBean = new ResultBean();
			record.setAddTime(new Date());
			record.setModifyTime(new Date());
			proxyDealerMapper.insertSelective(record);
			proxyDealerMapper.deleteAllScopeProxy(record);
			List<ScopeProxy> scopeProxy = new ArrayList<ScopeProxy>();
			if(!StringUtil.isNull(record.getScopeAreas())){
				String[] areas = record.getScopeAreas().split(",");
				for (int i = 0; i < areas.length; i++) {
					ScopeProxy scopeProxyTemp = new ScopeProxy();
					scopeProxyTemp.setProxyId(record.getProxyId());
					scopeProxyTemp.setScopeId(Integer.valueOf(areas[i]));
					scopeProxy.add(scopeProxyTemp);
				}
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("list",scopeProxy);
				map.put("dblink", record.getDblink());
				map.put("mgrdb", record.getMgrdb());
				proxyDealerMapper.insertScopeProxy(map);
			}
			resultBean.setResult(record);
			return resultBean;
	}

	@Override
	public ResultBean selectByPrimaryKey(ProxyDealer record) {
		ResultBean rbean = new ResultBean();
			ProxyDealer pdealer = proxyDealerMapper.selectByPrimaryKey(record);
			rbean.setResult(pdealer);
			return rbean;
	}

	@Override
	public ResultBean updateByPrimaryKeySelective(ProxyDealer record) {
			proxyDealerMapper.updateByPrimaryKeySelective(record);
			proxyDealerMapper.deleteAllScopeProxy(record);
			
			List<ScopeProxy> scopeProxy = new ArrayList<ScopeProxy>();
			if(!StringUtil.isNull(record.getScopeAreas())){
				String[] areas = record.getScopeAreas().split(",");
				for (int i = 0; i < areas.length; i++) {
					ScopeProxy scopeProxyTemp = new ScopeProxy();
					scopeProxyTemp.setProxyId(record.getProxyId());
					scopeProxyTemp.setScopeId(Integer.valueOf(areas[i]));
					scopeProxy.add(scopeProxyTemp);
				}
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("list",scopeProxy);
				map.put("dblink", record.getDblink());
				map.put("mgrdb", record.getMgrdb());
				proxyDealerMapper.insertScopeProxy(map);
			}
			return new ResultBean();
	}

	@Override
	public ResultBean getList(ProxyDealer proxyDealer) {
		proxyDealer.setStartRow((proxyDealer.getPageNo()-1) * proxyDealer.getPageSize());
		List<ProxyDealer> proxyList  = proxyDealerMapper.getList(proxyDealer);
		PageInfo<ProxyDealer> pagedResult = new PageInfo<>(proxyList);
		pagedResult.setTotal(proxyDealerMapper.getCount(proxyDealer));
		ResultBean resultBean = new ResultBean();
		resultBean.setResult(pagedResult);
		return resultBean;
	}

	@Override
	public ResultBean checkMutliName(ProxyDealer record) {
		ResultBean resultBean = new ResultBean();
			int count = proxyDealerMapper.checkMutliName(record);
			if (count == 0) {
				resultBean.setResult("ok");
			} else {
				resultBean.setResult("repeat");
			}
			return resultBean;
	}

	@Override
	public ResultBean onlyUpdateStatus(ProxyDealer record) {
			 proxyDealerMapper.onlyUpdateStatus(record);
			return new ResultBean();
	}

	@Override
	public ResultBean getAllByScopeId(ProxyDealer record) {
		List<ProxyDealer> proxyList  = proxyDealerMapper.getAllByScopeId(record);
		ResultBean resultBean = new ResultBean();
		resultBean.setResult(proxyList);
		return resultBean;
	}

}
