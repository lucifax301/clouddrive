package cn.com.liliyun.student.service.impl;

import cn.com.liliyun.common.model.RequestContext;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ApplyExam;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.student.mapper.FileItemMapper;
import cn.com.liliyun.student.mapper.FileMapper;
import cn.com.liliyun.student.mapper.StudentMapper;
import cn.com.liliyun.student.mapper.StudentStatusLogMapper;
import cn.com.liliyun.student.model.File;
import cn.com.liliyun.student.model.FileItem;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.model.StudentStatusLog;
import cn.com.liliyun.student.service.FileService;
import cn.com.liliyun.user.model.User;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileMapper fileMapper;
	
	@Autowired
	private FileItemMapper fileItemMapper;
	
	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private StudentStatusLogMapper studentStatusLogMapper;
	
	@Override
	public List<File> list(File file) {
		PageUtil.startPage(file);
		return fileMapper.selectList(file);
	}

	@Override
	public ResultBean doHandleFile( List<FileItem> list) {
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
		Date now = new Date();
		String tableId = user.getBatchId();

		Student query = new Student();
		
		Student result;
		Iterator<FileItem> iterator = list.iterator();
		List <StudentStatusLog> logList = new ArrayList<>();
		
		//生成档案编号 A000001 A999999 -> B000001
		FileItem item = new FileItem();
		
		item.setType(0);
		String filenum = fileItemMapper.selectMaxFilenum(item);
		while (iterator.hasNext()) {
			FileItem fi = iterator.next();
			query.setIdcard(fi.getIdcard());
			result = studentMapper.selectOne(query);
			if (result != null) {
				fi.setTableid(tableId);
				fi.setStudentid(result.getId());
				fi.setName(result.getName());
				fi.setAreaid(user.getAreaid());
				fi.setStoreid(user.getAreaid());
				filenum = getFilenum(filenum);
				fi.setFilenum(filenum);
				fi.setType(0);
				fi.setCtime(now);
				fi.setCuid(user.getId());
				fi.setCname(user.getRealname());

				StudentStatusLog studentStatusLog = new StudentStatusLog();
				studentStatusLog.setStudentid(result.getId());
				studentStatusLog.setIdcard(result.getIccard());
				studentStatusLog.setTableid(tableId);
				studentStatusLog.setSubject(ApplyExam.SIGNUP_ACCEPT_FILE.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.SIGNUP_ACCEPT_FILE.getName());
				logList.add(studentStatusLog);
			} else {
				iterator.remove();
			}
		}
		if (list.size() > 0) {
			Map <String,Object> params = new HashMap<>(2);
			
			params.put("list", list);
			int count = fileItemMapper.insertBatch(params);
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
			File file = new File();
			file.setTableid(tableId);
			file.setType(0);
			file.setCname(user.getRealname());
			file.setCuid(user.getId());
			file.setCtime(now);
			file.setAreaid(user.getAreaid());
			file.setStoreid(user.getAreaid());
			
			file.setItemcount(count);
			fileMapper.insertSelective(file);
		}
		ResultBean rb = new ResultBean();
		rb.setResult(tableId);
		return rb;
	}
	

	@Override
	public ResultBean doStuFile( List<FileItem> list) {
		User user = RequestContext.get(ConstantUtil.USER_SESSION);
		Date now = new Date();
		String tableId = user.getBatchId();
		Student query = new Student();
		
		Student result;
		Iterator<FileItem> iterator = list.iterator();
		List <StudentStatusLog> logList = new ArrayList<>();
		
		//生成档案编号 A000001 A999999 -> B000001
		FileItem item = new FileItem();
		
		item.setType(1);
		String filenum = fileItemMapper.selectMaxFilenum(item);
		while (iterator.hasNext()) {
			FileItem fi = iterator.next();
			query.setIdcard(fi.getIdcard());
			result = studentMapper.selectOne(query);
			if (result != null) {
				fi.setTableid(tableId);
				fi.setStudentid(result.getId());
				fi.setName(result.getName());
				fi.setAreaid(user.getAreaid());
				fi.setStoreid(user.getAreaid());
				filenum = getFilenum(filenum);
				fi.setFilenum(filenum);
				fi.setType(1);
				fi.setCtime(now);
				fi.setCuid(user.getId());
				fi.setCname(user.getRealname());
				
				StudentStatusLog studentStatusLog = new StudentStatusLog();
				studentStatusLog.setStudentid(result.getId());
				studentStatusLog.setIdcard(result.getIccard());
				studentStatusLog.setTableid(tableId);
				studentStatusLog.setSubject(ApplyExam.GRADUATE_GRADUATE_FILE.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.GRADUATE_GRADUATE_FILE.getName());
				logList.add(studentStatusLog);
			} else {
				iterator.remove();
			}
		}
		if (list.size() > 0) {
			Map <String,Object> params = new HashMap<>(2);
			
			params.put("list", list);
			int count = fileItemMapper.insertBatch(params);
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
			
			File file = new File();
			file.setTableid(tableId);
			file.setType(1);
			file.setAreaid(user.getAreaid());
			file.setStoreid(user.getAreaid());
			file.setCname(user.getRealname());
			file.setCuid(user.getId());
			file.setCtime(now);
			
			file.setItemcount(count);
			fileMapper.insertSelective(file);
		}
		ResultBean rb = new ResultBean();
		rb.setResult(tableId);
		return rb;
	}

	@Override
	public List<FileItem> listItem( FileItem fileItem) {
		return fileItemMapper.selectList(fileItem);
	}
	
	private String getFilenum(String filenum) {
		if (StringUtils.isBlank(filenum)) {
			return "A000001";
		}
		DecimalFormat df = new DecimalFormat("000000");
		char prefix = filenum.charAt(0);
		String seq = filenum.substring(1,7);
		if ("999999".equals(seq)) {
			prefix = (char)(prefix + 1);
			seq = df.format(1);
		} else {
			seq = df.format(Integer.parseInt(seq) + 1);
		}
		return prefix + seq; 
	}
	
}
