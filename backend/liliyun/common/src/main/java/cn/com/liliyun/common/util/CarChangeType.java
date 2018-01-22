package cn.com.liliyun.common.util;

public enum CarChangeType {

	//NEW("新增",0),
	NEW("迁入",0),
	SALED_OUT("出售",1),
	RETIRED("报废",2),
	USE("领用",3),
	TRANSFER("调动",4),
	AREA_TRANSFER("跨区调动",5),
	DONATE("捐赠",6),
	STOLEN("捐赠",7),
	OTHER("其他",8);
	private String name;
	private int status;
	
	private CarChangeType(String name,int status) {
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
	
	public static String getNameByStatus(int status) {
		for (CarChangeType ae :CarChangeType.values()) {
			if (ae.getStatus() == status) {
				return ae.getName();
			}
		}
		return null;
	}
	
}
