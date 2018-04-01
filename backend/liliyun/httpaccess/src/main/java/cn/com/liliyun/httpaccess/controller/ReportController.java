package cn.com.liliyun.httpaccess.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.report.service.ICertificateReportService;
import cn.com.liliyun.trainorg.model.TrainExamItemDetailVo;
import cn.com.liliyun.trainorg.model.TrainExamItemVo;
import cn.com.liliyun.trainorg.model.TrainExamItemYearVo;

import com.github.pagehelper.PageInfo;


/**
 * 报表统计
 */
@Controller
@ResponseBody
@RequestMapping(value="/report")
public class ReportController extends ExportController {
	
	@Autowired
	private ICertificateReportService certificateReportService;
	
	/**
	 * **********************************************
	 *  				牌证统计
	 * **********************************************
	 */
	
	/**
	 * 考试次数
	 * @param request
	 * @param trainExamItem
	 * @return
	 */
	@RequestMapping(value="/getExamTime")
	public ResultBean getExamTime(HttpServletRequest request,TrainExamItemVo trainExamItem) {
		ResultBean rb = new ResultBean();
		List<TrainExamItemVo> list = certificateReportService.getExamTime(trainExamItem);
		if (list != null && list.size() > 0) {
			rb.setResult(new PageInfo<>(list));
		}
		return rb;
	}
	
	@RequestMapping(value="/exportExamTime")
	public ResponseEntity<byte[]> exportExamTime(TrainExamItemVo trainExamItem, HttpServletRequest request) throws IOException {
		List <TrainExamItemVo> list = certificateReportService.getExamTime(trainExamItem);
		return this.export("考试次数导出数据", "导出数据", "导出数据", list, TrainExamItemVo.class);
	}
	
	
	/**
	 * 考试情况
	 * @param request
	 * @param trainExamItem
	 * @return
	 */
	@RequestMapping(value="/getExamCase")
	public ResultBean getExamCase(HttpServletRequest request,TrainExamItemVo trainExamItem) {
		List<TrainExamItemVo> list  = certificateReportService.getExamCase(trainExamItem);
		return this.<TrainExamItemVo>buildListResult(list);
	}
	
	@RequestMapping(value="/exportExamCase")
	public ResponseEntity<byte[]> exportExamCase(TrainExamItemVo trainExamItem, HttpServletRequest request) throws IOException {
		List <TrainExamItemVo> list = certificateReportService.getExamCase(trainExamItem);
		return this.export("考试情况导出数据", "考试情况导出数据", "导出数据", list, TrainExamItemVo.class);
	}
	
	/**
	 * 年度报表
	 * @param request
	 * @param trainExamItem
	 * @return
	 */
	@RequestMapping(value="/getWaitCaseYear")
	public ResultBean getWaitCaseYear(HttpServletRequest request,TrainExamItemYearVo trainExamItemYearVo) {
		List<TrainExamItemYearVo> list = certificateReportService.getWaitCaseYear(trainExamItemYearVo);
		return this.<TrainExamItemYearVo>buildListResult(list);
	}
	
	/**
	 * 等待值情况
	 * @param request
	 * @param trainExamItem
	 * @return
	 */
	@RequestMapping(value="/getWaitCase")
	public ResultBean getWaitCase(HttpServletRequest request,TrainExamItemVo trainExamItem) {
		List<TrainExamItemVo> list = certificateReportService.getWaitCase(trainExamItem);
		return this.<TrainExamItemVo>buildListResult(list);
	}
	
	@RequestMapping(value="/exportWaitCase")
	public ResponseEntity<byte[]> exportWaitCase(TrainExamItemVo trainExamItem, HttpServletRequest request) throws IOException {
		List <TrainExamItemVo> list = certificateReportService.getWaitCase(trainExamItem);
		return this.export("等待值情况导出数据", "等待值情况导出数据", "导出数据", list, TrainExamItemVo.class);
	}
	
	
	/**
	 * 等值情况明细
	 * @param request
	 * @param trainExamItem
	 * @return
	 */
	@RequestMapping(value="/getWaitCaseDetail")
	public ResultBean getWaitCaseDetail(HttpServletRequest request,TrainExamItemDetailVo trainExamItem) {
		List<TrainExamItemDetailVo> list = certificateReportService.getWaitCaseDetail(trainExamItem);
		return this.<TrainExamItemDetailVo>buildListResult(list);
	}
	
	
}
