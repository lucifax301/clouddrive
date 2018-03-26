package cn.com.liliyun.httpaccess.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.finance.model.FinanceDeposit;
import cn.com.liliyun.finance.model.FinancePosFlow;
import cn.com.liliyun.finance.model.FinanceReceipt;
import cn.com.liliyun.finance.service.FinanceService;
import cn.com.liliyun.student.dto.StudentApplyStat;

@Controller
@ResponseBody
@RequestMapping("/stat")
public class FinanceStatController extends BaseController {

	@Autowired
	private FinanceService financeService;
	
	@RequestMapping(value = "/getCashStat", method = RequestMethod.GET)
	public ResultBean getFinanceCashStatList(FinanceDeposit financeDeposit, HttpServletRequest request) {
		return financeService.getFinanceCashStatList(financeDeposit);
	}
	
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public ResultBean importflownum(@RequestParam("file") MultipartFile file, @RequestParam("filetype") Integer type, 
			HttpServletRequest request) throws Exception {
		Workbook wb = null;
		if (file.getOriginalFilename().endsWith(".xlsx")) {
			wb = new XSSFWorkbook(file.getInputStream());
		} else {
			wb = new HSSFWorkbook(file.getInputStream());
		}
		return financeService.addFinancePosFlow(wb, type);
	}
	
	@RequestMapping(value = "/getFinancePosFlowList", method = RequestMethod.GET)
	public ResultBean getFinancePosFlowList(FinancePosFlow financePosFlow, HttpServletRequest request) {
		return financeService.getFinancePosFlowList(financePosFlow);
	}
	
	@RequestMapping(value = "/getStorePayStat", method = RequestMethod.GET)
	public ResultBean getStorePayStatList(FinanceReceipt financeReceipt, HttpServletRequest request) {
		return financeService.getStorePayStatist(financeReceipt);
	}
	
	@RequestMapping(value = "/getBankPosStoreStat", method = RequestMethod.GET)
	public ResultBean getBankPosStoreStatList(FinanceReceipt financeReceipt, HttpServletRequest request) {
		return financeService.getBankPosStoreStatList(financeReceipt);
	}
	
	@RequestMapping(value = "/getStoreTypeStat", method = RequestMethod.GET)
	public ResultBean getStoreTypeStatList(FinanceReceipt financeReceipt, HttpServletRequest request) {
		return financeService.getStoreTypeStatList(financeReceipt);
	}
	
	@RequestMapping(value = "/getTypeStoreStat", method = RequestMethod.GET)
	public ResultBean getTypeStoreStatList(FinanceReceipt financeReceipt, HttpServletRequest request) {
		return financeService.getTypeStoreStatList(financeReceipt);
	}
	
	@RequestMapping(value = "/getCompanyPosStoreStat", method = RequestMethod.GET)
	public ResultBean getCompanyPosStoreStatList(FinanceReceipt financeReceipt, HttpServletRequest request) {
		return financeService.getCompanyPosStoreStatList(financeReceipt);
	}
	
	@RequestMapping(value = "/getApplyStat", method = RequestMethod.GET)
	public ResultBean getApplyStatList(StudentApplyStat studentApplyStat, HttpServletRequest request) {
		return financeService.getApplyStatList(studentApplyStat);
	}
}
