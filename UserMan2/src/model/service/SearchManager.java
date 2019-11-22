package model.service;

import java.sql.SQLException;
import java.util.List;
import model.dao.*;
import model.FieldDTO;

public class SearchManager {
	private static SearchManager searchMan = new SearchManager();
	private DepartmentDAO deptDAO;
	private FieldDAO fieldDAO;
	
	private SearchManager() {
		try {
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
}
