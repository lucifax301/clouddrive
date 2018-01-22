package cn.com.liliyun.trainorg.mapper;

import java.util.List;

import cn.com.liliyun.trainorg.model.Marketing;
import cn.com.liliyun.trainorg.model.Visit;

public interface MarketingMapper{
	//新增
	public int saveMarketing(Marketing marketing);
	//删除
	public int deleteMarketing(Marketing marketing);
	//更新
	public int updateMarketing(Marketing marketing);
	//列表
	public List getAllMarketing(Marketing marketing);
	//根据id查
	public Marketing getMarketingById(Marketing marketing);
	//回访部分
	public int saveVisit(Visit visit);
	//回访列表
	public List getAllVisit(Visit visit);
}