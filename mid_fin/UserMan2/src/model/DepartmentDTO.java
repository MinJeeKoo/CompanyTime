package model;

public class DepartmentDTO {
	
	private Integer CFD_NUM = null;
	private Integer C_NUM = null;
	private Integer CF_NUM = null;
	private String CFD_NAME = null;
	
	public Integer getCFD_NUM() {
		return CFD_NUM;
	}
	public void setCFD_NUM(Integer CFD_NUM) {
		this.CFD_NUM = CFD_NUM;
	}
	
	public Integer getC_NUM() {
		return C_NUM;
	}
	public void setC_NUM(Integer c_NUM) {
		C_NUM = c_NUM;
	}
	public Integer getCF_NUM() {
		return CF_NUM;
	}
	public void setCF_NUM(Integer CF_NUM) {
		this.CF_NUM = CF_NUM;
	}
	
	public String getCFD_NAME() {
		return CFD_NAME;
	}
	
	public void setCFD_NAME(String CFD_NAME) {
		this.CFD_NAME = CFD_NAME;
	}
	
	
}
