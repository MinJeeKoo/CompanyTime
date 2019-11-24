package model;

public class ForSearchList {

	
	private String c_name;
	private String cfd_name;
	private Integer search_category;
	
	public ForSearchList(String C_NAME, String CFD_NAME, Integer Search_category) {
		this.c_name = C_NAME;
		this.cfd_name = CFD_NAME;
		this.search_category = Search_category;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getCfd_name() {
		return cfd_name;
	}

	public void setCfd_name(String cfd_name) {
		this.cfd_name = cfd_name;
	}

	public Integer getSearch_category() {
		return search_category;
	}

	public void setSearch_category(Integer search_category) {
		this.search_category = search_category;
	}

	
	
	
}
