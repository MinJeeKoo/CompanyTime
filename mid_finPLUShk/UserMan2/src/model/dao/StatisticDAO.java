package model.dao;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.StatisticDTO;

public class StatisticDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	String sql = "INSERT INTO STATISTIC(ST_NUM, C_NUM, CFD_NUM, ANNUAL_INCOME, "
			  + "DEPARTMENT_MOOD, JOB_SATISFACTION, CAFETERIA, TRAFFIC_CONVENIENCE, EMPLOYEE_WELLFARE) " 
			  + "SELECT SEQUENCE_STATISTIC.NEXTVAL, C_NUM, CFD_NUM, ANNUAL_INCOME, "
			  + "DEPARTMENT_MOOD, JOB_SATISFACTION, CAFETERIA, TRAFFIC_CONVENIENCE, EMPLOYEE_WELLFARE"
			  + "FROM INFO ";
	
	public int create(StatisticDTO st) throws SQLException {
			
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
	
	public StatisticDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	//통계 기준 : 부서별(같은회사, 다른 부서)
	public StatisticDTO getStatisticByC_NUM(String C_NUM) {
		String searchQuery = "SELECT ST_NUM, C_NUM, CFD_NUM, ANNUAL_INCOME, DEPARTMENT_MOOD, " 
				+ "JOB_SATISFACTION, CAFETERIA, TRAFFIC_CONVENIENCE, EMPLOYEE_WELLFARE " 
				+ "WHERE C_NUM = ?"
				+ "GROUP BY C_NUM";
		Object[] param = new Object[] {C_NUM};

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

	//회사별(다른 회사, 같은 부서)
	public StatisticDTO getStatisticByCFD_NUM(String CFD_NUM) {
		String searchQuery = "SELECT ST_NUM, C_NUM, CFD_NUM, ANNUAL_INCOME, DEPARTMENT_MOOD, " 
				+ "JOB_SATISFACTION, CAFETERIA, TRAFFIC_CONVENIENCE, EMPLOYEE_WELLFARE " 
				+ "WHERE CFD_NUM = ?"
				+ "GROUP BY CFD_NUM";
		Object[] param = new Object[] {CFD_NUM};

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
	
	
	
//	public List<StatisticDTO> getStatisticList() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public Integer insertStatistic(StatisticDTO st) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Integer updateStatistic(StatisticDTO st) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Integer deleteStatistic(Integer sCode) {
		// TODO Auto-generated method stub
		return 0;
	}

	public StatisticDTO getStatisticByCode(String sCode) {
		// TODO Auto-generated method stub
		return null;
	}	
}
