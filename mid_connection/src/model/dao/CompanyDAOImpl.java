package model.dao;
import java.util.List;
import model.CompanyDTO;
import java.util.ArrayList;
import java.sql.*;
import model.dao.CompanyDAO;

public class CompanyDAOImpl implements CompanyDAO{

	
		JDBCUtil jdbcUtil = null;
		
		public CompanyDAOImpl(){
			jdbcUtil = new JDBCUtil();
		}
		
		

	public int insertCompany(CompanyDTO comp){
		
		int result = 0;
		String insertQuery = "INSERT INTO COMPANY(C_NUM, C_NAME, ADDRESS)" + 
				"VALUES(Sequence_COMPANY.NEXTVAL, ��?��, ��?��);";
		String compName = comp.getC_NAME();
		String compAddress = comp.getADDRESS();
		
		Object[] param = new Object[] {compName, compAddress};
		jdbcUtil.setSql(insertQuery);
		jdbcUtil.setParameters(param);
		
		try {
			result =jdbcUtil.executeUpdate();
			result = 1;//������ ��� 1
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			jdbcUtil.close();
		}
		return result;
		
	}
	
	/*
	private Integer C_NUM = null;
	private String C_NAME = null;
	private String ADDRESS = null;
	 */
	public Integer getC_NUMByC_NAME(String c_name) {
		
		Integer c_num = null;//Integer Ÿ���̶� null�� �ʱ�ȭ��.
		String query = "SELECT C_NUM "
				+ "FROM COMPANY "
				+ "WHERE C_NAME = ?" ;
		
		Object[] param = new Object[] {c_name};
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet result = jdbcUtil.executeQuery();
			while (result.next()) {
				c_num = result.getInt("C_NUM");
			}
			return c_num;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			jdbcUtil.close();
		}
		return null;
	}
	public List<CompanyDTO> getCompanyList(){
		
		String query = " SELECT C_NUM, C_NAME, C_ADDRESS FROM COMPANY; ";
		jdbcUtil.setSql(query);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<CompanyDTO> list = new ArrayList<CompanyDTO>();
			while(rs.next()) {
				CompanyDTO dto = new CompanyDTO(
						rs.getInt("C_NUM"),
						rs.getString("C_NAME"),
						rs.getString("C_ADDRESS")
				);
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
