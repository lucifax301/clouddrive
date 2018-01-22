package cn.com.liliyun.user.model;


import java.io.Serializable;
import java.util.Date;

public class Config implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1096809477757729440L;


	private Integer id;

    private Integer companyId;

    private Integer cityId;

    private Integer schoolId;

    private Integer channel;

    private Integer memuId;

    private Integer type;

    private Integer fileType;

    private Integer fileId;

    private Integer tableId;

    private String tableName;

    private String name;

    private String imgTwoxUrl;

    private String imgThreexUrl;

    private String url;

    private String ext;

    private String dtime;

    private Date startTime;

    private Date endTime;

    private Integer isDel;

    private Date ctime;

    private String creater;

    private Date mtime;

    private String updater;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getMemuId() {
        return memuId;
    }

    public void setMemuId(Integer memuId) {
        this.memuId = memuId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImgTwoxUrl() {
        return imgTwoxUrl;
    }

    public void setImgTwoxUrl(String imgTwoxUrl) {
        this.imgTwoxUrl = imgTwoxUrl == null ? null : imgTwoxUrl.trim();
    }

    public String getImgThreexUrl() {
        return imgThreexUrl;
    }

    public void setImgThreexUrl(String imgThreexUrl) {
        this.imgThreexUrl = imgThreexUrl == null ? null : imgThreexUrl.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime == null ? null : dtime.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }
}