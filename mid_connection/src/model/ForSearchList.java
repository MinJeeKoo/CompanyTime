package model;

public class ForSearchList {

	
	private String C_NAME;
	private String CFD_NAME;
	private Integer Search_category;
	
	public ForSearchList(String C_NAME, String CFD_NAME, Integer Search_category) {
		this.C_NAME = C_NAME;
		this.CFD_NAME = CFD_NAME;
		this.Search_category = Search_category;
	}
	

	public String getCFD_NAME() {
		return CFD_NAME;
	}
	public void setCFD_NAME(String cFD_NAME) {
		CFD_NAME = cFD_NAME;
	}
	
	public Integer getSearch_category() {
		return Search_category;
	}
	public void setSearch_category(Integer search_category) {
		Search_category = search_category;
	}


	public String getC_NAME() {
		return C_NAME;
	}


	public void setC_NAME(String c_NAME) {
		C_NAME = c_NAME;
	}
}
