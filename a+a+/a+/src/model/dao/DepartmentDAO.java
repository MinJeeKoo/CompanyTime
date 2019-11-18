package model.dao;
import java.util.List;
import model.dto.*;

public interface DepartmentDAO {

	public List<DepartmentDTO> getDepartmentList();
	public Integer getCFD_NUMByCFD_NAME(String cfd_name);
	public List<String> findDepartmentListByCf_name(String cf_name);
}
