package model.dao;

import model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;

public class WorkerDAOImpl {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	
	private JDBCUtil jdbcUtil = null;		// JDBCUtil ��ü�� �����ϱ� ���� ����
	// P_Turnover �� �⺻ ������ �����ϴ� query ��
	private static String query = "SELECT "  +
								         "w_id AS id, " +
								         "pw AS pw, " +
								         "name AS name, " +
								         "emp_num AS emp_num " +
								         "company_email AS company_email " +
								         "matching_result AS matching_result ";		
		
	// P_TurnoverDAOImpl ������
	public WorkerDAOImpl() {			
		jdbcUtil = new JDBCUtil();		// P_TurnoverDAOImpl ��ü ���� �� JDBCUtil ��ü ����
	}	
	/**
	 * ����� ���� ���̺� ���ο� ����� ����.
	 */
	public int create(WorkerDTO user) throws SQLException {
		String sql = "INSERT INTO worker (w_id, pw, name, emp_num, company_email, c_num, cf_num, cfd_num, matching_result )"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {user.getW_id(), user.getPw(), user.getName(), user.getEmpno(), user.getCompany_email(),
										user.getC_num(), user.getCf_num(), user.getCfd_num(), user.getMatching_result()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}
	/**
	 * ������ ����� ������ ����.
	 */
	public int update(WorkerDTO user) throws SQLException {
		String sql = "UPDATE Worker "
				+ "SET c_num=?, cf_num=?, cfd_num=?, name=?, company_email=?, pw=? "
				+ "WHERE p_id=?";
		Object[] param = new Object[] {user.getC_num(), user.getCf_num(), user.getCfd_num(), user.getName(), user.getCompany_email(), 
				user.getPw(), user.getW_id()};				
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
	 * ����� ID�� �ش��ϴ� ����ڸ� ����.
	 */
	public int remove(String userId) throws SQLException {
		String sql = "DELETE FROM Worker WHERE userid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}


	
	// ��ü �����غ��� ������ ȹ��
	public List<WorkerDTO> getWorkerList(){
		String allQuery = query + ", " + "c_num AS c_num  " + 
				"cf_num AS cf_num " +
				"cfd_num AS cfd_num " +
		    "FROM Preparation_for_Turnover ";	
		
	jdbcUtil.setSql(allQuery);		// JDBCUtil �� query ����
	
	try { 
		ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
		List<WorkerDTO> list = new ArrayList<WorkerDTO>();		// P_TurnoverDTO ��ü���� ������� list ��ü
		while (rs.next()) {	
			WorkerDTO dto = new WorkerDTO();		// �ϳ��� P_TurnoverDTO ��ü ���� �� ���� ����
			dto.setW_id(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setName(rs.getString("name"));
			dto.setCompany_email(rs.getString("company_email"));
			dto.setMatching_result(rs.getInt("matching_result"));
			dto.setC_num(rs.getInt("c_num"));
			dto.setCf_num(rs.getInt("cf_num"));
			dto.setCfd_num(rs.getInt("cfd_num"));
		
			list.add(dto);		// list ��ü�� ������ ������ P_TurnoverDTO ��ü ����
		}
			return list;		// �����غ��� ������ ������ dto ���� ����� ��ȯ
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
	}		
		return null;	
	}
	
	// ������������ �߰�
//	public int create(P_TurnoverDTO pt, String c_name, String cf_name, String cfd_name) throws SQLException {
//		int result = 0;
//		
//		String insertQuery = "INSERT INTO Preparation_for_Turnover (p_id, pw, name, company_email, matching_result, "
//							+ "c_num, cf_num, cfd_num) " +
//							 "VALUES (?, ?, ?, ?, 0, ?, ?, ?)";		
//		DAOFactory factory = new DAOFactory();		// ȸ�������� �ʵ������� �μ������� �˾ƿ��� ���� DAO ��ü�� �����ϴ� factory ��ü ����
//		
//		// CompanyDAO ��ü�� �����Ͽ� �����غ��� ������ ���ԵǾ� �ִ� ȸ���� c_num�� �˾ƿ�
//		CompanyDAO companyDAO = factory.getCompanyDAO();		// factory �� ���� ȸ�翡 ���� DAO ȹ��
//		Integer c_num = companyDAO.getC_NUMByC_NAME(c_name);
//		// ȸ�� DAO �� �̸��� ����Ͽ� �����ڵ带 ������ �޼ҵ� ���	// ȸ���ȣ�� ����
//		if (c_num == null) {
//			CompanyDTO newCompany = new CompanyDTO(null, c_name, null);
//			companyDAO.insertCompany(newCompany);
//			return result;
//		} //������ ���߱�
//		logger.debug(c_num.toString());
//		// FieldDAO ��ü�� �����Ͽ� �����غ��� ������ ���ԵǾ� �ִ� field�� cf_num�� �˾ƿ�
//		// Field�� �����ϰ��� �ϴ� �о߸� ����
//		FieldDAO fieldDAO = factory.getFieldDAO();		// factory �� ���� �ʵ忡 ���� DAO ȹ��
//		Integer cf_num = fieldDAO.getCF_NUMByCF_NAME(cf_name); // �ʵ� DAO �� �ʵ���� ����Ͽ� cf_num�� ������ �޼ҵ� ���			// cf_num�� ����
//		if (cf_num == null) {
//			logger.debug("cf_num is null");
////			return result;
//		}
////		logger.debug(cf_num.toString());
//		// DepartmentDAO ��ü�� �����Ͽ� �����غ��� ������ ���ԵǾ� �ִ� �μ��� cfd_num�� �˾ƿ�
//		DepartmentDAO departmentDAO = factory.getDepartmentDAO();		// factory �� ���� �μ��� ���� DAO ȹ��
//		Integer cfd_num = departmentDAO.getCFD_NUMByCFD_NAME(cfd_name);	// departmentDAO �� �̸��� ����Ͽ� cfd�� ������ �޼ҵ� ���		// �μ���ȣ�� ����
//		if (cfd_num == null) {
//			logger.debug("cfd_num is null");
////			return result;
//		}
////		logger.debug(cfd_num.toString());
//		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ����
//		Object[] param = new Object[] {pt.getP_id(), pt.getPw(), pt.getName(), 
//				pt.getCompany_email(), c_num, cf_num, cfd_num};		
//		jdbcUtil.setSqlAndParameters(insertQuery, param);			// JDBCUtil �� insert �� ����	// JDBCUtil �� �Ű����� ����
//				
////		try {				
////			result = jdbcUtil.executeUpdate();		// insert �� ����
////		} catch (SQLException ex) {
////			System.out.println("�Է¿��� �߻�!!!");
////			if (ex.getErrorCode() == 1)
////				System.out.println("������ �����غ��������� �̹� �����մϴ�."); 
////		} catch (Exception ex) {
////			jdbcUtil.rollback();
////			ex.printStackTrace();
////		} finally {		
////			jdbcUtil.commit();
////			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
////		}		
////		return result;		// insert �� ���� �ݿ��� ���ڵ� �� ��ȯ	
//		try {				
//			result = jdbcUtil.executeUpdate();	// update �� ����
//			return result;
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		}
//		finally {
//			jdbcUtil.commit();
//			jdbcUtil.close();	// resource ��ȯ
//		}		
//		return 0;
//	}

	// ������������ ����
	public int updateWorker(WorkerDTO w) {
		String updateQuery = "UPDATE Worker SET "
				+ "c_num = ?, cfd_num = ?, name = ?, company_email = ?, pw = ?, "
				+ "cf_num = ?, matching_result = ? "
				+ "WHERE w_id = ? ";
		Object[] param = new Object[] {w.getC_num(), w.getCfd_num(),
									w.getName(), w.getCompany_email(), w.getPw(), 
									w.getCf_num(), w.getMatching_result(), w.getW_id()};
		// update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
		jdbcUtil.setSql(updateQuery);
		jdbcUtil.setParameters(param);

		try {
			int result = jdbcUtil.executeUpdate();		// update �� ����
			return result;			// update �� ���� �ݿ��� ���ڵ� �� ��ȯ
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return 0;
	}
	
	// ������������ ����
	public int deleteWorker(int w_id) {
		String deleteQuery = "DELETE FROM Worker WHERE W_ID = ?";
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil �� query �� ����
		Object[] param = new Object[] {w_id};
		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
		
		try {
			int result = jdbcUtil.executeUpdate();		// delete �� ����
			return result;						// delete �� ���� �ݿ��� ���ڵ� �� ��ȯ
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();		
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}
		return 0;
	}
	// ������������ �̸����� ã��
	// �������� ó��!
	
	public WorkerDTO getWorkerByName(String name) {
		// �⺻ ������ ������ ȸ�����̺��� ȸ���, �ʵ����̺��� �ʵ��, �μ����̺��� �μ����� �������� ���̺�
		String searchQuery = query + ", " + "c_num AS c_num, " +
				  							"cf_num AS cf_num, " +
				  							"cfd_num AS cfd_num " + 
				  							"FROM Worker " +
				  							"WHERE name = ? "; 
//				  							
		jdbcUtil.setSql(searchQuery);				// JDBCUtil �� query �� ����
		Object[] param = new Object[] { name };		// �����ڸ� ã�� ���� �������� �̸��� ����
		jdbcUtil.setParameters(param);				// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
				
				try {
					ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
					WorkerDTO pt = null;
					if (rs.next()) {						// ã�� �������� ������ StudentDTO ��ü�� ����
						pt = new WorkerDTO();
						pt.setW_id(rs.getString("id"));
						pt.setPw(rs.getString("pw"));
						pt.setName(rs.getString("name"));
						pt.setCompany_email(rs.getString("company_email"));
						pt.setMatching_result(Integer.valueOf(0));
						pt.setC_num(rs.getInt("c_num"));
						pt.setCf_num(rs.getInt("cf_num"));
						pt.setCfd_num(rs.getInt("cfd_num"));
					}
					return pt;				// ã�� �������� ������ ��� �ִ� P_TurnoverDTO ��ü ��ȯ
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
				}
				return null;
	}
	
	// ������������ id�� ã��
	public WorkerDTO getWorkerById(String w_id) {
		// �⺻ ������ ������ ȸ�����̺��� ȸ���, �ʵ����̺��� �ʵ��, �μ����̺��� �μ����� �������� ���̺�
				String searchQuery = query + ", " + "c_num AS c_num, " +
						  							"cf_num AS cf_num, " +
						  							"cfd_num AS cfd_num " + 
						  							"FROM Worker " +
						  							"WHERE id = ? ";
				jdbcUtil.setSql(searchQuery);				// JDBCUtil �� query �� ����
				Object[] param = new Object[] { w_id };		// ������������ ã�� ���� �������� id�� ����
				jdbcUtil.setParameters(param);				// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
						
						try {
							ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
							WorkerDTO pt = null;
							if (rs.next()) {						// ã�� ������������ P_TurnoverDTO ��ü�� ����
								pt = new WorkerDTO();
								pt.setW_id(rs.getString("id"));
								pt.setPw(rs.getString("pw"));
								pt.setName(rs.getString("name"));
								pt.setCompany_email(rs.getString("company_email"));
								pt.setMatching_result(Integer.valueOf(0));
								pt.setC_num(rs.getInt("c_num"));
								pt.setCf_num(rs.getInt("cf_num"));
								pt.setCfd_num(rs.getInt("cfd_num"));
							}
							return pt;				// ã�� �������� ������ ��� �ִ� P_TurnoverDTO ��ü ��ȯ
						} catch (Exception ex) {
							ex.printStackTrace();
						} finally {
							jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
						}
				return null;
	}
	
	/**
	 * �־��� ����� ID�� �ش��ϴ� ����ڰ� �����ϴ��� �˻� 
	 */
	public boolean existingUser(String userId) throws SQLException {
		String sql = "SELECT count(*) FROM Worker WHERE w_id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}
	/**
	 * ��ü ����� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<WorkerDTO> findUserList() throws SQLException {
        String sql = "SELECT w_id, pw, name, c_num, cf_num, cfd_num, company_email, matching_result, emp_num " 
     		   + "FROM Worker "
     		   + "ORDER BY w_id";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<WorkerDTO> userList = new ArrayList<WorkerDTO>();	// User���� ����Ʈ ����
			while (rs.next()) {
				WorkerDTO user = new WorkerDTO(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getString("w_id"),
						rs.getString("pw"),
						rs.getString("name"),
						rs.getInt("c_num"),
						rs.getInt("cf_num"),
						rs.getInt("cfd_num"),
						rs.getString("company_email"),
						Integer.valueOf(0),
						rs.getString("emp_num")
						);	
				userList.add(user);				// List�� User ��ü ����
			}
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * ��ü ����� ������ �˻��� �� ���� �������� �������� ����� ����� ���� �̿��Ͽ�
	 * �ش��ϴ� ����� �������� List�� �����Ͽ� ��ȯ.
	 */
	public List<WorkerDTO> findUserList(int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT w_id, pw, name, c_num, cf_num, cfd_num, company_email, matching_result, emp_num " 
        		   + "FROM Worker "
        		   + "ORDER BY w_id";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
				List<WorkerDTO> userList = new ArrayList<WorkerDTO>();	// User���� ����Ʈ ����
				do {
					WorkerDTO user = new WorkerDTO(		// User ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getString("w_id"),
						rs.getString("pw"),
						rs.getString("name"),
						rs.getInt("c_num"),
						rs.getInt("cf_num"),
						rs.getInt("cfd_num"),
						rs.getString("company_email"),
						Integer.valueOf(0),
						rs.getString("empno")
						);	
					userList.add(user);							// ����Ʈ�� User ��ü ����
				} while ((rs.next()) && (--countPerPage > 0));		
				return userList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	/**
	 * �־��� ����� ID�� �ش��ϴ� ����� ������ �����ͺ��̽����� ã�� User ������ Ŭ������ 
	 * �����Ͽ� ��ȯ.
	 */
	public WorkerDTO findUser(String w_id) throws SQLException {
        String sql = "SELECT w_id, c_num, cf_num, cfd_num, name, company_email, pw, matching_result, emp_num "
        			+ "FROM Worker "
        			+ "WHERE w_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {w_id});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				WorkerDTO user = new WorkerDTO(		// User ��ü�� �����Ͽ� �л� ������ ����
						rs.getString("w_id"),
						rs.getString("pw"),
						rs.getString("name"),
						rs.getInt("c_num"),
						rs.getInt("cf_num"),
						rs.getInt("cfd_num"),
						rs.getString("company_email"),
						Integer.valueOf(0),
						rs.getString("emp_num")
				);
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
}