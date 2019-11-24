package model.dao;
import java.sql.SQLException;
import java.util.List;
import model.CompanyDTO;

public interface CompanyDAO {

	public int insertCompany(CompanyDTO comp) throws SQLException;
	public Integer getC_NUMByC_NAME(String c_name);
	
	public List<CompanyDTO> getCompanyList();
	
}
