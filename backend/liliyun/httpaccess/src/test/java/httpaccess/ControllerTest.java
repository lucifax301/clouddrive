package httpaccess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cn.com.liliyun.common.util.ApplicationContextUtil;
import cn.com.liliyun.httpaccess.test.TestController;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class) // 整合 
//@ContextConfiguration(locations={"classpath:spring-init.xml","classpath:spring-mvc.xml"}) // 加载配置
@ContextConfiguration(locations={"classpath:spring-test-init.xml","classpath:spring-test-mvc.xml"}) // 加载配置
@WebAppConfiguration("classpath:")
public class ControllerTest {

	// 模拟request,response  
    
//    @Autowired
//    private UserController userController;
//	
//    @Autowired
//    private LoginController loginController;
    @Autowired
    TestController TestController;
   
    
//	@Test
//    public void testLogin() throws Exception {   
//	    MockHttpServletRequest request = new MockHttpServletRequest();
//		MockHttpServletResponse response = new MockHttpServletResponse();
//		User user = new User();
//		loginController.login(user,"",1, request, response);
//    } 
    
    @Autowired
    private WebApplicationContext wac;
    
 // @Resource
    
    
    @Test
    public void testLogin() throws Exception { 
    	int a,b,c;
    	a=b=c=10;
    	System.out.println("#################"+a+" "+b+" "+c);
	    MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		User user = new User();
		//TestController.edit(null);
		
		MockMvc mockMvc =MockMvcBuilders.webAppContextSetup(wac).build();
		ResultActions resultActions = mockMvc.perform((MockMvcRequestBuilders.get("/test/edit").param("id", "1")));
		MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("response:" + result);
		
    } 
}
