package cn.com.liliyun.trainorg.model;

import cn.com.liliyun.common.model.BaseModel;
import java.io.Serializable;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

public class TrainExamItem extends BaseModel implements Serializable {
    private String tableid;

    @Excel(name="学员姓名")
    private String name;

    private Integer studentid;

    @Excel(name="驾校名称")
    private String schoolname;
    
    @Excel(name="准驾车型")
    private String traintype;
    
    @Excel(name="证件号码")
    private String idcard;
    
    @Excel(name="流水号")
    private String flownum;

    @Excel(name="类别",replace={"科目一培训_0","科目一考试_1","科目二培训_2","科目二考试_3","科目三培训_4","科目三考试_5","科目四考试_6"})
    private Integer subject;

    @Excel(name="结果",replace={"合格_0","不合格_1","未到_2","未考_3","取消_4"})
    private Integer result;
    
    @Excel(name="考试次数")
    private Integer examcount;
    
    @Excel(name="考试日期",exportFormat="yyyy-MM-dd")
    private Date examdate;
    
    @Excel(name="考场")
    private String examplace;

    private Integer examplaceid;

    @Excel(name="指标类别",replace={"正常_0","其他_1","紧急_2","额外_3"})
    private Integer indexcat;
    
    private String examtime;

    private Integer storeid;

    private Integer areaid;

    private String cname;

    private Date ctime;

    private Integer cuid;
    
    private Integer coachid;
    
    @Excel(name="错误信息")
    private String errormsg;

    private static final long serialVersionUID = 1L;
    
    public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public Integer getCoachid() {
		return coachid;
	}

	public void setCoachid(Integer coachid) {
		this.coachid = coachid;
	}

	public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid == null ? null : tableid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public Integer getExamplaceid() {
        return examplaceid;
    }

    public void setExamplaceid(Integer examplaceid) {
        this.examplaceid = examplaceid;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname == null ? null : schoolname.trim();
    }

    public String getTraintype() {
        return traintype;
    }

    public void setTraintype(String traintype) {
        this.traintype = traintype == null ? null : traintype.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getFlownum() {
        return flownum;
    }

    public void setFlownum(String flownum) {
        this.flownum = flownum == null ? null : flownum.trim();
    }

    public Integer getIndexcat() {
        return indexcat;
    }

    public void setIndexcat(Integer indexcat) {
        this.indexcat = indexcat;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public Date getExamdate() {
        return examdate;
    }

    public void setExamdate(Date examdate) {
        this.examdate = examdate;
    }

    public Integer getExamcount() {
        return examcount;
    }

    public void setExamcount(Integer examcount) {
        this.examcount = examcount;
    }

    public String getExamtime() {
        return examtime;
    }

    public void setExamtime(String examtime) {
        this.examtime = examtime == null ? null : examtime.trim();
    }

    public String getExamplace() {
        return examplace;
    }

    public void setExamplace(String examplace) {
        this.examplace = examplace == null ? null : examplace.trim();
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }
}