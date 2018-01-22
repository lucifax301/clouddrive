package cn.com.liliyun.report.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.com.liliyun.user.vo.ConfigVo;

/**
 * Created by lilixc on 2017/4/26.
 */
public class AppIndexInfo {

    //报名人数
    private Integer applycount;

    //昨日报名人数
    private Integer yestapplycount;

    //考试人数
    private Integer examcount;

    //通过率
    private Double passrate;

    //收入金额
    private BigDecimal income;

    //支出金额
    private BigDecimal pay;

    //查询时间
    private Date qtime;
    
    private List<ConfigVo> configList;

    public Integer getApplycount() {
        return applycount;
    }

    public void setApplycount(Integer applycount) {
        this.applycount = applycount;
    }

    public Integer getExamcount() {
        return examcount;
    }

    public void setExamcount(Integer examcount) {
        this.examcount = examcount;
    }

    public Double getPassrate() {
        return passrate;
    }

    public void setPassrate(Double passrate) {
        this.passrate = passrate;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public Date getQtime() {
        return qtime;
    }

    public void setQtime(Date qtime) {
        this.qtime = qtime;
    }

    public Integer getYestapplycount() {
        return yestapplycount;
    }

    public void setYestapplycount(Integer yestapplycount) {
        this.yestapplycount = yestapplycount;
    }

	public List<ConfigVo> getConfigList() {
		return configList;
	}

	public void setConfigList(List<ConfigVo> configList) {
		this.configList = configList;
	}
}
