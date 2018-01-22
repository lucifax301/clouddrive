package cn.com.liliyun.exam.service.impl;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ApplyExam;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.common.util.SubjectEnum;
import cn.com.liliyun.exam.mapper.TrainExamItemMapper;
import cn.com.liliyun.exam.mapper.TrainExamMapper;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.model.StudentStatusLog;
import cn.com.liliyun.student.service.StudentService;
import cn.com.liliyun.trainorg.model.*;
import cn.com.liliyun.trainorg.service.TrainExamService;
import cn.com.liliyun.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainExamServiceImpl implements TrainExamService {

	@Autowired
	private TrainExamMapper trainExamMapper;

	@Autowired
	private TrainExamItemMapper trainExamItemMapper;

	@Autowired
	private StudentService studentService;

	@Override
	public List<TrainExam> list(TrainExam trainExam) {
		PageUtil.startPage(trainExam);
		return trainExamMapper.selectList(trainExam);
	}

	@Override
	public List<TrainExamItem> listItem(TrainExamItem trainExamItem) {
		return trainExamItemMapper.selectList(trainExamItem);
	}

	@Override
	public List<TrainExamItem> listOfCoach(TrainExamItem trainExam) {
		PageUtil.startPage(trainExam);
		return trainExamItemMapper.selectCoachStuList(trainExam);
	}

	@Override
	public ResultBean add(User user, List<TrainExamItem> list) {
		String dblink = user.getDblink();
		Date now = new Date();
		String tableId = user.getBatchId();

		Student query = new Student();
		query.setDblink(dblink);

		StudentStatusLog studentStatusLog = new StudentStatusLog();
		studentStatusLog.setDblink(dblink);

		Student result;
		int ok = 0, fail = 0;
		Iterator<TrainExamItem> iterator = list.iterator();
		while (iterator.hasNext()) {
			TrainExamItem item = iterator.next();
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
                studentService.saveStudentStatusLog(user,studentStatusLog);

			} else {
				iterator.remove();
			}
		}

		if (list.size() > 0) {
		    Integer subject = list.get(0).getSubject();
			Map<String, Object> params = new HashMap<>();
			params.put("dblink", dblink);
			params.put("list", list);
			int count = trainExamItemMapper.insertBatch(params);
			TrainExam trainExam = new TrainExam();
			trainExam.setDblink(dblink);
			trainExam.setTableid(tableId);
			trainExam.setSubject(subject);
			trainExam.setItemcount(count);
			trainExam.setOkcount(ok);
			trainExam.setFailcount(fail);
			trainExam.setStoreid(user.getStoreid());
			trainExam.setAreaid(user.getAreaid());
			trainExam.setCname(user.getRealname());
			trainExam.setCuid(user.getId());
			trainExam.setCtime(now);
			trainExamMapper.insertSelective(trainExam);
		}
		return new ResultBean();
	}

	@Override
	public Map<String, Object> importData(User user, List<TrainExamItem> list) {
		String dblink = user.getDblink();
		Date now = new Date();
		String tableId = user.getBatchId();

		Student query = new Student();
		query.setDblink(dblink);

		StudentStatusLog studentStatusLog = new StudentStatusLog();
		studentStatusLog.setDblink(dblink);
		StringBuilder sb = new StringBuilder();
		Student result;
		int ok = 0, fail = 0;
		int errorCount = 0, total = list.size();
		Iterator <TrainExamItem> iterator = list.iterator();
		List <TrainExamItem> errorList = new ArrayList<>();
		while (iterator.hasNext()) {
			sb.setLength(0);
			TrainExamItem item = iterator.next();
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
            studentService.saveStudentStatusLog(user,studentStatusLog);
		}
		if (list.size() > 0) {
            Integer subject = list.get(0).getSubject();
			Map<String, Object> params = new HashMap<>();
			params.put("dblink", dblink);
			params.put("list", list);
			int count = trainExamItemMapper.insertBatch(params);
			TrainExam trainExam = new TrainExam();
			trainExam.setDblink(dblink);
			trainExam.setTableid(tableId);
			trainExam.setSubject(subject);
			trainExam.setItemcount(count);
			trainExam.setOkcount(ok);
			trainExam.setFailcount(fail);
			trainExam.setStoreid(user.getStoreid());
			trainExam.setAreaid(user.getAreaid());
			trainExam.setCname(user.getRealname());
			trainExam.setCuid(user.getId());
			trainExam.setCtime(now);
			trainExamMapper.insertSelective(trainExam);
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
		if (subject == SubjectEnum.SUBJECT1_TRAIN.getStatus()) {
			return ApplyExam.SUBJECT1_TRAIN_THEORY.getSubject();
		} else if (subject == SubjectEnum.SUBJECT2_TRAIN.getStatus()) {
			return ApplyExam.OTHER_SUBJECT2_TRAIN.getSubject();
		} else if (subject == SubjectEnum.SUBJECT3_TRAIN.getStatus()) {
			return ApplyExam.OTHER_SUBJECT3_TRAIN.getSubject();
		}
		return null;
	}

    private String getSubejctName(int subject) {
        if (subject == SubjectEnum.SUBJECT1_TRAIN.getStatus()) {
            return ApplyExam.SUBJECT1_TRAIN_THEORY.getName();
        } else if (subject == SubjectEnum.SUBJECT2_TRAIN.getStatus()) {
            return ApplyExam.OTHER_SUBJECT2_TRAIN.getName();
        } else if (subject == SubjectEnum.SUBJECT3_TRAIN.getStatus()) {
            return ApplyExam.OTHER_SUBJECT3_TRAIN.getName();
        }
        return null;
    }

	@Override
	public List<TrainExamItemVo> getExamTime(TrainExamItemVo trainExamItem) {
		List<TrainExamItemVo> list = trainExamItemMapper.countTable(trainExamItem);
		return list;
	}
	
	@Override
	public List<TrainExamItemVo> getExamCase(TrainExamItemVo trainExamItem) {
		List<TrainExamItemVo> list = trainExamItemMapper.countTableCase(trainExamItem);
		return list;
	}

	@Override
	public List<TrainExamItemVo> getWaitCase(TrainExamItemVo trainExamItem) {
		List<TrainExamItemVo> waitList = trainExamItemMapper.countWaitCase(trainExamItem);
		return waitList;
	}

	@Override
	public List<TrainExamItemDetailVo> getWaitCaseDetail(TrainExamItemDetailVo trainExamItem) {
		List<TrainExamItemDetailVo> waitList = trainExamItemMapper.getWaitCaseDetail(trainExamItem);
		return waitList;
	}

	@Override
	public List<TrainExamItemYearVo> getWaitCaseYear(TrainExamItemYearVo trainExamItemYearVo) {
		List<TrainExamItemYearVo> waitList = trainExamItemMapper.getWaitCaseYear(trainExamItemYearVo);
		return waitList;
	}

}
