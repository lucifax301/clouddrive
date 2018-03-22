package cn.com.liliyun.report.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import cn.com.liliyun.report.model.AppIndexInfo;
import cn.com.liliyun.report.service.AppReportService;
import cn.com.liliyun.user.model.User;

@Service
public class AppReportServiceImpl implements AppReportService {

	@Override
	public AppIndexInfo getAppIndexInfo(AppIndexInfo indexInfo, User user) {
		AppIndexInfo appIndexInfo = new AppIndexInfo();
	    appIndexInfo.setApplycount(Integer.valueOf(125));
	    appIndexInfo.setYestapplycount(Integer.valueOf(225));
	    appIndexInfo.setExamcount(Integer.valueOf(3292));
	    appIndexInfo.setIncome(new BigDecimal("3321.21"));
	    appIndexInfo.setPassrate(Double.valueOf(98.21D));
	    appIndexInfo.setPay(new BigDecimal("123.99"));
	    return appIndexInfo;
	}

}
