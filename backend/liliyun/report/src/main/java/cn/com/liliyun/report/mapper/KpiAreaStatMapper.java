package cn.com.liliyun.report.mapper;

import java.util.List;

import cn.com.liliyun.report.model.KpiAreaStatParam;
import cn.com.liliyun.report.model.KpiAreaStatRecord;



public interface KpiAreaStatMapper {

	public List<KpiAreaStatRecord> statByArea1(KpiAreaStatParam param);
	
	public List<KpiAreaStatRecord> statByArea2(KpiAreaStatParam param);
}
