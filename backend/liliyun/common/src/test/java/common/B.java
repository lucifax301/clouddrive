package common;

public class B {
	private String username;
	private String myage;
	@Override
	public String toString() {
		return "B [username=" + username + ", myage=" + myage + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMyage() {
		return myage;
	}
	public void setMyage(String myage) {
		this.myage = myage;
	}
}
