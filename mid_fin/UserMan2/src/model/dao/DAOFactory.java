package model.dao;

public class DAOFactory {
	public SpecDAO getSpecDAO() {
		return new SpecDAOImpl();
	}
	
	public FieldDAO getFieldDAO() {
		return new FieldDAOImpl();
	}
	
	public DepartmentDAO getDepartmentDAO() {
		return new DepartmentDAOImpl();
	}

	public CompanyDAO getCompanyDAO() {
		// TODO Auto-generated method stub
		return new CompanyDAOImpl();
	}
}
