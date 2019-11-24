package model;

/**
 * 사용자 관리를 위해 필요한 도메인 클래스. USERINFO 테이블과 대응됨
 */
public class User_JobSeeker {
	private String userId;
	private String password;
	private String name;
	private String school;
	private String major;
	private String email;

	public User_JobSeeker() { }		// 기본 생성자
	
	public User_JobSeeker(String userId, String password, String name, String school, String major, String email) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.school = school;
		this.major = major;
		this.email = email;
	}

	public void update(User_JobSeeker updateUser) {
        this.password = updateUser.password;
        this.name = updateUser.name;
        this.school = updateUser.school;
        this.major = updateUser.major;
        this.email = updateUser.email;
    }
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}
	
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", school=" + school + ", major=" + major + ", email=" + email + "]";
	}	
}
