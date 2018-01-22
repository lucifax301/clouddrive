package cn.com.liliyun.theory.model;

import cn.com.liliyun.common.model.BaseModel;

public class TheoryStore extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer theoryid;

    private Integer storeid;

    private Integer recomnum;

    private Integer arrangenum;

    private String extra;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTheoryid() {
        return theoryid;
    }

    public void setTheoryid(Integer theoryid) {
        this.theoryid = theoryid;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public Integer getRecomnum() {
        return recomnum;
    }

    public void setRecomnum(Integer recomnum) {
        this.recomnum = recomnum;
    }

    public Integer getArrangenum() {
        return arrangenum;
    }

    public void setArrangenum(Integer arrangenum) {
        this.arrangenum = arrangenum;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }
}