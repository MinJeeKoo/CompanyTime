package model.dao;

import model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InfoDAOImpl implements InfoDAO {

	private JDBCUtil jdbcUtil = null;
	
	public InfoDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}

	@Override
	public Integer insertInfo(InfoDTO Info) {
		// TODO Auto-generated method stub
		int result = 0;
		
		String insertQuery = "INSERT INTO INFO " + 
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, sequence_info.nextval, ?, ?)";
		
		DAOFactory factory = new DAOFactory();
		
		Integer i_num = Info.getiNo();
		String p_id = Info.getpId();
		String w_id = Info.getwId();
		Integer annual_Income = Info.getAnnual_Income();//1
		Integer InfoMood = Info.getInfoMood();//2
		Integer jobSat_R = Info.getJobSat_R();//3
		String jobSat_H = Info.getJobSat_H();
		Integer cafeteria = Info.getCafeteria();//4
		Integer trafficConven = Info.getTrafficConven();//5
		Integer empWellfare = Info.getEmpWellfare();//6
		Integer cfdNo = Info.getCfdNo();
		
		Object[] param = new Object[] {annual_Income, InfoMood, jobSat_R, cafeteria, 
				trafficConven, empWellfare, p_id, w_id, cfdNo, jobSat_H};
		
		jdbcUtil.setSqlAndParameters(insertQuery, param);
		
		try {				
			result = jdbcUtil.executeUpdate();		// insert �� ����
			
		} catch (SQLException ex) {
			System.out.println("�Է¿��� �߻�!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("������ ���ػ������� �̹� �����մϴ�."); 
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return result;
	}

	@Override
	public Integer updateInfo(InfoDTO Info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteInfo(Integer i_num) {
		// TODO Auto-generated method stub
		return null;
	}

	//�����ؾ���
	@Override
	public InfoDTO getInfoByName(String iName) {
		// TODO Auto-generated method stub
//		String searchQuery = query + "";
//		Object[] param = new Object[] {iName};
//
//		jdbcUtil.setSql(searchQuery);
//		jdbcUtil.setParameters(param);
//	
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();
//			InfoDTO dto = new InfoDTO();
//			while (rs.next()) {
//				dto.setiNo(rs.getInt("Info_INO"));
//				dto.setpNo(rs.getInt("Info_PNO"));
//				dto.setwNo(rs.getInt("Info_WNO"));
//				dto.setCfdNo(rs.getInt("Info_CFDNO"));
//				dto.setAnnual_Income(rs.getInt("Info_ANNUALINCOME"));
//				dto.setInfoMood(rs.getInt("Info_MOOD"));
//				dto.setJobSat(rs.getInt("Info_JOBSAT"));
//				dto.setCafeteria(rs.getInt("Info_CAFETERIA"));
//				dto.setTrafficConven(rs.getInt("Info_TRAFFIC"));
//				dto.setEmpWellfare(rs.getInt("Info_EMPWELL"));
//
//			}
//			return dto;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();
//		}
		return null;
	}

	@Override
	public InfoDTO getInfoByP_id(String p_id) {
		// TODO Auto-generated method stub
		String query = "SELECT i_num AS i_num, p_id AS p_id, w_id AS w_id, annual_income AS annual_income, "
				+ "department_mood AS department_mood, job_satisfaction_r AS job_satisfaction_r, cafeteria AS cafeteria, traffic_convenience AS traffic_convenience, "
				+ "employee_wellfare AS employee_wellfare, cfd_num AS cfd_num, job_satisfaction_h AS job_satisfaction_h "
				+ "FROM info "
				+ "WHERE p_id = ? ";
		
		Object[] param = new Object[] { p_id };
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			InfoDTO info = null;
			if (rs.next()) {
				info = new InfoDTO(
				rs.getInt("i_num"), 
				rs.getString("p_id"), 
				rs.getString("w_id"), 
				rs.getInt("annual_income"), 
				rs.getInt("department_mood"), 
				rs.getInt("job_satisfaction_r"), 
				rs.getInt("cafeteria"), 
				rs.getInt("traffic_convenience"), 
				rs.getInt("employee_wellfare")
				);
			}
			return info;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}

	@Override
	public InfoDTO getInfoByW_id(String w_id) {
		// TODO Auto-generated method stub
		String query = "SELECT i_num AS i_num, p_id AS p_id, w_id AS w_id, annual_income AS annual_income, "
				+ "department_mood AS department_mood, job_satisfaction_r AS job_satisfaction_r, cafeteria AS cafeteria, traffic_convenience AS traffic_convenience, "
				+ "employee_wellfare AS employee_wellfare, cfd_num AS cfd_num, job_satisfaction_h AS job_satisfaction_h "
				+ "FROM info "
				+ "WHERE w_id = ? ";
		
		Object[] param = new Object[] { w_id };
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			InfoDTO info = null;
			if (rs.next()) {
				info = new InfoDTO(
					rs.getInt("i_num"), 
					rs.getString("p_id"), 
					rs.getString("w_id"), 
					rs.getInt("annual_income"), 
					rs.getInt("department_mood"), 
					rs.getInt("job_satisfaction_r"), 
					rs.getInt("cafeteria"), 
					rs.getInt("traffic_convenience"), 
					rs.getInt("employee_wellfare")
				);
			}
			return info;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}

	@Override
	public Integer getI_numByP_id(String p_id) {
		// TODO Auto-generated method stub
		String query = "SELECT i_num AS i_num, "
				+ "FROM info "
				+ "WHERE p_id = ? ";
		
		Object[] param = new Object[] { p_id };
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt("i_num");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}

	@Override
	public Integer getI_numByW_id(String w_id) {
		// TODO Auto-generated method stub
		String query = "SELECT i_num AS i_num, "
				+ "FROM info "
				+ "WHERE w_id = ? ";
		
		Object[] param = new Object[] { w_id };
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt("i_num");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}
	
	 public boolean existingInfo(Integer infoId) throws SQLException {
	      String sql = "SELECT count(*) FROM Info WHERE i_num=?";      
	      jdbcUtil.setSqlAndParameters(sql, new Object[] {infoId});   // JDBCUtil�� query���� �Ű� ���� ����

	      try {
	         ResultSet rs = jdbcUtil.executeQuery();      // query ����
	         if (rs.next()) {
	            int count = rs.getInt(1);
	            return (count == 1 ? true : false);
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      // resource ��ȯ
	      }
	      return false;
	   }
	
}
