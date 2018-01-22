package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.report.service.ICertificateReportService;
import cn.com.liliyun.trainorg.model.TrainExamItemDetailVo;
import cn.com.liliyun.trainorg.model.TrainExamItemVo;
import cn.com.liliyun.trainorg.model.TrainExamItemYearVo;


/**
 * 报表统计
 */
@Controller
@ResponseBody
@RequestMapping(value="/report")
public class ReportController extends BaseController {
	
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
		ExportParams params = new ExportParams("考试次数导出数据", "导出数据", ExcelType.XSSF);// title
		Workbook workbook = ExcelExportUtil.exportExcel(params, TrainExamItemVo.class, list);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		HttpHeaders headers = new HttpHeaders();
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = new String(
		("考试次数导出数据" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
		HttpStatus.CREATED);
	}
	
	
	/**
	 * 考试情况
	 * @param request
	 * @param trainExamItem
	 * @return
	 */
	@RequestMapping(value="/getExamCase")
	public ResultBean getExamCase(HttpServletRequest request,TrainExamItemVo trainExamItem) {
		ResultBean rb = new ResultBean();
		List<TrainExamItemVo> list  = certificateReportService.getExamCase(trainExamItem);
		if (list != null && list.size() > 0) {
			rb.setResult(new PageInfo<>(list));
		}
		return rb;
	}
	
	@RequestMapping(value="/exportExamCase")
	public ResponseEntity<byte[]> exportExamCase(TrainExamItemVo trainExamItem, HttpServletRequest request) throws IOException {
		List <TrainExamItemVo> list = certificateReportService.getExamCase(trainExamItem);
		ExportParams params = new ExportParams("考试情况导出数据", "导出数据", ExcelType.XSSF);// title
		Workbook workbook = ExcelExportUtil.exportExcel(params, TrainExamItemVo.class, list);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		HttpHeaders headers = new HttpHeaders();
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = new String(
		("考试情况导出数据" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
		HttpStatus.CREATED);
	}
	
	/**
	 * 年度报表
	 * @param request
	 * @param trainExamItem
	 * @return
	 */
	@RequestMapping(value="/getWaitCaseYear")
	public ResultBean getWaitCaseYear(HttpServletRequest request,TrainExamItemYearVo trainExamItemYearVo) {
		ResultBean rb = new ResultBean();
		List<TrainExamItemYearVo> yearList = certificateReportService.getWaitCaseYear(trainExamItemYearVo);
		if (yearList != null && yearList.size() > 0) {
			rb.setResult(new PageInfo<>(yearList));
		}
		return rb;
	}
	
	/**
	 * 等待值情况
	 * @param request
	 * @param trainExamItem
	 * @return
	 */
	@RequestMapping(value="/getWaitCase")
	public ResultBean getWaitCase(HttpServletRequest request,TrainExamItemVo trainExamItem) {
		ResultBean rb = new ResultBean();
		List<TrainExamItemVo> list = certificateReportService.getWaitCase(trainExamItem);
		if (list != null && list.size() > 0) {
			rb.setResult(new PageInfo<>(list));
		}
		return rb;
	}
	
	@RequestMapping(value="/exportWaitCase")
	public ResponseEntity<byte[]> exportWaitCase(TrainExamItemVo trainExamItem, HttpServletRequest request) throws IOException {
		List <TrainExamItemVo> list = certificateReportService.getWaitCase(trainExamItem);
		ExportParams params = new ExportParams("等待值情况导出数据", "导出数据", ExcelType.XSSF);// title
		Workbook workbook = ExcelExportUtil.exportExcel(params, TrainExamItemVo.class, list);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		HttpHeaders headers = new HttpHeaders();
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = new String(
		("等待值情况导出数据" + time + ".xlsx").getBytes("UTF-8"), "iso-8859-1"); // 生成文件名
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(os.toByteArray(), headers,
		HttpStatus.CREATED);
	}
	
	
	/**
	 * 等值情况明细
	 * @param request
	 * @param trainExamItem
	 * @return
	 */
	@RequestMapping(value="/getWaitCaseDetail")
	public ResultBean getWaitCaseDetail(HttpServletRequest request,TrainExamItemDetailVo trainExamItem) {
		ResultBean rb = new ResultBean();
		List<TrainExamItemDetailVo> list = certificateReportService.getWaitCaseDetail(trainExamItem);
		if (list != null && list.size() > 0) {
			rb.setResult(new PageInfo<>(list));
		}
		return rb;
	}
	
	
	
	
}
