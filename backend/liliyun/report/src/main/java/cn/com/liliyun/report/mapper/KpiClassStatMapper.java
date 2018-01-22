package cn.com.liliyun.report.mapper;

import java.util.List;

import cn.com.liliyun.report.model.KpiClassStatParam;
import cn.com.liliyun.report.model.KpiClassStatRecord;



public interface KpiClassStatMapper {

	public List<KpiClassStatRecord> statByArea1(KpiClassStatParam param);
	
	public List<KpiClassStatRecord> statByArea2(KpiClassStatParam param);
}
