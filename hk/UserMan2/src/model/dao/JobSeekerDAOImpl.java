package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.JobSeekerDTO;

/**
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * USERINFO ���̺� ����� ������ �߰�, ����, ����, �˻� ���� 
 */
public class JobSeekerDAOImpl {
	private JDBCUtil jdbcUtil = null;
	
	public JobSeekerDAOImpl() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	/**
	 * ����� ���� ���̺� ���ο� ����� ����.
	 */
	public int create(JobSeekerDTO user) throws SQLException {
		String sql = "INSERT INTO Job_Seeker (js_id, name, pw, school, major, personal_email, cf_num, matching_result) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {user.getUserId(), user.getName(), user.getPassword(),
				user.getSchool(), user.getMajor(), user.getEmail(), user.getCf_num(), user.getMatching_result()};				
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
	public int update(JobSeekerDTO user) throws SQLException {
		String sql = "UPDATE Job_Seeker "
					+ "SET js_id=?, pw=?, name=?, "
					+ "school=?, major=?, personal_email=?, cf_num=?, matching_result=? "
					+ "WHERE userid=?";
		Object[] param = new Object[] {user.getUserId(), user.getPassword(), user.getName(), 
				user.getSchool(), user.getMajor(), user.getEmail(), user.getCf_num(), user.getMatching_result()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
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

	/**
	 * ����� ID�� �ش��ϴ� ����ڸ� ����.
	 */
	public int remove(String userId) throws SQLException {
		String sql = "DELETE FROM Job_Seeker WHERE js_id=?";		
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

	/**
	 * �־��� ����� ID�� �ش��ϴ� ����� ������ �����ͺ��̽����� ã�� User ������ Ŭ������ 
	 * �����Ͽ� ��ȯ.
	 */
	public JobSeekerDTO findUser(String userId) throws SQLException {
        String sql = "SELECT pw, name, school, major, personal_email, cf_num, matching_result "
        			+ "FROM Job_Seeker "
        			+ "WHERE js_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				JobSeekerDTO user = new JobSeekerDTO(		// User ��ü�� �����Ͽ� �л� ������ ����
					userId,
					rs.getString("pw"),
					rs.getString("name"),
					rs.getString("school"),
					rs.getString("major"),
					rs.getString("personal_email"),
					rs.getInt("cf_num"), 
					Integer.valueOf(0)
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

	/**
	 * ��ü ����� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<JobSeekerDTO> findUserList() throws SQLException {
        String sql = "SELECT js_id, pw, name, school, major, personal_email, cf_num, matching_result "
        		   + "FROM Job_Seeker "
        		   + "ORDER BY js_id";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<JobSeekerDTO> userList = new ArrayList<JobSeekerDTO>();	// User���� ����Ʈ ����
			while (rs.next()) {
				JobSeekerDTO user = new JobSeekerDTO(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getString("js_id"),
					rs.getString("pw"),
					rs.getString("name"),
					rs.getString("school"),
					rs.getString("major"),
					rs.getString("personal_email"),
					rs.getInt("cf_num"), 
					Integer.valueOf(0) );				
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
	public List<JobSeekerDTO> findUserList(int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT js_id, pw, name, school, major, personal_email " 
        		   + "FROM Job_Seeker "
        		   + "ORDER BY js_id";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
				List<JobSeekerDTO> userList = new ArrayList<JobSeekerDTO>();	// User���� ����Ʈ ����
				do {
					JobSeekerDTO user = new JobSeekerDTO(		// User ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getString("js_id"),
						rs.getString("pw"),
						rs.getString("name"),
						rs.getString("school"),
						rs.getString("major"),
						rs.getString("personal_email"),
						rs.getInt("cf_num"), 
						Integer.valueOf(0) );
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
	 * �־��� ����� ID�� �ش��ϴ� ����ڰ� �����ϴ��� �˻� 
	 */
	public boolean existingUser(String userId) throws SQLException {
		String sql = "SELECT count(*) FROM Job_Seeker WHERE js_id=?";      
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
}
