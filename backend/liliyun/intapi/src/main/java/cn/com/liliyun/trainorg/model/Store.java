package cn.com.liliyun.trainorg.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import cn.com.liliyun.common.model.BaseModel;

public class Store extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String storenum;

    private Integer areaid;

    private String name;

    private String images;

    private String tel;

    private String licensename;

    private Integer region;

    private Double lng;

    private Double lat;

    private String tradingarea;

    private String landmark;

    private String busstation;

    private Byte operatetype;

    private Byte type;

    private Integer status;

    private Date opendate;

    private BigDecimal area;

    private BigDecimal usingarea;

    private BigDecimal rent;

    private BigDecimal deposit;

    private BigDecimal rentunitprice;

    private Byte issublet;

    private Date starttime;

    private Date endtime;

    private String contact;

    private Byte renttype;

    private String rentpaylimit;

    private Date licensestart;

    private Date licenseend;

    private Integer licensetime;

    private String owner;

    private Byte ownertype;

    private String landlord;

    private String landlordphone;

    private String landlordaddress;

    private Integer totalfloor;

    private Integer curfloor;

    private String manager;

    private Integer workstart;

    private Integer workend;

    private Integer monitordevice;

    private Integer topad;

    private Integer frontad;

    private Integer ledad;

    private Integer signboard;

    private Integer boxad;
    
    private Integer scale;

    private Date ctime;

    private Date mtime;

    private String address;
    
    //范围筛选参数
    private Integer usingareatop; //面积筛选，最大值
    
    private Integer usingarealow; //面积筛选，最小值
    
    private Integer rentunitpricetop; //租金筛选，最大值
    
    private Integer rentunitpricelow; //租金筛选，最小值
    
    private Integer leftrenttimetop; //剩余租用时间，最大值，月份
    
    private Integer leftrenttimelow; //剩余租用时间，最小值，月份
    
    private Date renttimetop; //剩余租用时间，最大值
    
    private Date renttimelow; //剩余租用时间，最小值
    
    private Date opendatetop; //开业日期，最大值
    
    private Date opendatelow; //开业日期，最小值
    
    //额外的动态参数
    private Integer leftrenttime; //合同剩余年限
    
    private Integer leftlicensetime; //营业执照剩余年限
    
    private Integer customerservicenum; //客服人数
    
    private Integer coachnum; //教练人数

    private Integer getLeftMonths(Date start, Date end) {
    	if (start == null || end == null || end.before(start))
    		return 0;
    	Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        
        startCalendar.setTime(start);
        endCalendar.setTime(end);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);
        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        return year * 12 + month;
    }
    
    private Date setMonthsPlus(Integer months) {
    	if (months != null) {
			Date date = new Date();
			Calendar resultCalendar = Calendar.getInstance();
			resultCalendar.setTime(date);
			resultCalendar.add(Calendar.MONTH, months+1);
			resultCalendar.set(Calendar.DAY_OF_MONTH, 0);
			resultCalendar.set(Calendar.HOUR_OF_DAY, 0);
			resultCalendar.set(Calendar.MINUTE,0);  
			resultCalendar.set(Calendar.SECOND,0);  
			resultCalendar.set(Calendar.MILLISECOND,0); 
			return resultCalendar.getTime();
    	} else {
    		return null;
    	}
    }

	public Date getRenttimetop() {
		return renttimetop;
	}

	public void setRenttimetop(Date renttimetop) {
		this.renttimetop = renttimetop;
	}

	public Date getRenttimelow() {
		return renttimelow;
	}

	public void setRenttimelow(Date renttimelow) {
		this.renttimelow = renttimelow;
	}

	public Integer getUsingareatop() {
		return usingareatop;
	}

	public void setUsingareatop(Integer usingareatop) {
		this.usingareatop = usingareatop;
	}

	public Integer getUsingarealow() {
		return usingarealow;
	}

	public void setUsingarealow(Integer usingarealow) {
		this.usingarealow = usingarealow;
	}

	public Integer getRentunitpricetop() {
		return rentunitpricetop;
	}

	public void setRentunitpricetop(Integer rentunitpricetop) {
		this.rentunitpricetop = rentunitpricetop;
	}

	public Integer getRentunitpricelow() {
		return rentunitpricelow;
	}

	public void setRentunitpricelow(Integer rentunitpricelow) {
		this.rentunitpricelow = rentunitpricelow;
	}

	public Integer getLeftrenttimetop() {
		return leftrenttimetop;
	}

	public void setLeftrenttimetop(Integer leftrenttimetop) {
		this.leftrenttimetop = leftrenttimetop;
		renttimetop = setMonthsPlus(leftrenttimetop);
	}

	public Integer getLeftrenttimelow() {
		return leftrenttimelow;
	}

	public void setLeftrenttimelow(Integer leftrenttimelow) {
		this.leftrenttimelow = leftrenttimelow;
		renttimelow = setMonthsPlus(leftrenttimelow);
	}

	public Date getOpendatetop() {
		return opendatetop;
	}

	public void setOpendatetop(Date opendatetop) {
		this.opendatetop = opendatetop;
	}

	public Date getOpendatelow() {
		return opendatelow;
	}

	public void setOpendatelow(Date opendatelow) {
		this.opendatelow = opendatelow;
	}

	public Integer getLeftrenttime() {
		return leftrenttime;
	}

	public void setLeftrenttime(Integer leftrenttime) {
		this.leftrenttime = leftrenttime;
	}

	public Integer getLeftlicensetime() {
		return leftlicensetime;
	}

	public void setLeftlicensetime(Integer leftlicensetime) {
		this.leftlicensetime = leftlicensetime;
	}

	public Integer getCustomerservicenum() {
		return customerservicenum;
	}

	public void setCustomerservicenum(Integer customerservicenum) {
		this.customerservicenum = customerservicenum;
	}

	public Integer getCoachnum() {
		return coachnum;
	}

	public void setCoachnum(Integer coachnum) {
		this.coachnum = coachnum;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStorenum() {
        return storenum;
    }

    public void setStorenum(String storenum) {
        this.storenum = storenum == null ? null : storenum.trim();
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
        this.name = name == null ? null : name.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getLicensename() {
        return licensename;
    }

    public void setLicensename(String licensename) {
        this.licensename = licensename == null ? null : licensename.trim();
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getTradingarea() {
        return tradingarea;
    }

    public void setTradingarea(String tradingarea) {
        this.tradingarea = tradingarea == null ? null : tradingarea.trim();
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark == null ? null : landmark.trim();
    }

    public String getBusstation() {
        return busstation;
    }

    public void setBusstation(String busstation) {
        this.busstation = busstation == null ? null : busstation.trim();
    }

    public Byte getOperatetype() {
        return operatetype;
    }

    public void setOperatetype(Byte operatetype) {
        this.operatetype = operatetype;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOpendate() {
        return opendate;
    }

    public void setOpendate(Date opendate) {
        this.opendate = opendate;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getUsingarea() {
        return usingarea;
    }

    public void setUsingarea(BigDecimal usingarea) {
        this.usingarea = usingarea;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getRentunitprice() {
        return rentunitprice;
    }

    public void setRentunitprice(BigDecimal rentunitprice) {
        this.rentunitprice = rentunitprice;
    }

    public Byte getIssublet() {
        return issublet;
    }

    public void setIssublet(Byte issublet) {
        this.issublet = issublet;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
        if (endtime != null) {
	        Date date = new Date();
	        if (date.before(endtime)) {
	        	this.leftrenttime = 0;
	        } else {
	             this.leftrenttime = getLeftMonths(date, endtime);
	        }
        }
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public Byte getRenttype() {
        return renttype;
    }

    public void setRenttype(Byte renttype) {
        this.renttype = renttype;
    }

    public String getRentpaylimit() {
        return rentpaylimit;
    }

    public void setRentpaylimit(String rentpaylimit) {
        this.rentpaylimit = rentpaylimit == null ? null : rentpaylimit.trim();
    }

    public Date getLicensestart() {
        return licensestart;
    }

    public void setLicensestart(Date licensestart) {
        this.licensestart = licensestart;
    }

    public Date getLicenseend() {
        return licenseend;
    }

    public void setLicenseend(Date licenseend) {
        this.licenseend = licenseend;
        if (licenseend != null) {
	        Date date = new Date();
	        if (date.before(endtime)) {
	        	this.leftlicensetime = 0;
	        } else {
	             this.leftlicensetime = getLeftMonths(date, licenseend);
	        }
        }
    }

    public Integer getLicensetime() {
        return licensetime;
    }

    public void setLicensetime(Integer licensetime) {
        this.licensetime = licensetime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public Byte getOwnertype() {
        return ownertype;
    }

    public void setOwnertype(Byte ownertype) {
        this.ownertype = ownertype;
    }

    public String getLandlord() {
        return landlord;
    }

    public void setLandlord(String landlord) {
        this.landlord = landlord == null ? null : landlord.trim();
    }

    public String getLandlordphone() {
        return landlordphone;
    }

    public void setLandlordphone(String landlordphone) {
        this.landlordphone = landlordphone == null ? null : landlordphone.trim();
    }

    public String getLandlordaddress() {
        return landlordaddress;
    }

    public void setLandlordaddress(String landlordaddress) {
        this.landlordaddress = landlordaddress == null ? null : landlordaddress.trim();
    }

    public Integer getTotalfloor() {
        return totalfloor;
    }

    public void setTotalfloor(Integer totalfloor) {
        this.totalfloor = totalfloor;
    }

    public Integer getCurfloor() {
        return curfloor;
    }

    public void setCurfloor(Integer curfloor) {
        this.curfloor = curfloor;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public Integer getWorkstart() {
        return workstart;
    }

    public void setWorkstart(Integer workstart) {
        this.workstart = workstart;
    }

    public Integer getWorkend() {
        return workend;
    }

    public void setWorkend(Integer workend) {
        this.workend = workend;
    }

    public Integer getMonitordevice() {
        return monitordevice;
    }

    public void setMonitordevice(Integer monitordevice) {
        this.monitordevice = monitordevice;
    }

    public Integer getTopad() {
        return topad;
    }

    public void setTopad(Integer topad) {
        this.topad = topad;
    }

    public Integer getFrontad() {
        return frontad;
    }

    public void setFrontad(Integer frontad) {
        this.frontad = frontad;
    }

    public Integer getLedad() {
        return ledad;
    }

    public void setLedad(Integer ledad) {
        this.ledad = ledad;
    }

    public Integer getSignboard() {
        return signboard;
    }

    public void setSignboard(Integer signboard) {
        this.signboard = signboard;
    }

    public Integer getBoxad() {
        return boxad;
    }

    public void setBoxad(Integer boxad) {
        this.boxad = boxad;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}
    
    
}