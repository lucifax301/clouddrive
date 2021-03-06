package cn.com.liliyun.httpaccess.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.annotation.ActionTrace;
import cn.com.liliyun.common.service.ServiceMediator;
import cn.com.liliyun.httpaccess.test.TestService;
import cn.com.liliyun.httpaccess.test.TestService2;

@ActionTrace
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	ServiceMediator serviceMediator;
	
	
	@Override
	public void dotest() {
		System.out.println("dotest------");
		TestService2 service2=serviceMediator.getService(TestService2.class);
		dotest2();
	}

	public void dotest2(){
		System.out.println("dotest2------");
	}

	@Override
	public void dotest3() {
		System.out.println("dotest3------");
		dotest();
	}
	
	
}
