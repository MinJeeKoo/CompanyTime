package training;

public class P_TurnoverDTO {
	private Integer p_num;		//primary key, �ĺ���
	private String id = null;			// id
	private String pw = null; 			// pw
	private String name = null;			// �̸�
	private Integer emp_num;		// �����ȣ
	private String company_email = null;	// �系�̸���
	private Integer matching_result; //���丵 ��Ī���
	private Integer c_num;		//ȸ������
	private Integer cf_num;		//�ʵ�����(��Ī ���ϴ� �о��� ����)
	private Integer cfd_num;		//�μ�����
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public int getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(int emp_num) {
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
