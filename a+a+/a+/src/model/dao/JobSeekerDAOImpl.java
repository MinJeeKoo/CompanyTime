//package model.dao;
//
//import model.dto.*;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class JobSeekerDAOImpl {
//	private JDBCUtil jdbcUtil = null;		// JDBCUtil ��ü�� �����ϱ� ���� ����
////	private int js_num;
////	private String id = null;			// id
////	private String pw = null; 			// pw
////	private String name = null;			// �̸�
////	private String school = null;		// �б�
////	private String major = null;		// ����
////	private String personal_email = null; // ���� �̸����ּ�
////	private int cf_num;					// �ʵ� ���� (���丵 ��� �о� ����)
////	private int matching_result;		// ���丵 ��Ī ���
//	// Student �� �⺻ ������ �����ϴ� query ��
//	private static String query = "SELECT js_id AS id, " +
//								         "pw AS pw, " +
//								         "name AS name, " +
//								         "school AS school " +
//								         "major AS major " +
//								         "personal_email AS personal_email ";		
//		
//	// StudentDAOImpl ������
//	public JobSeekerDAOImpl() {			
//		jdbcUtil = new JDBCUtil();		// StudentDAOImpl ��ü ���� �� JDBCUtil ��ü ����
//	}
//	
//	// ��ü ���ػ� ������ ȹ��	
//	public List<JobSeekerDTO> getJobSeekerList(){
//				String allQuery = query + ", " + "CF_NUM AS CF_NUM, " +
//										         "matching_result AS matching_result  " + 
//										    "FROM JobSeeker ";		
//				jdbcUtil.setSql(allQuery);		// JDBCUtil �� query ����
//				
//				try { 
//					ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
//					List<JobSeekerDTO> list = new ArrayList<JobSeekerDTO>();		// JobSeekerDTO ��ü���� ������� list ��ü
//					while (rs.next()) {	
//						JobSeekerDTO dto = new JobSeekerDTO();		// �ϳ��� JobSeekerDTO ��ü ���� �� ���� ����
//						dto.setJs_id(rs.getString("id"));
//						dto.setPw(rs.getString("pw"));
//						dto.setName(rs.getString("name"));
//						dto.setSchool(rs.getString("school"));
//						dto.setMajor(rs.getString("major"));
//						dto.setPersonal_email(rs.getString("personal_email"));
//						dto.setCf_num(rs.getInt("cf_num"));
//						dto.setMatching_result(rs.getInt("matching_result"));
//						list.add(dto);		// list ��ü�� ������ ������ JobSeekerDTO ��ü ����
//					}
//					return list;		// ���ػ������� ������ dto ���� ����� ��ȯ
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				} finally {
//					jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
//				}		
//				return null;	
//	}
//	
//	// ���ػ������� �߰�
//	// �Ű������� cfName �޾ƿ;��ϴ°žƴѰ�..?
//	public int insertJobSeeker(JobSeekerDTO js, String cf_name) {
//		int result = 0;
//		String insertQuery = "INSERT INTO JobSeeker (js_id, pw, name, school, major, personal_email, cf_num, matching_result) " +
//							 "VALUES (?, ?, ?, ?, ?, ?, ?, 0) ";
//		
//		DAOFactory factory = new DAOFactory();		// ���ػ������� cf(�ʵ�)������ �˾ƿ��� ���� DAO ��ü�� �����ϴ� factory ��ü ����
//		
//		// JobSeekerDAO ��ü�� �����Ͽ� ���ػ� �� ���ԵǾ� �ִ� field�� cf_num�� �˾ƿ�
//		//***********CfDAO -> �̸� Ȯ�� �ʿ�, getCfById�޼ҵ� �����ϴ��� Ȯ�� �ʿ�
//		FieldDAO cfDAO = factory.getFieldDAO();		
//		Integer cf_num = cfDAO.getCf_numByCf_name(cf_name);	//�����������߱�	// cf_num�� ����
//		if (cf_num == null) {
//			return 0;
//		}
//	
//		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ����
//		Object[] param = new Object[] { js.getJs_id(), js.getPw(), js.getName(), js.getSchool(), js.getMajor(), js.getPersonal_email(), cf_num};		
//		jdbcUtil.setSql(insertQuery);			// JDBCUtil �� insert �� ����
//		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
//				
//		try {				
//			result = jdbcUtil.executeUpdate();		// insert �� ����
//			
//		} catch (SQLException ex) {
//			System.out.println("�Է¿��� �߻�!!!");
//			if (ex.getErrorCode() == 1)
//				System.out.println("������ ���ػ������� �̹� �����մϴ�."); 
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
//	// ���ػ������� ����
//	public int updateJobSeeker(JobSeekerDTO js) {
//		String updateQuery = "UPDATE JobSeeker SET ";
//		int index = 0;
//		Object[] tempParam = new Object[8];		// update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
//		
//		if (js.getName() != null) {		// �̸��� �����Ǿ� ���� ���
//			updateQuery += "NAME = ?, ";		// update ���� �̸� ���� �κ� �߰�
//			tempParam[index++] = js.getName();		// �Ű������� ������ �̸� �߰�
//		}
//		if (js.getJs_id() != null) {		// id�� �����Ǿ� ���� ���
//			updateQuery += "js_id = ?, ";		// update ���� �н����� ���� �κ� �߰�
//			tempParam[index++] = js.getJs_id();		// �Ű������� ������ �н����� �߰�
//		}
//		if (js.getPw() != null) {		// pw�� �����Ǿ� ���� ���
//			updateQuery += "PW = ?, ";		// update ���� �޴��� ���� �κ� �߰�
//			tempParam[index++] = js.getPw();		// �Ű������� ������ �޴��� �߰�
//		}
//		if (js.getName() != null) {		// �̸��� �����Ǿ� ���� ���
//			updateQuery += "NAME = ?, ";		// update ���� �г� ���� �κ� �߰�
//			tempParam[index++] = js.getName();		// �Ű������� ������ �г� �߰�
//		}
//		if (js.getSchool() != null) {		// �б��� �����Ǿ� ���� ���
//			updateQuery += "SCHOOL = ?, ";		// update ���� �������� ���� �κ� �߰�
//			tempParam[index++] = js.getSchool();		// �Ű������� ������ ���������ڵ� �߰�
//		}
//		if (js.getMajor() != null) {		// ������ �����Ǿ� ���� ���
//			updateQuery += "MAJOR = ?, ";		// update ���� �а� ���� �κ� �߰�
//			tempParam[index++] = js.getMajor();		// �Ű������� ������ �а��ڵ� �߰�
//		}
//		if (js.getPersonal_email() != null) {		// �����̸����� �����Ǿ� ���� ���
//			updateQuery += "PERSONAL_EMAIL = ?, ";		// update ���� �а� ���� �κ� �߰�
//			tempParam[index++] = js.getPersonal_email();		// �Ű������� ������ �а��ڵ� �߰�
//		}
//		if (js.getCf_num() != null) {		// �ʵ尡 �����Ǿ� ���� ���
//			updateQuery += "CF_NUM = ?, ";		// update ���� �ʵ� ���� �κ� �߰�
//			tempParam[index++] = js.getCf_num();		// �Ű������� ������ �ʵ� �߰�
//		}
//		updateQuery += "WHERE js_id = ? ";		// update ���� ���� ����
//		updateQuery = updateQuery.replace(", WHERE", " WHERE");		// update ���� where �� �տ� ���� �� �ִ� , ����
//		
//		tempParam[index++] = js.getJs_id();		// ã�� ���ǿ� �ش��ϴ� �й��� ���� �Ű����� �߰�
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
//	// ���ػ������� ����
//	public int deleteJobSeeker(int js_id) {
//		String deleteQuery = "DELETE FROM JobSeeker WHERE js_id = ?";
//		
//		jdbcUtil.setSql(deleteQuery);			// JDBCUtil �� query �� ����
//		Object[] param = new Object[] {js_id};
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
//	// ���ػ� ������ �̸����� ã��
//	public JobSeekerDTO getJobSeekerByName(String name) {
//		String searchQuery = query + ", " + "FROM JobSeeker " +
//			  "WHERE NAME = ? ";	 
//		jdbcUtil.setSql(searchQuery);				// JDBCUtil �� query �� ����
//		Object[] param = new Object[] { name };		// ���ػ��� ã�� ���� �������� �̸��� ����
//		jdbcUtil.setParameters(param);				// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
//		
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
//			JobSeekerDTO js = null;
//			if (rs.next()) {						// ã�� ���ػ��� ������ StudentDTO ��ü�� ����
//			js = new JobSeekerDTO();
//			js.setJs_id(rs.getString("id"));
//			js.setPw(rs.getString("pw"));
//			js.setName(rs.getString("name"));
//			js.setSchool(rs.getString("school"));
//			js.setMajor(rs.getString("major"));
//			js.setPersonal_email(rs.getString("personal_email"));
//			}
//			return js;				// ã�� ���ػ��� ������ ��� �ִ� StudentDTO ��ü ��ȯ
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
//		}
//		return null;
//		
//	}
//	
//	// ���ػ������� id�� ã��
//	public JobSeekerDTO getJobSeekerById(String js_id) {
//		String searchQuery = query + ", " + "FROM JobSeeker " +
//				  "WHERE id = ? ";	 
//			jdbcUtil.setSql(searchQuery);				// JDBCUtil �� query �� ����
//			Object[] param = new Object[] { js_id };		// ���ػ��� ã�� ���� �������� �̸��� ����
//			jdbcUtil.setParameters(param);				// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
//			
//			try {
//				ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
//				JobSeekerDTO js = null;
//				if (rs.next()) {						// ã�� ���ػ��� ������ StudentDTO ��ü�� ����
//				js = new JobSeekerDTO();
//				js.setJs_id(rs.getString("id"));
//				js.setPw(rs.getString("pw"));
//				js.setName(rs.getString("name"));
//				js.setSchool(rs.getString("school"));
//				js.setMajor(rs.getString("major"));
//				js.setPersonal_email(rs.getString("personal_email"));
//				}
//				return js;				// ã�� ���ػ��� ������ ��� �ִ� StudentDTO ��ü ��ȯ
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			} finally {
//				jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
//			}
//			return null;
//	}
//
//}
