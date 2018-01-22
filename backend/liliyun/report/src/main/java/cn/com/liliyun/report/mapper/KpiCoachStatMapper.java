package cn.com.liliyun.report.mapper;

import java.util.List;

import cn.com.liliyun.report.model.KpiCoachStatParam;
import cn.com.liliyun.report.model.KpiCoachStatRecord;



public interface KpiCoachStatMapper {

	public List<KpiCoachStatRecord> statByArea1(KpiCoachStatParam param);
	
	public List<KpiCoachStatRecord> statByArea2(KpiCoachStatParam param);
}
