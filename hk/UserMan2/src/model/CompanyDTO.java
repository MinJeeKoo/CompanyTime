package model;

public class CompanyDTO {

	private Integer C_NUM = null;
	private String C_NAME = null;
	private String ADDRESS = null;
	
	public CompanyDTO (Integer c_num, String c_name, String address) {
		C_NUM = c_num;
		C_NAME = c_name;
		ADDRESS = address;
	}
	
	public Integer getC_NUM() {
		return C_NUM;
	}
	public void setC_NUM(Integer C_NUM) {
		this.C_NUM = C_NUM;
	}
	
	public String getC_NAME() {
		return C_NAME;
	}
	
	public void setC_NAME(String C_NAME) {
		this.C_NAME = C_NAME;
	}
	
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String ADDRESS) {
		this.ADDRESS = ADDRESS;
	}
}
