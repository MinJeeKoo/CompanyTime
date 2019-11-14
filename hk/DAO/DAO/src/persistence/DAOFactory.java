package persistence;

import persistence.dao.*;
import persistence.dao.impl.*;

public class DAOFactory {
	public InfoDAO getInfoDAO() {
		return new InfoDAOImpl();		 
	}
	
	// InfoDAO �� ���� RDB �� DAO Implementation ��ü�� ��ȯ
	public TrustDAO getTrustDAO() {
		return new TrustDAOImpl();		
	}
	
	// ProfDAO �� ���� RDB �� DAO Implementation ��ü�� ��ȯ
	public StatisticDAO getStatisticDAO() {
		return new StatisticDAOImpl();		
	}
}
