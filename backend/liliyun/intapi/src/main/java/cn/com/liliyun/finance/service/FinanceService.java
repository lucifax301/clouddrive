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
	
	public ResultBean getFinancePosList(FinancePos financePos, User user);
	
	public ResultBean getPoscompanyList(FinancePos financePos, User user);
	
	public ResultBean addFinancePos(FinancePos financePos, User user);
	
	public ResultBean updateFinancePos(FinancePos financePos, User user);
	
	public ResultBean deleteFinancePos(FinancePos financePos, User user);
	
	public List<FinancePos> getFinancePosExport(FinancePos financePos, User user);
	
	public ResultBean getPosAccountList(PosAccount posAccount, User user);

	public FinancePos getFinancePos(FinancePos financePos, User user);
	
	public ResultBean addPosAccount(PosAccount posAccount, User user);
	
	public ResultBean deletePosAccount(PosAccount posAccount, User user);
	
	public ResultBean getCashAccountList(CashAccount cashAccount, User user);
	
	public ResultBean addCashAccount(CashAccount cashAccount, User user);
	
	public ResultBean deleteCashAccount(CashAccount cashAccount, User user);

	public ResultBean getFinanceSubjectList(FinanceSubject financeSubject, User user);
	
	public ResultBean addFinanceSubject(FinanceSubject financeSubject, User user);
	
	public ResultBean deleteFinanceSubject(FinanceSubject financeSubject, User user, Boolean isdel);

	public int updateFinanceSubject(FinanceSubject financeSubject, User user);

	public ResultBean getFinanceSubchargeList(FinanceSubcharge financeSubcharge, User user);

	public ResultBean addFinanceSubcharge(FinanceSubcharge financeSubcharge, User user);

	public ResultBean updateFinanceSubcharge(FinanceSubcharge financeSubcharge, User user);

	public ResultBean deleteFinanceSubcharge(FinanceSubcharge financeSubcharge, User user);

	public List<FinanceSubcharge> getFinanceSubchargeExport(FinanceSubcharge financeSubcharge, User user);

	public ResultBean getFinanceDepositList(FinanceDeposit financeDeposit, User user);

	public ResultBean addFinanceDeposit(FinanceDeposit financeDeposit, User user);

	public ResultBean updateFinanceDeposit(FinanceDeposit financeDeposit, User user, Integer isConfirm);

	public ResultBean deleteFinanceDeposit(FinanceDeposit financeDeposit, User user);

	public List<FinanceDeposit> getFinanceDepositExport(FinanceDeposit financeDeposit, User user);

	public ResultBean selectIncome(FinanceIncome financeIncome,User user);

	public ResultBean getFinanceReceiptList(FinanceReceipt financeReceipt, User user);

	public ResultBean addFinanceReceipt(FinanceReceipt financeReceipt, User user);

	public ResultBean updateFinanceReceipt(FinanceReceipt financeReceipt, User user);

	public ResultBean updateFinanceReceiptApply(FinanceReceipt financeReceipt, User user, Boolean isreview, String businessid);

	public ResultBean updateFinanceReceiptInvoiceState(FinanceReceipt financeReceipt, User user);

	public List<FinanceReceipt> getFinanceReceiptExport(FinanceReceipt financeReceipt, User user);

	public Map<Integer, MapDTO> getMap(FinanceSubject financeSubject);

	public List<FinanceInvoiceDTO> getFinanceInvoiceExport(FinanceReceipt financeReceipt, User user);

	public ResultBean addFinanceReceiptInvoice(List <FinanceInvoiceImport> list, User user);

	public ResultBean updateFinanceReceiptConfirm(FinanceReceipt financeReceipt, User user);
	
	public ResultBean getFinanceCashStatList(FinanceDeposit financeDeposit, User user);
	
	public ResultBean addFinancePosFlow(Workbook workbook, Integer type, User user) throws ParseException;
	
	public ResultBean getFinancePosFlowList(FinancePosFlow financePosFlow, User user);
	
	public ResultBean getStorePayStatist(FinanceReceipt financeReceipt, User user);

	public ResultBean getPosbanknameList(FinancePos financePos, User user);
	
	public ResultBean getBankPosStoreStatList(FinanceReceipt financeReceipt, User user);
	
	public ResultBean getStoreTypeStatList(FinanceReceipt financeReceipt, User user);
	
	public ResultBean getTypeStoreStatList(FinanceReceipt financeReceipt, User user);
	
	public ResultBean getCompanyPosStoreStatList(FinanceReceipt financeReceipt, User user);
	
	public ResultBean getApplyStatList(StudentApplyStat studentApplyStat, User user);
	
	/**
	 * ����ͳ��
	 * @param financeAppStat
	 * @param user
	 * @return
	 */
	public Map<String, Object> getIncomeStat(FinanceAppStat financeAppStat, User user);
	
	/**
	 * ֧��ͳ��
	 * @param financeAppStat
	 * @param user
	 * @return
	 */
	public Map<String, Object> getOutcomeStat(FinanceAppStat financeAppStat, User user);
	
	/**
	 * Ƿ��ͳ��
	 * @param financeAppStat
	 * @param user
	 * @return
	 */
	public Map<String, Object> getOwemoneyStat(FinanceAppStat financeAppStat, User user);
}
