package persistence.dao.impl;
import java.util.List;
import dto.DepartmentDTO;

import java.util.ArrayList;
import java.sql.*;
import persistence.dao.DepartmentDAO;

public class DepartmentDAOImpl implements DepartmentDAO{

	JDBCUtil jdbcUtil = null;
	
	public DepartmentDAOImpl(){
		jdbcUtil = new JDBCUtil();
	}
	/*회사마다 DEPARTMENT를 만드는 것인가,,,,?이상하다..
	 private Integer CFD_NUM = null;
	private Integer CF_NUM = null;
	private String CFD_NAME = null;
	private Integer ANNULAL_INCOME = null;....?
	 */
	
	@Override
	public List<DepartmentDTO> getDepartmentList() {
		String query = " SELECT CFD_NUM, CF_NUM, CFD_NAME FROM DEPARTMENT; ";
		jdbcUtil.setSql(query);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<DepartmentDTO> list = new ArrayList<DepartmentDTO>();
			while(rs.next()) {
				DepartmentDTO dto = new DepartmentDTO();
				dto.setCFD_NUM(rs.getInt("CFD_NUM"));
				dto.setCF_NUM(rs.getInt("CF_NUM"));
				dto.setCFD_NAME(rs.getString("CFD_NAME"));
				list.add(dto);
			}
			return list;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			jdbcUtil.close();
		}return null;
	}
	@Override
	public Integer getCFD_NUMByCFD_NAME(String cfd_name) {
		Integer cfd_num = null;//Integer 타입이라 null로 초기화함.
		String query = "SELECT CFD_NUM"
				+ "FROM DEPARTMENT"
				+ "WHERE CFD_NAME = ?;" ;
		
		Object[] param = new Object[] {cfd_num};
		jdbcUtil.setSql(query);
		jdbcUtil.setParameters(param);
		
		try {
			ResultSet result = jdbcUtil.executeQuery();
			cfd_num = result.getInt("CFD_NUM");
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			jdbcUtil.close();
		}
		return cfd_num;
	}

}
