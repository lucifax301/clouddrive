package cn.com.liliyun.student.model;

import cn.com.liliyun.common.model.BaseModel;
import java.io.Serializable;
import java.math.BigDecimal;

public class StudentMoney extends BaseModel implements Serializable {
    private Integer studentid;

    private BigDecimal signmoney;

    private BigDecimal contractmoney;

    private BigDecimal paymoney;

    private BigDecimal submoney;

    private BigDecimal owesubmoney;

    private BigDecimal oweresitmoney;

    private BigDecimal owetrainmoney;

    private BigDecimal owemoney;

    private Integer owestatus;

    private Integer returnstatus;

    private static final long serialVersionUID = 1L;

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public BigDecimal getSignmoney() {
        return signmoney;
    }

    public void setSignmoney(BigDecimal signmoney) {
        this.signmoney = signmoney;
    }

    public BigDecimal getContractmoney() {
        return contractmoney;
    }

    public void setContractmoney(BigDecimal contractmoney) {
        this.contractmoney = contractmoney;
    }

    public BigDecimal getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(BigDecimal paymoney) {
        this.paymoney = paymoney;
    }

    public BigDecimal getSubmoney() {
        return submoney;
    }

    public void setSubmoney(BigDecimal submoney) {
        this.submoney = submoney;
    }

    public BigDecimal getOwesubmoney() {
        return owesubmoney;
    }

    public void setOwesubmoney(BigDecimal owesubmoney) {
        this.owesubmoney = owesubmoney;
    }

    public BigDecimal getOweresitmoney() {
        return oweresitmoney;
    }

    public void setOweresitmoney(BigDecimal oweresitmoney) {
        this.oweresitmoney = oweresitmoney;
    }

    public BigDecimal getOwetrainmoney() {
        return owetrainmoney;
    }

    public void setOwetrainmoney(BigDecimal owetrainmoney) {
        this.owetrainmoney = owetrainmoney;
    }

    public BigDecimal getOwemoney() {
        return owemoney;
    }

    public void setOwemoney(BigDecimal owemoney) {
        this.owemoney = owemoney;
    }

    public Integer getOwestatus() {
        return owestatus;
    }

    public void setOwestatus(Integer owestatus) {
        this.owestatus = owestatus;
    }

    public Integer getReturnstatus() {
        return returnstatus;
    }

    public void setReturnstatus(Integer returnstatus) {
        this.returnstatus = returnstatus;
    }
}