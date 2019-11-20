package model.dao;

import model.dto.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ManagerDAOImpl implements ManagerDAO{
	private JDBCUtil jdbcUtil = null;

	private static String query = "SELECT Manager.mgr_num as mgr_num, " +
								"Manager.id as id, " + 
								"Manager.pw as pw ";
	public ManagerDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}
	
	public List<ManagerDTO> getManagerList(){
		String allQuery = query + ", " + "FROM Manager ORDER BY mgr_num";

	 	jdbcUtil.setSqlAndParameters(allQuery, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<ManagerDTO> list = new ArrayList<ManagerDTO>();		// StudentDTO ��ü���� ������� list ��ü
			while (rs.next()) {	
				ManagerDTO dto = new ManagerDTO();		// �ϳ��� StudentDTO ��ü ���� �� ���� ����
				dto.setMgr_num(rs.getInt("mgr_num"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				list.add(dto);		// list ��ü�� ������ ������ StudentDTO ��ü ����
			}
			return list;		// �л������� ������ dto ���� ����� ��ȯ
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return null;	
		}// ��ü �Ŵ��� ������ ȹ��
	
	// �Ŵ��������� �߰�
	public int insertManager(ManagerDTO mgr) {
		int result = 0;
		String insertQuery = "INSERT INTO MANAGER (mgr_num, id, pw) " +
							 "VALUES (sequence_MANAGER.nextval(), ?, ?) ";

		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ����
		Object[] param = new Object[] { mgr.getId(), mgr.getPw()};		
		jdbcUtil.setSqlAndParameters(insertQuery, param);			// JDBCUtil �� insert �� ��		// JDBCUtil �� �Ű����� ����
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert �� ����
			System.out.println(mgr.getMgr_num() + " �Ŵ�����ȣ�� �Ŵ��������� ���ԵǾ����ϴ�.");
		} catch (SQLException ex) {
			System.out.println("�Է¿��� �߻�!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("������ �Ŵ��������� �̹� �����մϴ�."); 
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return result;		// insert �� ���� �ݿ��� ���ڵ� �� ��ȯ	
	}
	
	// �Ŵ��������� id�� ã��
	public ManagerDTO getManagerById(String id) {
		// �⺻ ������ ������ �������̺��� ����������, �а� ���̺��� �а����� �������� ���̺�
				String searchQuery = query + ", " + "FROM Manager " +
				  							  "WHERE Manager.id = ? ";	 
				
				Object[] param = new Object[] { id };
				jdbcUtil.setSqlAndParameters(searchQuery, param);				// JDBCUtil �� query �� ����
						// �����ڸ� ã�� ���� �������� id�� ����			// JDBCUtil �� query���� �Ű����� ������ ����� �Ű����� ����
				
				try {
					ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
					ManagerDTO mgr = null;
					if (rs.next()) {						// ã�� �������� ������ StudentDTO ��ü�� ����
						mgr = new ManagerDTO();
						mgr.setMgr_num(rs.getInt("mgr_num"));
						mgr.setId(rs.getString("id"));
						mgr.setPw(rs.getString("pw"));
					}
					return mgr;				// ã�� �������� ������ ��� �ִ� StudentDTO ��ü ��ȯ
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
				}
				return null;
	}
	

	
	
		
	}		
	

