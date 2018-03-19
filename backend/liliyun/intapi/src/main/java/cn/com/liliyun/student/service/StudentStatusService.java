package cn.com.liliyun.student.service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.student.model.StudentStatus;

/**
 * 
 * 学员状态管理
 * @author Administrator
 *
 */
 interface StudentStatusService {
	
	 ResultBean addStudentStatus(StudentStatus StudentStatus);
	
	 ResultBean deleteStudentStatus(StudentStatus StudentStatus);
	
	 ResultBean updateStudentStatus(StudentStatus StudentStatus);
	
	 ResultBean getList(StudentStatus StudentStatus);
	
	 ResultBean getStudentStatusById(StudentStatus StudentStatus);
	
	 Integer getCount(StudentStatus StudentStatus);
	
}
