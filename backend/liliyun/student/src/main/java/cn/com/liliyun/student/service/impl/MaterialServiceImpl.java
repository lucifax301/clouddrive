package cn.com.liliyun.student.service.impl;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ApplyExam;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.student.mapper.MaterialItemMapper;
import cn.com.liliyun.student.mapper.MaterialMapper;
import cn.com.liliyun.student.mapper.StudentMapper;
import cn.com.liliyun.student.mapper.StudentStatusLogMapper;
import cn.com.liliyun.student.model.Material;
import cn.com.liliyun.student.model.MaterialItem;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.model.StudentStatusLog;
import cn.com.liliyun.student.service.MaterialService;
import cn.com.liliyun.student.service.StudentService;
import cn.com.liliyun.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialMapper materialMapper;
	
	@Autowired
	private MaterialItemMapper materialItemMapper;
	
	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentStatusLogMapper studentStatusLogMapper;

	@Override
	public List<Material> list(Material material) {
		PageUtil.startPage(material);
		return materialMapper.selectList(material);
	}
	
	@Override
	public List<MaterialItem> listItem(MaterialItem MaterialItem) {
		return materialItemMapper.selectList(MaterialItem);
	}

	@Override
	public ResultBean doLearncard(User user, List<MaterialItem> list) {
		String dblink = user.getDblink();
		Date now = new Date();
		String tableId = user.getBatchId();
		
		Student query = new Student();
		query.setDblink(dblink);
		
		Student update = new Student();
		update.setDblink(dblink);
		
		Student result;
		Iterator<MaterialItem> iterator = list.iterator();
		List <StudentStatusLog> logList = new ArrayList<>();
		while (iterator.hasNext()) {
			MaterialItem item = iterator.next();
			query.setIdcard(item.getIdcard());
			result = studentMapper.selectOne(query);
			if (result != null) {
				item.setTableid(tableId);
				item.setStudentid(result.getId());
				item.setName(result.getName());
				item.setSubject(0);
				item.setAreaid(user.getAreaid());
				item.setStoreid(user.getAreaid());
				item.setCtime(now);
				item.setCuid(user.getId());
				item.setCname(user.getRealname());		
				
				StudentStatusLog studentStatusLog = new StudentStatusLog();
				studentStatusLog.setStudentid(result.getId());
				studentStatusLog.setIdcard(result.getIdcard());
				studentStatusLog.setTableid(tableId);
				studentStatusLog.setSubject(ApplyExam.SUBJECT1_OK_LEARNCARD.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.SUBJECT1_OK_LEARNCARD.getName());
				addList(user,logList,studentStatusLog);

				update.setLearncard(item.getCardnum());
				update.setId(item.getStudentid());
				update.setApplyexam(ApplyExam.SUBJECT1_OK_LEARNCARD.getApplyexam());
				update.setApplyexam(ApplyExam.SUBJECT1_OK_LEARNCARD.getApplystatus());
				studentMapper.updateByPrimaryKeySelective(update);
			} else {
				iterator.remove();
			}
		}
		if (list.size() > 0) {
			Map <String,Object> params = new HashMap<>(2);
			params.put("dblink", dblink);
			params.put("list", list);
			int count = materialItemMapper.insertBatch(params);
			Material material = new Material();
			material.setTableid(tableId);
			material.setSubject(0);
			material.setAreaid(user.getAreaid());
			material.setStoreid(user.getAreaid());
			material.setCname(user.getRealname());
			material.setCuid(user.getId());
			material.setCtime(now);
			material.setDblink(dblink);
			material.setItemcount(count);
			materialMapper.insertSelective(material);
			
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
		}
		return new ResultBean();
	}
	
	@Override
	public ResultBean doApplyStamp(User user, List<MaterialItem> list) {
		String dblink = user.getDblink();
		Date now = new Date();
		String tableId = user.getBatchId();
		
		Student query = new Student();
		query.setDblink(dblink);
		
		Student result;
		Iterator<MaterialItem> iterator = list.iterator();
		List <StudentStatusLog> logList = new ArrayList<>();
		while (iterator.hasNext()) {
			MaterialItem item = iterator.next();
			query.setIdcard(item.getIdcard());
			result = studentMapper.selectOne(query);
			if (result != null) {
				item.setTableid(tableId);
				item.setStudentid(result.getId());
				item.setName(result.getName());
				item.setSubject(1);
				item.setAreaid(user.getAreaid());
				item.setStoreid(user.getAreaid());
				item.setCtime(now);
				item.setCuid(user.getId());
				item.setCname(user.getRealname());

				StudentStatusLog studentStatusLog = new StudentStatusLog();
				studentStatusLog.setStudentid(result.getId());
				studentStatusLog.setIdcard(result.getIdcard());
				studentStatusLog.setTableid(tableId);
				studentStatusLog.setSubject(ApplyExam.SUBJECT1_OK_APPLY_STAMP.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.SUBJECT1_OK_APPLY_STAMP.getName());
				addList(user,logList,studentStatusLog);

			} else {
				iterator.remove();
			}
		}
		
		if (list.size() > 0) {
			Map <String,Object> params = new HashMap<>(2);
			params.put("dblink", dblink);
			params.put("list", list);
			int count = materialItemMapper.insertBatch(params);
			Material material = new Material();
			material.setTableid(tableId);
			material.setSubject(1);
			material.setAreaid(user.getAreaid());
			material.setStoreid(user.getAreaid());
			material.setCname(user.getRealname());
			material.setCuid(user.getId());
			material.setCtime(now);
			material.setDblink(dblink);
			material.setItemcount(count);
			materialMapper.insertSelective(material);
			
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
		}
		return new ResultBean();
	}
	
	@Override
	public ResultBean doSchoolStamp(User user, List<MaterialItem> list) {
		String dblink = user.getDblink();
		Date now = new Date();
		String tableId = user.getBatchId();
		
		Student query = new Student();
		query.setDblink(dblink);
		
		Student result;
		Iterator<MaterialItem> iterator = list.iterator();
		List <StudentStatusLog> logList = new ArrayList<>();
		while (iterator.hasNext()) {
			MaterialItem item = iterator.next();
			query.setIdcard(item.getIdcard());
			result = studentMapper.selectOne(query);
			if (result != null) {
				item.setTableid(tableId);
				item.setStudentid(result.getId());
				item.setName(result.getName());
				item.setSubject(2);
				item.setAreaid(user.getAreaid());
				item.setStoreid(user.getAreaid());
				item.setCtime(now);
				item.setCuid(user.getId());
				item.setCname(user.getRealname());

				StudentStatusLog studentStatusLog = new StudentStatusLog();
				studentStatusLog.setStudentid(result.getId());
				studentStatusLog.setIdcard(result.getIdcard());
				studentStatusLog.setTableid(tableId);
				studentStatusLog.setSubject(ApplyExam.SUBJECT1_OK_SCHOOL_STAMP.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.SUBJECT1_OK_SCHOOL_STAMP.getName());
				addList(user,logList,studentStatusLog);
			} else {
				iterator.remove();
			}
		}
		if (list.size() > 0) {
			Map <String,Object> params = new HashMap<>(2);
			params.put("dblink", dblink);
			params.put("list", list);
			int count = materialItemMapper.insertBatch(params);
			Material material = new Material();
			material.setTableid(tableId);
			material.setSubject(2);
			material.setAreaid(user.getAreaid());
			material.setStoreid(user.getAreaid());
			material.setCname(user.getRealname());
			material.setCuid(user.getId());
			material.setCtime(now);
			material.setDblink(dblink);
			material.setItemcount(count);
			materialMapper.insertSelective(material);
			
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
		}
		return new ResultBean();
	}

	@Override
	public Map<String, Object> importIcCard(User user,List <MaterialItem> list) {
		String dblink = user.getDblink();
		Date now = new Date();
		String tableId = user.getBatchId();
		
		Student update = new Student();
		update.setDblink(dblink);
		Student query = new Student();
		query.setDblink(dblink);
		
		StringBuilder sb = new StringBuilder();
		int errorCount = 0 , total = list.size();
		Iterator<MaterialItem> iterator = list.iterator();
		List <StudentStatusLog> logList = new ArrayList<>();
		List <MaterialItem> successList = new ArrayList<>();
		while (iterator.hasNext()) {
			sb.setLength(0);
			MaterialItem item = iterator.next();
			query.setIdcard(item.getIdcard());
			Student result = studentMapper.selectOne(query);
			if (result == null) {
				sb.append("学员信息不存在");
			} 
			if (sb.length() > 0) {
				item.setErrormsg(sb.toString());
				errorCount++;
				continue;
			} else {
				item.setTableid(tableId);
				item.setStudentid(result.getId());
				item.setName(result.getName());
				item.setSubject(3);
				item.setAreaid(user.getAreaid());
				item.setStoreid(user.getAreaid());
				item.setCtime(now);
				item.setCuid(user.getId());
				item.setCname(user.getRealname());

				StudentStatusLog studentStatusLog = new StudentStatusLog();
				studentStatusLog.setStudentid(result.getId());
				studentStatusLog.setIdcard(result.getIdcard());
				studentStatusLog.setTableid(tableId);
				studentStatusLog.setSubject(ApplyExam.SIGNUP_ACCEPT_ICCARD.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.SIGNUP_ACCEPT_ICCARD.getName());
				addList(user,logList,studentStatusLog);
				
				update.setIdcard(item.getIdcard());
				update.setIccard(item.getCardnum());
				studentMapper.updateByPrimaryKeySelective(update);
				successList.add(item);
				iterator.remove();
			}
		}
		
		if (list.size() > 0) {
			Map <String,Object> params = new HashMap<>(2);
			params.put("dblink", dblink);
			params.put("list", successList);
			
			int count = materialItemMapper.insertBatch(params);
			
			Material material = new Material();
			material.setTableid(tableId);
			material.setSubject(3);
			material.setAreaid(user.getAreaid());
			material.setStoreid(user.getAreaid());
			material.setCname(user.getRealname());
			material.setCuid(user.getId());
			material.setCtime(now);
			material.setDblink(dblink);
			material.setItemcount(count);
			materialMapper.insertSelective(material);
			
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
		}
		Map<String,Object> rtnData = new HashMap<>();
		rtnData.put("total", total);
		rtnData.put("success", total - errorCount);
		rtnData.put("error", errorCount);
		rtnData.put("errorlist", list); //有问题的数据
		return rtnData;
	}

	@Override
	public ResultBean doTribillPrint(User user, List<MaterialItem> list) {
		String dblink = user.getDblink();
		Date now = new Date();
		String tableId = user.getBatchId();
		
		Student query = new Student();
		query.setDblink(dblink);
		
		Student result;
		Iterator<MaterialItem> iterator = list.iterator();
		List <StudentStatusLog> logList = new ArrayList<>();
		while (iterator.hasNext()) {
			MaterialItem item = iterator.next();
			query.setIdcard(item.getIdcard());
			result = studentMapper.selectOne(query);
			if (result != null) {
				item.setTableid(tableId);
				item.setStudentid(result.getId());
				item.setName(result.getName());
				item.setSubject(4);
				item.setAreaid(user.getAreaid());
				item.setStoreid(user.getAreaid());
				item.setCtime(now);
				item.setCuid(user.getId());
				item.setCname(user.getRealname());

				StudentStatusLog studentStatusLog = new StudentStatusLog();
				studentStatusLog.setStudentid(result.getId());
				studentStatusLog.setIdcard(result.getIdcard());
				studentStatusLog.setTableid(tableId);
				studentStatusLog.setSubject(ApplyExam.SUBJECT3_FEE_TRIBILL.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.SUBJECT3_FEE_TRIBILL.getName());
				addList(user,logList,studentStatusLog);
			} else {
				iterator.remove();
			}
		}
		if (list.size() > 0) {
			Map <String,Object> params = new HashMap<>(2);
			params.put("dblink", dblink);
			params.put("list", list);
			int count = materialItemMapper.insertBatch(params);
			Material material = new Material();
			material.setTableid(tableId);
			material.setSubject(4);
			material.setAreaid(user.getAreaid());
			material.setStoreid(user.getAreaid());
			material.setCname(user.getRealname());
			material.setCuid(user.getId());
			material.setCtime(now);
			material.setDblink(dblink);
			material.setItemcount(count);
			materialMapper.insertSelective(material);
			
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
		}
		return new ResultBean();
	}
	
	@Override
	public ResultBean doTribillStamp(User user, List<MaterialItem> list) {
		String dblink = user.getDblink();
		Date now = new Date();
		String tableId = user.getBatchId();
		
		Student query = new Student();
		query.setDblink(dblink);
		
		Student result;
		Iterator<MaterialItem> iterator = list.iterator();
		List <StudentStatusLog> logList = new ArrayList<>();
		while (iterator.hasNext()) {
			MaterialItem item = iterator.next();
			query.setIdcard(item.getIdcard());
			result = studentMapper.selectOne(query);
			if (result != null) {
				item.setTableid(tableId);
				item.setStudentid(result.getId());
				item.setName(result.getName());
				item.setSubject(5);
				item.setAreaid(user.getAreaid());
				item.setStoreid(user.getAreaid());
				item.setCtime(now);
				item.setCuid(user.getId());
				item.setCname(user.getRealname());

				StudentStatusLog studentStatusLog = new StudentStatusLog();
				studentStatusLog.setStudentid(result.getId());
				studentStatusLog.setIdcard(result.getIdcard());
				studentStatusLog.setTableid(tableId);
				studentStatusLog.setSubject(ApplyExam.SUBJECT3_FEE_TRIBILL_STAMP.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.SUBJECT3_FEE_TRIBILL_STAMP.getName());
				addList(user,logList,studentStatusLog);
			} else {
				iterator.remove();
			}
		}
		
		if (list.size() > 0) {
			Map <String,Object> params = new HashMap<>(2);
			params.put("dblink", dblink);
			params.put("list", list);
			int count = materialItemMapper.insertBatch(params);
			Material material = new Material();
			material.setTableid(tableId);
			material.setSubject(5);
			material.setAreaid(user.getAreaid());
			material.setStoreid(user.getAreaid());
			material.setCname(user.getRealname());
			material.setCuid(user.getId());
			material.setCtime(now);
			material.setDblink(dblink);
			material.setItemcount(count);
			materialMapper.insertSelective(material);
			
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
		}
		return new ResultBean();
	}

	@Override
	public ResultBean doRepayMaterial(User user, List<MaterialItem> list) {
		String dblink = user.getDblink();
		Date now = new Date();
		String tableId = user.getBatchId();
		
		Student query = new Student();
		query.setDblink(dblink);
		
		Student result;
		Iterator<MaterialItem> iterator = list.iterator();
		List <StudentStatusLog> logList = new ArrayList<>();
		while (iterator.hasNext()) {
			MaterialItem item = iterator.next();
			query.setIdcard(item.getIdcard());
			result = studentMapper.selectOne(query);
			if (result != null) {
				item.setTableid(tableId);
				item.setStudentid(result.getId());
				item.setName(result.getName());
				item.setSubject(6);
				item.setAreaid(user.getAreaid());
				item.setStoreid(user.getAreaid());
				item.setCtime(now);
				item.setCuid(user.getId());
				item.setCname(user.getRealname());

				StudentStatusLog studentStatusLog = new StudentStatusLog();
				studentStatusLog.setStudentid(result.getId());
				studentStatusLog.setIdcard(result.getIdcard());
				studentStatusLog.setTableid(tableId);
				studentStatusLog.setSubject(ApplyExam.OTHER_REPAY_MATERIAL.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.OTHER_REPAY_MATERIAL.getName());
				addList(user,logList,studentStatusLog);
			} else {
				iterator.remove();
			}
		}
		
		if (list.size() > 0) {
			Map <String,Object> params = new HashMap<>(2);
			params.put("dblink", dblink);
			params.put("list", list);
			int count = materialItemMapper.insertBatch(params);
			Material material = new Material();
			material.setTableid(tableId);
			material.setSubject(6);
			material.setAreaid(user.getAreaid());
			material.setStoreid(user.getAreaid());
			material.setCname(user.getRealname());
			material.setCuid(user.getId());
			material.setCtime(now);
			material.setDblink(dblink);
			material.setItemcount(count);
			materialMapper.insertSelective(material);
			
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
		}
		return new ResultBean();
	}

	@Override
	public ResultBean doRtnMaterial(User user, List<MaterialItem> list) {
		String dblink = user.getDblink();
		Date now = new Date();
		String tableId = user.getBatchId();
		
		Student query = new Student();
		query.setDblink(dblink);
		
		Student result;
		Iterator<MaterialItem> iterator = list.iterator();
		List <StudentStatusLog> logList = new ArrayList<>();
		while (iterator.hasNext()) {
			MaterialItem item = iterator.next();
			query.setIdcard(item.getIdcard());
			result = studentMapper.selectOne(query);
			if (result != null) {
				item.setTableid(tableId);
				item.setStudentid(result.getId());
				item.setName(result.getName());
				item.setSubject(7);
				item.setAreaid(user.getAreaid());
				item.setStoreid(user.getAreaid());
				item.setCtime(now);
				item.setCuid(user.getId());
				item.setCname(user.getRealname());

				StudentStatusLog studentStatusLog = new StudentStatusLog();
				studentStatusLog.setStudentid(result.getId());
				studentStatusLog.setIdcard(result.getIdcard());
				studentStatusLog.setTableid(tableId);
				studentStatusLog.setSubject(ApplyExam.OTHER_RETURN_CERTI.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.OTHER_RETURN_CERTI.getName());
				addList(user,logList,studentStatusLog);
			} else {
				iterator.remove();
			}
		}
		if (list.size() > 0) {
			Map <String,Object> params = new HashMap<>(2);
			params.put("dblink", dblink);
			params.put("list", list);
			int count = materialItemMapper.insertBatch(params);
			Material material = new Material();
			material.setTableid(tableId);
			material.setSubject(7);
			material.setAreaid(user.getAreaid());
			material.setStoreid(user.getAreaid());
			material.setCname(user.getRealname());
			material.setCuid(user.getId());
			material.setCtime(now);
			material.setDblink(dblink);
			material.setItemcount(count);
			materialMapper.insertSelective(material);
			
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
		}
		return new ResultBean();
	}

	@Override
	public ResultBean doLicense(User user, List<MaterialItem> list) {
		String dblink = user.getDblink();
		Date now = new Date();
		String tableId = user.getBatchId();
		
		Student query = new Student();
		query.setDblink(dblink);
		
		Student result;
		Iterator<MaterialItem> iterator = list.iterator();
		List <StudentStatusLog> logList = new ArrayList<>();
		List <Student> studentList = new ArrayList<>();
		while (iterator.hasNext()) {
			MaterialItem item = iterator.next();
			query.setIdcard(item.getIdcard());
			result = studentMapper.selectOne(query);
			if (result != null) {
				item.setTableid(tableId);
				item.setStudentid(result.getId());
				item.setName(result.getName());
				item.setSubject(8);
				item.setAreaid(user.getAreaid());
				item.setStoreid(user.getAreaid());
				item.setCtime(now);
				item.setCuid(user.getId());
				item.setCname(user.getRealname());

				StudentStatusLog studentStatusLog = new StudentStatusLog();
				studentStatusLog.setStudentid(result.getId());
				studentStatusLog.setIdcard(result.getIdcard());
				studentStatusLog.setTableid(tableId);
				studentStatusLog.setSubject(ApplyExam.GRADUATE_GRADUATE_LICENSE.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.GRADUATE_GRADUATE_LICENSE.getName());
				addList(user,logList,studentStatusLog);

				Student update = new Student();
				update.setId(result.getId());
				update.setApplyexam(ApplyExam.GRADUATE_GRADUATE_LICENSE.getApplyexam());
				update.setApplystatus(ApplyExam.GRADUATE_GRADUATE_LICENSE.getApplystatus());
				studentList.add(update);
			} else {
				iterator.remove();
			}
		}
		if (list.size() > 0) {
			Map <String,Object> params = new HashMap<>(2);
			params.put("dblink", dblink);
			params.put("list", list);
			int count = materialItemMapper.insertBatch(params);
			Material material = new Material();
			material.setTableid(tableId);
			material.setSubject(8);
			material.setAreaid(user.getAreaid());
			material.setStoreid(user.getAreaid());
			material.setCname(user.getRealname());
			material.setCuid(user.getId());
			material.setCtime(now);
			material.setDblink(dblink);
			material.setItemcount(count);
			materialMapper.insertSelective(material);
			
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);

			studentService.updateStudentBatch(user,studentList);
		}
		return new ResultBean();
	}

	private void addList(User user,List <StudentStatusLog> list,StudentStatusLog studentStatusLog) {
		studentStatusLog.setCuid(user.getId());
		studentStatusLog.setCname(user.getRealname());
		studentStatusLog.setCtime(new Date());
		studentStatusLog.setStoreid(user.getStoreid());
		studentStatusLog.setAreaid(user.getAreaid());
		list.add(studentStatusLog);
	}
	
}
