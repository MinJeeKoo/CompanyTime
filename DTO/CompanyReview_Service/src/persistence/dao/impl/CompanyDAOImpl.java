package persistence.dao.impl;
import java.util.List;
import dto.CompanyDTO;
import java.util.ArrayList;
import java.sql.*;
import persistence.dao.CompanyDAO;

public abstract class CompanyDAOImpl implements CompanyDAO{

	
		JDBCUtil jdbcUtil = null;
		
		public CompanyDAOImpl(){
			jdbcUtil = new JDBCUtil();
		}
		
		

	public int insertCompany(CompanyDTO comp){
		
		int result = 0;
		String insertQuery = "INSERT INTO COMPANY(C_NUM, C_NAME, ADDRESS)" + 
				"VALUES(Sequence_COMPANY.NEXTVAL, ‘?’, ‘?’);";
		String compName = comp.getC_NAME();
		String compAddress = comp.getADDRESS();
		
		Object[] param = new Object[] {compName, compAddress};
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
	public int upDateCompany(CompanyDTO comp, int choice, String change){
		
		int result = 0;
		if(choice == 1) {
			String updateQuery = "UPDATE COMPANY" + 
					"SET C_NAME= " + change +
					"WHERE C_NUM=?" ; 
					
			Integer company_NUM = comp.getC_NUM();
			
			Object[] param = new Object[] {company_NUM};
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
		else {
			String updateQuery = "UPDATE COMPANY" + 
					"SET ADDRESS= " + change +
					"WHERE C_NUM=?" ; 
					
			Integer company_NUM = comp.getC_NUM();
			
			Object[] param = new Object[] {company_NUM};
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
	/*
	private Integer C_NUM = null;
	private String C_NAME = null;
	private String ADDRESS = null;
	 */
	public List<CompanyDTO> getCompanyList(){
		
		String query = " SELECT C_NUM, C_NAME, C_ADDRESS FROM COMPANY; ";
		jdbcUtil.setSql(query);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<CompanyDTO> list = new ArrayList<CompanyDTO>();
			while(rs.next()) {
				CompanyDTO dto = new CompanyDTO();
				dto.setC_NUM(rs.getInt("C_NUM"));
				dto.setC_NAME(rs.getString("C_NAME"));
				dto.setADDRESS(rs.getString("C_ADDRESS"));
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
