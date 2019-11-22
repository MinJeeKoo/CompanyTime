package model.dao;

import model.dto.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobSeekerDAOImpl {
	private JDBCUtil jdbcUtil = null;		// JDBCUtil ��ü�� �����ϱ� ���� ����
//	private int js_num;
//	private String id = null;			// id
//	private String pw = null; 			// pw
//	private String name = null;			// �̸�
//	private String school = null;		// �б�
//	private String major = null;		// ����
//	private String personal_email = null; // ���� �̸����ּ�
//	private int cf_num;					// �ʵ� ���� (���丵 ��� �о� ����)
//	private int matching_result;		// ���丵 ��Ī ���
	// Student �� �⺻ ������ �����ϴ� query ��
	private static String query = "SELECT js_id AS id, " +
								         "pw AS pw, " +
								         "name AS name, " +
								         "school AS school " +
								         "major AS major " +
								         "personal_email AS personal_email ";		
		
	// StudentDAOImpl ������
	public JobSeekerDAOImpl() {			
		jdbcUtil = new JDBCUtil();		// StudentDAOImpl ��ü ���� �� JDBCUtil ��ü ����
	}
	
	// ��ü ���ػ� ������ ȹ��	
	public List<JobSeekerDTO> getJobSeekerList(){
				String allQuery = query + ", " + "CF_NUM AS CF_NUM, " +
										         "matching_result AS matching_result  " + 
										    "FROM JobSeeker ";		
				jdbcUtil.setSqlAndParameters(allQuery, null);		// JDBCUtil �� query ����
				
				try { 
					ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
					List<JobSeekerDTO> list = new ArrayList<JobSeekerDTO>();		// JobSeekerDTO ��ü���� ������� list ��ü
					while (rs.next()) {	
						JobSeekerDTO dto = new JobSeekerDTO();		// �ϳ��� JobSeekerDTO ��ü ���� �� ���� ����
						dto.setJs_id(rs.getString("id"));
						dto.setPw(rs.getString("pw"));
						dto.setName(rs.getString("name"));
						dto.setSchool(rs.getString("school"));
						dto.setMajor(rs.getString("major"));
						dto.setPersonal_email(rs.getString("personal_email"));
						dto.setCf_num(rs.getInt("cf_num"));
						dto.setMatching_result(rs.getInt("matching_result"));
						list.add(dto);		// list ��ü�� ������ ������ JobSeekerDTO ��ü ����
					}
					return list;		// ���ػ������� ������ dto ���� ����� ��ȯ
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
				}		
				return null;	
	}
	
	// ���ػ������� �߰�
	// �Ű������� cfName �޾ƿ;��ϴ°žƴѰ�..?
	public int insertJobSeeker(JobSeekerDTO js, String cf_name) {
		int result = 0;
		String insertQuery = "INSERT INTO JobSeeker (js_id, pw, name, school, major, personal_email, cf_num, matching_result) " +
							 "VALUES (?, ?, ?, ?, ?, ?, ?, 0) ";
		
		DAOFactory factory = new DAOFactory();		// ���ػ������� cf(�ʵ�)������ �˾ƿ��� ���� DAO ��ü�� �����ϴ� factory ��ü ����
		
		// JobSeekerDAO ��ü�� �����Ͽ� ���ػ� �� ���ԵǾ� �ִ� field�� cf_num�� �˾ƿ�
		//***********CfDAO -> �̸� Ȯ�� �ʿ�, getCfById�޼ҵ� �����ϴ��� Ȯ�� �ʿ�
		FieldDAO cfDAO = factory.getFieldDAO();		
		Integer cf_num = cfDAO.getCF_NUMByCF_NAME(cf_name);	//�����������߱�	// cf_num�� ����
		if (cf_num == null) {
			return 0;
		}
	
		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ����
		Object[] param = new Object[] { js.getJs_id(), js.getPw(), js.getName(), js.getSchool(), js.getMajor(), js.getPersonal_email(), cf_num};		
		jdbcUtil.setSqlAndParameters(insertQuery, param);			// JDBCUtil �� insert �� ����		// JDBCUtil �� �Ű����� ����
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert �� ����
			
		} catch (SQLException ex) {
			System.out.println("�Է¿��� �߻�!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("������ ���ػ������� �̹� �����մϴ�."); 
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return result;		// insert �� ���� �ݿ��� ���ڵ� �� ��ȯ	
	}
	
	// ���ػ������� ����
	public int updateJobSeeker(JobSeekerDTO js) {
		String updateQuery = "UPDATE JobSeeker SET "
							+ "pw = ?, name = ?, school = ?, major = ?, personal_email = ?, "
							+ "cf_num = ?, matching_result = ? "
							+ "WHERE js_id = ? ";
		Object[] param = new Object[] {js.getPw(), js.getName(), js.getSchool(), js.getMajor(),
									js.getPersonal_email(), js.getCf_num(), js.getMatching_result(), 
									js.getJs_id()};
				// update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
		jdbcUtil.setSqlAndParameters(updateQuery, param);
		
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
	
	// ���ػ������� ����
	public int deleteJobSeeker(int js_id) {
		String deleteQuery = "DELETE FROM JobSeeker WHERE js_id = ?";
		
		Object[] param = new Object[] {js_id};
		jdbcUtil.setSqlAndParameters(deleteQuery, param);			// JDBCUtil �� query �� ����
		// JDBCUtil �� �Ű����� ����
		
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
	
	// ���ػ� ������ �̸����� ã��
	public JobSeekerDTO getJobSeekerByName(String name) {
		String searchQuery = query + ", " + "FROM JobSeeker " +
			  "WHERE NAME = ? ";	
		
		Object[] param = new Object[] { name };	
		jdbcUtil.setSqlAndParameters(searchQuery, param);				// JDBCUtil �� query �� ����			// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
			JobSeekerDTO js = null;
			if (rs.next()) {						// ã�� ���ػ��� ������ StudentDTO ��ü�� ����
			js = new JobSeekerDTO();
			js.setJs_id(rs.getString("id"));
			js.setPw(rs.getString("pw"));
			js.setName(rs.getString("name"));
			js.setSchool(rs.getString("school"));
			js.setMajor(rs.getString("major"));
			js.setPersonal_email(rs.getString("personal_email"));
			}
			return js;				// ã�� ���ػ��� ������ ��� �ִ� StudentDTO ��ü ��ȯ
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}
		return null;
		
	}
	
	// ���ػ������� id�� ã��
	public JobSeekerDTO getJobSeekerById(String js_id) {
		String searchQuery = query + ", " + "FROM JobSeeker " +
				  "WHERE id = ? ";	 
		
		Object[] param = new Object[] { js_id };
		jdbcUtil.setSqlAndParameters(searchQuery, param);				// JDBCUtil �� query �� ����
				// ���ػ��� ã�� ���� �������� �̸��� ����			// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
				JobSeekerDTO js = null;
				if (rs.next()) {						// ã�� ���ػ��� ������ StudentDTO ��ü�� ����
				js = new JobSeekerDTO();
				js.setJs_id(rs.getString("id"));
				js.setPw(rs.getString("pw"));
				js.setName(rs.getString("name"));
				js.setSchool(rs.getString("school"));
				js.setMajor(rs.getString("major"));
				js.setPersonal_email(rs.getString("personal_email"));
				}
				return js;				// ã�� ���ػ��� ������ ��� �ִ� StudentDTO ��ü ��ȯ
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
			}
			return null;
	}

}