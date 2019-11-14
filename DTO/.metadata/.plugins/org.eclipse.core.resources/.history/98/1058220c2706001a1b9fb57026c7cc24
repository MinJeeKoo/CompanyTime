package persistence.dao.impl;
import java.sql.*;
import dto.FieldDTO;
import persistence.dao.FieldDAO;
public class FieldDAOImpl implements FieldDAO{
	JDBCUtil jdbcUtil = null;
	
	public FieldDAOImpl(){
		jdbcUtil = new JDBCUtil();
	}
	@Override
	public int insertField(FieldDTO field) {
		int result = 0;
		String insertQuery = "INSERT INTO DEPARTMENT(CF_NUM, CF_NAME)" + 
				"VALUES(Sequence_DEPARTMENT.NEXTVAL, ?)";
		String fieldName = field.getCF_NAME();
		
		Object[] param = new Object[] {fieldName};
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
	public int updateField(FieldDTO field, String change) {
		int result = 0;
		String updateQuery = "UPDATE FIELD" + 
				"SET CF_NAME= " + change +
				"WHERE CF	_NUM=?" ; 
				
		Integer FIELD_NUM = field.getCF_NUM();
		
		Object[] param = new Object[] {FIELD_NUM};
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

	
}
