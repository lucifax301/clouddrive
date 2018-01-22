package cn.com.liliyun.report.mapper;

import java.util.List;

import cn.com.liliyun.report.model.KpiStoreStatParam;
import cn.com.liliyun.report.model.KpiStoreStatRecord;



public interface KpiStoreStatMapper {

	public List<KpiStoreStatRecord> statByArea1(KpiStoreStatParam param);
	
	public List<KpiStoreStatRecord> statByArea2(KpiStoreStatParam param);
}
