package cn.com.liliyun.user.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.user.model.Config;

public interface AppUserService {

	public ResultBean login(String mobile, String password)  ;

	public ResultBean logout(String userId);

	public ResultBean getUserInfo(String userId);

	public ResultBean updateUser(Integer userId, String schoolId, String password,String passwordOld,  String photo, String name, String sex, String mobile, String codeInput);

	public ResultBean feedback(Long  userId, Integer deptId, String content, String pic);

	public ResultBean getSysMsg(Long userId, Integer schoolId);

	public ResultBean getToDo(Long userId, Integer schoolId);

	public ResultBean getToDoDetail(Long userId, Integer schoolId, String flowId);

	public ResultBean approve(Long userId, Integer schoolId, String flowId, String content);

	List<Config> getHomeConfig(Long userId, String schoolId, String companyId);

	public ResultBean getPotentialCustomerList(Long userId, Integer deptId, String schoolId, String companyId);

	public ResultBean getSalesChannel(Long userId, Integer deptId, String schoolId, String companyId);

	public ResultBean getFinanceReceipt(Long userId, Integer deptId, String schoolId, String companyId);

	public ResultBean getFinancePayOut(Long userId, Integer deptId, String schoolId, String companyId);

	public ResultBean getFinanceArrearage(Long userId, Integer deptId, String schoolId, String companyId);

	public ResultBean getExam(Long userId, Integer deptId, String schoolId, String companyId);

	public ResultBean getExamItem(Long userId, Integer deptId, String schoolId, String companyId);

	public ResultBean getDepartment(Long userId, String schoolId, String companyId);

	public ResultBean getTopContacts(Long userId, String schoolId, String companyId);

	public ResultBean upTopContacts(Long userId, String deptId, String schoolId, String companyId, Integer contsUserId,
			String contsSchoolId);

	public ResultBean getDepartmentStaff(Long userId, Integer deptId, String schoolId, String companyId);

	public ResultBean getStaffInfo(Integer userId, String deptId, String schoolId, String companyId, Integer staffId);

	public ResultBean updatePasswd(String mobile, String password);


}
