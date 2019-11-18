package model.dao;

public class DAOFactory {
	public SpecDAO getSpecDAO() {
		return new SpecDAOImpl();
	}
	
	public FieldDAO getFieldDAO() {
		return new FieldDAOImpl();
	}
	//...
}
