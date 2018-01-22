package cn.com.liliyun.market.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.market.model.SalesActivityClassinfo;
import cn.com.liliyun.market.model.SalesActivity;

public interface SalesMapper {

	public void addSales(SalesActivity sales);
	
	public void updateSales(SalesActivity market);
	
	public void updateSalesStatus(SalesActivity apply);
	
	public List<SalesActivity> listSales(SalesActivity param);
	
	
	public List<SalesActivity> listRunningSales(SalesActivity param);
	
	public List<SalesActivity> listCloseSales(SalesActivity param);
	
	public List<SalesActivity> listWaitSales(SalesActivity param);
	
	public List<SalesActivity> listEndSales(SalesActivity param);
	
	
	public List<SalesActivity> listMatchSales(SalesActivityClassinfo param);
	
	public List<Map> statActivityStudent(Map map);
	
	
	public SalesActivity getSales(SalesActivity market);
	
	public void batchinsertClass(Map map);
	
	public void batchdelClass(Map map);
	
	public void updateClass(SalesActivityClassinfo info);
	
	public List<SalesActivityClassinfo> getClass(SalesActivityClassinfo info);
	
	public SalesActivityClassinfo getMatchClass(SalesActivityClassinfo info);
}
