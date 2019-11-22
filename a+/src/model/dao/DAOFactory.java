package model.dao;

public class DAOFactory {
	public SpecDAO getSpecDAO() {
		return new SpecDAOImpl();
	}
	
	public FieldDAO getFieldDAO() {
		return new FieldDAOImpl();
	}
	//...
	
//	public CompanyDAO getCompanyDAO() {
//		return new CompanyDAOImpl();
//	}
	
	public DepartmentDAO getDeparmentDAO() {
		return new DepartmentDAOImpl();
	}
}
