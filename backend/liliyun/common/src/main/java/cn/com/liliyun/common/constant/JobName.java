package cn.com.liliyun.common.constant;


public enum JobName {
    STAFF_FUNCTION(1, "职能员工"),
    COACH(2, "教练员"),
    DISTRICTOR(3, "片区主管"),
    CHECKER(4, "考核员"),
    SERVICER(5, "客服"),
    RELAY_SERVICER(6, "顶班客服")   ;

    private byte type;
    private String desc;

    /**
     *
     */
    JobName(int type, String desc) {
        this.type = (byte) type;
        this.desc = desc;
    }

    /**
     * @return the type
     */
    public byte getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(byte type) {
        this.type = type;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
