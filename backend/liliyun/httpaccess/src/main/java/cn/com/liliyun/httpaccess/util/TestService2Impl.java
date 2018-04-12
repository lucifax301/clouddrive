package cn.com.liliyun.httpaccess.util;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import cn.com.liliyun.common.annotation.ActionTrace;
import cn.com.liliyun.common.service.ServiceMediator;
import cn.com.liliyun.httpaccess.test.TestService;
import cn.com.liliyun.httpaccess.test.TestService2;

@ActionTrace
@Service
public class TestService2Impl implements TestService2 {

	@Autowired
	ServiceMediator serviceMediator;
	
	
	@Override
	public void test3() {
		TestService service=serviceMediator.getService(TestService.class);

	}

}
