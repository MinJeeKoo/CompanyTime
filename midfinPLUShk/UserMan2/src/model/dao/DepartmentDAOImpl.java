package model.dao;

import java.util.List;
import model.DepartmentDTO;
import java.util.ArrayList;
import java.sql.*;
import model.dao.DepartmentDAO;
import model.dao.FieldDAO;

public class DepartmentDAOImpl implements DepartmentDAO{

	JDBCUtil jdbcUtil = null;
	
	public DepartmentDAOImpl(){
		jdbcUtil = new JDBCUtil();
	}
	/*ȸ�縶�� DEPARTMENT�� ����� ���ΰ�,,,,?�̻��ϴ�..
	 private Integer CFD_NUM = null;
	private Integer CF_NUM = null;
	private String CFD_NAME = null;
	private Integer ANNULAL_INCOME = null;....?
	 */
	
	@Override
	public List<DepartmentDTO> getDepartmentList() {
		String query = "SELECT CFD_NUM, CF_NUM, CFD_NAME FROM DEPARTMENT; ";
		jdbcUtil.setSqlAndParameters(query, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<DepartmentDTO> list = new ArrayList<DepartmentDTO>();
			while(rs.next()) {
				DepartmentDTO dto = new DepartmentDTO();
				dto.setCFD_NUM(rs.getInt("CFD_NUM"));
				dto.setCF_NUM(rs.getInt("CF_NUM"));
				dto.setCFD_NAME(rs.getString("CFD_NAME"));
				list.add(dto);
			}
			return list;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			jdbcUtil.close();
		}
		return null;
	}
	
	@Override
	public Integer getCFD_NUMByCFD_NAME(String cfd_name) throws SQLException{
		Integer cfd_num = null;//Integer Ÿ���̶� null�� �ʱ�ȭ��.
		String query = "SELECT CFD_NUM "
				+ "FROM DEPARTMENT "
				+ "WHERE CFD_NAME = ?" ;
		
		Object[] param = new Object[] {cfd_name};
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet result = jdbcUtil.executeQuery();
			while (result.next()) {
				cfd_num = result.getInt("CFD_NUM");
			}
			return cfd_num;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			jdbcUtil.close();
		}
		return null;
	}

	//�����ϱ�
	public List<String> findDepartmentListByCf_name(String cf_name) throws SQLException {
		String query = "SELECT cfd_name FROM department WHERE cf_num = ? ";
		
		DAOFactory factory = new DAOFactory();		// ���ػ������� cf(�ʵ�)������ �˾ƿ��� ���� DAO ��ü�� �����ϴ� factory ��ü ����
//		
		// JobSeekerDAO ��ü�� �����Ͽ� ���ػ� �� ���ԵǾ� �ִ� field�� cf_num�� �˾ƿ�
		//***********CfDAO -> �̸� Ȯ�� �ʿ�, getCfById�޼ҵ� �����ϴ��� Ȯ�� �ʿ�
		FieldDAO cfDAO = factory.getFieldDAO();		
		Integer cf_num = cfDAO.getCF_NUMByCF_NAME(cf_name);	//�����������߱�	// cf_num�� ����
		
		Object[] param = new Object[] {cf_num};
		jdbcUtil.setSqlAndParameters(query, param);
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
			List<String> list = new ArrayList<String>();		// JobSeekerDTO ��ü���� ������� list ��ü
			while (rs.next()) {	
				list.add(rs.getString("cfd_name"));		// list ��ü�� ������ ������ JobSeekerDTO ��ü ����
			}
			return list;		// ���ػ������� ������ dto ���� ����� ��ȯ
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return null;	
	}
}
