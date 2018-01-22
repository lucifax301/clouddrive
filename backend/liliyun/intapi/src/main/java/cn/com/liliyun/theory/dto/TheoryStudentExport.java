package cn.com.liliyun.theory.dto;

import cn.com.liliyun.common.model.BaseModel;
import java.io.Serializable;
import org.jeecgframework.poi.excel.annotation.Excel;

public class TheoryStudentExport extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Excel(name="学员编号", width=15)
	private String stunum;

	@Excel(name="学员姓名") //生成EXCEL表头顺序按字段的顺序排列
	private String name;

	@Excel(name="学员手机", width=15)
	private String phone;
	
	@Excel(name="性别",replace={"男_1","女_2"}) //码值转换
	private String sex;
	
	@Excel(name="所学车型")
	private String traintype;
	
	@Excel(name="证件号码", width=20)
	private String idcard;

	@Excel(name="流水号", width=20)
	private String flownum;
	
	private Byte applyexam;
	
	@Excel(name="学车状态")
	private String applyexamstr;
	
	public String getApplyexamstr() {
		return applyexamstr;
	}

	public void setApplyexamstr(String applyexamstr) {
		this.applyexamstr = applyexamstr;
	}

	public String getTraintype() {
		return traintype;
	}

	public void setTraintype(String traintype) {
		this.traintype = traintype;
	}

	public String getStunum() {
		return stunum;
	}

	public void setStunum(String stunum) {
		this.stunum = stunum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Byte getApplyexam() {
		return applyexam;
	}

	public void setApplyexam(Byte applyexam) {
		this.applyexam = applyexam;
	}

	public String getFlownum() {
		return flownum;
	}

	public void setFlownum(String flownum) {
		this.flownum = flownum;
	}

}