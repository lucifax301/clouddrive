package cn.com.liliyun.market.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class PotentialCustomer extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	@Excel(name="客户姓名")
    private String name;

	@Excel(name="性别",replace={"男_1","女_2"})
    private Byte sex;

	@Excel(name="手机号码")
    private String phone;
    
    private String idcard;

    private Integer targetclassid;

    private Integer targetarea;

    private Integer targetstore;

    private Byte infosource;

    private Integer saleschannel;

    private Integer cuid;

    @Excel(name="录入人")
    private String cname;

    @Excel(name="录入时间",exportFormat="yyyy-MM-dd HH:mm")
    private Date ctime;

    private Integer followerid;

    @Excel(name="跟踪人")
    private String followername;

    private Byte impression;

    private BigDecimal discount;

    private String focuson;

    private String extra;

    private String remark;

    private Byte introducetype;

    private Integer introducerid;

    private String introducername;

    private Byte introducersex;

    private String introducerphone;

    private String introduceridcard;

    private Date mtime;
    
    //额外字段
    private Integer applystate; //报名状态，0未报名，大于0则为学员对应ID
    
    @Excel(name="报名时间",exportFormat="yyyy-MM-dd")
    private Date applydate; //报名时间

    private Date ctimetop;
    
    private Date ctimelow;
    
    //导出字段，转换
    @Excel(name="意向班别")
    private String targetclassname; //意向班别
    
    @Excel(name="意向区域")
    private String targetregion; //意向区域：片区-门店
    
    @Excel(name="信息来源")
    private String infosourcestr; //信息来源
    
    @Excel(name="报名状态")
    private String applystatestr; //报名状态

	public Date getApplydate() {
		return applydate;
	}

	public void setApplydate(Date applydate) {
		this.applydate = applydate;
	}

	public String getTargetclassname() {
		return targetclassname;
	}

	public void setTargetclassname(String targetclassname) {
		this.targetclassname = targetclassname;
	}

	public String getTargetregion() {
		return targetregion;
	}

	public void setTargetregion(String targetregion) {
		this.targetregion = targetregion;
	}

	public String getInfosourcestr() {
		return infosourcestr;
	}

	public void setInfosourcestr(String infosourcestr) {
		this.infosourcestr = infosourcestr;
	}

	public String getApplystatestr() {
		return applystatestr;
	}

	public void setApplystatestr(String applystatestr) {
		this.applystatestr = applystatestr;
	}

	public Date getCtimetop() {
		return ctimetop;
	}

	public void setCtimetop(Date ctimetop) {
		this.ctimetop = ctimetop;
	}

	public Date getCtimelow() {
		return ctimelow;
	}

	public void setCtimelow(Date ctimelow) {
		this.ctimelow = ctimelow;
	}

	public Integer getApplystate() {
		return applystate;
	}

	public void setApplystate(Integer applystate) {
		this.applystate = applystate;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getTargetclassid() {
        return targetclassid;
    }

    public void setTargetclassid(Integer targetclassid) {
        this.targetclassid = targetclassid;
    }

    public Integer getTargetarea() {
        return targetarea;
    }

    public void setTargetarea(Integer targetarea) {
        this.targetarea = targetarea;
    }

    public Integer getTargetstore() {
        return targetstore;
    }

    public void setTargetstore(Integer targetstore) {
        this.targetstore = targetstore;
    }

    public Byte getInfosource() {
        return infosource;
    }

    public void setInfosource(Byte infosource) {
        this.infosource = infosource;
    }

    public Integer getSaleschannel() {
        return saleschannel;
    }

    public void setSaleschannel(Integer saleschannel) {
        this.saleschannel = saleschannel;
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

    public Integer getFollowerid() {
        return followerid;
    }

    public void setFollowerid(Integer followerid) {
        this.followerid = followerid;
    }

    public String getFollowername() {
        return followername;
    }

    public void setFollowername(String followername) {
        this.followername = followername == null ? null : followername.trim();
    }

    public Byte getImpression() {
        return impression;
    }

    public void setImpression(Byte impression) {
        this.impression = impression;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getFocuson() {
        return focuson;
    }

    public void setFocuson(String focuson) {
        this.focuson = focuson == null ? null : focuson.trim();
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra == null ? null : extra.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getIntroducetype() {
        return introducetype;
    }

    public void setIntroducetype(Byte introducetype) {
        this.introducetype = introducetype;
    }

    public Integer getIntroducerid() {
        return introducerid;
    }

    public void setIntroducerid(Integer introducerid) {
        this.introducerid = introducerid;
    }

    public String getIntroducername() {
        return introducername;
    }

    public void setIntroducername(String introducername) {
        this.introducername = introducername == null ? null : introducername.trim();
    }

    public Byte getIntroducersex() {
        return introducersex;
    }

    public void setIntroducersex(Byte introducersex) {
        this.introducersex = introducersex;
    }

    public String getIntroducerphone() {
        return introducerphone;
    }

    public void setIntroducerphone(String introducerphone) {
        this.introducerphone = introducerphone == null ? null : introducerphone.trim();
    }

    public String getIntroduceridcard() {
        return introduceridcard;
    }

    public void setIntroduceridcard(String introduceridcard) {
        this.introduceridcard = introduceridcard == null ? null : introduceridcard.trim();
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}