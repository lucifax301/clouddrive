package cn.com.liliyun.trainorg.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.trainorg.mapper.MarketingMapper;
import cn.com.liliyun.trainorg.model.Marketing;
import cn.com.liliyun.trainorg.model.Visit;
import cn.com.liliyun.trainorg.service.MarketingService;

@Service
public class MarketingServiceImpl implements MarketingService {
	
	@Autowired
	private MarketingMapper marketingMapper;

	@Override
	public ResultBean addMarket(Marketing marketing) {
		ResultBean r = new ResultBean();
		marketingMapper.saveMarketing(marketing);
		r.setCode(0);
		r.setMsg("添加成功");
		return r; 
	}

	@Override
	public ResultBean deleteMarket(Marketing marketing) {
		ResultBean r = new ResultBean();
		marketingMapper.deleteMarketing(marketing);
		r.setCode(0);
		r.setMsg("删除成功");
		return r; 
	}

	@Override
	public ResultBean updateMarket(Marketing marketing) {
		ResultBean r = new ResultBean();
		marketingMapper.updateMarketing(marketing);
		r.setCode(0);
		r.setMsg("修改成功");
		return r; 
	}

	@Override
	public ResultBean getMarketList(Marketing marketing) {
		ResultBean resultBean = new ResultBean();
		PageUtil.startPage(marketing);
		List<Marketing> marketingList = marketingMapper.getAllMarketing(marketing);
		PageInfo<Marketing> pagedResult = new PageInfo<>(marketingList);
		resultBean.setResult(pagedResult);
		resultBean.setCode(0);
		return resultBean;
	}
	
	//增加回访
	public ResultBean addVisit(Visit visit) {
		ResultBean r = new ResultBean();
		marketingMapper.saveVisit(visit);
		r.setCode(0);
		r.setMsg("添加成功");
		return r; 
	}

	@Override
	public ResultBean getMarketingById(Marketing marketing) {
		ResultBean r = new ResultBean();
		Marketing  market = marketingMapper.getMarketingById(marketing);
		r.setResult(market);
		return r; 
	}

	@Override
	public ResultBean getVisitList(Visit visit) {
		ResultBean resultBean = new ResultBean();
		PageUtil.startPage(visit);
		List<Visit> visitList = marketingMapper.getAllVisit(visit);
		PageInfo<Visit> pagedResult = new PageInfo<>(visitList);
		resultBean.setResult(pagedResult);
		resultBean.setCode(0);
		return resultBean;
	}

	@Override
	public void improtExcel(Marketing marketing,List list) {
		Iterator it = list.iterator();
		while(it.hasNext()){
			Marketing m = (Marketing) it.next();
			m.setDblink(marketing.getDblink());
			marketingMapper.saveMarketing(m);
		}
	}
	

}
