package model.dao;

import model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoDAOImpl implements InfoDAO {

	private JDBCUtil jdbcUtil = null;
	
	public InfoDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}

	public Integer insertInfo(InfoDTO Info) throws SQLException{
		// TODO Auto-generated method stub
		int result = 0;
		
		String insertQuery = "INSERT INTO INFO(I_NUM, P_ID,W_ID,ANNUAL_INCOME, DEPARTMENT_MOOD, JOB_SATISFACTION_R, , JOB_SATISFACTION_H, CAFETERIA, TRAFFIC_CONVENIENCE, EMPLOYEE_WELLFARE, CFD_NAME, C_NAME)" + 
				"VALUES(sequence_info.nextval, ?, ?, ?, ?, ?, ?, ?,? , ?, ?,?)";
	
		
		String p_id = Info.getpId();
		String w_id = Info.getwId();
		Integer annual_Income = Info.getAnnual_Income();//1
		Integer InfoMood = Info.getInfoMood();//2
		Integer jobSat_R = Info.getJobSat_R();//3
		String jobSat_H = Info.getJobSat_H();
		Integer cafeteria = Info.getCafeteria();//4
		Integer trafficConven = Info.getTrafficConven();//5
		Integer empWellfare = Info.getEmpWellfare();//6
		String cfdName = Info.getCfdName();
		String cName = Info.getCName();
		
		Object[] param = new Object[] {p_id, w_id, annual_Income, InfoMood, jobSat_R, jobSat_H, cafeteria, trafficConven, empWellfare, cfdName, cName};
		
		jdbcUtil.setSqlAndParameters(insertQuery, param);
		
		try {				
			result = jdbcUtil.executeUpdate();		// insert �� ����
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return result;
	}

	public Integer updateInfo(InfoDTO Info) throws SQLException{
		String sql = "UPDATE"
				+ "SET ANNUAL_INCOME =? , DEPARTEMENT_MOOD =? , JOB_SATISFACTION_R = ?, JOB_SATISFACTION_H =?,  CAFETERIA = ?, TRAFFIC_CONVENIENCE = ? , EMPLOYEE_WELLFARE= ?, CFD_NAME = ?, C_NAME = ?" 
				+ "WHERE I_NUM=?";
	Object[] param = new Object[] {Info.getAnnual_Income(), Info.getInfoMood(), Info.getJobSat_R(), Info.getJobSat_R(), Info.getCafeteria(), Info.getTrafficConven(), Info.getEmpWellfare(), Info.getCfdName(), Info.getCName() };				
	jdbcUtil.setSqlAndParameters(sql,param);	
	
	try {				
		int result = jdbcUtil.executeUpdate();	// update �� ����
		return result;
	} catch (Exception ex) {
		jdbcUtil.rollback();
		ex.printStackTrace();
	}
	finally {
		jdbcUtil.commit();
		jdbcUtil.close();	// resource ��ȯ
	}		
	return 0;
		
		
	}//2

	@Override
	public Integer deleteInfo(Integer iCode) throws SQLException{
		int result = 0;
		String deleteQuery = "DELETE FROM INFO WHERE I_NUM = iCode";
	
		jdbcUtil.setSql(deleteQuery);
		
		try {
			result =jdbcUtil.executeUpdate();
			result = 1;//������ ��� 1
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			jdbcUtil.close();
		}
		return result;
	}//3

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
	public InfoDTO getInfoByP_id(String p_id) throws SQLException{
		// TODO Auto-generated method stub
		String query = "SELECT i_num AS i_num, p_id AS p_id, w_id AS w_id, annual_income AS annual_income, "
				+ "department_mood AS department_mood, job_satisfaction_r AS job_satisfaction_r, job_satisfaction_h AS job_satisfaction_h, cafeteria AS cafeteria, traffic_convenience AS traffic_convenience, "
				+ "employee_wellfare AS employee_wellfare, cfd_name AS cfd_name, c_name AS c_name "
				+ "FROM info "
				+ "WHERE p_id = ? ";
		
		Object[] param = new Object[] { p_id };
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			InfoDTO info = new InfoDTO();
			if (rs.next()) {
				info.setiNo(rs.getInt("i_num"));
				info.setpId(rs.getString("p_id"));
				info.setwId(rs.getString("w_id"));
				info.setAnnual_Income(rs.getInt("annual_income"));
				info.setInfoMood(rs.getInt("department_mood"));
				info.setJobSat_R(rs.getInt("job_satisfaction_r"));
				info.setJobSat_H(rs.getString("job_satisfaction_h"));
				info.setCafeteria(rs.getInt("cafeteria"));
				info.setTrafficConven(rs.getInt("traffic_convenience"));
				info.setEmpWellfare(rs.getInt("employee_wellfare"));
				info.setCfdName(rs.getString("cfd_name"));
				info.setCName(rs.getString("c_name"));
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
	public InfoDTO getInfoByW_id(String w_id) throws SQLException{
		// TODO Auto-generated method stub
		String query = "SELECT i_num AS i_num, p_id AS p_id, w_id AS w_id, annual_income AS annual_income, "
				+ "department_mood AS department_mood, job_satisfaction_r AS job_satisfaction_r, job_satisfaction_h AS job_satisfaction_h, cafeteria AS cafeteria, traffic_convenience AS traffic_convenience, "
				+ "employee_wellfare AS employee_wellfare, cfd_name AS cfd_name, c_name AS c_name "
				+ "FROM info "
				+ "WHERE w_id = ? ";
		
		Object[] param = new Object[] { w_id };
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			InfoDTO info = new InfoDTO();
			if (rs.next()) {
				info.setiNo(rs.getInt("i_num"));
				info.setpId(rs.getString("p_id"));
				info.setwId(rs.getString("w_id"));
				info.setAnnual_Income(rs.getInt("annual_income"));
				info.setInfoMood(rs.getInt("department_mood"));
				info.setJobSat_R(rs.getInt("job_satisfaction_r"));
				info.setJobSat_H(rs.getString("job_satisfaction_h"));
				info.setCafeteria(rs.getInt("cafeteria"));
				info.setTrafficConven(rs.getInt("traffic_convenience"));
				info.setEmpWellfare(rs.getInt("employee_wellfare"));
				info.setCfdName(rs.getString("cfd_name"));
				info.setCName(rs.getString("c_name"));
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
	public Integer getI_numByP_id(String p_id) throws SQLException{
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
	public Integer getI_numByW_id(String w_id) throws SQLException{
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

	//C_NUM�� �ִ´ٸ�,,,,?**�߿�**
	
	@Override
	public List<ForSearchList> getSearchList(String cfd_name, String category) throws SQLException{
		if(category.equals("annual_income")){
			String query = "SELECT C_NAME ,CFD_NAME, ANNUAL_INCOME FROM INFO WHERE CFD_NAME = ? ORDER BY ANNUAL_INCOME";
			Object[] param = new Object[] {cfd_name};
			jdbcUtil.setSqlAndParameters(query, param);
			try {
				ResultSet rs = jdbcUtil.executeQuery();
				List<ForSearchList> return_list = new ArrayList<ForSearchList>();
				while(rs.next()) {
					ForSearchList search_list = new ForSearchList(
							rs.getString("C_NAME"), 
							rs.getString("CFD_NAME"),
							rs.getInt("ANNUAL_INCOME"));
					return_list.add(search_list);
				}
				return return_list;
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally{
				jdbcUtil.close();
			}
		}
		else if(category.equals("department_mood")) {
			String query = "SELECT C_NAME ,CFD_NAME, DEPARTMENT_MOOD FROM INFO WHERE CFD_NAME = ? ORDER BY DEPARTMENT_MOOD";
			Object[] param = new Object[] {cfd_name};
			jdbcUtil.setSqlAndParameters(query, param);
			try {
				ResultSet rs = jdbcUtil.executeQuery();
				List<ForSearchList> return_list = new ArrayList<ForSearchList>();
				while(rs.next()) {
					ForSearchList search_list = new ForSearchList(
							rs.getString("C_NAME"), 
							rs.getString("CFD_NAME"),
							rs.getInt("DEPARTMENT_MOOD"));
					return_list.add(search_list);
				}
				return return_list;
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally{
				jdbcUtil.close();
			}
		}
		else if(category.equals("job_satisfaction_r")) {
			String query = "SELECT C_NAME ,CFD_NAME, JOB_SATISFACTION_R FROM INFO WHERE CFD_NAME = ? ORDER BY JOB_SATISFACTION_R";
			Object[] param = new Object[] {cfd_name};
			jdbcUtil.setSqlAndParameters(query, param);
			try {
				ResultSet rs = jdbcUtil.executeQuery();
				List<ForSearchList> return_list = new ArrayList<ForSearchList>();
				while(rs.next()) {
					ForSearchList search_list = new ForSearchList(
							rs.getString("C_NAME"), 
							rs.getString("CFD_NAME"),
							rs.getInt("JOB_SATISFACTION_R"));
					return_list.add(search_list);
				}
				return return_list;
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally{
				jdbcUtil.close();
			}
		}
		else if(category.equals("traffic_convenience")) {
			String query = "SELECT C_NAME ,CFD_NAME, TRAFFIC_CONVENIENCE FROM INFO WHERE CFD_NAME = ? ORDER BY TRAFFIC_CONVENIENCE";
			Object[] param = new Object[] {cfd_name};
			jdbcUtil.setSqlAndParameters(query, param);
			try {
				ResultSet rs = jdbcUtil.executeQuery();
				List<ForSearchList> return_list = new ArrayList<ForSearchList>();
				while(rs.next()) {
					ForSearchList search_list = new ForSearchList(
							rs.getString("C_NAME"), 
							rs.getString("CFD_NAME"),
							rs.getInt("TRAFFIC_CONVENIENCE"));
					return_list.add(search_list);
				}
				return return_list;
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally{
				jdbcUtil.close();
			}
		}
		else if(category.equals("cafeteria")) {
			String query = "SELECT C_NAME ,CFD_NAME, CAFETERIA FROM INFO WHERE CFD_NAME = ? ORDER BY CAFETERIA";
			Object[] param = new Object[] {cfd_name};
			jdbcUtil.setSqlAndParameters(query, param);
			try {
				ResultSet rs = jdbcUtil.executeQuery();
				List<ForSearchList> return_list = new ArrayList<ForSearchList>();
				while(rs.next()) {
					ForSearchList search_list = new ForSearchList(
							rs.getString("C_NAME"), 
							rs.getString("CFD_NAME"),
							rs.getInt("CAFETERIA"));
					return_list.add(search_list);
				}
				return return_list;
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally{
				jdbcUtil.close();
			}
		}
		else {
			String query = "SELECT C_NAME ,CFD_NAME, EMPLOYEE_WELLFARE FROM INFO WHERE CFD_NAME = ? ORDER BY EMPLOYEE_WELLFARE";
			Object[] param = new Object[] {cfd_name};
			jdbcUtil.setSqlAndParameters(query, param);
			try {
				ResultSet rs = jdbcUtil.executeQuery();
				List<ForSearchList> return_list = new ArrayList<ForSearchList>();
				while(rs.next()) {
					ForSearchList search_list = new ForSearchList(
							rs.getString("C_NAME"), 
							rs.getString("CFD_NAME"),
							rs.getInt("EMPLOYEE_WELLFARE"));
					return_list.add(search_list);
				}
				return return_list;
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally{
				jdbcUtil.close();
			}
		}
		return null;
	}

	
}
