package persistence.dao.impl;

import java.sql.ResultSet;
import java.util.List;
import persistence.dao.StatisticDAO;
import service.dto.StatisticDTO;

public class StatisticDAOImpl implements StatisticDAO{
private JDBCUtil jdbcUtil = null;
	
	private static String query = "INSERT INTO STATISTIC(ST_NUM, C_NUM, CFD_NUM, ANNUAL_INCOME, "
								  + "DEPARTMENT_MOOD, JOB_SATISFACTION, CAFETERIA, TRAFFIC_CONVENIENCE, EMPLOYEE_WELLFARE) " 
								  + "SELECT SEQUENCE_STATISTIC.NEXTVAL, C_NUM, CFD_NUM, ANNUAL_INCOME, "
								  + "DEPARTMENT_MOOD, JOB_SATISFACTION, CAFETERIA, TRAFFIC_CONVENIENCE, EMPLOYEE_WELLFARE"
								  + "FROM INFO ";
		
	public StatisticDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}	
	
	@Override
	public StatisticDTO getStatisticByName(String stNum) {
		String searchQuery = query + "WHERE ST_NUM = ?";
		Object[] param = new Object[] {stNum};

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			StatisticDTO dto = new StatisticDTO();
			while (rs.next()) {
				dto.setStNo(rs.getInt("Statistic_STNO"));
				dto.setCNO(rs.getInt("Statistic_CNO"));
				dto.setCfdNo(rs.getInt("Statistic_CFDNO"));
				dto.setAnnual_Income(rs.getInt("Statistic_ANNUALINCOME"));
				dto.setInfoMood(rs.getInt("Statistic_MOOD"));
				dto.setJobSat(rs.getInt("Statistic_JOBSAT"));
				dto.setCafeteria(rs.getInt("Statistic_CAFETERIA"));
				dto.setTrafficConven(rs.getInt("Statistic_TRAFFIC"));
				dto.setEmpWellfare(rs.getInt("Statistic_EMPWELL"));

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
	public List<StatisticDTO> getStatisticList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertStatistic(StatisticDTO st) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer updateStatistic(StatisticDTO st) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer deleteStatistic(Integer sCode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public StatisticDTO getStatisticByCode(String sCode) {
		// TODO Auto-generated method stub
		return null;
	}	
}
