package org.coach;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.coach.service.CoachService;

@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations="classpath:spring-init.xml") // 加载配置
public class MyTest {

	
	@Autowired
	private CoachService coachService;

	
	@Test
	public void test() {

		Coach coach = new Coach();
		
		coach.setInscode("199");
		coach.setName("lisi");
		coach.setIdcard("1");;
		coach.setMobile("1372864");
		coach.setSex("1");
//		coachService.addCoach(coach);

		//coachService.updateCoach(coach);
//		
//		coachService.deleteById(new Integer(1));
//		
		

		
	}
}
