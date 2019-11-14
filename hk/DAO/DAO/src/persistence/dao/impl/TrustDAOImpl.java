package persistence.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import persistence.dao.TrustDAO;
import service.dto.TrustDTO;

public class TrustDAOImpl implements TrustDAO{

}private JDBCUtil jdbcUtil = null;

private static String query = "INSERT INTO STATISTIC(ST_NUM, C_NUM, CFD_NUM, ANNUAL_INCOME, "
							  + "DEPARTMENT_MOOD, JOB_SATISFACTION, CAFETERIA, TRAFFIC_CONVENIENCE, EMPLOYEE_WELLFARE) " 
							  + "SELECT SEQUENCE_STATISTIC.NEXTVAL, C_NUM, CFD_NUM, ANNUAL_INCOME, "
							  + "DEPARTMENT_MOOD, JOB_SATISFACTION, CAFETERIA, TRAFFIC_CONVENIENCE, EMPLOYEE_WELLFARE"
							  + "FROM INFO ";
	
public TrustDAOImpl() {
	jdbcUtil = new JDBCUtil();
}	

@Override
public TrustDTO getTrustByName(String tNum) {
	String searchQuery = query + "WHERE ST_NUM = ?";
	Object[] param = new Object[] {tNum};

	jdbcUtil.setSql(searchQuery);
	jdbcUtil.setParameters(param);

	try {
		ResultSet rs = jdbcUtil.executeQuery();
		TrustDTO dto = new TrustDTO();
		while (rs.next()) {
			dto.setTNo(rs.getInt("Trust_TNO"));
			dto.setCfdNo(rs.getInt("Trust_CFDNO"));
			dto.setAnnual_Income(rs.getInt("Trust_ANNUALINCOME"));
			dto.setInfoMood(rs.getInt("Trust_MOOD"));
			dto.setJobSat(rs.getInt("Trust_JOBSAT"));
			dto.setCafeteria(rs.getInt("Trust_CAFETERIA"));
			dto.setTrafficConven(rs.getInt("Trust_TRAFFIC"));
			dto.setEmpWellfare(rs.getInt("Trust_EMPWELL"));

		}
		return dto;
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		jdbcUtil.close();
	}
	return null;
}

@Override
public List<TrustDTO> getTrustList() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Integer insertTrust(TrustDTO st) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public Integer updateTrust(TrustDTO st) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public Integer deleteTrust(Integer sCode) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public TrustDTO getTrustByCode(String sCode) {
	// TODO Auto-generated method stub
	return null;
}	
}
