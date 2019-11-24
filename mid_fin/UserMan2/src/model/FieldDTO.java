package model;

public class FieldDTO {

	private Integer CF_NUM;
	private String CF_NAME;

	public FieldDTO(int CF_NUM, String CF_NAME) {
		// TODO Auto-generated constructor stub
		this.CF_NUM = CF_NUM;
		this.CF_NAME = CF_NAME;
	}
	public Integer getCF_NUM() {
		return CF_NUM;
	}
	public void setCF_NUM(Integer CF_NUM) {
		this.CF_NUM = CF_NUM;
	}
	
	public String getCF_NAME() {
		return CF_NAME;
	}
	
	public void setCF_NAME(String CF_NAME) {
		this.CF_NAME = CF_NAME;
	}
}
