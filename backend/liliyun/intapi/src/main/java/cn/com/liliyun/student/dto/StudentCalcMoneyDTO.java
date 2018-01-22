package cn.com.liliyun.student.dto;

import java.io.Serializable;

public class StudentCalcMoneyDTO implements Serializable {

    private Integer classinfoid;
    private Integer activityid;
    private String traintype;
    private Integer couponmoney;
    private Integer submoney;

    public Integer getClassinfoid() {
        return classinfoid;
    }

    public void setClassinfoid(Integer classinfoid) {
        this.classinfoid = classinfoid;
    }

    public Integer getActivityid() {
        return activityid;
    }

    public void setActivityid(Integer activityid) {
        this.activityid = activityid;
    }

    public String getTraintype() {
        return traintype;
    }

    public void setTraintype(String traintype) {
        this.traintype = traintype;
    }

    public Integer getCouponmoney() {
        return couponmoney;
    }

    public void setCouponmoney(Integer couponmoney) {
        this.couponmoney = couponmoney;
    }

    public Integer getSubmoney() {
        return submoney;
    }

    public void setSubmoney(Integer submoney) {
        this.submoney = submoney;
    }
}