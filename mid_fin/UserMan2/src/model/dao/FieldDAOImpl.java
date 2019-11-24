package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import model.FieldDTO;
import model.dao.FieldDAO;

public class FieldDAOImpl implements FieldDAO{
	JDBCUtil jdbcUtil = null;
	private static final Logger logger = LoggerFactory.getLogger(FieldDAOImpl.class);
	
	public FieldDAOImpl(){
		jdbcUtil = new JDBCUtil();
	}
	
	public Integer getCF_NUMByCF_NAME(String cf_name) throws SQLException {
		Integer cf_num = null;//Integer Ÿ���̶� null�� �ʱ�ȭ��.
		String query = "SELECT CF_NUM "
				+ "FROM FIELD "
				+ "WHERE CF_NAME = ?" ;
		
		Object[] param = new Object[] {cf_name};
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet result = jdbcUtil.executeQuery();
			while (result.next()) {
				cf_num = result.getInt("Cf_NUM");
			}
			return cf_num;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			jdbcUtil.close();
		}
		return null;
	}

	@Override
	public List<FieldDTO> getFieldList() throws SQLException {
		String query = "SELECT cf_num, cf_name FROM field";
		jdbcUtil.setSqlAndParameters(query, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<FieldDTO> list = new ArrayList<FieldDTO>();
			while(rs.next()) {
				FieldDTO dto = new FieldDTO(
						rs.getInt("cf_num"),
						rs.getString("cf_name"));
//				dto.setCF_NUM(rs.getInt("CF_NUM"));
//				dto.setCF_NAME(rs.getString("CF_NAME"));
				list.add(dto);
				logger.info("ha");
			}
			return list;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			jdbcUtil.close();
		}return null;
	}  
	
}
