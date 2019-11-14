package persistence;

import persistence.dao.*;
import persistence.dao.impl.*;

public class DAOFactory {
	public InfoDAO getInfoDAO() {
		return new InfoDAOImpl();		 
	}
	
	// InfoDAO 를 위한 RDB 용 DAO Implementation 객체를 반환
	public TrustDAO getTrustDAO() {
		return new TrustDAOImpl();		
	}
	
	// ProfDAO 를 위한 RDB 용 DAO Implementation 객체를 반환
	public StatisticDAO getStatisticDAO() {
		return new StatisticDAOImpl();		
	}
}
