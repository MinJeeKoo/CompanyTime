package Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DAO.WorkerDTO;

public class WorkerDAOImpl implements WorkerDAO {

	private JDBCUtil jdbcUtil = null;		// JDBCUtil ��ü�� �����ϱ� ���� ����
	
	// WorkerDAO �� �⺻ ������ �����ϴ� query ��
	private static String query = "SELECT Worker.w_num AS w_num, " +
								         "Worker.id AS id, " +
								         "Worker.pw AS pw, " +
								         "Worker.name AS name, " +
								         "Worker.emp_num AS emp_num " +
								         "Worker.company_email AS company_email " +
								         "Worker.matching_result AS matching_result, ";		
		
	// WorkerDAOImpl ������
	public WorkerDAOImpl() {			
		jdbcUtil = new JDBCUtil();		// WorkerDAOImpl ��ü ���� �� JDBCUtil ��ü ����
	}
	
	// ��ü ������ ������ ȹ��
	public List<WorkerDTO> getWorkerList(){
		String allQuery = query + ", " + "w.c_num AS c_num  " + 
				"w.cf_num AS cf_num " +
				"w.cfd_num AS cfd_num " +
		    "FROM Worker AS w ORDER BY Worker.w_num ASC ";	
		
		jdbcUtil.setSql(allQuery);		// JDBCUtil �� query ����
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
			List<WorkerDTO> list = new ArrayList<WorkerDTO>();		// WorkerDTO ��ü���� ������� list ��ü
			while (rs.next()) {	
				WorkerDTO dto = new WorkerDTO();		// �ϳ��� WorkerDTO ��ü ���� �� ���� ����
			dto.setW_num(rs.getString("w_num"));
			dto.setId(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setName(rs.getString("name"));
			dto.setEmp_num(rs.getString("emp_num"));
			dto.setcompany_email(rs.getString("company_email"));
			dto.setmatching_result(rs.getString("matching_result"));
			dto.setc_num(rs.getString("c_num"));
			dto.setcf_num(rs.getString("cf_num"));
			dto.setcfd_num(rs.getString("cfd_num"));
			
			list.add(dto);		// list ��ü�� ������ ������ WorkerDTO ��ü ����
		}
			return list;		// ������ ������ ������ dto ���� ����� ��ȯ
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
			return null;	
	}
	
	// ������������ �߰�
	public int insertWorker(WorkerDTO w) {
		String insertQuery = "INSERT INTO Worker (w_num, id, pw, name, emp_num, company_email, matching_result,"
				+ "c_num, cf_num, cfd_num) " +
							 "VALUES (?, ?, ?, ?, ?, ?, 0, ?, ?, ?) ";
		
		DAOFactory factory = new DAOFactory();		// ȸ�������� �ʵ������� �μ������� �˾ƿ��� ���� DAO ��ü�� �����ϴ� factory ��ü ����
		
		// CompanyDAO ��ü�� �����Ͽ� �����غ��� ������ ���ԵǾ� �ִ� ȸ���� c_num�� �˾ƿ�
		CompanyDAO workerDAO = factory.getWorkerDAO();		// factory �� ���� ȸ�翡 ���� DAO ȹ��
		WorkerDTO workerDTO = workerDAO.getWorkerByW_num(w.getW_name());		// ȸ�� DAO �� �̸��� ����Ͽ� �����ڵ带 ������ �޼ҵ� ���
		String c_num = workerDTO.getC_num();		// ȸ���ȣ�� ����
		if (c_num == null) {
			workerDTO.setC_num(w.getC_name());
			return 0;
		}
		
		// WorkerDAO ��ü�� �����Ͽ� �����غ��� ������ ���ԵǾ� �ִ� field�� cf_num�� �˾ƿ�
		// Field�� �������� �μ��� �о߸� ����
		FieldDAO fieldDAO = factory.getFieldDAO();		// factory �� ���� �ʵ忡 ���� DAO ȹ��
		FieldDTO fieldDTO = fieldDAO.getFieldByCf_num(w.getCf_name()); // �ʵ� DAO �� �ʵ���� ����Ͽ� cf_num�� ������ �޼ҵ� ���
		String cf_num = fieldDTO.getCf_num();			// cf_num�� ����
		if (cf_num == null) {
			fieldDTO.setCf_Num(w.getCf_name());
			return 0;
		}
		
		// DepartmentDAO ��ü�� �����Ͽ� ������ ������ ���ԵǾ� �ִ� �μ��� cfd_num�� �˾ƿ�
		DepartmentDAO departmentDAO = factory.getDepartmentDAO();		// factory �� ���� �μ��� ���� DAO ȹ��
		DepartmentDTO departmentDTO = departmentDAO.getDepartmentByCfd_num(w.getCfd_name());		// DepartmentDAO�� �̸��� ����Ͽ� cfd�� ������ �޼ҵ� ���
		String cfd_num = departmentDTO.getCfd_num();		// ȸ���ȣ�� ����
		if (cfd_num == null) {
			departmentDTO.setCfd_Num(w.getCfd_name());
			return 0;
		}
		
		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ����
		Object[] param = new Object[] {w.getW_num(), w.getId(), w.getPw(), w.getName(), 
				w.getEmp_num(), w.getCompany_email(), w.getC_num(), w.getCf_num(), w.getCfd_num()};		
		jdbcUtil.setSql(insertQuery);			// JDBCUtil �� insert �� ����
		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert �� ����
			System.out.println(w.getW_num() + " �� ������ ��ȣ�� ���ԵǾ����ϴ�.");
		} catch (SQLException ex) {
			System.out.println("�Է¿��� �߻�!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("������ ������������ �̹� �����մϴ�."); 
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return result;		// insert �� ���� �ݿ��� ���ڵ� �� ��ȯ	
	}
	
	// ������������ ����
	public int updateWorker(WorkerDTO w) {
		String updateQuery = "UPDATE Worker SET ";
		int index = 0;
		Object[] tempParam = new Object[10];		// update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
		
		if (w.getId() != null) {		// id�� �����Ǿ� ���� ���
			updateQuery += "ID = ?, ";		// update ���� �н����� ���� �κ� �߰�
			tempParam[index++] = w.getId();		// �Ű������� ������ �н����� �߰�
		}
		if (w.getPw() != null) {		// pw�� �����Ǿ� ���� ���
			updateQuery += "PW = ?, ";		// update ���� �޴��� ���� �κ� �߰�
			tempParam[index++] = w.getPw();		// �Ű������� ������ �޴��� �߰�
		}
		if (w.getName() != null) {		// �̸��� �����Ǿ� ���� ���
			updateQuery += "NAME = ?, ";		// update ���� �г� ���� �κ� �߰�
			tempParam[index++] = pt.getName();		// �Ű������� ������ �г� �߰�
		}		
		if (w.getEmp_num() != null) {		// �����ȣ�� �����Ǿ� ���� ���
			updateQuery += "EMP_NUM = ?, ";		// update ���� �����ȣ ���� �κ� �߰�
			tempParam[index++] = w.getEmp_num();		// �Ű������� ������ �����ȣ �߰�
		}
		if (w.getCompany_email() != null) {		// EMAIL�� �����Ǿ� ���� ���
			updateQuery += "COMPANY_EMAIL = ?, ";		// update ���� EMAIL ���� �κ� �߰�
			tempParam[index++] = w.getCompany_email();		// �Ű������� ������ EMAIL �߰�
		}
		if (w.getMatching_result() != null) {		// ��Ī����� �����Ǿ� ���� ���
			updateQuery += "MATCHING_RESULT = ?, ";		// update ���� ��Ī��� ���� �κ� �߰�
			tempParam[index++] = w.getMatching_result();		// �Ű������� ������ ��Ī��� �߰�
		}
		if (w.getC_num() != null) {		// ȸ�簡 �����Ǿ� ���� ���
			updateQuery += "C_NUM = ?, ";		// update ���� ȸ�� ���� �κ� �߰�
			tempParam[index++] = w.getC_num();		// �Ű������� ������ ȸ�� �߰�
		}
		if (w.getCf_num() != null) {		// �ʵ尡 �����Ǿ� ���� ���
			updateQuery += "CF_NUM = ?, ";		// update ���� �ʵ� ���� �κ� �߰�
			tempParam[index++] = w.getCf_num();		// �Ű������� ������ �ʵ� �߰�
		}
		if (w.getCfd_num() != null) {		// �μ��� �����Ǿ� ���� ���
			updateQuery += "CFD_NUM = ?, ";		// update ���� �μ� ���� �κ� �߰�
			tempParam[index++] = w.getCfd_num();		// �Ű������� ������ �μ� �߰�
		}
		updateQuery += "WHERE id = ? ";		// update ���� ���� ����
		updateQuery = updateQuery.replace(", WHERE", " WHERE");		// update ���� where �� �տ� ���� �� �ִ� , ����
		
		tempParam[index++] = w.getId();		// ã�� ���ǿ� �ش��ϴ� �й��� ���� �Ű����� �߰�
		
		Object[] newParam = new Object[index];
		for (int i=0; i < newParam.length; i++)		// �Ű������� ������ŭ�� ũ�⸦ ���� �迭�� �����ϰ� �Ű����� �� ����
			newParam[i] = tempParam[i];
		
		jdbcUtil.setSql(updateQuery);			// JDBCUtil�� update �� ����
		jdbcUtil.setParameters(newParam);		// JDBCUtil �� �Ű����� ����
		
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
	public int deleteWorker(int w_num) {
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil �� query �� ����
		Object[] param = new Object[] {w_num};
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
	public WorkerDTO getWorkerByName(String name) {
		// �⺻ ������ ������ ȸ�����̺����� ȸ���, �ʵ����̺����� �ʵ��, �μ����̺����� �μ����� �������� ���̺�
				String searchQuery = query + ", " + "COMPANY.C_NAME AS company_name, " +
						  							"FIELD.CF_NAME AS field_name " +
						  							"DEPARTMENT.CFD_NAME, AS department_name " + 
						  							"FROM worker AS w, company, field, department " +
						  							"WHERE w.name = ? AND " +
						  							"pt.c_num = company.c_num AND " + 
						  							"pt.cf_num = field.cf_num AND " + 
						  							"pt.cfd_num = department.cfd_num ";	 
				jdbcUtil.setSql(searchQuery);				// JDBCUtil �� query �� ����
				Object[] param = new Object[] { name };		// �����ڸ� ã�� ���� �������� �̸��� ����
				jdbcUtil.setParameters(param);				// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
						
						try {
							ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
							WorkerDTO pt = null;
							if (rs.next()) {						// ã�� �������� ������ StudentDTO ��ü�� ����
								pt = new WorkerDTO();
								pt.setW_num(rs.getString("w_num"));
								pt.setId(rs.getString("id"));
								pt.setPw(rs.getString("pw"));
								pt.setName(rs.getString("name"));
								pt.setEmp_num(rs.getString("emp_num"));
								pt.setCompany_email(rs.getString("company_email"));
								pt.setMatching_result(rs.getString("matching_result"));
								pt.setC_name(rs.getString("company_name"));
								pt.setCf_name(rs.getString("field_name"));
								pt.setCfd_name(rs.getString("department_name"));
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
	public WorkerDTO getWorkerById(String id) {
		// �⺻ ������ ������ ȸ�����̺����� ȸ���, �ʵ����̺����� �ʵ��, �μ����̺����� �μ����� �������� ���̺�
		String searchQuery = query + ", " + "COMPANY.C_NAME AS company_name, " +
				  							"FIELD.CF_NAME AS field_name " +
				  							"DEPARTMENT.CFD_NUM, AS department_name " + 
				  							"FROM worker AS w, company, field, department " +
				  							"WHERE w.id = ? AND " +
				  							"pt.c_num = company.c_num AND " + 
				  							"pt.cf_num = field.cf_num AND " + 
				  							"pt.cfd_num = department.cfd_num ";	 
		jdbcUtil.setSql(searchQuery);				// JDBCUtil �� query �� ����
		Object[] param = new Object[] { id };		// �л��� ã�� ���� �������� id�� ����
		jdbcUtil.setParameters(param);				// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
				
				try {
					ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
					WorkerDTO w = null;
					if (rs.next()) {						// ã�� �л��� ������ StudentDTO ��ü�� ����
						w = new WorkerDTO();
						w.setW_num(rs.getString("w_num"));
						w.setId(rs.getString("id"));
						w.setPw(rs.getString("pw"));
						w.setName(rs.getString("name"));
						w.setEmp_num(rs.getString("emp_num"));
						w.setCompany_email(rs.getString("company_email"));
						w.setMatching_result(rs.getString("matching_result"));
						w.setC_name(rs.getString("company_name"));
						w.setCf_name(rs.getString("field_name"));
						w.setCfd_name(rs.getString("department_name"));
					}
					return w;				// ã�� �л��� ������ ��� �ִ� StudentDTO ��ü ��ȯ
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
				}
				return null;
	}

}