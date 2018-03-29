package cn.com.liliyun.exam.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.CommonService;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ApplyExam;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.common.util.SubjectEnum;
import cn.com.liliyun.exam.mapper.ResultExamItemMapper;
import cn.com.liliyun.exam.mapper.ResultExamMapper;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.model.StudentStatusLog;
import cn.com.liliyun.student.service.StudentService;
import cn.com.liliyun.trainorg.model.ResultExam;
import cn.com.liliyun.trainorg.model.ResultExamItem;
import cn.com.liliyun.trainorg.service.ResultExamService;
import cn.com.liliyun.user.model.User;

@Service
public class ResultExamServiceImpl extends CommonService implements ResultExamService {

	@Autowired
	private ResultExamMapper resultExamMapper;

	@Autowired
	private ResultExamItemMapper resultExamItemMapper;

	@Autowired
	private StudentService studentService;

	@Override
	public List<ResultExam> list(ResultExam resultExam) {
		PageUtil.startPage(resultExam);
		return resultExamMapper.selectList(resultExam);
	}

	@Override
	public List<ResultExamItem> listItem(ResultExamItem trainExamItem) {
		return resultExamItemMapper.selectList(trainExamItem);
	}

	@Override
	public List<ResultExamItem> listOfCoach(ResultExamItem resultExam) {
		PageUtil.startPage(resultExam);
		return resultExamItemMapper.selectCoachStuList(resultExam);
	}

	@Override
	public ResultBean add(List<ResultExamItem> list) {
		User user = this.<User>getContextValue(ConstantUtil.USER_SESSION);
		Date now = new Date();
		String tableId = user.getBatchId();

		Student query = new Student();
		

		StudentStatusLog studentStatusLog = new StudentStatusLog();
		

		Student result;
		int ok = 0, fail = 0;
		Iterator<ResultExamItem> iterator = list.iterator();
		while (iterator.hasNext()) {
			ResultExamItem item = iterator.next();
			query.setIdcard(item.getIdcard());
			result = studentService.getStudent(query);
			if (result != null) {
				if (item.getResult() == 0) {
					ok++;
				} else {
					fail++;
				}

				item.setTableid(tableId);
				item.setFlownum(result.getFlownum());
				item.setName(result.getName());
				item.setTraintype(result.getTraintype());
				item.setStudentid(result.getId());
				item.setStoreid(result.getStoreid());
				item.setAreaid(result.getAreaid());
				item.setCname(user.getRealname());
				item.setCuid(user.getId());
				item.setCtime(now);

                studentStatusLog.setStudentid(item.getStudentid());
                studentStatusLog.setIdcard(result.getIdcard());
                studentStatusLog.setTableid(tableId);
                studentStatusLog.setSubject(getSubejct(item.getSubject()));
                studentStatusLog.setSubjectname(getSubejctName(item.getSubject()));
                studentService.saveStudentStatusLog(studentStatusLog);

			} else {
				iterator.remove();
			}
		}

		if (list.size() > 0) {
		    Integer subject = list.get(0).getSubject();
			Map<String, Object> params = new HashMap<>();
			
			params.put("list", list);
			int count = resultExamItemMapper.insertBatch(params);
			ResultExam resultExam = new ResultExam();
			
			resultExam.setTableid(tableId);
			resultExam.setSubject(subject);
			resultExam.setItemcount(count);
			resultExam.setOkcount(ok);
			resultExam.setFailcount(fail);
			resultExam.setStoreid(user.getStoreid());
			resultExam.setAreaid(user.getAreaid());
			resultExam.setCname(user.getRealname());
			resultExam.setCuid(user.getId());
			resultExam.setCtime(now);
			resultExamMapper.insertSelective(resultExam);
		}
		return new ResultBean();
	}

	@Override
	public Map<String, Object> importData(List<ResultExamItem> list) {
		User user = this.<User>getContextValue(ConstantUtil.USER_SESSION);
		Date now = new Date();
		String tableId = user.getBatchId();

		Student query = new Student();
		

		StudentStatusLog studentStatusLog = new StudentStatusLog();
		
		StringBuilder sb = new StringBuilder();
		Student result;
		int ok = 0, fail = 0;
		int errorCount = 0, total = list.size();
		Iterator <ResultExamItem> iterator = list.iterator();
		List <ResultExamItem> errorList = new ArrayList<>();
		while (iterator.hasNext()) {
			sb.setLength(0);
			ResultExamItem item = iterator.next();
			query.setIdcard(item.getIdcard());
			result = studentService.getStudent(query);
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
			item.setFlownum(result.getFlownum());
			item.setName(result.getName());
			item.setTraintype(result.getTraintype());
			item.setStudentid(result.getId());
			item.setStoreid(user.getStoreid());
			item.setAreaid(user.getAreaid());
			item.setCname(user.getRealname());
			item.setCuid(user.getId());
			item.setCtime(now);

			studentStatusLog.setStudentid(item.getStudentid());
			studentStatusLog.setIdcard(result.getIdcard());
            studentStatusLog.setTableid(tableId);
            studentStatusLog.setSubject(getSubejct(item.getSubject()));
            studentStatusLog.setSubjectname(getSubejctName(item.getSubject()));
            studentService.saveStudentStatusLog(studentStatusLog);
		}
		if (list.size() > 0) {
            Integer subject = list.get(0).getSubject();
			Map<String, Object> params = new HashMap<>();
			
			params.put("list", list);
			int count = resultExamItemMapper.insertBatch(params);
			ResultExam resultExam = new ResultExam();
			
			resultExam.setTableid(tableId);
			resultExam.setSubject(subject);
			resultExam.setItemcount(count);
			resultExam.setOkcount(ok);
			resultExam.setFailcount(fail);
			resultExam.setStoreid(user.getStoreid());
			resultExam.setAreaid(user.getAreaid());
			resultExam.setCname(user.getRealname());
			resultExam.setCuid(user.getId());
			resultExam.setCtime(now);
			resultExamMapper.insertSelective(resultExam);
		}
		Map<String, Object> rtnData = new HashMap<String, Object>();
		rtnData.put("total", total);
		rtnData.put("success", total - errorCount);
		rtnData.put("error", errorCount);
		rtnData.put("errorlist", errorList); // 有问题的数据
		return rtnData;
	}

    /**
     * @param subject
     * @return
     */

	private Integer getSubejct(int subject) {
		if (subject == SubjectEnum.SUBJECT1_EXAM.getStatus()) {
			return ApplyExam.SUBJECT1_OK_RESULT.getSubject();
		} else if (subject == SubjectEnum.SUBJECT2_EXAM.getStatus()) {
			return ApplyExam.SUBJECT2_OK_RESULT.getSubject();
		} else if (subject == SubjectEnum.SUBJECT3_EXAM.getStatus()) {
            return ApplyExam.SUBJECT3_OK_RESULT.getSubject();
		} else if (subject == SubjectEnum.SUBJECT4_EXAM.getStatus()) {
			return ApplyExam.SUBJECT4_OK_RESULT.getSubject();
		}
		return null;
	}

    private String getSubejctName(int subject) {
		if (subject == SubjectEnum.SUBJECT1_EXAM.getStatus()) {
			return ApplyExam.SUBJECT1_OK_RESULT.getName();
		} else if (subject == SubjectEnum.SUBJECT2_EXAM.getStatus()) {
			return ApplyExam.SUBJECT2_OK_RESULT.getName();
		} else if (subject == SubjectEnum.SUBJECT3_EXAM.getStatus()) {
			return ApplyExam.SUBJECT3_OK_RESULT.getName();
		} else if (subject == SubjectEnum.SUBJECT4_EXAM.getStatus()) {
			return ApplyExam.SUBJECT4_OK_RESULT.getName();
		}
        return null;
    }

//	@Override
//	public List<TrainExamItemVo> getExamTime(TrainExamItemVo trainExamItem) {
//		List<TrainExamItemVo> list = trainExamItemMapper.countTable(trainExamItem);
//		return list;
//	}
//
//	@Override
//	public List<TrainExamItemVo> getExamCase(TrainExamItemVo trainExamItem) {
//		List<TrainExamItemVo> list = trainExamItemMapper.countTableCase(trainExamItem);
//		return list;
//	}
//
//	@Override
//	public List<TrainExamItemVo> getWaitCase(TrainExamItemVo trainExamItem) {
//		List<TrainExamItemVo> waitList = trainExamItemMapper.countWaitCase(trainExamItem);
//		return waitList;
//	}
//
//	@Override
//	public List<TrainExamItemDetailVo> getWaitCaseDetail(TrainExamItemDetailVo trainExamItem) {
//		List<TrainExamItemDetailVo> waitList = trainExamItemMapper.getWaitCaseDetail(trainExamItem);
//		return waitList;
//	}
//
//	@Override
//	public List<TrainExamItemYearVo> getWaitCaseYear(TrainExamItemYearVo trainExamItemYearVo) {
//		List<TrainExamItemYearVo> waitList = trainExamItemMapper.getWaitCaseYear(trainExamItemYearVo);
//		return waitList;
//	}

}
