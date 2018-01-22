package user;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.user.mapper.UserMapper;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.RegisterService;

@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations="classpath:spring-init.xml") // 加载配置
public class Test {
	
    @Autowired
	private RegisterService registerService;
	
	@Autowired
	private UserMapper userMapper;
	
	@org.junit.Test
	public void test() {
		User t  = new User();
		t.setDblink("liliyun");
		PageUtil.startPage(t);
		//System.out.println(userMapper.selectAll(t));
	}
}
 