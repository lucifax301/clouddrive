package cn.com.liliyun.user.service;

import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.user.model.Config;

public interface AppUserService {

	ResultBean login(String mobile, String password)  ;

	ResultBean logout(String userId);

	ResultBean getUserInfo(String userId);

	ResultBean updateUser(Integer userId, String schoolId, String password,String passwordOld,  String photo, String name, String sex, String mobile, String codeInput);

	ResultBean feedback(Long  userId, Integer deptId, String content, String pic);

	ResultBean getSysMsg(Long userId, Integer schoolId);

	ResultBean getToDo(Long userId, Integer schoolId);

	ResultBean getToDoDetail(Long userId, Integer schoolId, String flowId);

	ResultBean approve(Long userId, Integer schoolId, String flowId, String content);

	List<Config> getHomeConfig(Long userId, String schoolId, String companyId);

	ResultBean getPotentialCustomerList(Long userId, Integer deptId, String schoolId, String companyId);

	ResultBean getSalesChannel(Long userId, Integer deptId, String schoolId, String companyId);

	ResultBean getFinanceReceipt(Long userId, Integer deptId, String schoolId, String companyId);

	ResultBean getFinancePayOut(Long userId, Integer deptId, String schoolId, String companyId);

	ResultBean getFinanceArrearage(Long userId, Integer deptId, String schoolId, String companyId);

	ResultBean getExam(Long userId, Integer deptId, String schoolId, String companyId);

	ResultBean getExamItem(Long userId, Integer deptId, String schoolId, String companyId);

	ResultBean getDepartment(Long userId, String schoolId, String companyId);

	ResultBean getTopContacts(Long userId, String schoolId, String companyId);

	ResultBean upTopContacts(Long userId, String deptId, String schoolId, String companyId, Integer contsUserId,
			String contsSchoolId);

	ResultBean getDepartmentStaff(Long userId, Integer deptId, String schoolId, String companyId);

	ResultBean getStaffInfo(Integer userId, String deptId, String schoolId, String companyId, Integer staffId);

	ResultBean updatePasswd(String mobile, String password);


}
