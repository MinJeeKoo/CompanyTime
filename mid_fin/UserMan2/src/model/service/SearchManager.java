package model.service;

import java.sql.SQLException;

import java.util.List;
import model.dao.*;
import model.CompanyDTO;
import model.FieldDTO;

public class SearchManager {
	private static SearchManager searchMan = new SearchManager();
	private CompanyDAO companyDAO;
	private DepartmentDAO deptDAO;
	private FieldDAO fieldDAO;
	
	private SearchManager() {
		try {
			companyDAO = new CompanyDAOImpl();
			deptDAO = new DepartmentDAOImpl();
			fieldDAO = new FieldDAOImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SearchManager getInstance() {
		return searchMan;
	}
	
	public List<String> findDepartmentListByCf_name(String cf_name) throws SQLException {
		return deptDAO.findDepartmentListByCf_name(cf_name);
	}
	
	public List<FieldDTO> getFieldList() throws SQLException {
		return fieldDAO.getFieldList();
	}
	
	public Integer getC_NUMByC_NAME(String c_name) throws SQLException {
		return companyDAO.getC_NUMByC_NAME(c_name);
	}
	
	public Integer getCF_NUMByCF_NAME(String cf_name) throws SQLException {
		return  fieldDAO.getCF_NUMByCF_NAME(cf_name);
	}
	 
	public Integer getCFD_NUMByCFD_NAME(String cfd_name) throws SQLException {
		return deptDAO.getCFD_NUMByCFD_NAME(cfd_name);
	}
	
	public int insertCompany(CompanyDTO comp) throws SQLException {
		return companyDAO.insertCompany(comp);
	}
}
