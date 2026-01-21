package tw.brad.apis;

public class Member {
	private long id;
	private String email, pwd, name;
	
	public Member() {}
	
	public Member(long id, String email, String pwd, String name) {
		this.id = id;
		this.email = email;
		this.pwd = pwd;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswd() {
		return pwd;
	}
	public void setPasswd(String passwd) {
		this.pwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("%s:%s", email, name);
	}
}
