package persistence.dao;
import java.util.List;
import dto.DepartmentDTO;

public interface DepartmentDAO {

	public List<DepartmentDTO> getDepartmentList();
	public Integer getCFD_NUMByCFD_NAME(String cfd_name);
}
