package cn.com.liliyun.market.model;

import java.io.Serializable;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.liliyun.common.model.BaseModel;

public class CustomerRecord extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer studentid;

    @Excel(name="学员姓名")
    private String name;

    private String idcard;

    @Excel(name="手机号码")
    private String phone;

    private Integer areaid;

    private Integer storeid;

    private Byte handlestate;

    private Byte handledeptype;

    private Integer handledepid;

    private Integer handlerid;

    @Excel(name="处理人")
    private String handlername;

    private Byte handlemethod;

    private Byte servicesource;

    private Byte servicemode;

    private Integer servicetype;

    private Integer servicesubtype;

    private String servicecontent;

    private Byte result;

    private Integer cuid;

    @Excel(name="提交人")
    private String cname;

    @Excel(name="服务时间",exportFormat="yyyy-MM-dd HH:mm")
    private Date ctime;

    private Date mtime;
    
    private Integer classid;
    
    @Excel(name="班别")
    private String classname;
    
    //范围筛选参数
    private Date ctimetop; //创建时间，最大值
    
    private Date ctimelow; //创建时间，最小值
    
    //导出数据，额外字段
    @Excel(name="服务来源")
    private String servicesourcestr;
    
    @Excel(name="服务类型")
    private String servicetypestr;
    
    @Excel(name="服务方式")
    private String servicemodestr;
    
    @Excel(name="处理状态")
    private String handlestatestr;
    
    public String getServicesourcestr() {
		return servicesourcestr;
	}

	public void setServicesourcestr(String servicesourcestr) {
		this.servicesourcestr = servicesourcestr;
	}

	public String getServicetypestr() {
		return servicetypestr;
	}

	public void setServicetypestr(String servicetypestr) {
		this.servicetypestr = servicetypestr;
	}

	public String getServicemodestr() {
		return servicemodestr;
	}

	public void setServicemodestr(String servicemodestr) {
		this.servicemodestr = servicemodestr;
	}

	public String getHandlestatestr() {
		return handlestatestr;
	}

	public void setHandlestatestr(String handlestatestr) {
		this.handlestatestr = handlestatestr;
	}

	public Integer getClassid() {
		return classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
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

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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

    public Byte getHandlestate() {
        return handlestate;
    }

    public void setHandlestate(Byte handlestate) {
        this.handlestate = handlestate;
    }

    public Byte getHandledeptype() {
        return handledeptype;
    }

    public void setHandledeptype(Byte handledeptype) {
        this.handledeptype = handledeptype;
    }

    public Integer getHandledepid() {
        return handledepid;
    }

    public void setHandledepid(Integer handledepid) {
        this.handledepid = handledepid;
    }

    public Integer getHandlerid() {
        return handlerid;
    }

    public void setHandlerid(Integer handlerid) {
        this.handlerid = handlerid;
    }

    public String getHandlername() {
        return handlername;
    }

    public void setHandlername(String handlername) {
        this.handlername = handlername == null ? null : handlername.trim();
    }

    public Byte getHandlemethod() {
        return handlemethod;
    }

    public void setHandlemethod(Byte handlemethod) {
        this.handlemethod = handlemethod;
    }

    public Byte getServicesource() {
        return servicesource;
    }

    public void setServicesource(Byte servicesource) {
        this.servicesource = servicesource;
    }

    public Byte getServicemode() {
        return servicemode;
    }

    public void setServicemode(Byte servicemode) {
        this.servicemode = servicemode;
    }

    public Integer getServicetype() {
        return servicetype;
    }

    public void setServicetype(Integer servicetype) {
        this.servicetype = servicetype;
    }

    public Integer getServicesubtype() {
        return servicesubtype;
    }

    public void setServicesubtype(Integer servicesubtype) {
        this.servicesubtype = servicesubtype;
    }

    public String getServicecontent() {
        return servicecontent;
    }

    public void setServicecontent(String servicecontent) {
        this.servicecontent = servicecontent == null ? null : servicecontent.trim();
    }

    public Byte getResult() {
        return result;
    }

    public void setResult(Byte result) {
        this.result = result;
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

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}