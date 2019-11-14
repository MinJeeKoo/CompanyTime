package persistence.dao.impl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.CompanyDTO;
import dto.FieldDTO;
import persistence.dao.FieldDAO;
public class FieldDAOImpl implements FieldDAO{
	JDBCUtil jdbcUtil = null;
	
	public FieldDAOImpl(){
		jdbcUtil = new JDBCUtil();
	}
	
	public Integer getCF_NUMByCF_NAME(String cf_name) {
		
		Integer cf_num = null;//Integer 타입이라 null로 초기화함.
		String query = "SELECT CF_NUM"
				+ "FROM FIELD"
				+ "WHERE CF_NAME = ?;" ;
		
		Object[] param = new Object[] {cf_num};
		jdbcUtil.setSql(query);
		jdbcUtil.setParameters(param);
		
		try {
			ResultSet result = jdbcUtil.executeQuery();
			cf_num = result.getInt("CF_NUM");
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			jdbcUtil.close();
		}
		return cf_num;
	}

	@Override
	public List<FieldDTO> getFieldList() {
		String query = " SELECT CF_NUM, CF_NAME FROM FIELD ; ";
		jdbcUtil.setSql(query);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<FieldDTO> list = new ArrayList<FieldDTO>();
			while(rs.next()) {
				FieldDTO dto = new FieldDTO();
				dto.setCF_NUM(rs.getInt("CF_NUM"));
				dto.setCF_NAME(rs.getString("CF_NAME"));
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
