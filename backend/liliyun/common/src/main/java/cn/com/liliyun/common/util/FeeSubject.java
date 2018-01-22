package cn.com.liliyun.common.util;

/**
 * Created by lilixc on 2017/4/6.
 */
public enum FeeSubject {

    INCMOE("收入",1),
    PAY("支出",2),

    REDUCE_FEE("减免费",1),
    RESIT_FEE("补考费",2),
    TRAIN_FEE("培训费",3),
    
    EXAM_FEE("考试费",4),
    SIGNUP_FEE("报名费",5),
    COST_FEE("工本费",6);
	
    private String name;
    private int status;

    private FeeSubject(String name,int status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
