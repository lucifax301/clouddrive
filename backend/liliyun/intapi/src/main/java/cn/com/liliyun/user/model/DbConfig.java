package cn.com.liliyun.user.model;

import cn.com.liliyun.common.model.BaseModel;

public class DbConfig extends BaseModel {
	
    private Integer id;

    private Byte isused;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getIsused() {
        return isused;
    }

    public void setIsused(Byte isused) {
        this.isused = isused;
    }

}