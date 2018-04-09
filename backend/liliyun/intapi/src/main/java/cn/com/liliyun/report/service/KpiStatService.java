package cn.com.liliyun.report.service;

import java.util.List;

import cn.com.liliyun.report.model.KpiAreaStat;
import cn.com.liliyun.report.model.KpiAreaStatParam;
import cn.com.liliyun.report.model.KpiClassStat;
import cn.com.liliyun.report.model.KpiClassStatParam;
import cn.com.liliyun.report.model.KpiCoachStat;
import cn.com.liliyun.report.model.KpiCoachStatParam;
import cn.com.liliyun.report.model.KpiExamStat;
import cn.com.liliyun.report.model.KpiExamStatParam;
import cn.com.liliyun.report.model.KpiHeadCoachStat;
import cn.com.liliyun.report.model.KpiHeadCoachStatParam;
import cn.com.liliyun.report.model.KpiStoreStat;
import cn.com.liliyun.report.model.KpiStoreStatParam;

public interface KpiStatService {

	List<KpiAreaStat> statByArea(KpiAreaStatParam param);
	
	List<KpiClassStat> statByClass(KpiClassStatParam param);
	
	List<KpiCoachStat> statByCoach(KpiCoachStatParam param) ;
	
	List<KpiStoreStat> statByStore(KpiStoreStatParam param);
	
	List<KpiHeadCoachStat> statByHeadCoach(KpiHeadCoachStatParam param);
	
	List<KpiExamStat> statByExam(KpiExamStatParam param);
}
