package model.dao;

import java.sql.ResultSet;
import java.util.List;
import model.dao.InfoDAO;
import dto.InfoDTO;

public class InfoDAOImpl implements InfoDAO{
private JDBCUtil jdbcUtil = null;
	
	private static String query = "";
		
	public InfoDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}	
	
	@Override
	public InfoDTO getInfoByName(String iName) {
		String searchQuery = query + "";
		Object[] param = new Object[] {iName};

		jdbcUtil.setSql(searchQuery);
		jdbcUtil.setParameters(param);
	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			InfoDTO dto = new InfoDTO();
			while (rs.next()) {
				dto.setINo(rs.getInt("Info_INO"));
				dto.setPNo(rs.getInt("Info_PNO"));
				dto.setWNo(rs.getInt("Info_WNO"));
				dto.setCfdNO(rs.getInt("Info_CFDNO"));
				dto.setAnnual_Income(rs.getInt("Info_ANNUALINCOME"));
				dto.setInfoMood(rs.getInt("Info_MOOD"));
				dto.setJobSat(rs.getInt("Info_JOBSAT"));
				dto.setCafeteria(rs.getInt("Info_CAFETERIA"));
				dto.setTrafficConven(rs.getInt("Info_TRAFFIC"));
				dto.setEmpWellfare(rs.getInt("Info_EMPWELL"));

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
	public List<InfoDTO> getInfoList() {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	   private Integer InfoMood = null;		// 부서 분위기
	   private Integer jobSat = null;			// 직업 만족도
	   private Integer cafeteria = null;			// 직원 식당
	   private Integer trafficConven = null;		// 교통편의성
	   private Integer empWellfare = null;*/
	@Override
	public Integer insertInfo(InfoDTO Info) {
		int result = 0;
		String insertQuery = "INSERT INTO INFO(I_NUM, P_NUM, W_NUM, ANNUAL_INCOME, DEPARTEMENT_MOOD, JOB_SATISFACTION,"
				+ "CAFETERIA, TRAFFIC_CONVENIENCE, EMPLOYEE_WELLFARE, CFD_NUM)" + 
				"VALUES(Sequence_INFO.NEXTVAL, ‘?’, ‘?’, ‘?’,‘?’,‘?’,‘?’,‘?’,‘?’,‘?’);";
		
		Integer pNo = Info.getPNo();
		Integer wNo = Info.getWNo();
		Integer annual_Income = Info.getAnnual_Income();//1
		Integer InfoMood = Info.getInfoMood();//2
		Integer jobSat = Info.getJobSat();//3
		Integer cafeteria = Info.getCafeteria();//4
		Integer trafficConven = Info.getTrafficConven();//5
		Integer empWellfare = Info.getEmpWellfare();//6
		Integer cfdNo = Info.getCfdNO();
		
		Object[] param = new Object[] {pNo,wNo, annual_Income, InfoMood, jobSat, cafeteria, trafficConven, empWellfare, cfdNo};
		jdbcUtil.setSql(insertQuery);
		jdbcUtil.setParameters(param);
		
		try {
			result =jdbcUtil.executeUpdate();
			result = 1;//성공할 경우 1
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			jdbcUtil.close();
		}
		return result;
	}//1

	@Override
	public Integer updateInfo(InfoDTO Info) {
		String sql = "UPDATE  "
				+ "SET ANNUAL_INCOME =? , DEPARTEMENT_MOOD =? , JOB_SATISFACTION = ?, CAFETERIA = ?, TRAFFIC_CONVENIENCE = ? , EMPLOYEE_WELLFARE= ?" 
				+ "WHERE I_NUM=?";
	Object[] param = new Object[] {Info.getAnnual_Income(), Info.getInfoMood(), Info.getJobSat(), Info.getCafeteria(), Info.getTrafficConven(), Info.getEmpWellfare()};				
	jdbcUtil.setSql(sql);	
	jdbcUtil.setParameters(param);
	
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

	@Override
	public InfoDTO getInfoByCode(String iCode) {
		// TODO Auto-generated method stub
		return null;
	}	
}
