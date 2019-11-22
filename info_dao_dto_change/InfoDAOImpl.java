package model.dao;

import model.dto.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoDAOImpl implements InfoDAO {

	private JDBCUtil jdbcUtil = null;
	
	public InfoDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}


	public Integer updateInfo(InfoDTO Info) {
		String sql = "UPDATE  "
				+ "SET ANNUAL_INCOME =? , DEPARTEMENT_MOOD =? , JOB_SATISFACTION = ?, CAFETERIA = ?, TRAFFIC_CONVENIENCE = ? , EMPLOYEE_WELLFARE= ?" 
				+ "WHERE I_NUM=?";
	Object[] param = new Object[] {Info.getAnnual_Income(), Info.getInfoMood(), Info.getJobSat_R(), Info.getCafeteria(), Info.getTrafficConven(), Info.getEmpWellfare()};				
	jdbcUtil.setSqlAndParameters(sql,param);	
	
	try {				
		int result = jdbcUtil.executeUpdate();	// update 문 실행
		return result;
	} catch (Exception ex) {
		jdbcUtil.rollback();
		ex.printStackTrace();
	}
	finally {
		jdbcUtil.commit();
		jdbcUtil.close();	// resource 반환
	}		
	return 0;
		
		
	}//2

	@Override
	public Integer deleteInfo(Integer iCode) {
		int result = 0;
		String deleteQuery = "DELETE FROM INFO WHERE I_NUM = iCode";
	
		jdbcUtil.setSql(deleteQuery);
		
		try {
			result =jdbcUtil.executeUpdate();
			result = 1;//성공할 경우 1
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			jdbcUtil.close();
		}
		return result;
	}//3

	//수정해야함
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
				InfoDTO dto = new InfoDTO(
						rs.getInt("iNo"),
						rs.getInt("pId"),
						rs.getInt("wId"),
						rs.getInt("cfdNo"),
						rs.getInt("annual_Income"),
						rs.getInt("InfoModd"),
						rs.getInt("jobSat_R"),
						rs.getString("jobSat_H"),
						rs.getInt("cafeteria"),
						rs.getInt("traffciConven"),
						rs.getString("empWellfare"));
				info.setiNo(rs.getInt("i_num"));
				info.setpId(rs.getInt("p_id"));
				info.setwId(rs.getInt("w_id"));
				info.setAnnual_Income(rs.getInt("annual_income"));
				info.setInfoMood(rs.getInt("department_mood"));
				info.setJobSat_R(rs.getInt("job_satisfaction_r"));
				info.setJobSat_H(rs.getString("job_satisfaction_h"));
				info.setCafeteria(rs.getInt("cafeteria"));
				info.setTrafficConven(rs.getInt("traffic_convenience"));
				info.setEmpWellfare(rs.getString("employee_wellfare"));
				info.setCfdNo(rs.getInt("cfd_num"));
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
				InfoDTO dto = new InfoDTO(
						rs.getInt("iNo"),
						rs.getInt("pId"),
						rs.getInt("wId"),
						rs.getInt("cfdNo"),
						rs.getInt("annual_Income"),
						rs.getInt("InfoModd"),
						rs.getInt("jobSat_R"),
						rs.getString("jobSat_H"),
						rs.getInt("cafeteria"),
						rs.getInt("traffciConven"),
						rs.getString("empWellfare"));
				
				info.setiNo(rs.getInt("i_num"));
				info.setpId(rs.getInt("p_id"));
				info.setwId(rs.getInt("w_id"));
				info.setAnnual_Income(rs.getInt("annual_income"));
				info.setInfoMood(rs.getInt("department_mood"));
				info.setJobSat_R(rs.getInt("job_satisfaction_r"));
				info.setJobSat_H(rs.getString("job_satisfaction_h"));
				info.setCafeteria(rs.getInt("cafeteria"));
				info.setTrafficConven(rs.getInt("traffic_convenience"));
				info.setEmpWellfare(rs.getString("employee_wellfare"));
				info.setCfdNo(rs.getInt("cfd_num"));
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

	/*
	 * 
	 */
	//C_NUM을 넣는다면,,,,?**중요**
	@Override
	public List<InfoDTO> getSearchList(Integer cfd_num, String category) {
		if(category.equals("ANNUAL_INCOME")){
			String query = "SELECT * INFO WHERE CFD_NUM = ? ORDER BY ANNUAL_INCOME";
			Object[] param = new Object[] {cfd_num};
			jdbcUtil.setSqlAndParameters(query, param);
			try {
				ResultSet rs = jdbcUtil.executeQuery();
				List<InfoDTO> list = new ArrayList<InfoDTO>();
				while(rs.next()) {
					InfoDTO dto = new InfoDTO(
							rs.getInt("iNo"),
							rs.getInt("pId"),
							rs.getInt("wId"),
							rs.getInt("cfdNo"),
							rs.getInt("annual_Income"),
							rs.getInt("InfoModd"),
							rs.getInt("jobSat_R"),
							rs.getString("jobSat_H"),
							rs.getInt("cafeteria"),
							rs.getInt("traffciConven"),
							rs.getString("empWellfare"));
	//				dto.setCF_NUM(rs.getInt("CF_NUM"));
	//				dto.setCF_NAME(rs.getString("CF_NAME"));
					list.add(dto);
				}
				return list;
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally{
				jdbcUtil.close();
			}
		}//일단 수입에 대해서만 처리함.
		return null;
	}

	
}
