package cn.com.liliyun.user.service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.user.model.Register;

/**
 * 注册申请服务
 * @author lilixc
 *
 */
public interface RegisterService {
	
	public ResultBean saveRegister(Register register);
	
	public ResultBean getList(Register register);
	
	public ResultBean sendCodeMail(Register register);
	
	public ResultBean checkRegister(Register register);
	
}