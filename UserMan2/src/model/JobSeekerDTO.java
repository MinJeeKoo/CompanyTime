package model;

public class JobSeekerDTO {
	private String userId;
	private String password;
	private String name;
	private String school;
	private String major;
	private String email;
	private Integer cf_num; //추천매칭 희망하는 분야
	private Integer matching_result;

	public JobSeekerDTO() { }		// 매개변수 없는 생성자
	public JobSeekerDTO(String userId, String password, String name, String school, 
			String major, String email, Integer cf_num) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.school = school;
		this.major = major;
		this.email = email;
		this.cf_num = cf_num;
	}
	public JobSeekerDTO(String userId, String password, String name, String school, 
			String major, String email, Integer cf_num, Integer matching_result) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.school = school;
		this.major = major;
		this.email = email;
		this.cf_num = cf_num;
		this.matching_result = matching_result;
	}

	public void update(JobSeekerDTO updateUser) {
        this.password = updateUser.password;
        this.name = updateUser.name;
        this.school = updateUser.school;
        this.major = updateUser.major;
        this.email = updateUser.email;
		this.cf_num = cf_num;
		this.matching_result = matching_result;
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

	public Integer getCf_num() {
		return cf_num;
	}

	public void setCf_num(Integer cf_num) {
		this.cf_num = cf_num;
	}

	public Integer getMatching_result() {
		return matching_result;
	}

	public void setMatching_result(Integer matching_result) {
		this.matching_result = matching_result;
	}


	/*비밀번호 일치여부 검사 */
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