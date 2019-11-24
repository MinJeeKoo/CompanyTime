package model.service;
import java.sql.SQLException;
import java.util.List;
import model.dao.*;
import model.*;
public class UserManager_search_rs {

	private static UserManager_search_rs searchMan = new UserManager_search_rs();
	private DepartmentDAO deptDAO;
	private InfoDAO infoDAO;
	
	private UserManager_search_rs() {
		try {
			deptDAO = new DepartmentDAOImpl();
			infoDAO = new InfoDAOImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static UserManager_search_rs getInstance() {
		return searchMan;
	}
	
	public Integer getDept_Num_By_DeptName(String cfd_name) throws SQLException{
		return deptDAO.getCFD_NUMByCFD_NAME(cfd_name);
	}
	
	public List<ForSearchList> get_SearchList(String cfd_name, String category)throws SQLException{
		return infoDAO.getSearchList(cfd_name, category);
	}
}
