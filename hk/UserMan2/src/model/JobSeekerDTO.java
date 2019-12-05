package model;

/**
 * ����� ������ ���� �ʿ��� ������ Ŭ����. USERINFO ���̺�� ������
 */
public class JobSeekerDTO {
	private String js_id;
	private String pw;
	private String name;
	private String school;
	private String major;
	private String email;
	private Integer cf_num;
	private Integer matching_result;

	public JobSeekerDTO() { }		// �⺻ ������
	public JobSeekerDTO(String js_id, String pw, String name, String school, 
			String major, String email, Integer cf_num) {
		this.js_id = js_id;
		this.pw = pw;
		this.name = name;
		this.school = school;
		this.major = major;
		this.email = email;
		this.cf_num = cf_num;
	}
	public JobSeekerDTO(String js_id, String pw, String name, String school, 
			String major, String email, Integer cf_num, Integer matching_result) {
		this.js_id = js_id;
		this.pw = pw;
		this.name = name;
		this.school = school;
		this.major = major;
		this.email = email;
		this.cf_num = cf_num;
		this.matching_result = matching_result;
	}

	public void update(JobSeekerDTO updateUser) {
        this.pw = updateUser.pw;
        this.name = updateUser.name;
        this.school = updateUser.school;
        this.major = updateUser.major;
        this.email = updateUser.email;
		this.cf_num = cf_num;
		this.matching_result = matching_result;
    }
	
	public String getJs_id() {
		return js_id;
	}

	public void setJs_id(String js_id) {
		this.js_id = js_id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
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


	/* ��й�ȣ �˻� */
	public boolean matchPw(String pw) {
		if (pw == null) {
			return false;
		}
		return this.pw.equals(pw);
	}
	
	public boolean isSameUser(String js_id) {
        return this.js_id.equals(js_id);
    }

	@Override
	public String toString() {
		return "User [Js_id=" + js_id + ", password=" + pw + ", name=" + name + ", school=" + school + ", major=" + major + ", email=" + email + "]";
	}	
}