package cn.com.liliyun.trainorg.model;

import java.io.Serializable;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class TrainExamItemDetailVo extends BaseModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6232005297752092848L;


	private String tableId;

    private String traintype;
    
    private Integer subject;
    
    private Integer upsubject;
    
    private String subjectName;

    private Integer result;
    
    private Integer classId;
    
    private String className;
    
    private Integer examcount;
    
    private Date examdate;
    
    private String examplace;

    private Integer indexcat;
    
    private Integer upResult;
    
    private Date upDay;
    
    private String examtime;

    private Integer storeId;
    
    private String storeName;

    private Integer areaId;
    
    private String areaName;

    private Integer coachId;
    
    private String coachName;
    
    private Integer headCoachId;
    
    private String headCoachName;
    
    private Double percentage;
    
    private Integer degree;
    
    private Integer nnt;
    
    private Integer waitDay;
    
    private Date ctime;
    
    private String idcard;
    
    private String name;
    
    private Integer examplaceid;
    
    

	public Integer getExamplaceid() {
		return examplaceid;
	}

	public void setExamplaceid(Integer examplaceid) {
		this.examplaceid = examplaceid;
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


	public Date getUpDay() {
		return upDay;
	}

	public void setUpDay(Date upDay) {
		this.upDay = upDay;
	}

	public Integer getUpResult() {
		return upResult;
	}

	public void setUpResult(Integer upResult) {
		this.upResult = upResult;
	}

}