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
import cn.com.liliyun.user.model.User;

public interface FinanceService {
	
	public ResultBean getFinancePosList(FinancePos financePos);
	
	public ResultBean getPoscompanyList(FinancePos financePos);
	
	public ResultBean addFinancePos(FinancePos financePos);
	
	public ResultBean updateFinancePos(FinancePos financePos);
	
	public ResultBean deleteFinancePos(FinancePos financePos);
	
	public List<FinancePos> getFinancePosExport(FinancePos financePos);
	
	public ResultBean getPosAccountList(PosAccount posAccount);

	public FinancePos getFinancePos(FinancePos financePos);
	
	public ResultBean addPosAccount(PosAccount posAccount);
	
	public ResultBean deletePosAccount(PosAccount posAccount);
	
	public ResultBean getCashAccountList(CashAccount cashAccount);
	
	public ResultBean addCashAccount(CashAccount cashAccount);
	
	public ResultBean deleteCashAccount(CashAccount cashAccount);

	public ResultBean getFinanceSubjectList(FinanceSubject financeSubject);
	
	public ResultBean addFinanceSubject(FinanceSubject financeSubject);
	
	public ResultBean deleteFinanceSubject(FinanceSubject financeSubject, Boolean isdel);

	public int updateFinanceSubject(FinanceSubject financeSubject);

	public ResultBean getFinanceSubchargeList(FinanceSubcharge financeSubcharge);

	public ResultBean addFinanceSubcharge(FinanceSubcharge financeSubcharge);

	public ResultBean updateFinanceSubcharge(FinanceSubcharge financeSubcharge);

	public ResultBean deleteFinanceSubcharge(FinanceSubcharge financeSubcharge);

	public List<FinanceSubcharge> getFinanceSubchargeExport(FinanceSubcharge financeSubcharge);

	public ResultBean getFinanceDepositList(FinanceDeposit financeDeposit);

	public ResultBean addFinanceDeposit(FinanceDeposit financeDeposit);

	public ResultBean updateFinanceDeposit(FinanceDeposit financeDeposit, Integer isConfirm);

	public ResultBean deleteFinanceDeposit(FinanceDeposit financeDeposit);

	public List<FinanceDeposit> getFinanceDepositExport(FinanceDeposit financeDeposit);

	public ResultBean selectIncome(FinanceIncome financeIncome);

	public ResultBean getFinanceReceiptList(FinanceReceipt financeReceipt);

	public ResultBean addFinanceReceipt(FinanceReceipt financeReceipt);

	public ResultBean updateFinanceReceipt(FinanceReceipt financeReceipt);

	public ResultBean updateFinanceReceiptApply(FinanceReceipt financeReceipt, Boolean isreview, String businessid);

	public ResultBean updateFinanceReceiptInvoiceState(FinanceReceipt financeReceipt);

	public List<FinanceReceipt> getFinanceReceiptExport(FinanceReceipt financeReceipt);

	public Map<Integer, MapDTO> getMap(FinanceSubject financeSubject);

	public List<FinanceInvoiceDTO> getFinanceInvoiceExport(FinanceReceipt financeReceipt);

	public ResultBean addFinanceReceiptInvoice(List <FinanceInvoiceImport> list);

	public ResultBean updateFinanceReceiptConfirm(FinanceReceipt financeReceipt);
	
	public ResultBean getFinanceCashStatList(FinanceDeposit financeDeposit);
	
	public ResultBean addFinancePosFlow(Workbook workbook, Integer type) throws ParseException;
	
	public ResultBean getFinancePosFlowList(FinancePosFlow financePosFlow);
	
	public ResultBean getStorePayStatist(FinanceReceipt financeReceipt);

	public ResultBean getPosbanknameList(FinancePos financePos);
	
	public ResultBean getBankPosStoreStatList(FinanceReceipt financeReceipt);
	
	public ResultBean getStoreTypeStatList(FinanceReceipt financeReceipt);
	
	public ResultBean getTypeStoreStatList(FinanceReceipt financeReceipt);
	
	public ResultBean getCompanyPosStoreStatList(FinanceReceipt financeReceipt);
	
	public ResultBean getApplyStatList(StudentApplyStat studentApplyStat);
	
	/**
	 * ����ͳ��
	 * @param financeAppStat
	 * @param user
	 * @return
	 */
	public Map<String, Object> getIncomeStat(FinanceAppStat financeAppStat);
	
	/**
	 * ֧��ͳ��
	 * @param financeAppStat
	 * @param user
	 * @return
	 */
	public Map<String, Object> getOutcomeStat(FinanceAppStat financeAppStat);
	
	/**
	 * Ƿ��ͳ��
	 * @param financeAppStat
	 * @param user
	 * @return
	 */
	public Map<String, Object> getOwemoneyStat(FinanceAppStat financeAppStat);
}
