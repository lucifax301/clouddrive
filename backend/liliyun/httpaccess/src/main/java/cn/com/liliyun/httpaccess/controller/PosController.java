package cn.com.liliyun.httpaccess.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.finance.model.CashAccount;
import cn.com.liliyun.finance.model.FinancePos;
import cn.com.liliyun.finance.model.PosAccount;
import cn.com.liliyun.finance.service.FinanceService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.user.model.User;

@Controller
@ResponseBody
@RequestMapping(value = "/pos")
public class PosController extends BaseController {

	@Autowired
	private FinanceService financeService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private StoreService storeService;
	
	@RequestMapping(value = "/posAccountList", method = RequestMethod.GET)
	public ResultBean getPosAccountList(PosAccount posAccount, HttpServletRequest request) {
		return financeService.getPosAccountList(posAccount);
	}
	
	@RequestMapping(value = "/posAccountAll", method = RequestMethod.GET)
	public ResultBean getPosAccountAll(PosAccount posAccount, HttpServletRequest request) {
		posAccount.setPageNo(-1);
		return financeService.getPosAccountList(posAccount);
	}
	
	@RequestMapping(value = "/addPosAccount", method = RequestMethod.POST)
	public ResultBean addPosAccount(PosAccount posAccount, HttpServletRequest request) {
		return financeService.addPosAccount(posAccount);
	}
	
	@RequestMapping(value = "/delPosAccount", method = RequestMethod.POST)
	public ResultBean delPosAccount(PosAccount posAccount, HttpServletRequest request) {
		return financeService.deletePosAccount(posAccount);
	}
	
	@RequestMapping(value = "/cashAccountList", method = RequestMethod.GET)
	public ResultBean getCashAccountList(CashAccount cashAccount, HttpServletRequest request) {
		return financeService.getCashAccountList(cashAccount);
	}
	
	@RequestMapping(value = "/cashAccountAll", method = RequestMethod.GET)
	public ResultBean getCashAccountAll(CashAccount cashAccount, HttpServletRequest request) {
		cashAccount.setPageNo(-1);
		return financeService.getCashAccountList(cashAccount);
	}
	
	@RequestMapping(value = "/addCashAccount", method = RequestMethod.POST)
	public ResultBean addCashAccount(CashAccount cashAccount, HttpServletRequest request) {
		return financeService.addCashAccount(cashAccount);
	}
	
	@RequestMapping(value = "/delCashAccount", method = RequestMethod.POST)
	public ResultBean delCashAccount(CashAccount cashAccount, HttpServletRequest request) {
		return financeService.deleteCashAccount(cashAccount);
	}
	
	@RequestMapping(value = "/financePosList", method = RequestMethod.GET)
	public ResultBean getFinancePosList(FinancePos financePos, HttpServletRequest request) {
		return financeService.getFinancePosList(financePos);
	}
	
	@RequestMapping(value = "/financePosALL", method = RequestMethod.GET)
	public ResultBean getFinancePosALL(FinancePos financePos, HttpServletRequest request) {
		financePos.setPageNo(-1);
		return financeService.getFinancePosList(financePos);
	}
	
	@RequestMapping(value = "/poscompanyALL", method = RequestMethod.GET)
	public ResultBean getPoscompanyALL(FinancePos financePos, HttpServletRequest request) {
		return financeService.getPoscompanyList(financePos);
	}
	
	@RequestMapping(value = "/posbanknameALL", method = RequestMethod.GET)
	public ResultBean getPosbanknameALL(FinancePos financePos, HttpServletRequest request) {
		return financeService.getPosbanknameList(financePos);
	}
	
	@RequestMapping(value = "/updateFinancePos", method = RequestMethod.POST)
	public ResultBean updateFinancePos(FinancePos financePos, HttpServletRequest request) {
		return financeService.updateFinancePos(financePos);
	}
	
	@RequestMapping(value = "/addFinancePos", method = RequestMethod.POST)
	public ResultBean addFinancePos(FinancePos financePos, HttpServletRequest request) {
		return financeService.addFinancePos(financePos);
	}
	
	@RequestMapping(value = "/delFinancePos", method = RequestMethod.POST)
	public ResultBean delFinancePos(FinancePos financePos, HttpServletRequest request) {
		return financeService.deleteFinancePos(financePos);
	}
	
	@RequestMapping(value = "/getFinancePosExport")
	public ResponseEntity<byte[]> getFinancePosExport(FinancePos financePos, HttpServletRequest request) {
		try {
			List<FinancePos> list = financeService.getFinancePosExport(financePos);
			User user = (User) request.getSession().getAttribute(ConstantUtil.USER_SESSION);
			Area pa=new Area();
			pa.setDblink(user.getDblink());
			Map<Integer, MapDTO> area = areaService.getMap(pa);
			Store ps=new Store();
			ps.setDblink(user.getDblink());
			Map<Integer, MapDTO> store = storeService.getMap(ps);
			
			for(FinancePos fp : list) {
				fp.setAreastr(area.get(fp.getAreaid())!=null?area.get(fp.getAreaid()).getName():"");
				fp.setStorestr(store.get(fp.getStoreid())!=null?store.get(fp.getStoreid()).getName():"");
			}
			ExportParams params = new ExportParams("Pos机记录", "导出数据", ExcelType.XSSF);//title sheetname 文件格式
	    	Workbook workbook = ExcelExportUtil.exportExcel(params, FinancePos.class, list);
	    	ByteArrayOutputStream os = new ByteArrayOutputStream();
	    	workbook.write(os);
	        HttpHeaders headers = new HttpHeaders();    
	        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	        String fileName = new String(("Pos机记录" + time + ".xlsx").getBytes("UTF-8"),"iso-8859-1"); //生成文件名
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        return new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.CREATED);    
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
