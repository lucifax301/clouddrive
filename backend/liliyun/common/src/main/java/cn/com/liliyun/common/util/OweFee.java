package cn.com.liliyun.common.util;

/**
 * Created by lilixc on 2017/4/6.
 */
public enum OweFee {

    UNOWE("不欠费",0),
    OWE("欠费",1);


    private String name;
    private int status;

    private OweFee(String name, int status) {
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
