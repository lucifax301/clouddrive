
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.liliyun.report.service.ICertificateReportService;
import cn.com.liliyun.trainorg.model.TrainExamItemVo;


@ContextConfiguration(locations = { "classpath:spring-init.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestStatus {


	@Autowired
	ICertificateReportService certificateReportService;
	
	@Test
	public void test() throws ParseException {
		
		TrainExamItemVo trainExamItem = new TrainExamItemVo();
		trainExamItem.setGroupId(1);
		//certificateReportService.getExamCase(trainExamItem , null);
		
	}

}
