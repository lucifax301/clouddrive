package cn.com.liliyun.report.mapper;

import java.util.List;

import cn.com.liliyun.report.model.KpiHeadCoachStatParam;
import cn.com.liliyun.report.model.KpiHeadCoachStatRecord;



public interface KpiHeadCoachStatMapper {

	public List<KpiHeadCoachStatRecord> statByArea1(KpiHeadCoachStatParam param);
	
	public List<KpiHeadCoachStatRecord> statByArea2(KpiHeadCoachStatParam param);
}
