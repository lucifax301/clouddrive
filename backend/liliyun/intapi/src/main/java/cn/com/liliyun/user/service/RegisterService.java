package cn.com.liliyun.user.service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.user.model.Register;

/**
 * 注册申请服务
 * @author lilixc
 *
 */
public interface RegisterService {
	
	ResultBean saveRegister(Register register);
	
	ResultBean getList(Register register);
	
	ResultBean sendCodeMail(Register register);
	
	ResultBean checkRegister(Register register);
	
}