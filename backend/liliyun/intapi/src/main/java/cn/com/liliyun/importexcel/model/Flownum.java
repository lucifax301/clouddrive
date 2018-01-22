package cn.com.liliyun.importexcel.model;

import java.io.Serializable;

import org.jeecgframework.poi.excel.annotation.Excel;

public class Flownum implements Serializable {

	private static final long serialVersionUID = 2920410012758766365L;

	@Excel(name="流水号",isImportField="true")
	private String flownum;

	@Excel(name="证件号码",isImportField="true")
	private String idcard;

	@Excel(name="姓名",isImportField="true")
	private String name;

	@Excel(name="准驾车型",isImportField="true")
	private String traintype;

	@Excel(name="驾校名称",isImportField="true")
	private String schoolname;

	@Excel(name="受理时间",isImportField="true")
	private String applydate;
	
	@Excel(name="错误信息")
	private String errormsg;

	public String getFlownum() {
		return flownum;
	}

	public void setFlownum(String flownum) {
		this.flownum = flownum;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTraintype() {
		return traintype;
	}

	public void setTraintype(String traintype) {
		this.traintype = traintype;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	public String getApplydate() {
		return applydate;
	}

	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

}
