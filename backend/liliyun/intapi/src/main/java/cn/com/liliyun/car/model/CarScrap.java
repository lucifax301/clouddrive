package cn.com.liliyun.car.model;

import cn.com.liliyun.common.model.BaseModel;
import java.io.Serializable;
import java.util.Date;

public class CarScrap extends BaseModel implements Serializable {
    private Integer id;

	private Integer carid;

	private String carno;

	private Integer delay;

	private Integer type;

	private String remark;

	private Date delaydate;

	private Date oridelaydate;

	private Date acceptdate;

	private Date retiredate;

	private Date ctime;

	private Integer cuid;

	private String cname;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCarid() {
		return carid;
	}

	public void setCarid(Integer carid) {
		this.carid = carid;
	}

	public String getCarno() {
		return carno;
	}

	public void setCarno(String carno) {
		this.carno = carno == null ? null : carno.trim();
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Date getDelaydate() {
		return delaydate;
	}

	public void setDelaydate(Date delaydate) {
		this.delaydate = delaydate;
	}

	public Date getOridelaydate() {
		return oridelaydate;
	}

	public void setOridelaydate(Date oridelaydate) {
		this.oridelaydate = oridelaydate;
	}

	public Date getAcceptdate() {
		return acceptdate;
	}

	public void setAcceptdate(Date acceptdate) {
		this.acceptdate = acceptdate;
	}

	public Date getRetiredate() {
		return retiredate;
	}

	public void setRetiredate(Date retiredate) {
		this.retiredate = retiredate;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
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
}