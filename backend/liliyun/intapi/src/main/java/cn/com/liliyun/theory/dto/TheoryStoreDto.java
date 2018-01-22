package cn.com.liliyun.theory.dto;

import cn.com.liliyun.common.model.BaseModel;

public class TheoryStoreDto extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer theoryid;

    private Integer storeid;

    private Integer recomnum;

    private Integer arrangenum;

    private String extra;
    
    //门店基本信息
    private String storenum;

    private Integer areaid;

    private String name;

    private String tel;
    
    //门店当前受理学员数
    private Integer acceptedStu;

    public String getStorenum() {
		return storenum;
	}

	public void setStorenum(String storenum) {
		this.storenum = storenum;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getAcceptedStu() {
		return acceptedStu;
	}

	public void setAcceptedStu(Integer acceptedStu) {
		this.acceptedStu = acceptedStu;
	}

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