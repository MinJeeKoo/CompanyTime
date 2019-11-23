package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User_JobSeeker;

/**
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * USERINFO ���̺��� ����� ������ �߰�, ����, ����, �˻� ���� 
 */
public class User_JobSeekerDAO {
	private JDBCUtil jdbcUtil = null;
	
	public User_JobSeekerDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	/**
	 * ����� ���� ���̺��� ���ο� ����� ����.
	 */
	public int create(User_JobSeeker user) throws SQLException {
		String sql = "INSERT INTO USERINFO (userId, name, password, school, major, email) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {user.getUserId(), user.getPassword(), 
				user.getName(), user.getSchool(), user.getMajor(), user.getEmail()};				
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
	public int update(User_JobSeeker user) throws SQLException {
		String sql = "UPDATE USERINFO "
					+ "SET password=?, name=?, school=?, major=?, email=? "
					+ "WHERE userid=?";
		Object[] param = new Object[] {user.getPassword(), user.getName(), user.getSchool(), 
				user.getMajor(), user.getEmail(), user.getUserId()};				
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
		String sql = "DELETE FROM USERINFO WHERE userid=?";		
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
	public User_JobSeeker findUser(String userId) throws SQLException {
        String sql = "SELECT password, name, school, major, email "
        			+ "FROM USERINFO "
        			+ "WHERE userid=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				User_JobSeeker user = new User_JobSeeker(		// User ��ü�� �����Ͽ� �л� ������ ����
					userId,
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("school"),
					rs.getString("major"),
					rs.getString("email"));
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
	public List<User_JobSeeker> findUserList() throws SQLException {
        String sql = "SELECT userId, password, name, email " 
        		   + "FROM USERINFO "
        		   + "ORDER BY userId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<User_JobSeeker> userList = new ArrayList<User_JobSeeker>();	// User���� ����Ʈ ����
			while (rs.next()) {
				User_JobSeeker user = new User_JobSeeker(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getString("userId"),
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("school"),
					rs.getString("major"),
					rs.getString("email"));	
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
	public List<User_JobSeeker> findUserList(int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT userId, password, name, school, major, email " 
        		   + "FROM USERINFO "
        		   + "ORDER BY userId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
				List<User_JobSeeker> userList = new ArrayList<User_JobSeeker>();	// User���� ����Ʈ ����
				do {
					User_JobSeeker user = new User_JobSeeker(		// User ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("school"),
						rs.getString("major"),
						rs.getString("email"));	
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
		String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";      
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