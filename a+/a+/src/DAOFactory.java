
public class DAOFactory {
	public SpecDAO getSpecDAO() {
		return new SpecDAOImpl();
	}
	
	//...
}
