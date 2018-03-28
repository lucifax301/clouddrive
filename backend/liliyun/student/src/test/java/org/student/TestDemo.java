package org.student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.liliyun.common.model.RequestContext;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.student.mapper.TheoryLessonMapper;
import cn.com.liliyun.user.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-test-init.xml")
public class TestDemo {
	public static void main(String[] args) {
		System.out.println("x");
	}
	
	//@Autowired
	//private StudentService studentService;
	
	@Autowired
	private TheoryLessonMapper theoryLessonMapper;
	
	@Test
	public void fun1(){
		/*StudentService ss = new StudentServiceImpl();
		
		ss.addUser(new Student(1,"hello"));*/
		@SuppressWarnings("unused")
		RequestContext rc = RequestContext.getOrCreate();
		User user = new User();
		user.setMgrdb(false);
		user.setDblink("jx_00002");
		RequestContext.put(ConstantUtil.USER_SESSION, user);
		System.out.println("###############################xx");
		theoryLessonMapper.selectByPrimaryKey(1);
		theoryLessonMapper.selectLesson(null);
	}
}
