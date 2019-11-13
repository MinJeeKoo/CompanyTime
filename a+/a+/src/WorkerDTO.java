
public class WorkerDTO {
	private String w_id = null;			// id
	private String pw = null; 			// pw
	private String name = null;			// 이름
	private Integer emp_num;		// 사원번호
	private String company_email = null;	// 사내이메일
	private Integer matching_result; //멘토링 매칭결과
	private Integer c_num;		//회사정보
	private Integer cf_num;		//필드정보(매칭 원하는 분야의 정보)
	private Integer cfd_num;		//부서정보
	
	public String getW_id() {
		return w_id;
	}
	public void setW_id(String w_id) {
		this.w_id = w_id;
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
	public Integer getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(Integer emp_num) {
		this.emp_num = emp_num;
	}
	public String getCompany_email() {
		return company_email;
	}
	public void setCompany_email(String company_email) {
		this.company_email = company_email;
	}
	public Integer getMatching_result() {
		return matching_result;
	}
	public void setMatching_result(Integer matching_result) {
		this.matching_result = matching_result;
	}
	public Integer getC_num() {
		return c_num;
	}
	public void setC_num(Integer c_num) {
		this.c_num = c_num;
	}
	public Integer getCf_num() {
		return cf_num;
	}
	public void setCf_num(Integer cf_num) {
		this.cf_num = cf_num;
	}
	public Integer getCfd_num() {
		return cfd_num;
	}
	public void setCfd_num(Integer cfd_num) {
		this.cfd_num = cfd_num;
	}
	
	

}
