package model.dto;

public class JobSeekerDTO {
	private String js_id = null;			// id
	private String pw = null; 			// pw
	private String name = null;			// �̸�
	private String school = null;		// �б�
	private String major = null;		// ����
	private String personal_email = null; // ���� �̸����ּ�
	private Integer cf_num;					// �ʵ� ���� (���丵 ��� �о� ����)
	private Integer matching_result;		// ���丵 ��Ī ���
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
	public void setName(String name) {
		this.name = name;
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
	public String getPersonal_email() {
		return personal_email;
	}
	public void setPersonal_email(String personal_email) {
		this.personal_email = personal_email;
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
	
	
	
}
