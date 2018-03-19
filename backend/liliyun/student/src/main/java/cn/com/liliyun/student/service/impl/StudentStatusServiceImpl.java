package cn.com.liliyun.student.service.impl;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.student.model.StudentStatus;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentStatusServiceImpl implements StudentStatusService {
	
	Logger logger = Logger.getLogger(StudentStatusServiceImpl.class);
	
	@Autowired
	private StudentStatusMapper StudentStatusMapper;

	@Override
	public ResultBean addStudentStatus(StudentStatus StudentStatus) {
		StudentStatusMapper.insertSelective(StudentStatus);
		return new ResultBean();
	}

	@Override
	public ResultBean deleteStudentStatus(StudentStatus StudentStatus) {
		String [] ids = StudentStatus.getIds().split(",");
		for (String id : ids) {
			StudentStatus.setId(Integer.parseInt(id));
			StudentStatusMapper.deleteByPrimaryKey(StudentStatus);
		}
		return new ResultBean();
	}

	@Override
	public ResultBean getList(StudentStatus StudentStatus) {
		ResultBean rb = new ResultBean();
		PageUtil.startPage(StudentStatus);
		if(StudentStatus==null)StudentStatus=new StudentStatus();
		List<StudentStatus> list = StudentStatusMapper.selectList(StudentStatus);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}

	@Override
	public ResultBean getStudentStatusById(StudentStatus StudentStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount(StudentStatus StudentStatus) {
		return StudentStatusMapper.getCount(StudentStatus);
	}

	@Override
	public ResultBean updateStudentStatus(StudentStatus StudentStatus) {
		StudentStatusMapper.updateByPrimaryKeySelective(StudentStatus);
		return new ResultBean();
	}

}
