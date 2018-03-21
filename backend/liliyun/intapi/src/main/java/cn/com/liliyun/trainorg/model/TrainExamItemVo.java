package cn.com.liliyun.trainorg.model;

import java.io.Serializable;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class TrainExamItemVo extends BaseModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 818048284562136128L;

	private String tableId;

	@Excel(name="准驾车型")
    private String traintype;
    
	 @Excel(name="类别",replace={"科目一培训_0","科目一考试_1","科目二培训_2","科目二考试_3","科目三培训_4","科目三考试_5","科目四考试_6"})
    private Integer subject;
    
	 @Excel(name="上一科目类别",replace={"科目一培训_0","科目一考试_1","科目二培训_2","科目二考试_3","科目三培训_4","科目三考试_5","科目四考试_6"})
    private Integer upsubject;
    
    private String subjectName;

    @Excel(name="考试结果",replace={"合格_0","不合格_1","未到_2","未考_3","取消_4"})
    private Integer result;
    
    private Integer classId;
    
    @Excel(name="班别")
    private String className;
    
    @Excel(name="考试次数")
    private Integer examcount;
    
    @Excel(name="考试日期",exportFormat="yyyy-MM-dd")
    private Date examdate;
    
    @Excel(name="训练场")
    private String examplace;

    @Excel(name="指标类别",replace={"正常_0","其他_1","紧急_2","额外_3"})
    private Integer indexcat;
    
    private String examtime;

    private Integer storeId;
    
    @Excel(name="门店地址")
    private String storeName;

    private Integer areaId;
    
    @Excel(name="区域")
    private String areaName;

    private Integer coachId;
    
    @Excel(name="教练")
    private String coachName;
    
    private Integer headCoachId;
    
    @Excel(name="教学组长")
    private String headCoachName;
    
    private Double percentage;
    
    private Integer degree;
    
    @Excel(name="人数")
    private Integer nnt;
    
    private Integer groupId;
    
    @Excel(name="等待天数")
    private Integer waitDay;
    
    private Date startTime;
    
    private Date endTime;
    
    private Date ctime;
    
    private Date upDay;
    
    private String type;
    
    private Integer examplaceid;
    
    

	public Integer getExamplaceid() {
		return examplaceid;
	}

	public void setExamplaceid(Integer examplaceid) {
		this.examplaceid = examplaceid;
	}

	public Date getUpDay() {
		return upDay;
	}

	public void setUpDay(Date upDay) {
		this.upDay = upDay;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}


	public String getTraintype() {
		return traintype;
	}

	public void setTraintype(String traintype) {
		this.traintype = traintype;
	}


	public Integer getSubject() {
		return subject;
	}

	public void setSubject(Integer subject) {
		this.subject = subject;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getHeadCoachId() {
		return headCoachId;
	}

	public void setHeadCoachId(Integer headCoachId) {
		this.headCoachId = headCoachId;
	}

	public String getHeadCoachName() {
		return headCoachName;
	}

	public void setHeadCoachName(String headCoachName) {
		this.headCoachName = headCoachName;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getExamcount() {
		return examcount;
	}

	public void setExamcount(Integer examcount) {
		this.examcount = examcount;
	}

	public Date getExamdate() {
		return examdate;
	}

	public void setExamdate(Date examdate) {
		this.examdate = examdate;
	}

	public String getExamplace() {
		return examplace;
	}

	public void setExamplace(String examplace) {
		this.examplace = examplace;
	}

	public Integer getIndexcat() {
		return indexcat;
	}

	public void setIndexcat(Integer indexcat) {
		this.indexcat = indexcat;
	}

	public String getExamtime() {
		return examtime;
	}

	public void setExamtime(String examtime) {
		this.examtime = examtime;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getCoachId() {
		return coachId;
	}

	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public Integer getNnt() {
		return nnt;
	}

	public void setNnt(Integer nnt) {
		this.nnt = nnt;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Integer getUpsubject() {
		return upsubject;
	}

	public void setUpsubject(Integer upsubject) {
		this.upsubject = upsubject;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getWaitDay() {
		return waitDay;
	}

	public void setWaitDay(Integer waitDay) {
		this.waitDay = waitDay;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}