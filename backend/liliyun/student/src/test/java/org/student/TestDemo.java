package org.student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.liliyun.common.model.RequestContext;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.student.mapper.StudentMapper;
import cn.com.liliyun.student.mapper.TheoryLessonMapper;
import cn.com.liliyun.student.model.Student;
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
	@Autowired
	private StudentMapper studentMapper;
	
	@Test
	public void fun1(){
		/*StudentService ss = new StudentServiceImpl();
		
		ss.addUser(new Student(1,"hello"));*/
		@SuppressWarnings("unused")
		RequestContext rc = RequestContext.getOrCreate();
		User user = new User();
		user.setMgrdb(false);
		user.setDblink("jx_00002");
		RequestContext.putValue(ConstantUtil.USER_SESSION, user);
		System.out.println("###############################xx");
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		List<Runnable> list =new ArrayList();
		for(int i=0;i<10;i++){
			TestThread t=new TestThread();
			list.add(t);
		}
		
//		Student student = new Student();
//		student.setPageNo(1);
//		student.setPageSize(2);
//		PageUtil.startPage(student);
		
		//List<Student> list2 = studentMapper.selectList(student);
		
		for(int i=0;i<10;i++){
			executor.execute(list.get(i));
		}
		try {
			Thread.currentThread().sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//theoryLessonMapper.selectByPrimaryKey(1);
		//theoryLessonMapper.selectLesson(null);
		
		
	}
	
	class TestThread implements Runnable{

		@Override
		public void run() {
			RequestContext rc = RequestContext.getOrCreate();
			User user = new User();
			user.setMgrdb(false);
			user.setDblink("jx_00002");
			RequestContext.putValue(ConstantUtil.USER_SESSION, user);
			//theoryLessonMapper.selectByPrimaryKey(1);
			Student student = new Student();
			for(int i=0;i<10;i++){
			student.setPageNo(1);
			student.setPageSize(2);
			PageUtil.startPage(student);
			
			List<Student> list = studentMapper.selectList(student);
			}
		}
		
	}
}
