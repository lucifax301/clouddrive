package cn.com.liliyun.common.util;

public enum UserLevel {

	SCHOOL("驾校牌证",0),
	AREA("片区",1),
	STORE("门店",2);

	private String name;
	private int status;

	private UserLevel(String name, int status) {
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
		for (UserLevel ae : UserLevel.values()) {
			if (ae.getStatus() == status) {
				return ae.getName();
			}
		}
		return null;
	}
	
}
