package model.service;

import java.sql.SQLException;
import java.util.List;
import model.dao.*;

public class SearchManager {
	private static SearchManager searchMan = new SearchManager();
	private DepartmentDAO deptDAO;
	
	private SearchManager() {
		try {
			deptDAO = new DepartmentDAOImpl();
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
}
