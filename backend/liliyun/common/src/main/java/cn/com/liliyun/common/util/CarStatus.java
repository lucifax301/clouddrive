package cn.com.liliyun.common.util;

public enum CarStatus {

	IN_USE("在用",0),
	STAY_STOP("停放",1),
	WAIT_SALE("待出售",2),
	WAIT_RETIRE("待报废",3),
	SALED_OUT("已出售",4),
	RETIRED("已报废",5),
	DONATE("捐赠",6),
	STOLEN("被盗",7);
	
	private String name;
	private int status;
	
	private CarStatus(String name,int status) {
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
		for (CarStatus ae :CarStatus.values()) {
			if (ae.getStatus() == status) {
				return ae.getName();
			}
		}
		return null;
	}
	
}
