package cn.com.liliyun.trainorg.model;

import cn.com.liliyun.common.model.BaseModel;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

public class ResultExamItem extends BaseModel implements Serializable {
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

    @Excel(name="预约科目",replace={"科目一_1","科目二_2","科目三_3","科目四_4"})
    private Integer subject;

    @Excel(name="预约结果",replace={"考试合格_1","考试不合格_0"})
    private Integer result;

    @Excel(name="考试日期",exportFormat="yyyy-MM-dd")
    private Date examdate;

    @Excel(name="考试时间",importFormat="hh:mm")
    private String examtime;

    @Excel(name="考试次数")
    private Integer examcount;

    @Excel(name="考场")
    private Integer examplaceid;

    @Excel(name="是否改期",replace={"是_1","否_0"})
    private Integer changedate;
    
    private Integer areaid;
    
    private Integer storeid;

    private Integer cuid;

    private String cname;

    private Date ctime;
    
    private Integer coachid;

    private String coachname;

    @Excel(name="指标类别",replace={"正常_0","其他_1","紧急_2","额外_3"})
    private Integer indexcat;
    
    @Excel(name="错误信息")
    private String errormsg;

    private static final long serialVersionUID = 1L;

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

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getExamdate() {
        return examdate;
    }

    public void setExamdate(Date examdate) {
        this.examdate = examdate;
    }

    public String getExamtime() {
        return examtime;
    }

    public void setExamtime(String examtime) {
        this.examtime = examtime == null ? null : examtime.trim();
    }

    public Integer getExamplaceid() {
        return examplaceid;
    }

    public void setExamplaceid(Integer examplaceid) {
        this.examplaceid = examplaceid;
    }

    public Integer getChangedate() {
        return changedate;
    }

    public void setChangedate(Integer changedate) {
        this.changedate = changedate;
    }

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
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

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

    public String getCoachname() {
        return coachname;
    }

    public void setCoachname(String coachname) {
        this.coachname = coachname;
    }

    public Integer getExamcount() {
        return examcount;
    }

    public void setExamcount(Integer examcount) {
        this.examcount = examcount;
    }

    public Integer getIndexcat() {
        return indexcat;
    }

    public void setIndexcat(Integer indexcat) {
        this.indexcat = indexcat;
    }
}