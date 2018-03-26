package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.enmus.ExcelType;
import org.jeecgframework.poi.exception.excel.ExcelImportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.finance.model.FinanceDeposit;
import cn.com.liliyun.finance.model.FinanceIncome;
import cn.com.liliyun.finance.model.FinanceInvoiceDTO;
import cn.com.liliyun.finance.model.FinanceInvoiceImport;
import cn.com.liliyun.finance.model.FinanceReceipt;
import cn.com.liliyun.finance.model.FinanceSubcharge;
import cn.com.liliyun.finance.model.FinanceSubject;
import cn.com.liliyun.finance.service.FinanceService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.StoreService;


@Controller
@ResponseBody
public class FinanceReceiptController extends BaseController {

	@Autowired
	private FinanceService financeService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private StoreService storeService;
	
	@RequestMapping(value = "/financeSubject/listAll", method = RequestMethod.GET)
	public ResultBean getFinanceSubjectListAll(FinanceSubject financeSubject, HttpServletRequest request) {
		return financeService.getFinanceSubjectList(financeSubject);
	}
	
	@RequestMapping(value = "/financeSubject/list", method = RequestMethod.GET)
	public ResultBean getFinanceSubjectList(FinanceSubject financeSubject, HttpServletRequest request) {
		financeSubject.setDisable((byte) 0);
		financeSubject.setPageNo(-1);
		return financeService.getFinanceSubjectList(financeSubject);
	}
	
	@RequestMapping(value = "/financeSubject/add", method = RequestMethod.POST)
	public ResultBean addFinanceSubject(FinanceSubject financeSubject, HttpServletRequest request) {
		return financeService.addFinanceSubject(financeSubject);
	}
	
	@RequestMapping(value = "/financeSubject/del", method = RequestMethod.POST)
	public ResultBean delFinanceSubject(FinanceSubject financeSubject, HttpServletRequest request) {
		Boolean isdel = true;
		if (financeSubject.getDisable() != null && financeSubject.getDisable() == (byte) 0) {
			isdel = false;
		}
		return financeService.deleteFinanceSubject(financeSubject, isdel);
	}
	
	@RequestMapping(value = "/financeSubcharge/list", method = RequestMethod.GET)
	public ResultBean getFinanceSubchargeList(FinanceSubcharge financeSubcharge, HttpServletRequest request) {
		return financeService.getFinanceSubchargeList(financeSubcharge);
	}
	
	@RequestMapping(value = "/financeSubcharge/add", method = RequestMethod.POST)
	public ResultBean addFinanceSubcharge(FinanceSubcharge financeSubcharge, HttpServletRequest request) {
		return financeService.addFinanceSubcharge(financeSubcharge);
	}
	
	@RequestMapping(value = "/financeSubcharge/update", method = RequestMethod.POST)
	public ResultBean updateFinanceSubcharge(FinanceSubcharge financeSubcharge, HttpServletRequest request) {
		return financeService.updateFinanceSubcharge(financeSubcharge);
	}
	
	@RequestMapping(value = "/financeSubcharge/del", method = RequestMethod.POST)
	public ResultBean delFinanceSubcharge(FinanceSubcharge financeSubcharge, HttpServletRequest request) {
		return financeService.deleteFinanceSubcharge(financeSubcharge);
	}
	
	@RequestMapping(value = "/financeSubcharge/export")
	public  ResponseEntity<byte[]> exportFinanceSubchargeList(FinanceSubcharge financeSubcharge, HttpServletRequest request) {
		try {
			
			List<FinanceSubcharge> list = financeService.getFinanceSubchargeExport(financeSubcharge);
			
			
			Map<Integer, MapDTO> area = areaService.getMap(null);
			
			Map<Integer, MapDTO> store = storeService.getMap(null);
			
			for(FinanceSubcharge fs : list) {
				fs.setAreastr(area.get(fs.getAreaid())!=null?area.get(fs.getAreaid()).getName():"");
				fs.setStorestr(store.get(fs.getStoreid())!=null?store.get(fs.getStoreid()).getName():"");
			}
			ExportParams params = new ExportParams("代收费用记录", "导出数据", ExcelType.XSSF);//title sheetname 文件格式
	    	Workbook workbook = ExcelExportUtil.exportExcel(params, FinanceSubcharge.class, list);
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    	workbook.write(os);
	        HttpHeaders headers = new HttpHeaders();    
	        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String fileName = new String(("代收费用记录" + time + ".xlsx").getBytes("UTF-8"),"iso-8859-1"); //生成文件名
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        return new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.CREATED);    
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/financeDeposit/list", method = RequestMethod.GET)
	public ResultBean getFinanceDepositList(FinanceDeposit financeDeposit, HttpServletRequest request) {
		return financeService.getFinanceDepositList(financeDeposit);
	}
	
	@RequestMapping(value = "/financeDeposit/add", method = RequestMethod.POST)
	public ResultBean addFinanceSubcharge(FinanceDeposit financeDeposit, HttpServletRequest request) {
		return financeService.addFinanceDeposit(financeDeposit);
	}
	
	@RequestMapping(value = "/financeDeposit/update", method = RequestMethod.POST)
	public ResultBean updateFinanceDeposit(FinanceDeposit financeDeposit, HttpServletRequest request, 
			@RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm) {
		Boolean isConfirm = false;
		if (confirm == 1)
			isConfirm = true;
		return financeService.updateFinanceDeposit(financeDeposit, confirm);
	}
	
	@RequestMapping(value = "/financeDeposit/del", method = RequestMethod.POST)
	public ResultBean delFinanceDeposit(FinanceDeposit financeDeposit, HttpServletRequest request) {
		return financeService.deleteFinanceDeposit(financeDeposit);
	}
	
	@RequestMapping(value = "/financeDeposit/export")
	public  ResponseEntity<byte[]> exportFinanceDepositList(FinanceDeposit financeDeposit, HttpServletRequest request) {
		try {
			
			List<FinanceDeposit> list = financeService.getFinanceDepositExport(financeDeposit);
			
			Map<Integer, MapDTO> area = areaService.getMap(null);
			
			Map<Integer, MapDTO> store = storeService.getMap(null);
			for(FinanceDeposit fd : list) {
				fd.setAreastr(area.get(fd.getAreaid())!=null?area.get(fd.getAreaid()).getName():"");
				fd.setStorestr(store.get(fd.getStoreid())!=null?store.get(fd.getStoreid()).getName():"");
			}
			ExportParams params = new ExportParams("现金存款记录", "导出数据", ExcelType.XSSF);//title sheetname 文件格式
	    	Workbook workbook = ExcelExportUtil.exportExcel(params, FinanceDeposit.class, list);
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    	workbook.write(os);
	        HttpHeaders headers = new HttpHeaders();    
	        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String fileName = new String(("现金存款记录" + time + ".xlsx").getBytes("UTF-8"),"iso-8859-1"); //生成文件名
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        return new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.CREATED);    
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/financeReceipt/list", method = RequestMethod.GET)
	public ResultBean getFinanceReceiptList(FinanceReceipt financeReceipt, HttpServletRequest request) {
		return financeService.getFinanceReceiptList(financeReceipt);
	}
	
	@RequestMapping(value = "/financeReceipt/add", method = RequestMethod.POST)
	public ResultBean addFinanceReceipt(FinanceReceipt financeReceipt, HttpServletRequest request) {
		return financeService.addFinanceReceipt(financeReceipt);
	}
	
	@RequestMapping(value = "/financeReceipt/update", method = RequestMethod.POST)
	public ResultBean updateFinanceReceipt(FinanceReceipt financeReceipt, HttpServletRequest request) {
		return financeService.updateFinanceReceipt(financeReceipt);
	}
	
	@RequestMapping(value = "/financeReceipt/apply", method = RequestMethod.POST)
	public ResultBean updateFinanceReceiptApply(FinanceReceipt financeReceipt, HttpServletRequest request) {
		Boolean isreview = false;
		int state = financeReceipt.getModifystate();
		if (state != 1) {
			isreview = true;
		}
		String bussinessid = (String) request.getSession().getAttribute(
				ConstantUtil.SESSION_BUSINESS);
		return financeService.updateFinanceReceiptApply(financeReceipt, isreview, bussinessid);
	}
	
	@RequestMapping(value = "/financeReceipt/invoiceState", method = RequestMethod.POST)
	public ResultBean updateFinanceReceiptInvoiceState(FinanceReceipt financeReceipt, HttpServletRequest request) {
		return financeService.updateFinanceReceiptInvoiceState(financeReceipt);
	}
	
	@RequestMapping(value = "/financeReceipt/confirm", method = RequestMethod.POST)
	public ResultBean updateFinanceReceiptConfirm(FinanceReceipt financeReceipt, HttpServletRequest request) {
		return financeService.updateFinanceReceiptConfirm(financeReceipt);
	}
	
	@RequestMapping(value = "/financeReceipt/listIncome", method = RequestMethod.GET)
	public ResultBean listIncome(FinanceIncome financeIncome, HttpServletRequest request) {
		return financeService.selectIncome(financeIncome);
	}
	
	@RequestMapping(value = "/financeReceipt/export")
	public  ResponseEntity<byte[]> exportFinanceReceiptList(FinanceReceipt financeReceipt, HttpServletRequest request) {
		try {
			
			List<FinanceReceipt> list = financeService.getFinanceReceiptExport(financeReceipt);
			
			Map<Integer, MapDTO> area = areaService.getMap(null);
			
			Map<Integer, MapDTO> store = storeService.getMap(null);
			
			
			
			Map<Integer, MapDTO> subject = financeService.getMap(null);
			for(FinanceReceipt fr : list) {
				fr.setAreastr(area.get(fr.getAreaid())!=null?area.get(fr.getAreaid()).getName():"");
				fr.setStorestr(store.get(fr.getStoreid())!=null?store.get(fr.getStoreid()).getName():"");
				fr.setTypestr(subject.get(fr.getType())!=null?subject.get(fr.getType()).getName():"");
			}
			ExportParams params = new ExportParams("学员票据记录", "导出数据", ExcelType.XSSF);//title sheetname 文件格式
	    	Workbook workbook = ExcelExportUtil.exportExcel(params, FinanceReceipt.class, list);
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    	workbook.write(os);
	        HttpHeaders headers = new HttpHeaders();    
	        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String fileName = new String(("学员票据记录" + time + ".xlsx").getBytes("UTF-8"),"iso-8859-1"); //生成文件名
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        return new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.CREATED);    
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/financeReceipt/invoiceexport")
	public  ResponseEntity<byte[]> exportFinanceReceiptInvoice(FinanceReceipt financeReceipt, HttpServletRequest request) {
		try {
			
			List<FinanceInvoiceDTO> list = financeService.getFinanceInvoiceExport(financeReceipt);
			
			Map<Integer, MapDTO> area = areaService.getMap(null);
			
			Map<Integer, MapDTO> store = storeService.getMap(null);
			
			
			Map<Integer, MapDTO> subject = financeService.getMap(null);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			DecimalFormat df_bill = new DecimalFormat("0000");
			String prenum = sdf.format(new Date());
			int billnum = 1;
			for(FinanceInvoiceDTO fidto : list) {
				fidto.setAreastr(area.get(fidto.getAreaid())!=null?area.get(fidto.getAreaid()).getName():"");
				fidto.setStorestr(store.get(fidto.getStoreid())!=null?store.get(fidto.getStoreid()).getName():"");
				fidto.setTypestr(subject.get(fidto.getType())!=null?subject.get(fidto.getType()).getName():"");
				fidto.setBillnum(prenum + df_bill.format(billnum));
				fidto.setTaxrate((float) 0.03);
				fidto.setGoodscodeversion("12.0");
				fidto.setGoodscode("3079900000000000000");
				fidto.setIsdiscount("0");
				billnum++;
			}
			ExportParams params = new ExportParams(null, "财务机打发票表", ExcelType.XSSF);//title sheetname 文件格式
	    	Workbook workbook = ExcelExportUtil.exportExcel(params, FinanceInvoiceDTO.class, list);
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    	workbook.write(os);
	        HttpHeaders headers = new HttpHeaders();    
	        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String fileName = new String(("财务机打发票表" + time + ".xlsx").getBytes("UTF-8"),"iso-8859-1"); //生成文件名
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        return new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.CREATED);    
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/financeReceipt/import", method = RequestMethod.POST)
	public ResultBean importflownum(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws Exception {
		ResultBean rb = new ResultBean();
		List <FinanceInvoiceImport> list = null;
		try {
			list = ExcelImportUtil.importExcel(file.getInputStream(), FinanceInvoiceImport.class, new ImportParams());
		} catch (ExcelImportException e) {
			rb.setCode(100);
			rb.setMsg("数据解析错误,请检查导入数据模板!");
			return rb;
		}
		if (list != null && list.size() > 0) {
			financeService.addFinanceReceiptInvoice(list);
		}
		return rb;
	}
}
