package cn.com.liliyun.httpaccess.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.finance.model.CashAccount;
import cn.com.liliyun.finance.model.FinancePos;
import cn.com.liliyun.finance.model.PosAccount;
import cn.com.liliyun.finance.service.FinanceService;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.StoreService;

@Controller
@ResponseBody
@RequestMapping(value = "/pos")
public class PosController extends ExportController {

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
	public ResponseEntity<byte[]> getFinancePosExport(FinancePos financePos, HttpServletRequest request) throws IOException {
		
			List<FinancePos> list = financeService.getFinancePosExport(financePos);
			
			Map<Integer, MapDTO> area = areaService.getMap(null);
			
			Map<Integer, MapDTO> store = storeService.getMap(null);
			
			for(FinancePos fp : list) {
				fp.setAreastr(area.get(fp.getAreaid())!=null?area.get(fp.getAreaid()).getName():"");
				fp.setStorestr(store.get(fp.getStoreid())!=null?store.get(fp.getStoreid()).getName():"");
			}
			return this.export("Pos机记录", "Pos机记录", "导出数据", list, FinancePos.class);
			
	}
}
