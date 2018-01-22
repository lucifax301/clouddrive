package cn.com.liliyun.exportexcel.model;

import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

public class StudentExport {

		@Excel(name="学员姓名") //生成EXCEL表头顺序按字段的顺序排列
		private String name;

		@Excel(name="性别",replace={"男_1","女_2"}) //码值转换
		private String sex;
		
		@Excel(name="证件号码")
		private String idcard;

		@Excel(name="联系电话")
		private String phone;

		@Excel(name="照片回执")
		private String photoback;

		@Excel(name="报考车型")
		private String traintype;

		@Excel(name="")
		private Date applydate;

		private Integer applyexam;

		private Integer applytype;

}
