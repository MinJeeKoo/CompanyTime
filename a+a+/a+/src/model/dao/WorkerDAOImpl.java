//package model.dao;
//
//import model.dto.*;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class WorkerDAOImpl implements WorkerDAO {
//
//	private JDBCUtil jdbcUtil = null;		// JDBCUtil ��ü�� �����ϱ� ���� ����
//	
//	// WorkerDAO �� �⺻ ������ �����ϴ� query ��
//	private static String query = "SELECT " +
//								         "w_id AS id, " +
//								         "pw AS pw, " +
//								         "name AS name, " +
//								         "emp_num AS emp_num, " +
//								         "company_email AS company_email, " +
//								         "matching_result AS matching_result ";		
//		
//	// WorkerDAOImpl ������
//	public WorkerDAOImpl() {			
//		jdbcUtil = new JDBCUtil();		// WorkerDAOImpl ��ü ���� �� JDBCUtil ��ü ����
//	}
//	
//	// ��ü ������ ������ ȹ��
//	public List<WorkerDTO> getWorkerList(){
//		String allQuery = query + ", " + "c_num AS c_num  " + 
//				"cf_num AS cf_num, " +
//				"cfd_num AS cfd_num, " +
//		    "FROM Worker ";	
//		
//		jdbcUtil.setSql(allQuery);		// JDBCUtil �� query ����
//		
//		try { 
//			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
//			List<WorkerDTO> list = new ArrayList<WorkerDTO>();		// WorkerDTO ��ü���� ������� list ��ü
//			while (rs.next()) {	
//				WorkerDTO dto = new WorkerDTO();		// �ϳ��� WorkerDTO ��ü ���� �� ���� ����
//			dto.setW_id(rs.getString("id"));
//			dto.setPw(rs.getString("pw"));
//			dto.setName(rs.getString("name"));
//			dto.setEmp_num(rs.getInt("emp_num"));
//			dto.setCompany_email(rs.getString("company_email"));
//			dto.setMatching_result(rs.getInt("matching_result"));
//			dto.setC_num(rs.getInt("c_num"));
//			dto.setCf_num(rs.getInt("cf_num"));
//			dto.setCfd_num(rs.getInt("cfd_num"));
//			
//			list.add(dto);		// list ��ü�� ������ ������ WorkerDTO ��ü ����
//		}
//			return list;		// ������ ������ ������ dto ���� ����� ��ȯ
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
//		}		
//			return null;	
//	}
//	
//	// ������������ �߰�
//	public int insertWorker(WorkerDTO w, String c_name, String cf_name, String cfd_name) {
//		int result = 0;
//		String insertQuery = "INSERT INTO Worker (w_id, pw, name, emp_num, company_email, matching_result, "
//				+ "c_num, cf_num, cfd_num) " +
//							 "VALUES (?, ?, ?, ?, ?, 0, ?, ?, ?) ";
//		
//		DAOFactory factory = new DAOFactory();		// ȸ�������� �ʵ������� �μ������� �˾ƿ��� ���� DAO ��ü�� �����ϴ� factory ��ü ����
//		
//		// CompanyDAO ��ü�� �����Ͽ� �����غ��� ������ ���ԵǾ� �ִ� ȸ���� c_num�� �˾ƿ�
//		CompanyDAO companyDAO = factory.getCompanyDAO();		// factory �� ���� ȸ�翡 ���� DAO ȹ��
//		Integer c_num = companyDAO.getC_numByC_name(c_name);		// ȸ�� DAO �� �̸��� ����Ͽ� �����ڵ带 ������ �޼ҵ� ���
//		if (c_num == null) {
//			CompanyDTO newCompany = new CompanyDTO(null, c_name, null);
//			companyDAO.insertCompany(newCompany);
//			return 0;
//		} //������ ���߱�
//		
//		// WorkerDAO ��ü�� �����Ͽ� �����غ��� ������ ���ԵǾ� �ִ� field�� cf_num�� �˾ƿ�
//		// Field�� �������� �μ��� �о߸� ����
//		FieldDAO fieldDAO = factory.getFieldDAO();		// factory �� ���� �ʵ忡 ���� DAO ȹ��
//		Integer cf_num = fieldDAO.getCf_numByCf_name(cf_name); // �ʵ� DAO �� �ʵ���� ����Ͽ� cf_num�� ������ �޼ҵ� ���
//		if (cf_num == null) {
//			return 0;
//		}
//		
//		// DepartmentDAO ��ü�� �����Ͽ� ������ ������ ���ԵǾ� �ִ� �μ��� cfd_num�� �˾ƿ�
//		DepartmentDAO departmentDAO = factory.getDepartmentDAO();		// factory �� ���� �μ��� ���� DAO ȹ��
//		Integer cfd_num = departmentDAO.getCfd_numByCfd_name(cfd_name);		// DepartmentDAO�� �̸��� ����Ͽ� cfd�� ������ �޼ҵ� ���
//		if (cfd_num == null) {
//			return 0;
//		}
//		
//		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ����
//		Object[] param = new Object[] { w.getW_id(), w.getPw(), w.getName(), 
//				w.getEmp_num(), w.getCompany_email(), c_num, cf_num, cfd_num};		
//		jdbcUtil.setSql(insertQuery);			// JDBCUtil �� insert �� ����
//		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
//				
//		try {				
//			result = jdbcUtil.executeUpdate();		// insert �� ����
//		} catch (SQLException ex) {
//			System.out.println("�Է¿��� �߻�!!!");
//			if (ex.getErrorCode() == 1)
//				System.out.println("������ ������������ �̹� �����մϴ�."); 
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
//	public int updateWorker(WorkerDTO w) {
//		String updateQuery = "UPDATE Worker SET ";
//		int index = 0;
//		Object[] tempParam = new Object[9];		// update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
//		
//		if (w.getW_id() != null) {		// id�� �����Ǿ� ���� ���
//			updateQuery += "ID = ?, ";		// update ���� �н����� ���� �κ� �߰�
//			tempParam[index++] = w.getW_id();		// �Ű������� ������ �н����� �߰�
//		}
//		if (w.getPw() != null) {		// pw�� �����Ǿ� ���� ���
//			updateQuery += "PW = ?, ";		// update ���� �޴��� ���� �κ� �߰�
//			tempParam[index++] = w.getPw();		// �Ű������� ������ �޴��� �߰�
//		}
//		if (w.getName() != null) {		// �̸��� �����Ǿ� ���� ���
//			updateQuery += "NAME = ?, ";		// update ���� �г� ���� �κ� �߰�
//			tempParam[index++] = w.getName();		// �Ű������� ������ �г� �߰�
//		}		
//		if (w.getEmp_num() != null) {		// �����ȣ�� �����Ǿ� ���� ���
//			updateQuery += "EMP_NUM = ?, ";		// update ���� �����ȣ ���� �κ� �߰�
//			tempParam[index++] = w.getEmp_num();		// �Ű������� ������ �����ȣ �߰�
//		}
//		if (w.getCompany_email() != null) {		// EMAIL�� �����Ǿ� ���� ���
//			updateQuery += "COMPANY_EMAIL = ?, ";		// update ���� EMAIL ���� �κ� �߰�
//			tempParam[index++] = w.getCompany_email();		// �Ű������� ������ EMAIL �߰�
//		}
//		if (w.getMatching_result() != null) {		// ��Ī����� �����Ǿ� ���� ���
//			updateQuery += "MATCHING_RESULT = ?, ";		// update ���� ��Ī��� ���� �κ� �߰�
//			tempParam[index++] = w.getMatching_result();		// �Ű������� ������ ��Ī��� �߰�
//		}
//		if (w.getC_num() != null) {		// ȸ�簡 �����Ǿ� ���� ���
//			updateQuery += "C_NUM = ?, ";		// update ���� ȸ�� ���� �κ� �߰�
//			tempParam[index++] = w.getC_num();		// �Ű������� ������ ȸ�� �߰�
//		}
//		if (w.getCf_num() != null) {		// �ʵ尡 �����Ǿ� ���� ���
//			updateQuery += "CF_NUM = ?, ";		// update ���� �ʵ� ���� �κ� �߰�
//			tempParam[index++] = w.getCf_num();		// �Ű������� ������ �ʵ� �߰�
//		}
//		if (w.getCfd_num() != null) {		// �μ��� �����Ǿ� ���� ���
//			updateQuery += "CFD_NUM = ?, ";		// update ���� �μ� ���� �κ� �߰�
//			tempParam[index++] = w.getCfd_num();		// �Ű������� ������ �μ� �߰�
//		}
//		updateQuery += "WHERE id = ? ";		// update ���� ���� ����
//		updateQuery = updateQuery.replace(", WHERE", " WHERE");		// update ���� where �� �տ� ���� �� �ִ� , ����
//		
//		tempParam[index++] = w.getW_id();		// ã�� ���ǿ� �ش��ϴ� �й��� ���� �Ű����� �߰�
//		
//		Object[] newParam = new Object[index];
//		for (int i=0; i < newParam.length; i++)		// �Ű������� ������ŭ�� ũ�⸦ ���� �迭�� �����ϰ� �Ű����� �� ����
//			newParam[i] = tempParam[i];
//		
//		jdbcUtil.setSql(updateQuery);			// JDBCUtil�� update �� ����
//		jdbcUtil.setParameters(newParam);		// JDBCUtil �� �Ű����� ����
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
//	public int deleteWorker(int w_id) {
//		jdbcUtil.setSql(deleteQuery);			// JDBCUtil �� query �� ����
//		Object[] param = new Object[] {w_id};
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
//	
//	// ������������ �̸����� ã��
//	public WorkerDTO getWorkerByName(String name) {
//				String searchQuery = query + ", " + "c_num AS c_num, " +
//						  							"cf_num AS cf_num, " +
//						  							"cfd_num AS cfd_num " + 
//						  							"FROM worker " +
//						  							"WHERE name = ? ";	 
//				jdbcUtil.setSql(searchQuery);				// JDBCUtil �� query �� ����
//				Object[] param = new Object[] { name };		// �����ڸ� ã�� ���� �������� �̸��� ����
//				jdbcUtil.setParameters(param);				// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
//						
//						try {
//							ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
//							WorkerDTO pt = null;
//							if (rs.next()) {						// ã�� �������� ������ StudentDTO ��ü�� ����
//								pt = new WorkerDTO();
//								pt.setW_id(rs.getString("id"));
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
//						return null;
//	}
//	
//	// ������������ id�� ã��
//	public WorkerDTO getWorkerById(String w_id) {
//		// �⺻ ������ ������ ȸ�����̺��� ȸ���, �ʵ����̺��� �ʵ��, �μ����̺��� �μ����� �������� ���̺�
//		String searchQuery = query + ", " + "c_num AS c_num, " +
//				  							"cf_num AS cf_num " +
//				  							"cfd_num AS cfd_num " + 
//				  							"FROM worker " +
//				  							"WHERE id = ? ";
//		jdbcUtil.setSql(searchQuery);				// JDBCUtil �� query �� ����
//		Object[] param = new Object[] { w_id };		// �л��� ã�� ���� �������� id�� ����
//		jdbcUtil.setParameters(param);				// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
//				
//				try {
//					ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
//					WorkerDTO w = null;
//					if (rs.next()) {						// ã�� �л��� ������ StudentDTO ��ü�� ����
//						w = new WorkerDTO();
//						w.setW_id(rs.getString("id"));
//						w.setPw(rs.getString("pw"));
//						w.setName(rs.getString("name"));
//						w.setEmp_num(rs.getInt("emp_num"));
//						w.setCompany_email(rs.getString("company_email"));
//						w.setMatching_result(rs.getInt("matching_result"));
//						w.setC_num(rs.getInt("c_num"));
//						w.setCf_num(rs.getInt("cf_num"));
//						w.setCfd_num(rs.getInt("cfd_num"));
//					}
//					return w;				// ã�� �л��� ������ ��� �ִ� StudentDTO ��ü ��ȯ
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				} finally {
//					jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
//				}
//				return null;
//	}
//
//}
