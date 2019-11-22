package model.dao;
import java.util.List;
import model.CompanyDTO;

public interface CompanyDAO {

	public int insertCompany(CompanyDTO comp);
	public Integer getC_NUMByC_NAME(String c_name);
	
	public List<CompanyDTO> getCompanyList();
	
}
