package cn.com.liliyun.report.mapper;

import java.util.List;

import cn.com.liliyun.report.model.KpiExamStatParam;
import cn.com.liliyun.report.model.KpiExamStatRecord;



public interface KpiExamStatMapper {

	public List<KpiExamStatRecord> statByArea1(KpiExamStatParam param);
	
	public List<KpiExamStatRecord> statByArea2(KpiExamStatParam param);
}
