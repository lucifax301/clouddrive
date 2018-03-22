package cn.com.liliyun.finance.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.FeeSubject;
import cn.com.liliyun.common.util.HttpConstant;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.finance.mapper.CashAccountMapper;
import cn.com.liliyun.finance.mapper.FinanceAppStatMapper;
import cn.com.liliyun.finance.mapper.FinanceDepositMapper;
import cn.com.liliyun.finance.mapper.FinancePosFlowMapper;
import cn.com.liliyun.finance.mapper.FinancePosMapper;
import cn.com.liliyun.finance.mapper.FinanceReceiptMapper;
import cn.com.liliyun.finance.mapper.FinanceSubchargeMapper;
import cn.com.liliyun.finance.mapper.FinanceSubjectMapper;
import cn.com.liliyun.finance.mapper.PosAccountMapper;
import cn.com.liliyun.finance.model.CashAccount;
import cn.com.liliyun.finance.model.FinanceAppStat;
import cn.com.liliyun.finance.model.FinanceDeposit;
import cn.com.liliyun.finance.model.FinanceIncome;
import cn.com.liliyun.finance.model.FinanceInvoiceDTO;
import cn.com.liliyun.finance.model.FinanceInvoiceImport;
import cn.com.liliyun.finance.model.FinancePos;
import cn.com.liliyun.finance.model.FinancePosFlow;
import cn.com.liliyun.finance.model.FinancePosStat;
import cn.com.liliyun.finance.model.FinanceReceipt;
import cn.com.liliyun.finance.model.FinanceStat;
import cn.com.liliyun.finance.model.FinanceSubcharge;
import cn.com.liliyun.finance.model.FinanceSubject;
import cn.com.liliyun.finance.model.PosAccount;
import cn.com.liliyun.finance.service.FinanceService;
import cn.com.liliyun.flow.model.Flow;
import cn.com.liliyun.flow.service.FlowService;
import cn.com.liliyun.student.dto.StudentApplyStat;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.model.StudentMoney;
import cn.com.liliyun.student.service.StudentService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.ClassinfoService;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.user.model.User;

import com.github.pagehelper.PageInfo;

@Service
public class FinanceServiceImpl implements FinanceService {

	@Autowired
	private FinancePosMapper financePosMapper;
	
	@Autowired
	private PosAccountMapper posAccountMapper;
	
	@Autowired
	private CashAccountMapper cashAccountMapper;

	@Autowired
	private FinanceSubjectMapper financeSubjectMapper;
	
	@Autowired
	private FinanceSubchargeMapper financeSubchargeMapper;
	
	@Autowired
	private FinanceDepositMapper financeDepositMapper;
	
	@Autowired
	private FinanceReceiptMapper financeReceiptMapper;
	
	@Autowired
	private FinancePosFlowMapper financePosFlowMapper;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ClassinfoService classinfoService;
	
	@Autowired
	private FlowService flowService;
	@Autowired
	private FinanceAppStatMapper financeAppStatMapper;
	
	@Override
	public ResultBean getFinancePosList(FinancePos financePos, User user) {
		ResultBean r = new ResultBean();
		
		financePos.setDblink(user.getDblink());
		PageUtil.startPage(financePos);
		List<FinancePos> list = financePosMapper.selectList(financePos);
		r.setResult(new PageInfo<>(list));
		
		return r;
	}

	@Override
	public ResultBean addFinancePos(FinancePos financePos, User user) {
		ResultBean r = new ResultBean();
		
		financePos.setDblink(user.getDblink());
		FinancePos fp = financePosMapper.selectByPosnum(financePos);
		if (fp != null){
			return r;
		}
		financePos.setCuid(user.getId());
		financePos.setCname(user.getRealname());
		financePos.setCtime(new Date());
		financePosMapper.insertSelective(financePos);
		
		return r;
	}

	@Override
	public ResultBean updateFinancePos(FinancePos financePos, User user) {
		ResultBean r = new ResultBean();
		
		financePos.setDblink(user.getDblink());
		FinancePos fp = financePosMapper.selectByPosnum(financePos);
		if (fp != null){
			return r;
		}
		financePos.setMuid(user.getId());
		financePos.setMname(user.getRealname());
		financePos.setMtime(new Date());
		financePosMapper.updateByPrimaryKeySelective(financePos);
		
		return r;
	}

	@Override
	public ResultBean deleteFinancePos(FinancePos financePos, User user) {
		ResultBean r = new ResultBean();
		
		financePos.setDblink(user.getDblink());
		financePos.setMuid(user.getId());
		financePos.setMname(user.getRealname());
		financePos.setMtime(new Date());
		financePosMapper.deleteByIds(financePos);
		
		return r;
	}

	@Override
	public List<FinancePos> getFinancePosExport(FinancePos financePos, User user) {
		List<FinancePos> r = null;
		
		financePos.setDblink(user.getDblink());
		r = financePosMapper.selectList(financePos);
		
		return r;
	}

	@Override
	public ResultBean getPosAccountList(PosAccount posAccount, User user) {
		ResultBean r = new ResultBean();
		
		posAccount.setDblink(user.getDblink());
		PageUtil.startPage(posAccount);
		List<PosAccount> list = posAccountMapper.selectList(posAccount);
		r.setResult(new PageInfo<>(list));
		
		return r;
	}

	@Override
	public FinancePos getFinancePos(FinancePos financePos, User user) {
		FinancePos pos = new FinancePos();
		pos.setDblink(user.getDblink());
		pos.setId(financePos.getId());
		return financePosMapper.selectByPrimaryKey(pos);
	}

	@Override
	public ResultBean addPosAccount(PosAccount posAccount, User user) {
		ResultBean r = new ResultBean();
		
		posAccount.setDblink(user.getDblink());
		PosAccount pa = posAccountMapper.selectByAccount(posAccount);
		if (pa != null) {
			return r;
		}
		posAccount.setCuid(user.getId());
		posAccount.setCname(user.getRealname());
		posAccount.setCtime(new Date());
		posAccountMapper.insertSelective(posAccount);
		
		return r;
	}

	@Override
	public ResultBean deletePosAccount(PosAccount posAccount, User user) {
		ResultBean r = new ResultBean();
		
		posAccount.setDblink(user.getDblink());
		posAccount.setMuid(user.getId());
		posAccount.setMname(user.getRealname());
		posAccount.setMtime(new Date());
		posAccountMapper.deleteByIds(posAccount);
		
		return r;
	}

	@Override
	public ResultBean getCashAccountList(CashAccount cashAccount, User user) {
		ResultBean r = new ResultBean();
		
		cashAccount.setDblink(user.getDblink());
		PageUtil.startPage(cashAccount);
		List<CashAccount> list = cashAccountMapper.selectList(cashAccount);
		r.setResult(new PageInfo<>(list));
		return r;
	}

	@Override
	public ResultBean addCashAccount(CashAccount cashAccount, User user) {
		ResultBean r = new ResultBean();
		
		cashAccount.setDblink(user.getDblink());
		CashAccount ca = cashAccountMapper.selectByAccount(cashAccount);
		if (ca != null) {
			return r;
		}
		cashAccount.setCuid(user.getId());
		cashAccount.setCname(user.getRealname());
		cashAccount.setCtime(new Date());
		cashAccountMapper.insertSelective(cashAccount);
		
		return r;
	}

	@Override
	public ResultBean deleteCashAccount(CashAccount cashAccount, User user) {
		ResultBean r = new ResultBean();
		
		cashAccount.setDblink(user.getDblink());
		cashAccount.setMuid(user.getId());
		cashAccount.setMname(user.getRealname());
		cashAccount.setMtime(new Date());
		cashAccountMapper.deleteByIds(cashAccount);
		
		return r;
	}

	@Override
	public ResultBean getFinanceSubjectList(FinanceSubject financeSubject, User user) {
		ResultBean r = new ResultBean();
		
		financeSubject.setDblink(user.getDblink());
		List<FinanceSubject> list = financeSubjectMapper.selectList(financeSubject);
		r.setResult(new PageInfo<>(list));
		
		return r;
	}

	@Override
	public ResultBean deleteFinanceSubject(FinanceSubject financeSubject, User user, Boolean isdel) {
		ResultBean r = new ResultBean();
		
		FinanceSubject fs = new FinanceSubject();
		fs.setDblink(user.getDblink());
		fs.setId(financeSubject.getId());
		fs.setDisable(isdel?(byte) 1:(byte) 0);
		fs.setMuid(user.getId());
		fs.setMname(user.getRealname());
		fs.setMtime(new Date());
		financeSubjectMapper.updateByPrimaryKeySelective(fs);
			
		//20170410 去掉科目的级别
		//假如有子结点，一并禁用/启用
//		fs.setId(null);
//		fs.setPid(financeSubject.getId());
//		financeSubjectMapper.updateByModel(fs);
		
		return r;
	}

	@Override
	public ResultBean addFinanceSubject(FinanceSubject financeSubject, User user) {
		ResultBean r = new ResultBean();
		
		financeSubject.setDblink(user.getDblink());
		financeSubject.setCuid(user.getId());
		financeSubject.setCname(user.getRealname());
		financeSubject.setCtime(new Date());
		financeSubjectMapper.insertSelective(financeSubject);
		
		return r;
	}

	@Override
	public int updateFinanceSubject(FinanceSubject financeSubject, User user) {
		int r = 0;
		financeSubject.setDblink(user.getDblink());
		financeSubject.setMuid(user.getId());
		financeSubject.setMname(user.getRealname());
		financeSubject.setMtime(new Date());
		r = financeSubjectMapper.updateByModel(financeSubject);
		return r;
	}

	@Override
	public ResultBean getFinanceSubchargeList(FinanceSubcharge financeSubcharge, User user) {
		ResultBean r = new ResultBean();
		financeSubcharge.setDblink(user.getDblink());
		PageUtil.startPage(financeSubcharge);
		List<FinanceSubcharge> list = financeSubchargeMapper.selectList(financeSubcharge);
		r.setResult(new PageInfo<>(list));
		return r;
	}

	@Override
	public ResultBean addFinanceSubcharge(FinanceSubcharge financeSubcharge, User user) {
		ResultBean r = new ResultBean();
		if (financeSubcharge.getPosid() != null) {
			FinancePos fp = new FinancePos();
			fp.setId(financeSubcharge.getPosid());
			fp.setDblink(user.getDblink());
			FinancePos financePos = financePosMapper.selectByPrimaryKey(fp);
			if (financePos == null) {
				r.setCode(HttpConstant.DATA_ERROR_COCE);
				r.setMsg(HttpConstant.DATA_ERROR_MSG);
				return r;
			}
			financeSubcharge.setPosnum(financePos.getPosnum());
			financeSubcharge.setBankname(financePos.getBankname());
		}
		financeSubcharge.setDblink(user.getDblink());
		financeSubcharge.setAreaid(user.getAreaid());
		financeSubcharge.setStoreid(user.getStoreid());
		financeSubcharge.setCuid(user.getId());
		financeSubcharge.setCname(user.getRealname());
		financeSubcharge.setCtime(new Date());
		financeSubchargeMapper.insertSelective(financeSubcharge);
		return r;
	}

	@Override
	public ResultBean updateFinanceSubcharge(FinanceSubcharge financeSubcharge, User user) {
		ResultBean r = new ResultBean();
		if (financeSubcharge.getPosid() != null) {
			FinancePos fp = new FinancePos();
			fp.setId(financeSubcharge.getPosid());
			fp.setDblink(user.getDblink());
			FinancePos financePos = financePosMapper.selectByPrimaryKey(fp);
			if (financePos == null) {
				r.setCode(HttpConstant.DATA_ERROR_COCE);
				r.setMsg(HttpConstant.DATA_ERROR_MSG);
				return r;
			}
			financeSubcharge.setPosnum(financePos.getPosnum());
			financeSubcharge.setBankname(financePos.getBankname());
		}
		financeSubcharge.setDblink(user.getDblink());
		financeSubcharge.setMuid(user.getId());
		financeSubcharge.setMname(user.getRealname());
		financeSubcharge.setMtime(new Date());
		financeSubchargeMapper.updateByPrimaryKeySelective(financeSubcharge);
		return r;
	}

	@Override
	public ResultBean deleteFinanceSubcharge(FinanceSubcharge financeSubcharge, User user) {
		ResultBean r = new ResultBean();
		FinanceSubcharge fs = new FinanceSubcharge();
		fs.setDblink(user.getDblink());
		fs.setIds(financeSubcharge.getIds());
		fs.setMuid(user.getId());
		fs.setMname(user.getRealname());
		fs.setMtime(new Date());
		financeSubchargeMapper.deleteByIds(fs);
		return r;
	}

	@Override
	public List<FinanceSubcharge> getFinanceSubchargeExport(FinanceSubcharge financeSubcharge, User user) {
		financeSubcharge.setDblink(user.getDblink());
		List<FinanceSubcharge> list = financeSubchargeMapper.selectList(financeSubcharge);
		return list;
	}

	@Override
	public ResultBean getFinanceDepositList(FinanceDeposit financeDeposit, User user) {
		ResultBean r = new ResultBean();
		financeDeposit.setDblink(user.getDblink());
		PageUtil.startPage(financeDeposit);
		List<FinanceDeposit> list = financeDepositMapper.selectList(financeDeposit);
		r.setResult(new PageInfo<>(list));
		return r;
	}

	@Override
	public ResultBean addFinanceDeposit(FinanceDeposit financeDeposit, User user) {
		ResultBean r = new ResultBean();
		financeDeposit.setDblink(user.getDblink());
		financeDeposit.setAreaid(user.getAreaid());
		financeDeposit.setStoreid(user.getStoreid());
		financeDeposit.setIsconfirm((byte) 0);
		financeDeposit.setIsdel((byte) 0);
		financeDeposit.setCuid(user.getId());
		financeDeposit.setCname(user.getRealname());
		financeDepositMapper.insertSelective(financeDeposit);
		return r;
	}

	@Override
	public ResultBean updateFinanceDeposit(FinanceDeposit financeDeposit, User user, Integer isConfirm) {
		ResultBean r = new ResultBean();
		financeDeposit.setDblink(user.getDblink());
		financeDeposit.setMuid(user.getId());
		financeDeposit.setMname(user.getRealname());
		if (isConfirm == 1) {
			if (financeDeposit.getIds().trim().length() > 0) {
				FinanceDeposit fd = new FinanceDeposit();
				fd.setDblink(user.getDblink());
				fd.setMuid(user.getId());
				fd.setMname(user.getRealname());
				fd.setIds(financeDeposit.getIds());
				financeDepositMapper.updateConfirmByIds(fd);
			}
			return r;
		}
		financeDeposit.setMtime(new Date());
		financeDepositMapper.updateByPrimaryKeySelective(financeDeposit);
		return r;
	}

	@Override
	public ResultBean deleteFinanceDeposit(FinanceDeposit financeDeposit, User user) {
		ResultBean r = new ResultBean();
		FinanceDeposit fd = new FinanceDeposit();
		fd.setIds(financeDeposit.getIds());
		fd.setIsdel((byte) 1);
		fd.setDblink(user.getDblink());
		fd.setMtime(new Date());
		fd.setMuid(user.getId());
		fd.setMname(user.getRealname());
		financeDepositMapper.deleteByIds(fd);
		return r;
	}

	@Override
	public List<FinanceDeposit> getFinanceDepositExport(FinanceDeposit financeDeposit, User user) {
		financeDeposit.setDblink(user.getDblink());
		List<FinanceDeposit> list = financeDepositMapper.selectList(financeDeposit);
		return list;
	}

	@Override
	public ResultBean getFinanceReceiptList(FinanceReceipt financeReceipt, User user) {
		ResultBean r = new ResultBean();
		financeReceipt.setDblink(user.getDblink());
		PageUtil.startPage(financeReceipt);
		List<FinanceReceipt> list = financeReceiptMapper.selectList(financeReceipt);
		r.setResult(new PageInfo<>(list));
		return r;
	}

	@Override
	public ResultBean addFinanceReceipt(FinanceReceipt financeReceipt, User user) {
		ResultBean r = new ResultBean();
		Student student = null;
		if (financeReceipt.getStudentid() != null) {
			Student s = new Student();
			s.setId(financeReceipt.getStudentid());
			s.setDblink(user.getDblink());
			student = studentService.getStudent(s);
			if (student == null) {
				r.setCode(HttpConstant.ERROR_CODE);
				r.setMsg(HttpConstant.ERROR_MSG);
				return r;
			}
			financeReceipt.setAreaid(student.getAreaid());
			financeReceipt.setStoreid(student.getStoreid());
		}
		financeReceipt.setDblink(user.getDblink());
		financeReceipt.setAreaid(student.getAreaid());
		financeReceipt.setStoreid(student.getStoreid());
		financeReceipt.setCuid(user.getId());
		financeReceipt.setCtime(new Date());
		financeReceipt.setCname(user.getRealname());
		financeReceipt.setReceiptnum(String.valueOf((new Date()).getTime()));
		financeReceiptMapper.insertSelective(financeReceipt);
		return r;
	}

	@Override
	public ResultBean updateFinanceReceipt(FinanceReceipt financeReceipt, User user) {
		ResultBean r = new ResultBean();
		financeReceipt.setDblink(user.getDblink());
		FinanceReceipt fr = financeReceiptMapper.selectByPrimaryKey(financeReceipt);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (fr == null || fr.getIsconfirm() == 1) {
			return r;
		} else if (!sdf.format(fr.getCtime()).equals(sdf.format(new Date())) && fr.getModifystate() != 2) {
			return r;
		}
		if (fr.getModifystate() == 2) {
			financeReceipt.setModifystate((byte) 4);
		}
		financeReceipt.setMtime(new Date());
		financeReceipt.setMuid(user.getId());
		financeReceipt.setMname(user.getRealname());
		financeReceiptMapper.updateByPrimaryKeySelective(financeReceipt);
		return r;
	}

	@Override
	public ResultBean updateFinanceReceiptApply(FinanceReceipt financeReceipt, User user, Boolean isreview, String businessid) {
		ResultBean r = new ResultBean();
		financeReceipt.setDblink(user.getDblink());
		FinanceReceipt fr = financeReceiptMapper.selectByPrimaryKey(financeReceipt);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (fr == null || fr.getIsconfirm() == 1) {
			return r;
		} else if (sdf.format(fr.getCtime()).equals(sdf.format(new Date()))) {
			return r;
		} else if (!isreview && fr.getModifystate() != 0 && fr.getModifystate() != 4 && fr.getModifystate() != 3) {
			return r;
		} else if (isreview && fr.getModifystate() != 1) {
			return r;
		}
		if (!isreview) {
			fr.setModifystate((byte) 1);
			fr.setApplier(user.getRealname());
			fr.setApplierid(user.getId());
			fr.setApplydate(new Date());
			fr.setApplyreason(financeReceipt.getApplyreason());
			String desc = "学员[" + fr.getStuname() + "]票据修改申请";
			String transactionid = flowService.addFlow(businessid, user.getId(), desc,user);
			fr.setBusinessid(businessid);
			fr.setTransactionid(transactionid);
		} else {
			fr.setModifystate(financeReceipt.getModifystate());
			fr.setReviewer(user.getRealname());
			fr.setReviewerid(user.getId());
			fr.setReviewdate(new Date());
			fr.setReviewremark(financeReceipt.getReviewremark());
			
			Flow flow = flowService.getFlow(fr.getTransactionid(),user);
			flow.setDblink(user.getDblink());
			if (fr.getModifystate() == 2) { //申请通过
				boolean next = (flow != null) && flowService.auditFlow(flow, user.getId(), ConstantUtil.AUDIT_ACCEPT);
				if (next)
					return r;
			} else if (fr.getModifystate() == 3 && flow != null) { //申请拒绝
					flowService.auditFlow(flow, user.getId(), ConstantUtil.AUDIT_REJECT);
			}
		}
		financeReceiptMapper.updateByPrimaryKeySelective(fr);
		return r;
	}

	@Override
	public ResultBean updateFinanceReceiptInvoiceState(FinanceReceipt financeReceipt, User user) {
		ResultBean r = new ResultBean();
		int state = financeReceipt.getInvoicestate();
		FinanceReceipt fr = new FinanceReceipt();
		fr.setDblink(user.getDblink());
		fr.setId(financeReceipt.getId());
		fr.setInvoicestate((byte) state);
		switch (state) {
		case 1: 
			financeReceipt.setState1date(new Date());
			financeReceipt.setState1name(user.getRealname());
			financeReceipt.setState1uid(user.getId());
			break;
		case 2: 
			financeReceipt.setState2date(new Date());
			financeReceipt.setState2name(user.getRealname());
			financeReceipt.setState2uid(user.getId());
			break;
		case 3: 
			financeReceipt.setState3date(new Date());
			financeReceipt.setState3name(user.getRealname());
			financeReceipt.setState3uid(user.getId());
			break;
		case 4: 
			financeReceipt.setState4date(new Date());
			financeReceipt.setState4name(user.getRealname());
			financeReceipt.setState4uid(user.getId());
			break;
		default: 
			return r;
		}
		financeReceiptMapper.updateByPrimaryKeySelective(fr);
		return r;
	}
	
	@Override
	public List<FinanceReceipt> getFinanceReceiptExport(FinanceReceipt financeReceipt, User user) {
		financeReceipt.setDblink(user.getDblink());
		return financeReceiptMapper.selectList(financeReceipt);
	}

	@Override
	public Map<Integer, MapDTO> getMap(FinanceSubject financeSubject) {
		return financeSubjectMapper.getMap(financeSubject);
	}

	@Override
	public List<FinanceInvoiceDTO> getFinanceInvoiceExport(FinanceReceipt financeReceipt, User user) {
		if (financeReceipt.getIds() != null && !"".equals(financeReceipt.getIds())) {
			String ids = financeReceipt.getIds();
			financeReceipt = new FinanceReceipt();
			financeReceipt.setIds(ids);
		} else {
			financeReceipt.setIds(null);
		}
		//批次号规则：yyyyMMdd + 当天序号（三位数，不足补0）
		financeReceipt.setDblink(user.getDblink());
		String curbatchnum = financeReceiptMapper.selectMaxBatchnum(financeReceipt);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String prebatchnum = sdf.format(new Date());
		DecimalFormat df_batch = new DecimalFormat("000");
		int curnum = 1;
		if (curbatchnum != null && curbatchnum.substring(0, 8).equals(prebatchnum)) {
			curnum = Integer.parseInt(curbatchnum.substring(8)) + 1;
		} 
		financeReceipt.setNewbatchnum(prebatchnum + df_batch.format(curnum));
		financeReceipt.setDblink(user.getDblink());
		financeReceiptMapper.updateBatchnum(financeReceipt);
		return financeReceiptMapper.selectInvoiceExport(financeReceipt);
	}

	@Override
	public ResultBean addFinanceReceiptInvoice(List<FinanceInvoiceImport> list, User user) {
		ResultBean r = new ResultBean();
		for (FinanceInvoiceImport fii : list) {
			String[] remark = fii.getRemark().split("/");
			fii.setReceiptnum(remark[7]);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("dblink", user.getDblink());
		map.put("list", list);
		financeReceiptMapper.updateByReceiptnumBatch(map);
		return r;
	}
	
	@Override
	public ResultBean updateFinanceReceiptConfirm(FinanceReceipt financeReceipt, User user) {
		ResultBean r = new ResultBean();
		if (financeReceipt.getIds() == null || financeReceipt.getIds().trim().equals(""))
			return r;
		List<FinanceReceipt> list = financeReceiptMapper.selectList(financeReceipt);
		financeReceipt.setDblink(user.getDblink());
		financeReceipt.setIsconfirm((byte) 1);
		financeReceipt.setConfirmdate(new Date());
		financeReceipt.setConfirmname(user.getRealname());
		financeReceipt.setConfirmuid(user.getId());
		financeReceiptMapper.updateConfirmByIds(financeReceipt);
		
		String stuids = "";
		List<StudentMoney> stuList = new ArrayList<>();
		for (FinanceReceipt fr : list) {
			if (fr.getIsconfirm() == 1)
				continue;
			stuids += ("," + fr.getStudentid().toString()); 
			StudentMoney studentMoney = new StudentMoney();
			studentMoney.setStudentid(fr.getStudentid());
			if (fr.getType() == FeeSubject.REDUCE_FEE.getStatus()) 
				studentMoney.setOwesubmoney(fr.getReceiptmoney().negate());
			else if (fr.getType() == FeeSubject.TRAIN_FEE.getStatus()) 
				studentMoney.setOwetrainmoney(fr.getReceiptmoney().negate());
			else if (fr.getType() == FeeSubject.RESIT_FEE.getStatus()) 
				studentMoney.setOweresitmoney(fr.getReceiptmoney().negate());
			studentMoney.setPaymoney(fr.getReceiptmoney());
			studentMoney.setOwemoney(fr.getReceiptmoney().negate());
			stuList.add(studentMoney);
		}
		studentService.updateReceiptStudentMoney(stuList, stuids, user);
		return r;
	}

	@Override
	public ResultBean getFinanceCashStatList(FinanceDeposit financeDeposit, User user) {
		ResultBean r = new ResultBean();
		financeDeposit.setDblink(user.getDblink());
		List<FinanceDeposit> list = financeDepositMapper.selectList(financeDeposit);
		if (list == null || list.size() == 0) 
			return r;
		Area area=new Area();
		area.setDblink(user.getDblink());
		Map<Integer, MapDTO> areaMap = areaService.getMap(area);
		Store store=new Store();
		store.setDblink(user.getDblink());
		Map<Integer, MapDTO> storeMap = storeService.getMap(store);
		Map<String, LinkedList<FinanceStat>> map = new HashMap<>();
		for (FinanceDeposit fd : list) {
			String key = fd.getBankname() + "_" + fd.getAreaid().toString();
			if (map.get(key) == null) {
				LinkedList<FinanceStat> linkedList = new LinkedList<>();
				FinanceStat fs = new FinanceStat();
				fs.setBank(fd.getBankname());
				fs.setArea(areaMap.get(fd.getAreaid())!=null?areaMap.get(fd.getAreaid()).getName():fd.getAreaid().toString());
				fs.setAreaid(fd.getAreaid());
				fs.setStore("小计");
				fs.setStoreid(0);
				fs.setPrice(BigDecimal.ZERO);
				linkedList.add(fs);
				map.put(key, linkedList);
			}
			LinkedList<FinanceStat> linkedList = map.get(key);
			boolean isExist = false;
			for (FinanceStat fs : linkedList) {
				if (fs.getStoreid().intValue() == fd.getStoreid().intValue()) {
					fs.setPricePlus(fd.getAmount());
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				FinanceStat fs = new FinanceStat();
				fs.setBank(fd.getBankname());
				fs.setArea(areaMap.get(fd.getAreaid())!=null?areaMap.get(fd.getAreaid()).getName():fd.getAreaid().toString());
				fs.setAreaid(fd.getAreaid());
				fs.setStore(storeMap.get(fd.getStoreid())!=null?storeMap.get(fd.getStoreid()).getName():fd.getStoreid().toString());
				fs.setStoreid(fd.getStoreid());
				fs.setPrice(fd.getAmount());
				linkedList.addFirst(fs);
			}
			linkedList.getLast().setPricePlus(fd.getAmount());
		}
		Map<String, LinkedList<FinanceStat>> resultMap = new HashMap<>();
		for (String key : map.keySet()) {
			LinkedList<FinanceStat> linkedList = map.get(key);
			String bankname = key.split("_")[0];
			if (resultMap.get(bankname) == null) {
				FinanceStat fs = new FinanceStat();
				fs.setBank("");
				fs.setArea("合计");
				fs.setAreaid(0);
				fs.setStore("");
				fs.setPrice(linkedList.getLast().getPrice());
				linkedList.addLast(fs);
				resultMap.put(bankname, linkedList);
			} else {
				resultMap.get(bankname).getLast().setPricePlus(linkedList.getLast().getPrice());
				resultMap.get(bankname).addAll(0, linkedList);
			}
		}
		LinkedList<FinanceStat> result = new LinkedList<>();
		FinanceStat total = new FinanceStat();
		total.setBank("总计");
		total.setArea("");
		total.setStore("");
		total.setPrice(BigDecimal.ZERO);
		for (String key : resultMap.keySet()) {
			result.addAll(resultMap.get(key));
			total.setPricePlus(resultMap.get(key).getLast().getPrice());
		}
		result.addLast(total);
		r.setResult(new PageInfo<>(result));
		return r;
	}

	@Override
	public ResultBean getPoscompanyList(FinancePos financePos, User user) {
		ResultBean r = new ResultBean();
		financePos.setDblink(user.getDblink());
		List<FinancePos> list = financePosMapper.selectPoscompany(financePos);
		r.setResult(new PageInfo<>(list));
		return r;
	}
	
	@Override
	public ResultBean getPosbanknameList(FinancePos financePos, User user) {
		ResultBean r = new ResultBean();
		financePos.setDblink(user.getDblink());
		List<FinancePos> list = financePosMapper.selectPosbankname(financePos);
		r.setResult(new PageInfo<>(list));
		return r;
	}

	@Override
	public ResultBean addFinancePosFlow(Workbook workbook, Integer type, User user) throws ParseException {
		ResultBean r = new ResultBean(HttpConstant.ERROR_CODE, HttpConstant.ERROR_MSG);
		if (workbook == null || type < 1 || type > 3)
			return r;
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		Map<String, Object> map = new HashMap<>();
		map.put("dblink", user.getDblink());
		List<FinancePosFlow> list = new ArrayList<>();
		
		FinancePos financePos = new FinancePos();
		financePos.setDblink(user.getDblink());
		List<FinancePos> posList = financePosMapper.selectList(financePos);
		Map<String, FinancePos> posMap = new HashMap<>();
		for (FinancePos fp : posList) {
			posMap.put(fp.getPosnum(), fp);
		}
		/* type = 1 : 银联商务
		 * type = 2 : 北京技术
		 * type = 3 : 银盛
		 */
		if (type == 1) {
			while (rows.hasNext()) {
				Row row = rows.next();
				//第五行开始解析，行数从0开始
				if (row.getRowNum() > 3 && row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC && row.getCell(1).getNumericCellValue() > 35000) {
					FinancePosFlow fpf = new FinancePosFlow();
					FinancePos fp = posMap.get(row.getCell(3).getStringCellValue());
					if (fp != null) {
						fpf.setAreaid(fp.getAreaid());
						fpf.setStoreid(fp.getStoreid());
					}
					fpf.setPoscompany("银联商务");
					fpf.setTradedate(row.getCell(1).getDateCellValue());
					fpf.setPosnum(row.getCell(3).getStringCellValue());
					fpf.setTrademoney(new BigDecimal(row.getCell(4).getNumericCellValue()));
					fpf.setTradenum(row.getCell(8).getStringCellValue());
					fpf.setSettlemoney(new BigDecimal(row.getCell(5).getNumericCellValue()));
					fpf.setPoundage(new BigDecimal(row.getCell(6).getNumericCellValue()));
					fpf.setBank(row.getCell(12).getStringCellValue());
					fpf.setCuid(user.getId());
					fpf.setCname(user.getRealname());
					list.add(fpf);
//					System.out.println("日期：" + row.getCell(1).getDateCellValue() + " Pos机号：" + row.getCell(3).getStringCellValue()
//							+ " 金额：" + row.getCell(4).getNumericCellValue() + " 参考号：" + row.getCell(8).getStringCellValue());
				}
			}
		} else if (type == 2) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			while (rows.hasNext()) {
				Row row = rows.next();
				String[] rowData = row.getCell(0).getStringCellValue().trim().split("\\s+");
				if (rowData.length == 12 && !rowData[0].equals("终端编号")) {
					FinancePosFlow fpf = new FinancePosFlow();
					FinancePos fp = posMap.get(rowData[0]);
					if (fp != null) {
						fpf.setAreaid(fp.getAreaid());
						fpf.setStoreid(fp.getStoreid());
					}
					fpf.setPoscompany("北京技术");
					fpf.setTradedate(sdf.parse("2017" + rowData[1].substring(0, 4)));
					fpf.setPosnum(rowData[0]);
					fpf.setTrademoney(new BigDecimal(rowData[5]));
					fpf.setTradenum(rowData[8]);
					fpf.setSettlemoney(new BigDecimal(rowData[7]));
					fpf.setPoundage(new BigDecimal(rowData[6]).negate());
					fpf.setBank(rowData[4]);
					fpf.setCuid(user.getId());
					fpf.setCname(user.getRealname());
					list.add(fpf);
//					System.out.println("日期：" + rowData[1].substring(0, 4) + " Pos机号：" + rowData[0]
//							+ " 金额：" + rowData[5] + " 参考号：" + rowData[8]);
				}
			}
		} else {
			String datePattern = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";
			String lastPosNum = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while (rows.hasNext()) {
				Row row = rows.next();
				//第七行开始解析，行数从0开始
				if (row.getRowNum() > 5 && row.getCell(4) != null && row.getCell(4).getStringCellValue().matches(datePattern)) {
					if (row.getCell(2).getStringCellValue().length() > 0)
						lastPosNum = row.getCell(2).getStringCellValue();
					FinancePosFlow fpf = new FinancePosFlow();
					FinancePos fp = posMap.get(lastPosNum);
					if (fp != null) {
						fpf.setAreaid(fp.getAreaid());
						fpf.setStoreid(fp.getStoreid());
					}
					fpf.setPoscompany("银盛");
					fpf.setTradedate(sdf.parse(row.getCell(4).getStringCellValue()));
					fpf.setPosnum(lastPosNum);
					fpf.setTrademoney(new BigDecimal(row.getCell(13).getNumericCellValue()));
					fpf.setTradenum(row.getCell(12).getStringCellValue());
					fpf.setPoundage(new BigDecimal(row.getCell(14).getNumericCellValue()));
					fpf.setSettlemoney(fpf.getTrademoney().subtract(fpf.getPoundage()));
					fpf.setBank(row.getCell(10).getStringCellValue());
					fpf.setCuid(user.getId());
					fpf.setCname(user.getRealname());
					list.add(fpf);
//					System.out.println("日期：" + row.getCell(4).getStringCellValue() + " Pos机号：" + lastPosNum
//							+ " 金额：" + row.getCell(13).getNumericCellValue() + " 参考号：" + row.getCell(12).getStringCellValue());
				}
			}
		}
		map.put("list", list);
		financePosFlowMapper.insertBatch(map);
		r.setCode(HttpConstant.SUCCESS_CODE);
		r.setMsg(HttpConstant.SUCCESS_MSG);
		return r;
	}

	@Override
	public ResultBean getFinancePosFlowList(FinancePosFlow financePosFlow, User user) {
		ResultBean r = new ResultBean();
		financePosFlow.setDblink(user.getDblink());
		List<FinancePosFlow> list = financePosFlowMapper.selectList(financePosFlow);
		if (list == null || list.size() == 0)
			return r;
		Map<String, LinkedList<FinancePosFlow>> map = new HashMap<>();
		for (FinancePosFlow fpf : list) {
			String key = fpf.getPoscompany() + "_" + fpf.getPosnum();
			if (map.get(key) == null) {
				LinkedList<FinancePosFlow> linkedList = new LinkedList<>();
				FinancePosFlow fPosFlow = new FinancePosFlow();
				fPosFlow.setPoscompany("");
				fPosFlow.setPosnum("合计");
				fPosFlow.setTrademoney(BigDecimal.ZERO);
				fPosFlow.setSettlemoney(BigDecimal.ZERO);
				fPosFlow.setPoundage(BigDecimal.ZERO);
				fPosFlow.setTradenum("");
				fPosFlow.setBank("");
				linkedList.add(fPosFlow);
				map.put(key, linkedList);
			}
			LinkedList<FinancePosFlow> linkedList = map.get(key);
			linkedList.addFirst(fpf);
			linkedList.getLast().setTrademoneyPlus(fpf.getTrademoney());
			linkedList.getLast().setSettlemoneyPlus(fpf.getSettlemoney());
			linkedList.getLast().setPoundagePlus(fpf.getPoundage());
		}
		Map<String, LinkedList<FinancePosFlow>> resultMap = new HashMap<>();
		for (String key : map.keySet()) {
			String poscompany = key.split("_")[0];
			LinkedList<FinancePosFlow> linkedList = map.get(key);
			if (resultMap.get(poscompany) == null) {
				resultMap.put(poscompany, linkedList);
			} else {
				FinancePosFlow fPosFlow = linkedList.getLast();
				linkedList.removeLast(); //去掉公司-Pos号的合计
				resultMap.get(poscompany).addAll(0, linkedList);
				//整合所有数据到公司的合计
				resultMap.get(poscompany).getLast().setTrademoneyPlus(fPosFlow.getTrademoney());
				resultMap.get(poscompany).getLast().setSettlemoneyPlus(fPosFlow.getSettlemoney());
				resultMap.get(poscompany).getLast().setPoundagePlus(fPosFlow.getPoundage());
			}
		}
		LinkedList<FinancePosFlow> result = new LinkedList<>();
		FinancePosFlow totalFlow = new FinancePosFlow();
		totalFlow.setPoscompany("总计");
		totalFlow.setPosnum("");
		totalFlow.setTrademoney(BigDecimal.ZERO);
		totalFlow.setSettlemoney(BigDecimal.ZERO);
		totalFlow.setPoundage(BigDecimal.ZERO);
		totalFlow.setTradenum("");
		totalFlow.setBank("");
		for (String key : resultMap.keySet()) {
			LinkedList<FinancePosFlow> linkedList = resultMap.get(key);
			result.addAll(linkedList);
			totalFlow.setTrademoneyPlus(linkedList.getLast().getTrademoney());
			totalFlow.setSettlemoneyPlus(linkedList.getLast().getSettlemoney());
			totalFlow.setPoundagePlus(linkedList.getLast().getPoundage());
		}
		result.addLast(totalFlow);
		r.setResult(new PageInfo<>(result));
		return r;
	}

	@Override
	public ResultBean getStorePayStatist(FinanceReceipt financeReceipt, User user) {
		ResultBean r = new ResultBean();
		financeReceipt.setDblink(user.getDblink());
		List<FinanceReceipt> list = financeReceiptMapper.selectList(financeReceipt);
		if (list == null || list.size() == 0)
			return r;
		Store store=new Store();
		store.setDblink(user.getDblink());
		Map<Integer, MapDTO> storeMap = storeService.getMap(store);
		FinancePos financePos = new FinancePos();
		financePos.setDblink(user.getDblink());
		List<FinancePos> posList = financePosMapper.selectList(financePos);
		Map<Integer, String> posMap = new HashMap<>();
		//组合成一个对应的Map，获取对应PosId的银行名字
		for (FinancePos fp : posList) {
			posMap.put(fp.getId(), fp.getBankname());
		}
		
		Map<Integer, LinkedList<FinanceStat>> map = new HashMap<>();
		for (FinanceReceipt fr : list) {
			fr.setPosbankname(posMap.get(fr.getPosid())); //刚取出来的数据不包含银行名字信息
			Integer key = fr.getStoreid();
			if (map.get(key) == null) {
				LinkedList<FinanceStat> linkedList = new LinkedList<>();
				FinanceStat fs = new FinanceStat();
				fs.setStoreid(fr.getStoreid());
				fs.setStore(storeMap.get(fr.getStoreid())!=null?storeMap.get(fr.getStoreid()).getName():fr.getStoreid().toString());
				fs.setBank("现金");
				fs.setPrice(BigDecimal.ZERO);
				linkedList.add(fs);
				FinanceStat fsTotal = new FinanceStat();
				fsTotal.setStore("");
				fsTotal.setBank("小计");
				fsTotal.setPrice(BigDecimal.ZERO);
				linkedList.add(fsTotal);
				map.put(key, linkedList);
			}
			LinkedList<FinanceStat> linkedList = map.get(key);
			if (fr.getPosbankname() != null && !"".equals(fr.getPosbankname()) && fr.getPosmoney() != null && fr.getPosmoney().compareTo(BigDecimal.ZERO) != 0) {
				if (financeReceipt.getPosbankname() == null || "".equals(financeReceipt.getPosbankname()) || financeReceipt.getPosbankname().equals(fr.getPosbankname())) {
					boolean isExist = false;
					for (FinanceStat fs : linkedList) {
						if (fs.getBank().equals(fr.getPosbankname())) {
							fs.setPricePlus(fr.getPosmoney());
							isExist = true;
							break;
						}
					}
					if (!isExist) {
						FinanceStat fs = new FinanceStat();
						fs.setStoreid(fr.getStoreid());
						fs.setStore(storeMap.get(fr.getStoreid())!=null?storeMap.get(fr.getStoreid()).getName():fr.getStoreid().toString());
						fs.setBank(fr.getPosbankname());
						fs.setPrice(fr.getPosmoney());
						linkedList.addFirst(fs);
					}
					linkedList.getLast().setPricePlus(fr.getPosmoney());//若有pos数据，则增加合计数据
				}
			}
			linkedList.get(linkedList.size() - 2).setPricePlus(fr.getCashmoney()); //现金统计
			linkedList.getLast().setPricePlus(fr.getCashmoney());//若有现金，则增加合计的数据
		}
		LinkedList<FinanceStat> result = new LinkedList<>();
		FinanceStat total = new FinanceStat();
		total.setStore("总计");
		total.setBank("");
		total.setPrice(BigDecimal.ZERO);
		for (Integer key : map.keySet()) {
			result.addAll(map.get(key));
			total.setPricePlus(map.get(key).getLast().getPrice());
		}
		result.addLast(total);
		r.setResult(new PageInfo<>(result));
		return r;
	}

	@Override
	public ResultBean getBankPosStoreStatList(FinanceReceipt financeReceipt, User user) {
		ResultBean r = new ResultBean();
		financeReceipt.setDblink(user.getDblink());
		List<FinanceReceipt> receiptList = financeReceiptMapper.selectList(financeReceipt);
		FinancePosFlow financePosFlow = new FinancePosFlow();
		financePosFlow.setDblink(user.getDblink());
		financePosFlow.setAreaid(financeReceipt.getAreaid());
		financePosFlow.setTradedatelow(financeReceipt.getReceiptdatelow());
		financePosFlow.setTradedatetop(financeReceipt.getReceiptdatetop());
		List<FinancePosFlow> posflowList = financePosFlowMapper.selectList(financePosFlow);
		if ((receiptList == null || receiptList.size() == 0) && (posflowList == null || posflowList.size() == 0)) 
			return r;
		
		FinancePos financePos = new FinancePos();
		financePos.setDblink(user.getDblink());
		List<FinancePos> posList = financePosMapper.selectList(financePos);
		Map<String, String> posMap = new HashMap<>();
		//组合成一个对应的Map，获取对应Posnum的银行名字
		for (FinancePos fp : posList) {
			posMap.put(fp.getPosnum(), fp.getBankname());
		}
		Map<String, LinkedList<FinancePosStat>> map = new HashMap<>();
		if (receiptList != null && receiptList.size() > 0) {
			for (FinanceReceipt fr : receiptList) {
				fr.setPosbankname(posMap.get(fr.getPosnum()));
				//做银行的筛选，以及对不包含银行的数据进行筛选
				if (fr.getPosbankname() == null || (financeReceipt.getPosbankname() != null && !financeReceipt.getPosbankname().trim().equals("") && !financeReceipt.getPosbankname().equals(fr.getPosbankname())))
					continue;
				String key = fr.getPosbankname() + "_" + fr.getPosnum();
				if (map.get(key) == null) {
					LinkedList<FinancePosStat> linkedList = new LinkedList<>();
					FinancePosStat fps = new FinancePosStat();
					fps.setBank("");
					fps.setPosnum("合计");
					fps.setPosmoney(BigDecimal.ZERO);
					fps.setRposmoney(BigDecimal.ZERO);
					fps.setDiffmoney(BigDecimal.ZERO);
					fps.setConfirmmoney(BigDecimal.ZERO);
					linkedList.add(fps);
					map.put(key, linkedList);
				}
				LinkedList<FinancePosStat> linkedList = map.get(key);
				boolean isExist = false;
				for (FinancePosStat fps : linkedList) {
					if (fps.getPosnum().equals(fr.getPosnum())) {
						fps.setIdsPlus(fr.getId().toString());
						fps.setPosmoneyPlus(fr.getPosmoney());
						if (fr.getIsconfirm() == 1)
							fps.setConfirmmoneyPlus(fr.getPosmoney());
						isExist = true;
						break;
					}
				}
				if (!isExist) {
					FinancePosStat fps = new FinancePosStat();
					fps.setIdsPlus(fr.getId().toString());
					fps.setBank(fr.getPosbankname());
					fps.setPosnum(fr.getPosnum());
					fps.setStoreid(fr.getStoreid());
					fps.setPosmoney(fr.getPosmoney());
					fps.setRposmoney(BigDecimal.ZERO);
					if (fr.getIsconfirm() == 1)
						fps.setConfirmmoney(fr.getPosmoney());
					else 
						fps.setConfirmmoney(BigDecimal.ZERO);
					linkedList.addFirst(fps);
				}
				linkedList.getLast().setIdsPlus(fr.getId().toString());
				linkedList.getLast().setPosmoneyPlus(fr.getPosmoney());
				if (fr.getIsconfirm() == 1)
					linkedList.getLast().setConfirmmoneyPlus(fr.getPosmoney());
			}
		}
		if (posflowList != null && posflowList.size() > 0) {
			for (FinancePosFlow fpf : posflowList) {
				fpf.setPosbankname(posMap.get(fpf.getPosnum()));
				//做银行的筛选，以及对不包含银行的数据进行筛选
				if (fpf.getPosbankname() == null || (financeReceipt.getPosbankname() != null && !financeReceipt.getPosbankname().trim().equals("") && !financeReceipt.getPosbankname().equals(fpf.getPosbankname())))
					continue;
				String key = fpf.getPosbankname() + "_" + fpf.getPosnum();
				if (map.get(key) == null) {
					LinkedList<FinancePosStat> linkedList = new LinkedList<>();
					FinancePosStat fps = new FinancePosStat();
					fps.setBank("");
					fps.setPosnum("合计");
					fps.setPosmoney(BigDecimal.ZERO);
					fps.setRposmoney(BigDecimal.ZERO);
					fps.setDiffmoney(BigDecimal.ZERO);
					fps.setConfirmmoney(BigDecimal.ZERO);
					linkedList.add(fps);
					map.put(key, linkedList);
				}
				LinkedList<FinancePosStat> linkedList = map.get(key);
				boolean isExist = false;
				for (FinancePosStat fps : linkedList) {
					if (fps.getPosnum().equals(fpf.getPosnum())) {
						fps.setRposmoneyPlus(fpf.getTrademoney());
						isExist = true;
						break;
					}
				}
				if (!isExist) {
					FinancePosStat fps = new FinancePosStat();
					fps.setBank(fpf.getPosbankname());
					fps.setPosnum(fpf.getPosnum());
					fps.setStoreid(fpf.getStoreid());
					fps.setPosmoney(BigDecimal.ZERO);
					fps.setRposmoney(fpf.getTrademoney());
					fps.setConfirmmoney(BigDecimal.ZERO);
					linkedList.addFirst(fps);
				}
				linkedList.getLast().setRposmoneyPlus(fpf.getTrademoney());
			}
		}
		Map<String, LinkedList<FinancePosStat>> resultMap = new HashMap<>();
		for (String key : map.keySet()) {
			LinkedList<FinancePosStat> data = map.get(key);
			Byte isdiff = financeReceipt.getIsdiff();
			if (isdiff != null && isdiff == 1) { //有差异，只保留有差异的数据
				for (FinancePosStat fps : data) {
					if (!fps.getPosnum().equals("合计") && (fps.getDiffmoney() == null || fps.getDiffmoney().compareTo(BigDecimal.ZERO) == 0)) {
						//保留有差异的数据，筛选出没差异的数据，并剔除
						data.remove(fps);
					}
				}
			} else if (isdiff != null && isdiff == 0) { //无差异，只保留无差异的数据
				for (FinancePosStat fps : data) {
					if (!fps.getPosnum().equals("合计") && fps.getDiffmoney() != null && fps.getDiffmoney().compareTo(BigDecimal.ZERO) != 0) {
						//保留无差异的数据，筛选出有差异的数据，并剔除
						data.remove(fps);
					}
				}
			}
			if (data.size() == 1)
				continue;
			
			String bankname = key.split("_")[0];
			if (resultMap.get(bankname) == null) {
				resultMap.put(bankname, data);
			} else {
				FinancePosStat fps = data.getLast();
				data.removeLast();
				resultMap.get(bankname).addAll(0, data);
				LinkedList<FinancePosStat> linkedList = resultMap.get(bankname);
				linkedList.getLast().setPosmoneyPlus(fps.getPosmoney());
				linkedList.getLast().setRposmoneyPlus(fps.getRposmoney());
				linkedList.getLast().setConfirmmoneyPlus(fps.getConfirmmoney());
				linkedList.getLast().setIdsPlus(fps.getIds());
			}
		}
		LinkedList<FinancePosStat> result = new LinkedList<>();
		FinancePosStat total = new FinancePosStat();
		total.setBank("总计");
		total.setPosnum("");
		total.setPosmoney(BigDecimal.ZERO);
		total.setRposmoney(BigDecimal.ZERO);
		total.setDiffmoney(BigDecimal.ZERO);
		total.setConfirmmoney(BigDecimal.ZERO);
		for (String key : resultMap.keySet()) {
			LinkedList<FinancePosStat> linkedList = resultMap.get(key);
			result.addAll(linkedList);
			total.setPosmoneyPlus(linkedList.getLast().getPosmoney());
			total.setRposmoneyPlus(linkedList.getLast().getRposmoney());
			total.setConfirmmoneyPlus(linkedList.getLast().getConfirmmoney());
			total.setIdsPlus(linkedList.getLast().getIds());
		}
		result.addLast(total);
		r.setResult(new PageInfo<>(result));
		return r;
	}

	@Override
	public ResultBean getStoreTypeStatList(FinanceReceipt financeReceipt, User user) {
		ResultBean r = new ResultBean();
		financeReceipt.setDblink(user.getDblink());
		List<FinanceReceipt> list = financeReceiptMapper.selectList(financeReceipt);
		if (list == null || list.size() == 0)
			return r;
		Map<Integer, LinkedList<FinanceStat>> map = new HashMap<>();
		Store store=new Store();
		store.setDblink(user.getDblink());
		Map<Integer, MapDTO> storeMap = storeService.getMap(store);
		FinanceSubject financeSubject = new FinanceSubject();
		financeSubject.setDblink(user.getDblink());
		List<FinanceSubject> typeList = financeSubjectMapper.selectList(financeSubject);
		Map<Integer, String> typeMap = new HashMap<>();
		for (FinanceSubject fs : typeList) {
			typeMap.put(fs.getId(), fs.getSubject());
		}
		for (FinanceReceipt fr : list) {
			Integer key = fr.getStoreid();
			if (map.get(key) == null) {
				LinkedList<FinanceStat> linkedList = new LinkedList<>();
				FinanceStat fs = new FinanceStat();
				fs.setStore("");
				fs.setType(0);
				fs.setTypestr("小计");
				fs.setPrice(BigDecimal.ZERO);
				linkedList.add(fs);
				map.put(key, linkedList);
			}
			LinkedList<FinanceStat> linkedList = map.get(key);
			boolean isExist = false;
			for (FinanceStat fs : linkedList) {
				if (fs.getType().intValue() == fr.getType().intValue()) {
					fs.setPricePlus(fr.getReceiptmoney());
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				FinanceStat fs = new FinanceStat();
				fs.setStoreid(fr.getStoreid());
				fs.setStore(storeMap.get(fr.getStoreid())!=null?storeMap.get(fr.getStoreid()).getName():fr.getStoreid().toString());
				fs.setType(fr.getType());
				fs.setTypestr(typeMap.get(fr.getType())==null?"":typeMap.get(fr.getType()));
				fs.setPrice(fr.getReceiptmoney());
				linkedList.addFirst(fs);
			}
			linkedList.getLast().setPricePlus(fr.getReceiptmoney());
		}
		
		LinkedList<FinanceStat> result = new LinkedList<>();
		FinanceStat total = new FinanceStat();
		total.setStore("总计");
		total.setTypestr("");
		total.setPrice(BigDecimal.ZERO);
		for (Integer key : map.keySet()) {
			result.addAll(map.get(key));
			total.setPricePlus(map.get(key).getLast().getPrice());
		}
		result.addLast(total);
		r.setResult(new PageInfo<>(result));
		return r;
	}

	@Override
	public ResultBean getTypeStoreStatList(FinanceReceipt financeReceipt, User user) {
		ResultBean r = new ResultBean();
		financeReceipt.setDblink(user.getDblink());
		List<FinanceReceipt> list = financeReceiptMapper.selectList(financeReceipt);
		if (list == null || list.size() == 0)
			return r;
		Store store=new Store();
		store.setDblink(user.getDblink());
		Map<Integer, LinkedList<FinanceStat>> map = new HashMap<>();
		Map<Integer, MapDTO> storeMap = storeService.getMap(store);
		
		
		FinanceSubject financeSubject = new FinanceSubject();
		financeSubject.setDblink(user.getDblink());
		List<FinanceSubject> typeList = financeSubjectMapper.selectList(financeSubject);
		Map<Integer, String> typeMap = new HashMap<>();
		for (FinanceSubject fs : typeList) {
			typeMap.put(fs.getId(), fs.getSubject());
		}
		for (FinanceReceipt fr : list) {
			Integer key = fr.getType();
			if (map.get(key) == null) {
				LinkedList<FinanceStat> linkedList = new LinkedList<>();
				FinanceStat fs = new FinanceStat();
				fs.setStoreid(0);
				fs.setStore("小计");
				fs.setType(0);
				fs.setTypestr("");
				fs.setPrice(BigDecimal.ZERO);
				linkedList.add(fs);
				map.put(key, linkedList);
			}
			LinkedList<FinanceStat> linkedList = map.get(key);
			boolean isExist = false;
			for (FinanceStat fs : linkedList) {
				if (fs.getStoreid().intValue() == fr.getStoreid().intValue()) {
					fs.setPricePlus(fr.getReceiptmoney());
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				FinanceStat fs = new FinanceStat();
				fs.setStoreid(fr.getStoreid());
				fs.setStore(storeMap.get(fr.getStoreid())!=null?storeMap.get(fr.getStoreid()).getName():fr.getStoreid().toString());
				fs.setType(fr.getType());
				fs.setTypestr(typeMap.get(fr.getType())==null?"":typeMap.get(fr.getType()));
				fs.setPrice(fr.getReceiptmoney());
				linkedList.addFirst(fs);
			}
			linkedList.getLast().setPricePlus(fr.getReceiptmoney());
		}
		
		LinkedList<FinanceStat> result = new LinkedList<>();
		FinanceStat total = new FinanceStat();
		total.setStore("");
		total.setTypestr("总计");
		total.setPrice(BigDecimal.ZERO);
		for (Integer key : map.keySet()) {
			result.addAll(map.get(key));
			total.setPricePlus(map.get(key).getLast().getPrice());
		}
		result.addLast(total);
		r.setResult(new PageInfo<>(result));
		return r;
	}

	@Override
	public ResultBean getCompanyPosStoreStatList(FinanceReceipt financeReceipt, User user) {
		ResultBean r = new ResultBean();
		financeReceipt.setDblink(user.getDblink());
		List<FinanceReceipt> receiptList = financeReceiptMapper.selectList(financeReceipt);
		FinancePosFlow financePosFlow = new FinancePosFlow();
		financePosFlow.setDblink(user.getDblink());
		financePosFlow.setAreaid(financeReceipt.getAreaid());
		financePosFlow.setTradedatelow(financeReceipt.getReceiptdatelow());
		financePosFlow.setTradedatetop(financeReceipt.getReceiptdatetop());
		List<FinancePosFlow> posflowList = financePosFlowMapper.selectList(financePosFlow);
		FinanceDeposit financeDeposit = new FinanceDeposit();
		financeDeposit.setDblink(user.getDblink());
		financeDeposit.setAreaid(financeReceipt.getAreaid());
		financeDeposit.setDepositdatelow(financeReceipt.getReceiptdatelow());
		financeDeposit.setDepositdatetop(financeReceipt.getReceiptdatetop());
		List<FinanceDeposit> cashList = financeDepositMapper.selectList(financeDeposit);
		if ((receiptList == null || receiptList.size() == 0) && (posflowList == null || posflowList.size() == 0) && (cashList == null || cashList.size() == 0)) 
			return r;
		
		Map<String, LinkedList<FinancePosStat>> map = new HashMap<>();
		if (receiptList != null && receiptList.size() > 0) {
			for (FinanceReceipt fr : receiptList) {
				//处理Pos金额
				if (fr.getPosnum() != null && fr.getPosmoney() != null && fr.getPosmoney().compareTo(BigDecimal.ZERO) != 0) {
					String key = fr.getPoscompany() + "_" + fr.getPosnum();
					if (map.get(key) == null) {
						LinkedList<FinancePosStat> linkedList = new LinkedList<>();
						FinancePosStat fps = new FinancePosStat();
						fps.setPoscompany("");
						fps.setPosnum("合计");
						fps.setPosmoney(BigDecimal.ZERO);
						fps.setRposmoney(BigDecimal.ZERO);
						fps.setDiffmoney(BigDecimal.ZERO);
						fps.setConfirmmoney(BigDecimal.ZERO);
						linkedList.add(fps);
						map.put(key, linkedList);
					}
					LinkedList<FinancePosStat> linkedList = map.get(key);
					boolean isExist = false;
					for (FinancePosStat fps : linkedList) {
						if (fps.getPosnum().equals(fr.getPosnum())) {
							fps.setIdsPlus(fr.getId().toString());
							fps.setPosmoneyPlus(fr.getPosmoney());
							if (fr.getIsconfirm() == 1)
								fps.setConfirmmoneyPlus(fr.getPosmoney());
							isExist = true;
							break;
						}
					}
					if (!isExist) {
						FinancePosStat fps = new FinancePosStat();
						fps.setIdsPlus(fr.getId().toString());
						fps.setPoscompany(fr.getPoscompany());
						fps.setPosnum(fr.getPosnum());
						fps.setStoreid(fr.getStoreid());
						fps.setPosmoney(fr.getPosmoney());
						fps.setRposmoney(BigDecimal.ZERO);
						if (fr.getIsconfirm() == 1)
							fps.setConfirmmoney(fr.getPosmoney());
						else 
							fps.setConfirmmoney(BigDecimal.ZERO);
						linkedList.addFirst(fps);
					}
					linkedList.getLast().setIdsPlus(fr.getId().toString());
					linkedList.getLast().setPosmoneyPlus(fr.getPosmoney());
					if (fr.getIsconfirm() == 1)
						linkedList.getLast().setConfirmmoneyPlus(fr.getPosmoney());
				}
				//处理现金金额
				if (fr.getCashmoney() != null && fr.getCashmoney().compareTo(BigDecimal.ZERO) != 0) {
					String key = "现金";
					if (map.get(key) == null) {
						LinkedList<FinancePosStat> linkedList = new LinkedList<>();
						FinancePosStat fps = new FinancePosStat();
						fps.setPoscompany("");
						fps.setPosnum("合计");
						fps.setPosmoney(BigDecimal.ZERO);
						fps.setRposmoney(BigDecimal.ZERO);
						fps.setDiffmoney(BigDecimal.ZERO);
						fps.setConfirmmoney(BigDecimal.ZERO);
						linkedList.add(fps);
						map.put(key, linkedList);
					}
					LinkedList<FinancePosStat> linkedList = map.get(key);
					boolean isExist = false;
					for (FinancePosStat fps : linkedList) {
						if (fps.getStoreid() != null && fps.getStoreid().equals(fr.getStoreid())) {
							fps.setIdsPlus(fr.getId().toString());
							fps.setPosmoneyPlus(fr.getCashmoney()); //以Posmoney作为现金统计数据
							if (fr.getIsconfirm() == 1)
								fps.setConfirmmoneyPlus(fr.getCashmoney());
							isExist = true;
							break;
						}
					}
					if (!isExist) {
						FinancePosStat fps = new FinancePosStat();
						fps.setIdsPlus(fr.getId().toString());
						fps.setPoscompany("现金");
						fps.setPosnum("");
						fps.setStoreid(fr.getStoreid());
						fps.setPosmoney(fr.getCashmoney());
						fps.setRposmoney(BigDecimal.ZERO);
						if (fr.getIsconfirm() == 1)
							fps.setConfirmmoney(fr.getCashmoney());
						else 
							fps.setConfirmmoney(BigDecimal.ZERO);
						linkedList.addFirst(fps);
					}
					linkedList.getLast().setIdsPlus(fr.getId().toString());
					linkedList.getLast().setPosmoneyPlus(fr.getCashmoney());
					if (fr.getIsconfirm() == 1)
						linkedList.getLast().setConfirmmoneyPlus(fr.getCashmoney());
				}
			}
		}
		if (posflowList != null && posflowList.size() > 0) {
			for (FinancePosFlow fpf : posflowList) {
				String key = fpf.getPoscompany() + "_" + fpf.getPosnum();
				if (map.get(key) == null) {
					LinkedList<FinancePosStat> linkedList = new LinkedList<>();
					FinancePosStat fps = new FinancePosStat();
					fps.setPoscompany("");
					fps.setPosnum("合计");
					fps.setPosmoney(BigDecimal.ZERO);
					fps.setRposmoney(BigDecimal.ZERO);
					fps.setDiffmoney(BigDecimal.ZERO);
					fps.setConfirmmoney(BigDecimal.ZERO);
					linkedList.add(fps);
					map.put(key, linkedList);
				}
				LinkedList<FinancePosStat> linkedList = map.get(key);
				boolean isExist = false;
				for (FinancePosStat fps : linkedList) {
					if (fps.getPosnum().equals(fpf.getPosnum())) {
						fps.setRposmoneyPlus(fpf.getTrademoney());
						isExist = true;
						break;
					}
				}
				if (!isExist) {
					FinancePosStat fps = new FinancePosStat();
					fps.setPoscompany(fpf.getPoscompany());
					fps.setPosnum(fpf.getPosnum());
					fps.setStoreid(fpf.getStoreid());
					fps.setPosmoney(BigDecimal.ZERO);
					fps.setRposmoney(fpf.getTrademoney());
					fps.setConfirmmoney(BigDecimal.ZERO);
					linkedList.addFirst(fps);
				}
				linkedList.getLast().setRposmoneyPlus(fpf.getTrademoney());
			}
		}
		if (cashList != null && cashList.size() > 0) {
			for (FinanceDeposit fd : cashList) {
				if (fd.getIsconfirm() != 1)
					continue;
				String key = "现金";
				if (map.get(key) == null) {
					LinkedList<FinancePosStat> linkedList = new LinkedList<>();
					FinancePosStat fps = new FinancePosStat();
					fps.setPoscompany("");
					fps.setPosnum("合计");
					fps.setPosmoney(BigDecimal.ZERO);
					fps.setRposmoney(BigDecimal.ZERO);
					fps.setDiffmoney(BigDecimal.ZERO);
					fps.setConfirmmoney(BigDecimal.ZERO);
					linkedList.add(fps);
					map.put(key, linkedList);
				}
				LinkedList<FinancePosStat> linkedList = map.get(key);
				boolean isExist = false;
				for (FinancePosStat fps : linkedList) {
					if (fps.getStoreid() != null && fps.getStoreid().equals(fd.getStoreid())) {
						fps.setRposmoneyPlus(fd.getAmount());
						isExist = true;
						break;
					}
				}
				if (!isExist) {
					FinancePosStat fps = new FinancePosStat();
					fps.setPoscompany("现金");
					fps.setPosnum("");
					fps.setStoreid(fd.getStoreid());
					fps.setPosmoney(BigDecimal.ZERO);
					fps.setRposmoney(fd.getAmount());
					fps.setConfirmmoney(BigDecimal.ZERO);
					linkedList.addFirst(fps);
				}
				linkedList.getLast().setRposmoneyPlus(fd.getAmount());
			}
		}
		Map<String, LinkedList<FinancePosStat>> resultMap = new HashMap<>();
		for (String key : map.keySet()) {
			LinkedList<FinancePosStat> data = map.get(key);
			Byte isdiff = financeReceipt.getIsdiff();
			if (isdiff != null && isdiff == 1) { //有差异，只保留有差异的数据
				for (int index = data.size() - 1; index >= 0; index--) {
					FinancePosStat fps = data.get(index);
					if (!fps.getPosnum().equals("合计") && (fps.getDiffmoney() == null || fps.getDiffmoney().compareTo(BigDecimal.ZERO) == 0)) {
						//保留有差异的数据，筛选出没差异的数据，并剔除
						data.remove(fps);
					}
				}
			} else if (isdiff != null && isdiff == 0) { //无差异，只保留无差异的数据
				for (int index = data.size() - 1; index >= 0; index--) {
					FinancePosStat fps = data.get(index);
					if (!fps.getPosnum().equals("合计") && fps.getDiffmoney() != null && fps.getDiffmoney().compareTo(BigDecimal.ZERO) != 0) {
						//保留无差异的数据，筛选出有差异的数据，并剔除
						data.remove(fps);
					}
				}
			}
			if (data.size() == 1)
				continue;
			
			String poscompany = key.split("_")[0];
			if (resultMap.get(poscompany) == null) {
				resultMap.put(poscompany, data);
			} else {
				FinancePosStat fps = data.getLast();
				data.removeLast();
				resultMap.get(poscompany).addAll(0, data);
				LinkedList<FinancePosStat> linkedList = resultMap.get(poscompany);
				linkedList.getLast().setPosmoneyPlus(fps.getPosmoney());
				linkedList.getLast().setRposmoneyPlus(fps.getRposmoney());
				linkedList.getLast().setConfirmmoneyPlus(fps.getConfirmmoney());
				linkedList.getLast().setIdsPlus(fps.getIds());
			}
		}
		LinkedList<FinancePosStat> result = new LinkedList<>();
		FinancePosStat total = new FinancePosStat();
		total.setPoscompany("总计");
		total.setPosnum("");
		total.setPosmoney(BigDecimal.ZERO);
		total.setRposmoney(BigDecimal.ZERO);
		total.setDiffmoney(BigDecimal.ZERO);
		total.setConfirmmoney(BigDecimal.ZERO);
		for (String key : resultMap.keySet()) {
			LinkedList<FinancePosStat> linkedList = resultMap.get(key);
			result.addAll(linkedList);
			total.setPosmoneyPlus(linkedList.getLast().getPosmoney());
			total.setRposmoneyPlus(linkedList.getLast().getRposmoney());
			total.setConfirmmoneyPlus(linkedList.getLast().getConfirmmoney());
			total.setIdsPlus(linkedList.getLast().getIds());
		}
		result.addLast(total);
		r.setResult(new PageInfo<>(result));
		return r;
	}

	@Override
	public ResultBean selectIncome(FinanceIncome financeIncome, User user) {
		ResultBean rb = new ResultBean();
		PageUtil.startPage(financeIncome);
		List <FinanceIncome> list = financeReceiptMapper.selectIncome(financeIncome);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}

	@Override
	public ResultBean getApplyStatList(StudentApplyStat studentApplyStat, User user) {
		ResultBean r = new ResultBean();
		List<StudentApplyStat> list = studentService.selectApplyStat(studentApplyStat, user);
		if (list == null || list.size() == 0)
			return r;
		Area area=new Area();
		area.setDblink(user.getDblink());
		Map<Integer, MapDTO> areaMap = areaService.getMap(area);
		Store store=new Store();
		store.setDblink(user.getDblink());
		Map<Integer, MapDTO> storeMap = storeService.getMap(store);
		Classinfo ci=new Classinfo();
		ci.setDblink(user.getDblink());
		Map<Integer, MapDTO> classMap = classinfoService.getMap(ci);
		Map<String, LinkedList<StudentApplyStat>> map = new HashMap<>();
		for (StudentApplyStat sas : list) {
			String key = sas.getAreaid().toString() + "_" + sas.getStoreid().toString();
			if (map.get(key) == null) {
				LinkedList<StudentApplyStat> linkedList = new LinkedList<>();
				StudentApplyStat stat = new StudentApplyStat();
				stat.setArea(areaMap.get(sas.getAreaid())!=null?areaMap.get(sas.getAreaid()).getName():sas.getAreaid().toString());
				stat.setAreaid(sas.getAreaid());
				stat.setStore(storeMap.get(sas.getStoreid())!=null?storeMap.get(sas.getStoreid()).getName():sas.getStoreid().toString());
				stat.setStoreid(sas.getStoreid());
				stat.setClassinfo("小计");
				stat.setClassid(0);
				stat.setCount(0);
				stat.setContractmoney(BigDecimal.ZERO);
				stat.setApplymoney(BigDecimal.ZERO);
				linkedList.add(stat);
				map.put(key, linkedList);
			}
			LinkedList<StudentApplyStat> linkedList = map.get(key);
			boolean isExist = false;
			for (StudentApplyStat stat : linkedList) {
				if (stat.getClassid().intValue() == sas.getClassid().intValue()) {
					stat.setContractmoneyPlus(sas.getContractmoney());
					stat.setApplymoneyPlus(sas.getApplymoney());
					stat.setCountPlus(1);
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				StudentApplyStat stat = new StudentApplyStat();
				stat.setArea(areaMap.get(sas.getAreaid())!=null?areaMap.get(sas.getAreaid()).getName():sas.getAreaid().toString());
				stat.setAreaid(sas.getAreaid());
				stat.setStore(storeMap.get(sas.getStoreid())!=null?storeMap.get(sas.getStoreid()).getName():sas.getStoreid().toString());
				stat.setStoreid(sas.getStoreid());
				stat.setClassinfo(classMap.get(sas.getClassid())!=null?classMap.get(sas.getClassid()).getName():sas.getClassid().toString());
				stat.setClassid(sas.getClassid());
				stat.setCount(1);
				stat.setContractmoney(sas.getContractmoney());
				stat.setApplymoney(sas.getApplymoney());
				linkedList.addFirst(stat);
			}
			linkedList.getLast().setContractmoneyPlus(sas.getContractmoney());
			linkedList.getLast().setApplymoneyPlus(sas.getApplymoney());
			linkedList.getLast().setCountPlus(1);
		}
		Map<String, LinkedList<StudentApplyStat>> resultMap = new HashMap<>();
		for (String key : map.keySet()) {
			LinkedList<StudentApplyStat> linkedList = map.get(key);
			String areaid = key.split("_")[0];
			if (resultMap.get(areaid) == null) {
				StudentApplyStat stat = new StudentApplyStat();
				stat.setArea("");
				stat.setAreaid(0);
				stat.setStore("合计");
				stat.setStoreid(0);
				stat.setClassinfo("");
				stat.setClassid(0);
				stat.setCount(linkedList.getLast().getCount());
				stat.setContractmoney(linkedList.getLast().getContractmoney());
				stat.setApplymoney(linkedList.getLast().getApplymoney());
				linkedList.addLast(stat);
				resultMap.put(areaid, linkedList);
			} else {
				resultMap.get(areaid).getLast().setContractmoneyPlus(linkedList.getLast().getContractmoney());
				resultMap.get(areaid).getLast().setApplymoneyPlus(linkedList.getLast().getApplymoney());
				resultMap.get(areaid).getLast().setCountPlus(linkedList.getLast().getCount());
				resultMap.get(areaid).addAll(0, linkedList);
			}
		}
		LinkedList<StudentApplyStat> result = new LinkedList<>();
		StudentApplyStat total = new StudentApplyStat();
		total.setArea("总计");
		total.setAreaid(0);
		total.setStore("");
		total.setStoreid(0);
		total.setClassinfo("");
		total.setClassid(0);
		total.setCount(0);
		total.setContractmoney(BigDecimal.ZERO);
		total.setApplymoney(BigDecimal.ZERO);
		for (String key : resultMap.keySet()) {
			result.addAll(resultMap.get(key));
			StudentApplyStat last = resultMap.get(key).getLast();
			total.setCountPlus(last.getCount());
			total.setContractmoneyPlus(last.getContractmoney());
			total.setApplymoneyPlus(last.getApplymoney());
		}
		result.addLast(total);
		r.setResult(new PageInfo<>(result));
		return r;
	}

	@Override
	public Map<String, Object> getIncomeStat(FinanceAppStat financeAppStat,
			User user) {
		if ((financeAppStat == null) || (financeAppStat.getStartdate() == null) || (financeAppStat.getEnddate() == null)) {
		      return null;
		    }
		    financeAppStat.setDblink(user.getDblink());
		    List<FinanceAppStat> list = this.financeAppStatMapper.selectIncomeStat(financeAppStat);
		    Map<String, FinanceAppStat> map = new HashMap();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    if (list == null) {
		      list = new ArrayList();
		    }
		    for (FinanceAppStat fas : list) {
		      if ((fas.getId() != null) && (fas.getDate() != null))
		      {
		        String key = fas.getId().toString() + "_" + sdf.format(fas.getDate());
		        map.put(key, fas);
		      }
		    }
		    Area a = new Area();
		    a.setDblink(user.getDblink());
		    a.setPageNo(Integer.valueOf(-1));
		    List<Area> areas = this.areaService.selectAllList(a);
		    Calendar calendar = Calendar.getInstance();
		    Date curdate = financeAppStat.getStartdate();
		    calendar.setTime(financeAppStat.getEnddate());
		    calendar.add(5, 1);
		    Date enddate = calendar.getTime();
		    
		    Map<Integer, FinanceAppStat> resultMap = new HashMap();
		    FinanceAppStat total = new FinanceAppStat();
		    total.setName("总计");
		    total.setData(new ArrayList());
		    String key;
		    while (curdate.before(enddate))
		    {
		      BigDecimal moneytotal = BigDecimal.ZERO;
		      for (Area area : areas)
		      {
		        key = area.getId().toString() + "_" + sdf.format(curdate);
		        BigDecimal money = BigDecimal.ZERO;
		        if (map.get(key) != null) {
		          money = ((FinanceAppStat)map.get(key)).getMoney();
		        }
		        FinanceAppStat resultStat = (FinanceAppStat)resultMap.get(area.getId());
		        if (resultStat == null)
		        {
		          FinanceAppStat fas = new FinanceAppStat();
		          fas.setId(area.getId());
		          fas.setName(area.getName());
		          fas.setData(new ArrayList());
		          fas.setTotal(BigDecimal.ZERO);
		          resultMap.put(area.getId(), fas);
		          resultStat = (FinanceAppStat)resultMap.get(area.getId());
		        }
		        resultStat.getData().add(money);
		        resultStat.setTotalPlus(money);
		        moneytotal = moneytotal.add(money);
		      }
		      total.getData().add(moneytotal);
		      total.setTotalPlus(moneytotal);
		      calendar.setTime(curdate);
		      calendar.add(5, 1);
		      curdate = calendar.getTime();
		    }
		    Map<String, Object> result = new HashMap();
		    List<FinanceAppStat> data = new ArrayList();
		    data.add(total);
		    for (Integer areaid : resultMap.keySet())
		    {
		      FinanceAppStat cs = (FinanceAppStat)resultMap.get(areaid);
		      data.add(cs);
		    }
		    Object chart = new ArrayList();
		    ((List)chart).add(total);
		    result.put("data", data);
		    result.put("chart", chart);
		    return result;
	}

	@Override
	public Map<String, Object> getOutcomeStat(FinanceAppStat financeAppStat,
			User user) {
		if ((financeAppStat == null) || (financeAppStat.getStartdate() == null) || (financeAppStat.getEnddate() == null)) {
		      return null;
		    }
		    financeAppStat.setDblink(user.getDblink());
		    List<FinanceAppStat> list = this.financeAppStatMapper.selectOutcomeStat(financeAppStat);
		    Map<String, FinanceAppStat> map = new HashMap();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    if (list == null) {
		      list = new ArrayList();
		    }
		    for (FinanceAppStat fas : list) {
		      if ((fas.getId() != null) && (fas.getDate() != null))
		      {
		        String key = fas.getId().toString() + "_" + sdf.format(fas.getDate());
		        map.put(key, fas);
		      }
		    }
		    Area a = new Area();
		    a.setDblink(user.getDblink());
		    a.setPageNo(Integer.valueOf(-1));
		    List<Area> areas = this.areaService.selectAllList(a);
		    Calendar calendar = Calendar.getInstance();
		    Date curdate = financeAppStat.getStartdate();
		    calendar.setTime(financeAppStat.getEnddate());
		    calendar.add(5, 1);
		    Date enddate = calendar.getTime();
		    
		    Map<Integer, FinanceAppStat> resultMap = new HashMap();
		    FinanceAppStat total = new FinanceAppStat();
		    total.setName("总计");
		    total.setData(new ArrayList());
		    String key;
		    while (curdate.before(enddate))
		    {
		      BigDecimal moneytotal = BigDecimal.ZERO;
		      for (Area area : areas)
		      {
		        key = area.getId().toString() + "_" + sdf.format(curdate);
		        BigDecimal money = BigDecimal.ZERO;
		        if (map.get(key) != null) {
		          money = ((FinanceAppStat)map.get(key)).getMoney();
		        }
		        FinanceAppStat resultStat = (FinanceAppStat)resultMap.get(area.getId());
		        if (resultStat == null)
		        {
		          FinanceAppStat fas = new FinanceAppStat();
		          fas.setId(area.getId());
		          fas.setName(area.getName());
		          fas.setData(new ArrayList());
		          fas.setTotal(BigDecimal.ZERO);
		          resultMap.put(area.getId(), fas);
		          resultStat = (FinanceAppStat)resultMap.get(area.getId());
		        }
		        resultStat.getData().add(money);
		        resultStat.setTotalPlus(money);
		        moneytotal = moneytotal.add(money);
		      }
		      total.getData().add(moneytotal);
		      total.setTotalPlus(moneytotal);
		      calendar.setTime(curdate);
		      calendar.add(5, 1);
		      curdate = calendar.getTime();
		    }
		    Map<String, Object> result = new HashMap();
		    List<FinanceAppStat> data = new ArrayList();
		    data.add(total);
		    for (Integer areaid : resultMap.keySet())
		    {
		      FinanceAppStat cs = (FinanceAppStat)resultMap.get(areaid);
		      data.add(cs);
		    }
		    Object chart = new ArrayList();
		    ((List)chart).add(total);
		    result.put("data", data);
		    result.put("chart", chart);
		    return result;
	}

	@Override
	public Map<String, Object> getOwemoneyStat(FinanceAppStat financeAppStat,
			User user) {
		return new HashMap();
	}

}
