package model.dao;

import model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;

public class P_TurnoverDAOImpl implements P_TurnoverDAO {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	
	private JDBCUtil jdbcUtil = null;		// JDBCUtil 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹깍옙 占쏙옙占쏙옙 占쏙옙占쏙옙
	// P_Turnover 占쏙옙 占썩본 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 query 占쏙옙
	private static String query = "SELECT "  +
								         "p_id AS id, " +
								         "pw AS pw, " +
								         "name AS name, " +
								         "emp_num AS emp_num " +
								         "company_email AS company_email " +
								         "matching_result AS matching_result ";		
		
	// P_TurnoverDAOImpl 占쏙옙占쏙옙占쏙옙
	public P_TurnoverDAOImpl() {			
		jdbcUtil = new JDBCUtil();		// P_TurnoverDAOImpl 占쏙옙체 占쏙옙占쏙옙 占쏙옙 JDBCUtil 占쏙옙체 占쏙옙占쏙옙
	}	
	/**
	 * 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占싱븝옙 占쏙옙占싸울옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙.
	 */
	public int create(P_TurnoverDTO user) throws SQLException {
		String sql = "INSERT INTO preparation_for_turnover "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {user.getC_num(), user.getCfd_num(), user.getP_id(), user.getName(), user.getCompany_email(),
										user.getPw(), user.getCf_num(), user.getMatching_result()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 占쏙옙 insert占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 占쏙옙환
		}		
		return 0;			
	}
	/**
	 * 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙.
	 */
	public int update(P_TurnoverDTO user) throws SQLException {
		String sql = "UPDATE preparation_for_turnover "
					+ "SET c_num=?, cf_num=?, cfd_num=?, name=?, company_email=?, pw=? "
					+ "WHERE p_id=?";
		Object[] param = new Object[] {user.getC_num(), user.getCf_num(), user.getCfd_num(), user.getName(), user.getCompany_email(), 
				user.getPw(), user.getP_id()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil占쏙옙 update占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 占쏙옙환
		}		
		return 0;
	}

	/**
	 * 占쏙옙占쏙옙占� ID占쏙옙 占쌔댐옙占싹댐옙 占쏙옙占쏙옙美占� 占쏙옙占쏙옙.
	 */
	public int remove(String userId) throws SQLException {
		String sql = "DELETE FROM USERINFO WHERE p_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil占쏙옙 delete占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 占쏙옙환
		}		
		return 0;
	}


	
	// 占쏙옙체 占쏙옙占쏙옙占쌔븝옙占쏙옙 占쏙옙占쏙옙占쏙옙 획占쏙옙
	public List<P_TurnoverDTO> getP_TurnoverList(){
		String allQuery = query + ", " + "c_num AS c_num  " + 
				"cf_num AS cf_num " +
				"cfd_num AS cfd_num " +
		    "FROM Preparation_for_Turnover ";	
		
	jdbcUtil.setSql(allQuery);		// JDBCUtil 占쏙옙 query 占쏙옙占쏙옙
	
	try { 
		ResultSet rs = jdbcUtil.executeQuery();		// query 占쏙옙 占쏙옙占쏙옙			
		List<P_TurnoverDTO> list = new ArrayList<P_TurnoverDTO>();		// P_TurnoverDTO 占쏙옙체占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占� list 占쏙옙체
		while (rs.next()) {	
			P_TurnoverDTO dto = new P_TurnoverDTO();		// 占싹놂옙占쏙옙 P_TurnoverDTO 占쏙옙체 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
			dto.setP_id(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setName(rs.getString("name"));
			dto.setCompany_email(rs.getString("company_email"));
			dto.setMatching_result(rs.getInt("matching_result"));
			dto.setC_num(rs.getInt("c_num"));
			dto.setCf_num(rs.getInt("cf_num"));
			dto.setCfd_num(rs.getInt("cfd_num"));
		
			list.add(dto);		// list 占쏙옙체占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 P_TurnoverDTO 占쏙옙체 占쏙옙占쏙옙
		}
			return list;		// 占쏙옙占쏙옙占쌔븝옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 dto 占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙환
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 占쏙옙환
	}		
		return null;	
	}
	
	// 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쌩곤옙
//	public int create(P_TurnoverDTO pt, String c_name, String cf_name, String cfd_name) throws SQLException {
//		int result = 0;
//		
//		String insertQuery = "INSERT INTO Preparation_for_Turnover (p_id, pw, name, company_email, matching_result, "
//							+ "c_num, cf_num, cfd_num) " +
//							 "VALUES (?, ?, ?, ?, 0, ?, ?, ?)";		
//		DAOFactory factory = new DAOFactory();		// 회占쏙옙占쏙옙占쏙옙占쏙옙 占십듸옙占쏙옙占쏙옙占쏙옙 占싸쇽옙占쏙옙占쏙옙占쏙옙 占싯아울옙占쏙옙 占쏙옙占쏙옙 DAO 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹댐옙 factory 占쏙옙체 占쏙옙占쏙옙
//		
//		// CompanyDAO 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙占쌔븝옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쌉되억옙 占쌍댐옙 회占쏙옙占쏙옙 c_num占쏙옙 占싯아울옙
//		CompanyDAO companyDAO = factory.getCompanyDAO();		// factory 占쏙옙 占쏙옙占쏙옙 회占썹에 占쏙옙占쏙옙 DAO 획占쏙옙
//		Integer c_num = companyDAO.getC_NUMByC_NAME(c_name);
//		// 회占쏙옙 DAO 占쏙옙 占싱몌옙占쏙옙 占쏙옙占쏙옙臼占� 占쏙옙占쏙옙占쌘드를 占쏙옙占쏙옙占쏙옙 占쌨소듸옙 占쏙옙占�	// 회占쏙옙占싫ｏ옙占� 占쏙옙占쏙옙
//		if (c_num == null) {
//			CompanyDTO newCompany = new CompanyDTO(null, c_name, null);
//			companyDAO.insertCompany(newCompany);
//			return result;
//		} //占쏙옙占쏙옙占쏙옙 占쏙옙占쌩깍옙
//		logger.debug(c_num.toString());
//		// FieldDAO 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙占쌔븝옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쌉되억옙 占쌍댐옙 field占쏙옙 cf_num占쏙옙 占싯아울옙
//		// Field占쏙옙 占쏙옙占쏙옙占싹곤옙占쏙옙 占싹댐옙 占싻야몌옙 占쏙옙占쏙옙
//		FieldDAO fieldDAO = factory.getFieldDAO();		// factory 占쏙옙 占쏙옙占쏙옙 占십드에 占쏙옙占쏙옙 DAO 획占쏙옙
//		Integer cf_num = fieldDAO.getCF_NUMByCF_NAME(cf_name); // 占십듸옙 DAO 占쏙옙 占십듸옙占쏙옙占� 占쏙옙占쏙옙臼占� cf_num占쏙옙 占쏙옙占쏙옙占쏙옙 占쌨소듸옙 占쏙옙占�			// cf_num占쏙옙 占쏙옙占쏙옙
//		if (cf_num == null) {
//			logger.debug("cf_num is null");
////			return result;
//		}
////		logger.debug(cf_num.toString());
//		// DepartmentDAO 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙占쌔븝옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쌉되억옙 占쌍댐옙 占싸쇽옙占쏙옙 cfd_num占쏙옙 占싯아울옙
//		DepartmentDAO departmentDAO = factory.getDepartmentDAO();		// factory 占쏙옙 占쏙옙占쏙옙 占싸쇽옙占쏙옙 占쏙옙占쏙옙 DAO 획占쏙옙
//		Integer cfd_num = departmentDAO.getCFD_NUMByCFD_NAME(cfd_name);	// departmentDAO 占쏙옙 占싱몌옙占쏙옙 占쏙옙占쏙옙臼占� cfd占쏙옙 占쏙옙占쏙옙占쏙옙 占쌨소듸옙 占쏙옙占�		// 占싸쇽옙占쏙옙호占쏙옙 占쏙옙占쏙옙
//		if (cfd_num == null) {
//			logger.debug("cfd_num is null");
////			return result;
//		}
////		logger.debug(cfd_num.toString());
//		// query 占쏙옙占쏙옙 占쏙옙占쏙옙占� 占신곤옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占신곤옙占쏙옙占쏙옙 占썼열 占쏙옙占쏙옙
//		Object[] param = new Object[] {pt.getP_id(), pt.getPw(), pt.getName(), 
//				pt.getCompany_email(), c_num, cf_num, cfd_num};		
//		jdbcUtil.setSqlAndParameters(insertQuery, param);			// JDBCUtil 占쏙옙 insert 占쏙옙 占쏙옙占쏙옙	// JDBCUtil 占쏙옙 占신곤옙占쏙옙占쏙옙 占쏙옙占쏙옙
//				
////		try {				
////			result = jdbcUtil.executeUpdate();		// insert 占쏙옙 占쏙옙占쏙옙
////		} catch (SQLException ex) {
////			System.out.println("占쌉력울옙占쏙옙 占쌩삼옙!!!");
////			if (ex.getErrorCode() == 1)
////				System.out.println("占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쌔븝옙占쏙옙占쏙옙占쏙옙占쏙옙 占싱뱄옙 占쏙옙占쏙옙占쌌니댐옙."); 
////		} catch (Exception ex) {
////			jdbcUtil.rollback();
////			ex.printStackTrace();
////		} finally {		
////			jdbcUtil.commit();
////			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 占쏙옙환
////		}		
////		return result;		// insert 占쏙옙 占쏙옙占쏙옙 占쌥울옙占쏙옙 占쏙옙占쌘듸옙 占쏙옙 占쏙옙환	
//		try {				
//			result = jdbcUtil.executeUpdate();	// update 占쏙옙 占쏙옙占쏙옙
//			return result;
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		}
//		finally {
//			jdbcUtil.commit();
//			jdbcUtil.close();	// resource 占쏙옙환
//		}		
//		return 0;
//	}

	// 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
	public int updateP_Turnover(P_TurnoverDTO pt) {
		String updateQuery = "UPDATE Preparation_For_Turnover SET "
				+ "c_num = ?, cfd_num = ?, name = ?, company_email = ?, pw = ?, "
				+ "cf_num = ?, matching_result = ? "
				+ "WHERE p_id = ? ";
		Object[] param = new Object[] {pt.getC_num(), pt.getCfd_num(),
									pt.getName(), pt.getCompany_email(), pt.getPw(), 
									pt.getCf_num(), pt.getMatching_result(), pt.getP_id()};
		// update 占쏙옙占쏙옙 占쏙옙占쏙옙占� 占신곤옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙 占쌍댐옙 占쌈쏙옙 占썼열
		jdbcUtil.setSql(updateQuery);
		jdbcUtil.setParameters(param);

		try {
			int result = jdbcUtil.executeUpdate();		// update 占쏙옙 占쏙옙占쏙옙
			return result;			// update 占쏙옙 占쏙옙占쏙옙 占쌥울옙占쏙옙 占쏙옙占쌘듸옙 占쏙옙 占쏙옙환
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 占쏙옙환
		}		
		return 0;
	}
	
	// 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
	public int deleteP_Turnover(int p_id) {
		String deleteQuery = "DELETE FROM P_Turnover WHERE P_ID = ?";
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil 占쏙옙 query 占쏙옙 占쏙옙占쏙옙
		Object[] param = new Object[] {p_id};
		jdbcUtil.setParameters(param);			// JDBCUtil 占쏙옙 占신곤옙占쏙옙占쏙옙 占쏙옙占쏙옙
		
		try {
			int result = jdbcUtil.executeUpdate();		// delete 占쏙옙 占쏙옙占쏙옙
			return result;						// delete 占쏙옙 占쏙옙占쏙옙 占쌥울옙占쏙옙 占쏙옙占쌘듸옙 占쏙옙 占쏙옙환
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();		
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 占쏙옙환
		}
		return 0;
	}
	// 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占싱몌옙占쏙옙占쏙옙 찾占쏙옙
	// 占쏙옙占쏙옙占쏙옙占쏙옙 처占쏙옙!
	
	public P_TurnoverDTO getP_TurnoverByName(String name) {
		// 占썩본 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 회占쏙옙占쏙옙占싱븝옙占쏙옙 회占쏙옙占�, 占십듸옙占쏙옙占싱븝옙占쏙옙 占십듸옙占�, 占싸쇽옙占쏙옙占싱븝옙占쏙옙 占싸쇽옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占싱븝옙
		String searchQuery = query + ", " + "c_num AS c_num, " +
				  							"cf_num AS cf_num, " +
				  							"cfd_num AS cfd_num " + 
				  							"FROM preparation_for_turnover " +
				  							"WHERE name = ? "; 
//				  							
		jdbcUtil.setSql(searchQuery);				// JDBCUtil 占쏙옙 query 占쏙옙 占쏙옙占쏙옙
		Object[] param = new Object[] { name };		// 占쏙옙占쏙옙占쌘몌옙 찾占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占싱몌옙占쏙옙 占쏙옙占쏙옙
		jdbcUtil.setParameters(param);				// JDBCUtil 占쏙옙 query 占쏙옙占쏙옙 占신곤옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占� 占신곤옙占쏙옙占쏙옙 占쏙옙占쏙옙
				
				try {
					ResultSet rs = jdbcUtil.executeQuery();		// query 占쏙옙 占쏙옙占쏙옙
					P_TurnoverDTO pt = null;
					if (rs.next()) {						// 찾占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 StudentDTO 占쏙옙체占쏙옙 占쏙옙占쏙옙
						pt = new P_TurnoverDTO();
						pt.setP_id(rs.getString("id"));
						pt.setPw(rs.getString("pw"));
						pt.setName(rs.getString("name"));
						pt.setCompany_email(rs.getString("company_email"));
						pt.setMatching_result(rs.getInt("matching_result"));
						pt.setC_num(rs.getInt("c_num"));
						pt.setCf_num(rs.getInt("cf_num"));
						pt.setCfd_num(rs.getInt("cfd_num"));
					}
					return pt;				// 찾占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쌍댐옙 P_TurnoverDTO 占쏙옙체 占쏙옙환
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 占쏙옙환
				}
				return null;
	}
	
	// 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 id占쏙옙 찾占쏙옙
	public P_TurnoverDTO getP_TurnoverById(String p_id) {
		// 占썩본 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 회占쏙옙占쏙옙占싱븝옙占쏙옙 회占쏙옙占�, 占십듸옙占쏙옙占싱븝옙占쏙옙 占십듸옙占�, 占싸쇽옙占쏙옙占싱븝옙占쏙옙 占싸쇽옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占싱븝옙
				String searchQuery = query + ", " + "c_num AS c_num, " +
						  							"cf_num AS cf_num, " +
						  							"cfd_num AS cfd_num " + 
						  							"FROM preparation_for_turnover " +
						  							"WHERE id = ? ";
				jdbcUtil.setSql(searchQuery);				// JDBCUtil 占쏙옙 query 占쏙옙 占쏙옙占쏙옙
				Object[] param = new Object[] { p_id };		// 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 찾占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 id占쏙옙 占쏙옙占쏙옙
				jdbcUtil.setParameters(param);				// JDBCUtil 占쏙옙 query 占쏙옙占쏙옙 占신곤옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占� 占신곤옙占쏙옙占쏙옙 占쏙옙占쏙옙
						
						try {
							ResultSet rs = jdbcUtil.executeQuery();		// query 占쏙옙 占쏙옙占쏙옙
							P_TurnoverDTO pt = null;
							if (rs.next()) {						// 찾占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 P_TurnoverDTO 占쏙옙체占쏙옙 占쏙옙占쏙옙
								pt = new P_TurnoverDTO();
								pt.setP_id(rs.getString("id"));
								pt.setPw(rs.getString("pw"));
								pt.setName(rs.getString("name"));
								pt.setCompany_email(rs.getString("company_email"));
								pt.setMatching_result(rs.getInt("matching_result"));
								pt.setC_num(rs.getInt("c_num"));
								pt.setCf_num(rs.getInt("cf_num"));
								pt.setCfd_num(rs.getInt("cfd_num"));
							}
							return pt;				// 찾占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쌍댐옙 P_TurnoverDTO 占쏙옙체 占쏙옙환
						} catch (Exception ex) {
							ex.printStackTrace();
						} finally {
							jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 占쏙옙환
						}
				return null;
	}
	
	/**
	 * 占쌍억옙占쏙옙 占쏙옙占쏙옙占� ID占쏙옙 占쌔댐옙占싹댐옙 占쏙옙占쏙옙微占� 占쏙옙占쏙옙占싹댐옙占쏙옙 占싯삼옙 
	 */
	public boolean existingUser(String userId) throws SQLException {
		String sql = "SELECT count(*) FROM preparation_for_turnover WHERE p_id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil占쏙옙 query占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쏙옙占쏙옙
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return false;
	}
	/**
	 * 占쏙옙체 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙 占싯삼옙占싹울옙 List占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙환
	 */
	public List<P_TurnoverDTO> findUserList() throws SQLException {
        String sql = "SELECT p_id, pw, name, c_num, cf_num, cfd_num, company_email, matching_result " 
     		   + "FROM preparation_for_turnover "
     		   + "ORDER BY p_id";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쏙옙占쏙옙			
			List<P_TurnoverDTO> userList = new ArrayList<P_TurnoverDTO>();	// User占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
			while (rs.next()) {
				P_TurnoverDTO user = new P_TurnoverDTO(			// User 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
						rs.getString("p_id"),
						rs.getString("pw"),
						rs.getString("name"),
						rs.getInt("c_num"),
						rs.getInt("cf_num"),
						rs.getInt("cfd_num"),
						rs.getString("company_email"),
						Integer.valueOf(0)
						);	
				userList.add(user);				// List占쏙옙 User 占쏙옙체 占쏙옙占쏙옙
			}
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
	}
	
	/**
	 * 占쏙옙체 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙 占싯삼옙占쏙옙 占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占싱울옙占싹울옙
	 * 占쌔댐옙占싹댐옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙 List占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙환.
	 */
	public List<P_TurnoverDTO> findUserList(int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT p_id, pw, name, c_num, cf_num, cfd_num, company_email, matching_result " 
        		   + "FROM preparation_for_turnover "
        		   + "ORDER BY p_id";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 占쏙옙占쏙옙
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query 占쏙옙占쏙옙			
			int start = ((currentPage-1) * countPerPage) + 1;	// 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쏙옙 占쏙옙호 占쏙옙占�
			if ((start >= 0) && rs.absolute(start)) {			// 커占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싱듸옙
				List<P_TurnoverDTO> userList = new ArrayList<P_TurnoverDTO>();	// User占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
				do {
					P_TurnoverDTO user = new P_TurnoverDTO(		// User 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
						rs.getString("p_id"),
						rs.getString("pw"),
						rs.getString("name"),
						rs.getInt("c_num"),
						rs.getInt("cf_num"),
						rs.getInt("cfd_num"),
						rs.getString("company_email"),
						rs.getInt("matching_result")
						);	
					userList.add(user);							// 占쏙옙占쏙옙트占쏙옙 User 占쏙옙체 占쏙옙占쏙옙
				} while ((rs.next()) && (--countPerPage > 0));		
				return userList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
	}
	/**
	 * 占쌍억옙占쏙옙 占쏙옙占쏙옙占� ID占쏙옙 占쌔댐옙占싹댐옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싶븝옙占싱쏙옙占쏙옙占쏙옙 찾占쏙옙 User 占쏙옙占쏙옙占쏙옙 클占쏙옙占쏙옙占쏙옙 
	 * 占쏙옙占쏙옙占싹울옙 占쏙옙환.
	 */
	public P_TurnoverDTO findUser(String p_id) throws SQLException {
        String sql = "SELECT p_id, c_num, cf_num, cfd_num, name, company_email, pw, matching_result "
        			+ "FROM preparation_for_turnover "
        			+ "WHERE p_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {p_id});	// JDBCUtil占쏙옙 query占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쏙옙占쏙옙
			if (rs.next()) {						// 占싻삼옙 占쏙옙占쏙옙 占쌩곤옙
				P_TurnoverDTO user = new P_TurnoverDTO(		// User 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占싻삼옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
						rs.getString("p_id"),
						rs.getString("pw"),
						rs.getString("name"),
						rs.getInt("c_num"),
						rs.getInt("cf_num"),
						rs.getInt("cfd_num"),
						rs.getString("company_email"),
						rs.getInt("matching_result")
				);
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
	}
}
