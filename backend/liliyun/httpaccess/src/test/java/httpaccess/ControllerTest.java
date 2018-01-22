package httpaccess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.liliyun.httpaccess.controller.LoginController;
import cn.com.liliyun.httpaccess.controller.UserController;
import cn.com.liliyun.user.model.User;

@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations={"classpath:spring-init.xml","classpath:spring-mvc.xml"}) // 加载配置
public class ControllerTest {

	// 模拟request,response  
    
    @Autowired
    private UserController userController;
	
    @Autowired
    private LoginController loginController;
	
	@Test
    public void testLogin() {   
	    MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		User user = new User();
		//loginController.login(user, request, response);
    }  
}
