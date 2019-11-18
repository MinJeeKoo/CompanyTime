//package model.dao;
//
//import model.dto.*;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import model.dao.*;
//
//public class P_TurnoverDAOImpl implements P_TurnoverDAO {
//	private JDBCUtil jdbcUtil = null;		// JDBCUtil ��ü�� �����ϱ� ���� ����
//	
//	// P_Turnover �� �⺻ ������ �����ϴ� query ��
//	private static String query = "SELECT "  +
//								         "p_id AS id, " +
//								         "pw AS pw, " +
//								         "name AS name, " +
//								         "emp_num AS emp_num " +
//								         "company_email AS company_email " +
//								         "matching_result AS matching_result ";		
//		
//	// P_TurnoverDAOImpl ������
//	public P_TurnoverDAOImpl() {			
//		jdbcUtil = new JDBCUtil();		// P_TurnoverDAOImpl ��ü ���� �� JDBCUtil ��ü ����
//	}
//	
//	// ��ü �����غ��� ������ ȹ��
//	public List<P_TurnoverDTO> getP_TurnoverList(){
//		String allQuery = query + ", " + "c_num AS c_num  " + 
//				"cf_num AS cf_num " +
//				"cfd_num AS cfd_num " +
//		    "FROM Preparation_for_Turnover ";	
//		
//	jdbcUtil.setSql(allQuery);		// JDBCUtil �� query ����
//	
//	try { 
//		ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
//		List<P_TurnoverDTO> list = new ArrayList<P_TurnoverDTO>();		// P_TurnoverDTO ��ü���� ������� list ��ü
//		while (rs.next()) {	
//			P_TurnoverDTO dto = new P_TurnoverDTO();		// �ϳ��� P_TurnoverDTO ��ü ���� �� ���� ����
//			dto.setP_id(rs.getString("id"));
//			dto.setPw(rs.getString("pw"));
//			dto.setName(rs.getString("name"));
//			dto.setEmp_num(rs.getInt("emp_num"));
//			dto.setCompany_email(rs.getString("company_email"));
//			dto.setMatching_result(rs.getInt("matching_result"));
//			dto.setC_num(rs.getInt("c_num"));
//			dto.setCf_num(rs.getInt("cf_num"));
//			dto.setCfd_num(rs.getInt("cfd_num"));
//		
//			list.add(dto);		// list ��ü�� ������ ������ P_TurnoverDTO ��ü ����
//		}
//			return list;		// �����غ��� ������ ������ dto ���� ����� ��ȯ
//	} catch (Exception ex) {
//		ex.printStackTrace();
//	} finally {
//		jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
//	}		
//		return null;	
//	}
//	
//	// ������������ �߰�
//	public int insertP_Turnover(P_TurnoverDTO pt, String c_name, String cf_name, String cfd_name) {
//		int result = 0;
//		//c_num, c_name �� �� �ʿ�
//		//c_num�� fk��
//		//c_name�� c_num�� ���ϱ� ���� string����
//		//company���̺� �Է��� c_name�� ������ c_num�� sequence�� �����ϰ�, c_name���� �߰�
//		//company���̺� �Է��� c_name�� ������ c_num�� ������ pt�� �Է�
//		//c_num�� �Է¹����� ���ʷ� �Է� ���� c_num�� ������ ����� ��, � ȸ���̸����� ����� ���� ����.
//		//companyfield�� c_num�� primarykey��
//		
//		String insertQuery = "INSERT INTO Preparation_for_Turnover (p_id, pw, name, emp_num, company_email, matching_result,"
//							+ "c_num, cf_num, cfd_num) " +
//							 "?, ?, ?, ?, ?, ?, 0, ?, ?, ?) ";
//		
//		DAOFactory factory = new DAOFactory();		// ȸ�������� �ʵ������� �μ������� �˾ƿ��� ���� DAO ��ü�� �����ϴ� factory ��ü ����
//		
//		// CompanyDAO ��ü�� �����Ͽ� �����غ��� ������ ���ԵǾ� �ִ� ȸ���� c_num�� �˾ƿ�
//		CompanyDAO companyDAO = factory.getCompanyDAO();		// factory �� ���� ȸ�翡 ���� DAO ȹ��
//		Integer c_num = companyDAO.getC_numByC_name(c_name);		// ȸ�� DAO �� �̸��� ����Ͽ� �����ڵ带 ������ �޼ҵ� ���	// ȸ���ȣ�� ����
//		if (c_num == null) {
//			CompanyDTO newCompany = new CompanyDTO(null, c_name, null);
//			companyDAO.insertCompany(newCompany);
//			return 0;
//		} //������ ���߱�
//		
//		// FieldDAO ��ü�� �����Ͽ� �����غ��� ������ ���ԵǾ� �ִ� field�� cf_num�� �˾ƿ�
//		// Field�� �����ϰ��� �ϴ� �о߸� ����
//		FieldDAO fieldDAO = factory.getFieldDAO();		// factory �� ���� �ʵ忡 ���� DAO ȹ��
//		Integer cf_num = fieldDAO.getCF_NUMByCF_NAME(cf_name); // �ʵ� DAO �� �ʵ���� ����Ͽ� cf_num�� ������ �޼ҵ� ���			// cf_num�� ����
//		if (cf_num == null) {
//			return 0;
//		}
//		
//		// DepartmentDAO ��ü�� �����Ͽ� �����غ��� ������ ���ԵǾ� �ִ� �μ��� cfd_num�� �˾ƿ�
//		DepartmentDAO departmentDAO = factory.getDepartmentDAO();		// factory �� ���� �μ��� ���� DAO ȹ��
//		Integer cfd_num = departmentDAO.getCFD_NUMByCFD_NAME(cfd_name);	// departmentDAO �� �̸��� ����Ͽ� cfd�� ������ �޼ҵ� ���		// �μ���ȣ�� ����
//		if (cfd_num == null) {
//			return 0;
//		}
//		
//		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ����
//		Object[] param = new Object[] {pt.getP_id(), pt.getPw(), pt.getName(), 
//				pt.getEmp_num(), pt.getCompany_email(), c_num, cf_num, cfd_num};		
//		jdbcUtil.setSql(insertQuery);			// JDBCUtil �� insert �� ����
//		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
//				
//		try {				
//			result = jdbcUtil.executeUpdate();		// insert �� ����
//		} catch (SQLException ex) {
//			System.out.println("�Է¿��� �߻�!!!");
//			if (ex.getErrorCode() == 1)
//				System.out.println("������ �����غ��������� �̹� �����մϴ�."); 
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		} finally {		
//			jdbcUtil.commit();
//			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
//		}		
//		return result;		// insert �� ���� �ݿ��� ���ڵ� �� ��ȯ	
//	}
//
//	// ������������ ����
//	public int updateP_Turnover(P_TurnoverDTO pt) {
//		String updateQuery = "UPDATE Preparation_For_Turnover SET "
//				+ "c_num = ?, cfd_num = ?, name = ?, company_email = ?, pw = ?, "
//				+ "cf_num = ?, matching_result = ? "
//				+ "WHERE p_id = ? ";
//		Object[] param = new Object[] {pt.getC_num(), pt.getCfd_num(),
//									pt.getName(), pt.getCompany_email(), pt.getPw(), 
//									pt.getCf_num(), pt.getMatching_result(), pt.getP_id()};
//		// update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
//		jdbcUtil.setSql(updateQuery);
//		jdbcUtil.setParameters(param);
//
//		try {
//			int result = jdbcUtil.executeUpdate();		// update �� ����
//			return result;			// update �� ���� �ݿ��� ���ڵ� �� ��ȯ
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		}
//		finally {
//			jdbcUtil.commit();
//			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
//		}		
//		return 0;
//	}
//	
//	// ������������ ����
//	public int deleteP_Turnover(int p_id) {
//		String deleteQuery = "DELETE FROM P_Turnover WHERE P_ID = ?";
//		jdbcUtil.setSql(deleteQuery);			// JDBCUtil �� query �� ����
//		Object[] param = new Object[] {p_id};
//		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
//		
//		try {
//			int result = jdbcUtil.executeUpdate();		// delete �� ����
//			return result;						// delete �� ���� �ݿ��� ���ڵ� �� ��ȯ
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();		
//		} finally {
//			jdbcUtil.commit();
//			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
//		}
//		return 0;
//	}
//	// ������������ �̸����� ã��
//	// �������� ó��!
//	
//	public P_TurnoverDTO getP_TurnoverByName(String name) {
//		// �⺻ ������ ������ ȸ�����̺��� ȸ���, �ʵ����̺��� �ʵ��, �μ����̺��� �μ����� �������� ���̺�
//		String searchQuery = query + ", " + "c_num AS c_num, " +
//				  							"cf_num AS cf_num, " +
//				  							"cfd_num AS cfd_num " + 
//				  							"FROM preparation_for_turnover " +
//				  							"WHERE name = ? "; 
////				  							
//		jdbcUtil.setSql(searchQuery);				// JDBCUtil �� query �� ����
//		Object[] param = new Object[] { name };		// �����ڸ� ã�� ���� �������� �̸��� ����
//		jdbcUtil.setParameters(param);				// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
//				
//				try {
//					ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
//					P_TurnoverDTO pt = null;
//					if (rs.next()) {						// ã�� �������� ������ StudentDTO ��ü�� ����
//						pt = new P_TurnoverDTO();
//						pt.setP_id(rs.getString("id"));
//						pt.setPw(rs.getString("pw"));
//						pt.setName(rs.getString("name"));
//						pt.setEmp_num(rs.getInt("emp_num"));
//						pt.setCompany_email(rs.getString("company_email"));
//						pt.setMatching_result(rs.getInt("matching_result"));
//						pt.setC_num(rs.getInt("c_num"));
//						pt.setCf_num(rs.getInt("cf_num"));
//						pt.setCfd_num(rs.getInt("cfd_num"));
//					}
//					return pt;				// ã�� �������� ������ ��� �ִ� P_TurnoverDTO ��ü ��ȯ
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				} finally {
//					jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
//				}
//				return null;
//	}
//	
//	// ������������ id�� ã��
//	public P_TurnoverDTO getP_TurnoverById(String p_id) {
//		// �⺻ ������ ������ ȸ�����̺��� ȸ���, �ʵ����̺��� �ʵ��, �μ����̺��� �μ����� �������� ���̺�
//				String searchQuery = query + ", " + "c_num AS c_num, " +
//						  							"cf_num AS cf_num, " +
//						  							"cfd_num AS cfd_num " + 
//						  							"FROM preparation_for_turnover " +
//						  							"WHERE id = ? ";
//				jdbcUtil.setSql(searchQuery);				// JDBCUtil �� query �� ����
//				Object[] param = new Object[] { p_id };		// ������������ ã�� ���� �������� id�� ����
//				jdbcUtil.setParameters(param);				// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
//						
//						try {
//							ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
//							P_TurnoverDTO pt = null;
//							if (rs.next()) {						// ã�� ������������ P_TurnoverDTO ��ü�� ����
//								pt = new P_TurnoverDTO();
//								pt.setP_id(rs.getString("id"));
//								pt.setPw(rs.getString("pw"));
//								pt.setName(rs.getString("name"));
//								pt.setEmp_num(rs.getInt("emp_num"));
//								pt.setCompany_email(rs.getString("company_email"));
//								pt.setMatching_result(rs.getInt("matching_result"));
//								pt.setC_num(rs.getInt("c_num"));
//								pt.setCf_num(rs.getInt("cf_num"));
//								pt.setCfd_num(rs.getInt("cfd_num"));
//							}
//							return pt;				// ã�� �������� ������ ��� �ִ� P_TurnoverDTO ��ü ��ȯ
//						} catch (Exception ex) {
//							ex.printStackTrace();
//						} finally {
//							jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
//						}
//				return null;
//	}
//
//}
