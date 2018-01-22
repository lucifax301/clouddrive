package cn.com.liliyun.exam.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.util.ApplyExam;
import cn.com.liliyun.common.util.SubjectEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.exam.mapper.OrderExamItemMapper;
import cn.com.liliyun.exam.mapper.OrderExamMapper;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.model.StudentStatusLog;
import cn.com.liliyun.student.service.StudentService;
import cn.com.liliyun.trainorg.model.OrderExam;
import cn.com.liliyun.trainorg.model.OrderExamItem;
import cn.com.liliyun.trainorg.service.OrderExamService;
import cn.com.liliyun.user.model.User;

@Service
public class OrderExamServiceImpl implements OrderExamService {

	@Autowired
	private OrderExamMapper orderExamMapper;

	@Autowired
	private OrderExamItemMapper orderExamItemMapper;

	@Autowired
	private StudentService studentService;

	@Override
	public List<OrderExam> list(OrderExam orderExam) {
		PageUtil.startPage(orderExam);
		return orderExamMapper.selectList(orderExam);
	}

	@Override
	public List<OrderExamItem> listOfCoach(OrderExamItem orderExam) {
		PageUtil.startPage(orderExam);
		return orderExamItemMapper.selectCoachStuList(orderExam);
	}

    @Override
    public OrderExamItem selectOne(OrderExamItem orderExamItem) {
        return orderExamItemMapper.selectOne(orderExamItem);
    }

    @Override
	public ResultBean add(User user, List<OrderExamItem> list) {
		String dblink = user.getDblink();
        Date now = new Date();
        String tableId = user.getBatchId();
        Student query = new Student();
        query.setDblink(dblink);
        Student result;
        Iterator<OrderExamItem> iterator = list.iterator();
        int ok = 0, fail = 0, change = 0;
        StudentStatusLog studentStatusLog = new StudentStatusLog();
        while (iterator.hasNext()) {
            OrderExamItem item = iterator.next();
            query.setIdcard(item.getIdcard());
            result = studentService.getStudent(query);
            // 学员验证
            // TODO
            if (result != null) {
                if (item.getResult() == 0) {
                    ok++;
                } else {
                    fail++;
                }
                item.setTableid(tableId);
                item.setName(result.getName());
                item.setTraintype(result.getTraintype());
                item.setFlownum(result.getFlownum());
                item.setTraintype(result.getTraintype());
                item.setStudentid(result.getId());
                item.setCname(user.getRealname());
                item.setCtime(now);
                item.setCuid(user.getId());
                item.setAreaid(user.getAreaid());
                item.setStoreid(user.getStoreid());
                // 改期数据处理
                if (item.getChangedate() == 1) {
                    change++;
                    OrderExamItem oei = new OrderExamItem();
                    oei.setDblink(dblink);
                    oei.setSubject(item.getSubject());
                    oei.setResult(item.getResult());
                    oei.setStudentid(item.getStudentid());
                    oei = orderExamItemMapper.selectChangedate(oei);
                    if (oei != null) {
                        oei.setExamdate(item.getExamdate());
                        oei.setExamtime(item.getExamtime());
                        oei.setChangedate(1);
                        orderExamItemMapper.updateByPrimaryKeySelective(oei);
                    }
                }
                studentStatusLog.setStudentid(item.getStudentid());
                studentStatusLog.setIdcard(result.getIdcard());
                studentStatusLog.setTableid(tableId);
                studentStatusLog.setSubject(getSubject(item.getSubject()));
                studentStatusLog.setSubjectname(getSubjectname(item.getSubject()));
                studentService.saveStudentStatusLog(user, studentStatusLog);
            } else {
				iterator.remove();
			}
		}

		if (list.size() > 0) {
            Integer subject = list.get(0).getSubject();
			Map<String, Object> params = new HashMap<>();
			params.put("dblink", dblink);
			params.put("list", list);
			int count = orderExamItemMapper.insertBatch(params);
			OrderExam orderExam = new OrderExam();
			orderExam.setDblink(dblink);
			orderExam.setTableid(tableId);
			orderExam.setAreaid(user.getAreaid());
			orderExam.setStoreid(user.getStoreid());
			orderExam.setCtime(now);
			orderExam.setCname(user.getRealname());
			orderExam.setCuid(user.getId());
			orderExam.setItemcount(count);
			orderExam.setOkcount(ok);
			orderExam.setFailcount(fail);
			orderExam.setChangecount(change);
			orderExam.setSubject(subject);// 同一批次科目相同
			orderExamMapper.insertSelective(orderExam);
		}
		return new ResultBean();
	}

	private Integer getSubject(int subject) {
		if (subject == SubjectEnum.SUBJECT1_ORDER.getStatus()) {
			return ApplyExam.SUBJECT1_SIGN_REPLY.getSubject();
		} else if (subject == SubjectEnum.SUBJECT2_ORDER.getStatus()) {
			return ApplyExam.SUBJECT2_SIGN_REPLY.getSubject();
		} else if (subject == SubjectEnum.SUBJECT3_ORDER.getStatus()) {
			return ApplyExam.SUBJECT3_SIGN_REPLY.getSubject();
		} else if (subject == SubjectEnum.SUBJECT4_ORDER.getStatus()) {
			return ApplyExam.SUBJECT4_SIGN_REPLY.getSubject();
		}
		return null;
	}

	private String getSubjectname(int subject) {
        if (subject == SubjectEnum.SUBJECT1_ORDER.getStatus()) {
            return ApplyExam.SUBJECT1_SIGN_REPLY.getName();
        } else if (subject == SubjectEnum.SUBJECT2_ORDER.getStatus()) {
            return ApplyExam.SUBJECT2_SIGN_REPLY.getName();
        } else if (subject == SubjectEnum.SUBJECT3_ORDER.getStatus()) {
            return ApplyExam.SUBJECT3_SIGN_REPLY.getName();
        } else if (subject == SubjectEnum.SUBJECT4_ORDER.getStatus()) {
            return ApplyExam.SUBJECT4_SIGN_REPLY.getName();
        }
		return null;
	}

	@Override
	public List<OrderExamItem> listItem(OrderExamItem orderExamItem) {
		return orderExamItemMapper.selectList(orderExamItem);
	}

	@Override
	public Map<String,Object> importData(User user, List<OrderExamItem> list) {
		String dblink = user.getDblink();
		Date now = new Date();
		String tableId = user.getBatchId();
		Student query = new Student();
		query.setDblink(dblink);
		StudentStatusLog studentStatusLog = new StudentStatusLog();
		studentStatusLog.setDblink(dblink);
		Student result;
		StringBuilder sb = new StringBuilder();
		Iterator<OrderExamItem> iterator = list.iterator();
		int ok = 0, fail = 0, change = 0;
		int errorCount = 0 , total = list.size();
		List <OrderExamItem> errorList = new ArrayList<>();
		while (iterator.hasNext()) {
			sb.setLength(0);
			OrderExamItem item = iterator.next();
			query.setIdcard(item.getIdcard());
			result = studentService.getStudent(query);
			// 学员验证
			// TODO
			if (result == null) {
				sb.append("学员信息不存在;");
			} else {
				if (!result.getTraintype().equals(item.getTraintype())) {
					sb.append("学员培训车型与系统中数据不匹配;");
				}
				if (!result.getFlownum().equals(item.getFlownum())) {
					sb.append("学员流水号与系统中数据不匹配;");
				}
			}
			if (sb.length() > 0) {
				item.setErrormsg(sb.toString());
				errorList.add(item);
				errorCount++;
				iterator.remove();
				continue;
			}
			if (item.getResult() == 0) {
				ok++;
			} else {
				fail++;
			}
			item.setTableid(tableId);
			item.setName(result.getName());
			item.setTraintype(result.getTraintype());
			item.setFlownum(result.getFlownum());
			item.setTraintype(result.getTraintype());
			item.setStudentid(result.getId());
			item.setCname(user.getRealname());
			item.setCtime(now);
			item.setCuid(user.getId());
			item.setAreaid(user.getAreaid());
			item.setStoreid(user.getStoreid());
			// 改期数据处理
			if (item.getChangedate() == 1) {
				change++;
				OrderExamItem oei = new OrderExamItem();
				oei.setDblink(dblink);
				oei.setSubject(item.getSubject());
				oei.setResult(item.getResult());
				oei.setStudentid(item.getStudentid());
				oei = orderExamItemMapper.selectChangedate(oei);
				if (oei != null) {
					oei.setExamdate(item.getExamdate());
					oei.setExamtime(item.getExamtime());
					oei.setChangedate(1);
					orderExamItemMapper.updateByPrimaryKeySelective(oei);
				}
			}

			studentStatusLog.setStudentid(item.getStudentid());
			studentStatusLog.setIdcard(result.getIdcard());
			studentStatusLog.setTableid(tableId);
			studentStatusLog.setSubject(getSubject(item.getSubject()));
			studentStatusLog.setSubjectname(getSubjectname(item.getSubject()));
			studentService.saveStudentStatusLog(user,studentStatusLog);
		} 
		if (list.size() > 0) {
            Integer subject = list.get(0).getSubject();
			Map<String, Object> params = new HashMap<>();
			params.put("dblink", dblink);
			params.put("list", list);
			int count = orderExamItemMapper.insertBatch(params);
			OrderExam orderExam = new OrderExam();
			orderExam.setDblink(dblink);
			orderExam.setTableid(tableId);
			orderExam.setAreaid(user.getAreaid());
			orderExam.setStoreid(user.getStoreid());
			orderExam.setCtime(now);
			orderExam.setCname(user.getRealname());
			orderExam.setCuid(user.getId());
			orderExam.setItemcount(count);
			orderExam.setOkcount(ok);
			orderExam.setFailcount(fail);
			orderExam.setChangecount(change);
			orderExam.setSubject(subject);// 同一批次科目相同
			orderExamMapper.insertSelective(orderExam);
		}
		Map<String,Object> rtnData = new HashMap<String, Object>();
		rtnData.put("total", total);
		rtnData.put("success", total - errorCount);
		rtnData.put("error", errorCount);
		rtnData.put("errorlist", errorList); //有问题的数据
		return rtnData;
	}
}
