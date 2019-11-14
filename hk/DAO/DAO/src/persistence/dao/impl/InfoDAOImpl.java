package persistence.dao.impl;

import java.sql.ResultSet;
import java.util.List;
import persistence.dao.InfoDAO;
import service.dto.InfoDTO;

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

	@Override
	public Integer insertInfo(InfoDTO Info) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer updateInfo(InfoDTO Info) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer deleteInfo(Integer iCode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InfoDTO getInfoByCode(String iCode) {
		// TODO Auto-generated method stub
		return null;
	}	
}