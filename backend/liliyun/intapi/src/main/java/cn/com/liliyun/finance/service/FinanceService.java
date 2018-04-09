package cn.com.liliyun.finance.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.finance.model.CashAccount;
import cn.com.liliyun.finance.model.FinanceAppStat;
import cn.com.liliyun.finance.model.FinanceDeposit;
import cn.com.liliyun.finance.model.FinanceIncome;
import cn.com.liliyun.finance.model.FinanceInvoiceDTO;
import cn.com.liliyun.finance.model.FinanceInvoiceImport;
import cn.com.liliyun.finance.model.FinancePos;
import cn.com.liliyun.finance.model.FinancePosFlow;
import cn.com.liliyun.finance.model.FinanceReceipt;
import cn.com.liliyun.finance.model.FinanceSubcharge;
import cn.com.liliyun.finance.model.FinanceSubject;
import cn.com.liliyun.finance.model.PosAccount;
import cn.com.liliyun.student.dto.StudentApplyStat;

public interface FinanceService {
	
	ResultBean getFinancePosList(FinancePos financePos);
	
	ResultBean getPoscompanyList(FinancePos financePos);
	
	ResultBean addFinancePos(FinancePos financePos);
	
	ResultBean updateFinancePos(FinancePos financePos);
	
	ResultBean deleteFinancePos(FinancePos financePos);
	
	List<FinancePos> getFinancePosExport(FinancePos financePos);
	
	ResultBean getPosAccountList(PosAccount posAccount);

	FinancePos getFinancePos(FinancePos financePos);
	
	ResultBean addPosAccount(PosAccount posAccount);
	
	ResultBean deletePosAccount(PosAccount posAccount);
	
	ResultBean getCashAccountList(CashAccount cashAccount);
	
	ResultBean addCashAccount(CashAccount cashAccount);
	
	ResultBean deleteCashAccount(CashAccount cashAccount);

	ResultBean getFinanceSubjectList(FinanceSubject financeSubject);
	
	ResultBean addFinanceSubject(FinanceSubject financeSubject);
	
	ResultBean deleteFinanceSubject(FinanceSubject financeSubject, Boolean isdel);

	int updateFinanceSubject(FinanceSubject financeSubject);

	ResultBean getFinanceSubchargeList(FinanceSubcharge financeSubcharge);

	ResultBean addFinanceSubcharge(FinanceSubcharge financeSubcharge);

	ResultBean updateFinanceSubcharge(FinanceSubcharge financeSubcharge);

	ResultBean deleteFinanceSubcharge(FinanceSubcharge financeSubcharge);

	List<FinanceSubcharge> getFinanceSubchargeExport(FinanceSubcharge financeSubcharge);

	ResultBean getFinanceDepositList(FinanceDeposit financeDeposit);

	ResultBean addFinanceDeposit(FinanceDeposit financeDeposit);

	ResultBean updateFinanceDeposit(FinanceDeposit financeDeposit, Integer isConfirm);

	ResultBean deleteFinanceDeposit(FinanceDeposit financeDeposit);

	List<FinanceDeposit> getFinanceDepositExport(FinanceDeposit financeDeposit);

	ResultBean selectIncome(FinanceIncome financeIncome);

	ResultBean getFinanceReceiptList(FinanceReceipt financeReceipt);

	ResultBean addFinanceReceipt(FinanceReceipt financeReceipt);

	ResultBean updateFinanceReceipt(FinanceReceipt financeReceipt);

	ResultBean updateFinanceReceiptApply(FinanceReceipt financeReceipt, Boolean isreview, String businessid);

	ResultBean updateFinanceReceiptInvoiceState(FinanceReceipt financeReceipt);

	List<FinanceReceipt> getFinanceReceiptExport(FinanceReceipt financeReceipt);

	Map<Integer, MapDTO> getMap(FinanceSubject financeSubject);

	List<FinanceInvoiceDTO> getFinanceInvoiceExport(FinanceReceipt financeReceipt);

	ResultBean addFinanceReceiptInvoice(List <FinanceInvoiceImport> list);

	ResultBean updateFinanceReceiptConfirm(FinanceReceipt financeReceipt);
	
	ResultBean getFinanceCashStatList(FinanceDeposit financeDeposit);
	
	ResultBean addFinancePosFlow(Workbook workbook, Integer type) throws ParseException;
	
	ResultBean getFinancePosFlowList(FinancePosFlow financePosFlow);
	
	ResultBean getStorePayStatist(FinanceReceipt financeReceipt);

	ResultBean getPosbanknameList(FinancePos financePos);
	
	ResultBean getBankPosStoreStatList(FinanceReceipt financeReceipt);
	
	ResultBean getStoreTypeStatList(FinanceReceipt financeReceipt);
	
	ResultBean getTypeStoreStatList(FinanceReceipt financeReceipt);
	
	ResultBean getCompanyPosStoreStatList(FinanceReceipt financeReceipt);
	
	ResultBean getApplyStatList(StudentApplyStat studentApplyStat);
	
	/**
	 * ����ͳ��
	 * @param financeAppStat
	 * @param user
	 * @return
	 */
	Map<String, Object> getIncomeStat(FinanceAppStat financeAppStat);
	
	/**
	 * ֧��ͳ��
	 * @param financeAppStat
	 * @param user
	 * @return
	 */
	Map<String, Object> getOutcomeStat(FinanceAppStat financeAppStat);
	
	/**
	 * Ƿ��ͳ��
	 * @param financeAppStat
	 * @param user
	 * @return
	 */
	Map<String, Object> getOwemoneyStat(FinanceAppStat financeAppStat);
}
