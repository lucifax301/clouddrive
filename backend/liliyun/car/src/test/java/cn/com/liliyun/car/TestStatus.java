package cn.com.liliyun.car;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.liliyun.car.model.CarAnnual;
import cn.com.liliyun.car.service.ICarService;

@ContextConfiguration(locations = {"classpath:spring-init.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestStatus {

	@Autowired
	ICarService carService;
	
	@Test
	public void test() {
		System.out.println("**************************************");
	
		CarAnnual record =new CarAnnual();
        record.setCarId(1);
        record.setAssertDate(new Date());
        record.setAnnualIndate(new Date());
        record.setYgSign(1);
        record.setRohsDate(new Date());
        record.setCname("天天2");
        carService.addCarAnnual(record);
        
        record.setCname("阿修罗");
        record.setId(3);
        carService.updateCarAnnual(record);
        
        record.setId(4);
        carService.deleteCarAnnual(record);
	}

}
