package cn.com.liliyun.finance.service.impl;

import cn.com.liliyun.common.model.RequestContext;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ApplyExam;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.FeeSubject;
import cn.com.liliyun.common.util.HttpConstant;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.finance.mapper.FinanceFeeItemMapper;
import cn.com.liliyun.finance.mapper.FinanceFeeMapper;
import cn.com.liliyun.finance.mapper.FinanceSubjectMapper;
import cn.com.liliyun.finance.model.*;
import cn.com.liliyun.finance.service.FinanceFeeService;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.model.StudentMoney;
import cn.com.liliyun.student.model.StudentStatusLog;
import cn.com.liliyun.student.service.StudentService;
import cn.com.liliyun.user.model.User;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class FinanceFeeServiceImpl implements FinanceFeeService {

	@Autowired
	private FinanceFeeMapper financeFeeMapper;
	
	@Autowired
	private FinanceFeeItemMapper financeFeeItemMapper;
	
	@Autowired
	private FinanceSubjectMapper financeSubjectMapper;
	
	@Autowired
	private StudentService studentService;

	@Override
	public List<FinanceFee> selectList(FinanceFee financeFee) {
		PageUtil.startPage(financeFee);
		return financeFeeMapper.selectList(financeFee);
	}

	@Override
	public List<FinanceFeeItem> selectItemList(FinanceFeeItem financeFeeItem) {
		PageUtil.startPage(financeFeeItem);
		return financeFeeItemMapper.selectList(financeFeeItem);
	}

	@Override
	public List<FinanceFeeItem> selectAllItemList( FinanceFeeItem financeFeeItem) {
		PageUtil.startPage(financeFeeItem);
		return financeFeeItemMapper.selectAllList(financeFeeItem);
	}

	@Override
	public ResultBean save(FinanceFeeDTO dto, List<FinanceFeeItem> list) {
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
        Date now = new Date();
        String tableId = user.getBatchId();

        FinanceSubject subject = new FinanceSubject();
        
        subject.setId(dto.getSubject());
        subject = financeSubjectMapper.selectByPrimaryKey(subject);
        Iterator<FinanceFeeItem> itr = list.iterator();

        Student query = new Student();
        
        List <StudentStatusLog> logList = new ArrayList<>();
//          List <Student> studentList = new ArrayList<>();
        while (itr.hasNext()) {
            FinanceFeeItem item = itr.next();
            query.setIdcard(item.getIdcard());
            Student student = studentService.getStudent(query);
            if (student != null) {
                item.setStudentid(student.getId());
                item.setName(student.getName());
                item.setIdcard(student.getIdcard());
                item.setTableid(tableId);
                item.setMoney(dto.getMoney());
                item.setApplyexam(dto.getApplyexam());
                item.setSubject1(subject.getPid());
                item.setSubject2(subject.getId());
                item.setCuid(user.getId());
                item.setCname(user.getRealname());
                item.setCtime(now);
                item.setStatus(0);
                item.setSource(0);
                item.setCheckstatus(0);
                item.setAreaid(student.getAreaid());
                item.setStoreid(student.getStoreid());
                item.setMuid(user.getId());
                item.setMname(user.getRealname());
                item.setMtime(now);

                StudentStatusLog studentStatusLog = new StudentStatusLog();
                studentStatusLog.setStudentid(item.getStudentid());
                studentStatusLog.setIdcard(student.getIdcard());
                studentStatusLog.setTableid(tableId);
                studentStatusLog.setCuid(user.getId());
                studentStatusLog.setCname(user.getRealname());
                studentStatusLog.setCtime(now);
                studentStatusLog.setStoreid(user.getStoreid());
                studentStatusLog.setAreaid(user.getAreaid());
//                Student update = new Student();
//              update.setId(student.getId());
                Integer sub = null;
                String subjectname = null;
                if (item.getApplyexam() == 0) {
                    sub = ApplyExam.SIGNUP_ACCEPT_FEE.getSubject();
                    subjectname = ApplyExam.SIGNUP_ACCEPT_FEE.getName();
                } else if (item.getApplyexam() == 1) {
                    sub = ApplyExam.SUBJECT2_COACH_FEE.getSubject();
                    subjectname = ApplyExam.SUBJECT2_COACH_FEE.getName();
//                    update.setApplyexam(ApplyExam.SUBJECT1_FEE_PAY.getApplyexam());
//                    update.setApplystatus(ApplyExam.SUBJECT1_FEE_PAY.getApplystatus());
                } else if (item.getApplyexam() == 2) {
                    if (item.getSubject2() == FeeSubject.COST_FEE.getStatus()) {
                        sub = ApplyExam.SUBJECT2_OK_COST.getSubject();
                        subjectname = ApplyExam.SUBJECT2_OK_COST.getName();
                    } else {
                        sub = ApplyExam.SUBJECT2_OK_FEE.getSubject();
                        subjectname = ApplyExam.SUBJECT2_OK_FEE.getName();
                    }
                } else if (item.getApplyexam() == 3) {
                    sub = ApplyExam.SUBJECT3_OK_FEE.getSubject();
                    subjectname = ApplyExam.SUBJECT3_OK_FEE.getName();
                }
                studentStatusLog.setSubject(sub);
                studentStatusLog.setSubjectname(subjectname);
                logList.add(studentStatusLog);
//				studentList.add(update);
            } else {
                itr.remove();
            }
        }
		int count = 0;
		if (list.size() > 0) {
			Map<String, Object> params = new HashMap<>();
			
			params.put("list", list);
			count = financeFeeItemMapper.insertBatch(params);
			studentService.saveLogBatch(logList);
//			studentService.updateStudentBatch(user,studentList);
		}
		if (count > 0) {
			FinanceFee fee = new FinanceFee();
			fee.setTableid(tableId);
			
			fee.setApplyexam(dto.getApplyexam());
			fee.setSubject1(FeeSubject.PAY.getStatus());
			fee.setSubject2(subject.getId());
			fee.setMoney(dto.getMoney());
			fee.setCuid(user.getId());
			fee.setCname(user.getRealname());
			fee.setCtime(now);
			fee.setStoreid(user.getStoreid());
			fee.setAreaid(user.getAreaid());
			fee.setTotal(count);
			fee.setPaytotal(0);
			fee.setPaymoney(BigDecimal.ZERO);
			fee.setTotalmoney(dto.getMoney().multiply(new BigDecimal(count)));
			financeFeeMapper.insertSelective(fee);
		}
		return new ResultBean();
	}

	@Override
	public ResultBean edit( FinanceFeeDTO dto, List<FinanceFeeItem> list) {
		
		String tableid = dto.getTableid();
		FinanceFee financeFee = new FinanceFee();
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
		financeFee.setTableid(tableid);
		financeFee = financeFeeMapper.selectByPrimaryKey(financeFee);
		Date now = new Date();
		Iterator<FinanceFeeItem> itr = list.iterator();
		Student query = new Student();
		
		while (itr.hasNext()) {
			FinanceFeeItem item = itr.next();
			query.setIdcard(item.getIdcard());
			Student student = studentService.getStudent(query);
			if (student != null) {
				item.setStudentid(student.getId());
				item.setName(student.getName());
				item.setIdcard(student.getIdcard());
				item.setTableid(tableid);
				item.setMoney(financeFee.getMoney());
				item.setApplyexam(financeFee.getApplyexam());
				item.setSubject1(FeeSubject.PAY.getStatus());
				item.setSubject2(financeFee.getSubject2());
				item.setCuid(user.getId());
				item.setCname(user.getRealname());
				item.setCtime(now);
				item.setStatus(0);
				item.setSource(0);
				item.setCheckstatus(0);
				item.setAreaid(user.getAreaid());
				item.setStoreid(user.getStoreid());
				item.setMuid(user.getId());
				item.setMname(user.getRealname());
				item.setMtime(now);
			} else {
				itr.remove();
			}
		}
		int size = list.size();
		if (size > 0) {
			Map<String, Object> params = new HashMap<>();
			
			params.put("list", list);
			financeFeeItemMapper.insertBatch(params);
			recount(tableid);
		}
		return new ResultBean();
	}


	@Override
	public ResultBean initData( Student student) {
		student = studentService.getStudent(student);
		StudentMoney studentMoney = new StudentMoney();
		Map <String,Object> data = new HashMap<>();
		if (student != null) {
			
			studentMoney.setStudentid(student.getId());
			studentMoney = studentService.getStudentMoney(studentMoney);
			data.put("studentid", student.getId());
			data.put("name", student.getName());
			data.put("applydate", student.getApplydate());
			data.put("phone", student.getPhone());
			data.put("idcard", student.getIdcard());
			data.put("storeid", student.getStoreid());
			data.put("classid", student.getClassid());
			data.put("traintype", student.getTraintype());
		}
		if (studentMoney != null) {
            data.put("signmoney", studentMoney.getSignmoney());
			data.put("contractmoney", studentMoney.getContractmoney());
			data.put("paymoney", studentMoney.getPaymoney());
			data.put("submoney", studentMoney.getSubmoney());
			data.put("owesubmoney", studentMoney.getOwesubmoney());
			data.put("oweresitmoney", studentMoney.getOweresitmoney());
			data.put("owemoney", studentMoney.getOwemoney());
			data.put("owestatus", studentMoney.getOwestatus());
			data.put("returnstatus", studentMoney.getReturnstatus());
		}
		ResultBean rb = new ResultBean();
		rb.setResult(data);
		return rb;
	}

	@Override
	public ResultBean saveItem( FinanceFeeItem feeItem) {
	    User user = RequestContext.get(ConstantUtil.USER_SESSION);
		Student student = new Student();
		
		student.setId(feeItem.getStudentid());
		student = studentService.getStudent(student);
		if (student != null) {
            Date now = new Date();
            
            feeItem.setCuid(user.getId());
            feeItem.setName(student.getName());
            feeItem.setIdcard(student.getIdcard());
            feeItem.setCtime(now);
            feeItem.setCname(user.getRealname());
            feeItem.setSource(1);
            feeItem.setCheckstatus(0);
            feeItem.setMuid(user.getId());
            feeItem.setMname(user.getRealname());
            feeItem.setMtime(now);
            feeItem.setAreaid(student.getAreaid());
            feeItem.setStoreid(student.getStoreid());
            financeFeeItemMapper.insertSelective(feeItem);
        }
		return new ResultBean();
	}

    @Override
    public ResultBean saveFinanceItem( FinanceFeeItem feeItem,Student student) {
        
        Date now = new Date();
        User user = RequestContext.get(ConstantUtil.USER_SESSION);
        feeItem.setName(student.getName());
        feeItem.setIdcard(student.getIdcard());
        feeItem.setCuid(user.getId());
        feeItem.setCtime(now);
        feeItem.setCname(user.getRealname());
        feeItem.setSource(1);
        if (feeItem.getCheckstatus() != 0) {
            feeItem.setCheckname("system");
            feeItem.setCheckuid(0);
            feeItem.setChecktime(now);
        }
        feeItem.setMuid(user.getId());
        feeItem.setMname(user.getRealname());
        feeItem.setMtime(now);
        feeItem.setAreaid(student.getAreaid());
        feeItem.setStoreid(student.getStoreid());
        financeFeeItemMapper.insertSelective(feeItem);
        return new ResultBean();
    }

    @Override
	public ResultBean editItem( FinanceFeeItem feeItem) {
    	User user = RequestContext.get(ConstantUtil.USER_SESSION);
		feeItem.setMuid(user.getId());
		feeItem.setMname(user.getRealname());
		feeItem.setMtime(new Date());
		feeItem.setStatus(0);
		financeFeeItemMapper.updateByPrimaryKeySelective(feeItem);
		return new ResultBean();
	}

	@Override
	public ResultBean deleteItem( FinanceFeeItem financeFeeItem) {
		String checkIds = financeFeeItem.getIds();
		String tableid = financeFeeItem.getTableid();
		if (StringUtils.isBlank(checkIds)
				|| StringUtils.isBlank(tableid)) {
			return new ResultBean(HttpConstant.DATA_ERROR_COCE,
					HttpConstant.DATA_ERROR_MSG);
		}
		String [] ids = financeFeeItem.getIds().split(",");
		for (String id : ids) {
			financeFeeItem.setItemid(Integer.parseInt(id));
			financeFeeItemMapper.deleteByPrimaryKey(financeFeeItem);
		}
		recount( tableid);
		return new ResultBean();
	}

    @Override
    public ResultBean check( FinanceFeeItem feeItem) {
    	User user = RequestContext.get(ConstantUtil.USER_SESSION);
        String tableid = feeItem.getTableid();
        Date now = new Date();
        feeItem.setCheckuid(user.getId());
        feeItem.setChecktime(now);
        feeItem.setCheckname(user.getRealname());
        feeItem.setMuid(user.getId());
        feeItem.setMname(user.getRealname());
        feeItem.setMtime(now);
        if (feeItem.getCheckstatus() == 2) { //审核通过后,缴费状态为已缴费
            feeItem.setStatus(1);
        }
        //减免费审核通过
        if (feeItem.getSubject2() == FeeSubject.REDUCE_FEE.getStatus()) {
            StudentMoney studentMoney = new StudentMoney();
            
            studentMoney.setStudentid(feeItem.getStudentid());
            studentMoney.setOwesubmoney(BigDecimal.ZERO);
            studentService.updateStudentMoney(studentMoney);
        }
        financeFeeItemMapper.updateByPrimaryKeySelective(feeItem);
        recount( tableid);
        return new ResultBean();
    }

	@Override
	public ResultBean checkBatch( FinanceFeeItem financeFeeItem) {
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
		String checkids = financeFeeItem.getIds();
        String tableids = financeFeeItem.getTableids();
        if (StringUtils.isBlank(checkids)) {
            return new ResultBean(HttpConstant.DATA_ERROR_COCE,
                    HttpConstant.DATA_ERROR_MSG);
        }
        String ids [] = checkids.split(",");
        Date now = new Date();
        financeFeeItem.setCheckuid(user.getId());
        financeFeeItem.setChecktime(now);
        financeFeeItem.setCheckname(user.getRealname());
        financeFeeItem.setMuid(user.getId());
        financeFeeItem.setMname(user.getRealname());
        financeFeeItem.setMtime(now);
        if (financeFeeItem.getCheckstatus() == 2) {
            financeFeeItem.setStatus(1);
        }
		for (String id : ids) {
            financeFeeItem.setItemid(Integer.parseInt(id));
            financeFeeItemMapper.updateByPrimaryKeySelective(financeFeeItem);
        }
		if (StringUtils.isNotBlank(tableids)) {
            String [] tids = tableids.split(",");
            Set <String> idset = new HashSet<>();
            for (String tid : tids) {
                if (StringUtils.isNotBlank(tid) && idset.add(tid)) {
                    recount( tid);
                }
            }
        }
        return new ResultBean();
    }
	
	private void recount(String tableid) {
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
		FinanceFee financeFee = new FinanceFee();
		financeFee.setTableid(tableid);
		
		financeFee = financeFeeMapper.selectByPrimaryKey(financeFee);
		if (financeFee != null) {
			FinanceFeeItem query = new FinanceFeeItem();
			
			query.setTableid(tableid);
			List <FinanceFeeItem> all = financeFeeItemMapper.selectList(query);
			BigDecimal paymoney = BigDecimal.ZERO;
			int paytotal = 0;
			BigDecimal money = financeFee.getMoney();
			for (FinanceFeeItem item : all) {
				if (item.getStatus() == 1) {
					paymoney = paymoney.add(money);
					paytotal++;
				}
			}
			FinanceFee fee = new FinanceFee();
			fee.setTableid(financeFee.getTableid());
			
			fee.setTotal(all.size());
			fee.setTotalmoney(money.multiply(new BigDecimal(all.size())));
			fee.setPaytotal(paytotal);
			fee.setPaymoney(paymoney);
			fee.setMuid(user.getId());
			fee.setMname(user.getRealname());
			fee.setMtime(new Date());
			financeFeeMapper.updateByPrimaryKeySelective(fee);
		}
	}

	@Override
	public List<FinancePay> selectPay( FinancePay financePay) {
		PageUtil.startPage(financePay);
		return financeFeeItemMapper.selectPay(financePay);
	}
	
}
