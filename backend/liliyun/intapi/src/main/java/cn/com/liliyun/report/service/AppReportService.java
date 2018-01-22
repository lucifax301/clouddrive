package cn.com.liliyun.report.service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.report.model.AppIndexInfo;
import cn.com.liliyun.report.model.CertificateCollectDto;
import cn.com.liliyun.trainorg.model.TrainExamItemDetailVo;
import cn.com.liliyun.trainorg.model.TrainExamItemVo;
import cn.com.liliyun.trainorg.model.TrainExamItemYearVo;
import cn.com.liliyun.user.model.User;
import org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument;

import java.util.List;

/**
 * APP获取首页统计信息
 * @author lilixc
 *
 */
public interface AppReportService {

	/**
	 * 获取首页统计信息
	 * @param user 当前用户
     * @param  qtime 查询时间
	 * @return
	 */
	AppIndexInfo getAppIndexInfo(AppIndexInfo appIndexInfo,User user);
}
