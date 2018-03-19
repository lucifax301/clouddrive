package cn.com.liliyun.trainorg.service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.trainorg.model.StudentStatus;
/**
 * 
 * 学员状态管理
 * @author Administrator
 *
 */
public interface StudentStatusService {
	
	public ResultBean addStudentStatus(StudentStatus StudentStatus);
	
	public ResultBean deleteStudentStatus(StudentStatus StudentStatus);
	
	public ResultBean updateStudentStatus(StudentStatus StudentStatus);
	
	public ResultBean getList(StudentStatus StudentStatus);
	
	public ResultBean getStudentStatusById(StudentStatus StudentStatus);
	
	public Integer getCount(StudentStatus StudentStatus);
	
}
