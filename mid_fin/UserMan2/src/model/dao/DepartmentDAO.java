package model.dao;
import java.sql.SQLException;
import java.util.List;

import model.DepartmentDTO;

public interface DepartmentDAO {

	public List<DepartmentDTO> getDepartmentList();
	public Integer getCFD_NUMByCFD_NAME(String cfd_name) throws SQLException;
	public List<String> findDepartmentListByCf_name(String cf_name) throws SQLException;
}
