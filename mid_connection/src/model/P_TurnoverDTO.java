package model;

public class P_TurnoverDTO {
	private String p_id = null;			// id
	private String pw = null; 			// pw
	private String name = null;			// �̸�
	private String company_email = null;	// �系�̸���
	private Integer matching_result; //���丵 ��Ī���
	private Integer c_num;		//ȸ������
	private Integer cf_num;		//�ʵ�����(��Ī ���ϴ� �о��� ����)
	private Integer cfd_num;		//�μ�����

	
	public P_TurnoverDTO() {};
	
	public P_TurnoverDTO(String p_id, String pw, String name,
			 Integer c_num, Integer cf_num, Integer cfd_num,
			 String company_email, Integer matching_result) {
		this.p_id = p_id;
		this.c_num = c_num;
		this.cf_num = cf_num;
		this.cfd_num = cfd_num;
		this.name = name;
		this.company_email = company_email;
		this.pw = pw;
		this.matching_result = matching_result;
	};
	
	/* ��й�ȣ �˻� */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.pw.equals(password);
	}
	public boolean isSameUser(String userid) {
        return this.p_id.equals(userid);
    }

	@Override
	public String toString() {
		return "User [userId=" + p_id + ", c_num=" + c_num + ", name=" + name + ", company_email=" + company_email + ", pw=" + pw + "]";
	}	
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
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
	
}
