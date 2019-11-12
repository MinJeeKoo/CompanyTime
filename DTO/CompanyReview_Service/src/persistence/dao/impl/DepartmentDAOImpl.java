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
	public int insertDepartment(DepartmentDTO dept) {
		int result = 0;
		String insertQuery = "INSERT INTO DEPARTMENT(CFD_NUM, CF_NUM, CFD_NAME)" + 
				"VALUES(Sequence_DEPARTMENT.NEXTVAL, ?, ?)";
		Integer deptNum = dept.getCF_NUM();
		String detpName = dept.getCFD_NAME();
		
		Object[] param = new Object[] {deptNum, detpName};
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
	}
	
	@Override
	public int updateDeparment(DepartmentDTO dept, String change) {
		int result = 0;
		String updateQuery = "UPDATE DEPARTMENT" + 
				"SET CFD_NAME= " + change +
				"WHERE CFD_NUM=?" ; 
				
		Integer DEPT_NUM = dept.getCFD_NUM();
		
		Object[] param = new Object[] {DEPT_NUM};
		jdbcUtil.setSql(updateQuery);
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

	}
	
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

}
