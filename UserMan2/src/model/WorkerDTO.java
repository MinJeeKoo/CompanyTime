package model;

public class WorkerDTO {
	private String w_id = null;			// id
	private String pw = null; 			// pw
	private String name = null;			// 이름
	private String company_email = null;	// 회사이메일주소
	private Integer matching_result; //추천매칭여부(0-매칭안됨, 1-매칭됨)
	private Integer c_num;		//회사번호
	private Integer cf_num;		//분야번호(현재 재직중인 회사의 분야)
	private Integer cfd_num;		//부서번호
	private String empno;
	
	public WorkerDTO() {};
	
	public WorkerDTO(String w_id, String pw, String name,
			 Integer c_num, Integer cf_num, Integer cfd_num,
			 String company_email, Integer matching_result, String empno) {
		this.w_id = w_id;
		this.c_num = c_num;
		this.cf_num = cf_num;
		this.cfd_num = cfd_num;
		this.name = name;
		this.company_email = company_email;
		this.pw = pw;
		this.matching_result = matching_result;
		this.empno = empno;
	}
	
	public WorkerDTO(String w_id, String pw, String name,
			 Integer c_num, Integer cf_num, Integer cfd_num,
			 String company_email) {
		this.w_id = w_id;
		this.c_num = c_num;
		this.cf_num = cf_num;
		this.cfd_num = cfd_num;
		this.name = name;
		this.company_email = company_email;
		this.pw = pw;
	}
	
	
	public void update(WorkerDTO updateUser) {
        this.pw = updateUser.pw;
        this.name = updateUser.name;
        this.company_email = updateUser.company_email;
        this.c_num = updateUser.c_num;
        this.cf_num = updateUser.cf_num;
        this.cfd_num = updateUser.cfd_num;
    }
	
	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.pw.equals(password);
	}
	public boolean isSameUser(String userid) {
        return this.w_id.equals(userid);
    }

	@Override
	public String toString() {
		return "User [userId=" + w_id + ", c_num=" + c_num + ", name=" + name + ", company_email=" + company_email + ", pw=" + pw + "]";
	}	
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
	
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
}