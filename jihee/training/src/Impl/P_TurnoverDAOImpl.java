package Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DAO.P_TurnoverDTO;
import persistence.DAOFactory;
import persistence.dao.DeptDAO;
import persistence.dao.ProfDAO;
import service.dto.DeptDTO;
import service.dto.ProfDTO;
import service.dto.StudentDTO;

public class P_TurnoverDAOImpl implements P_TurnoverDAO {
	private JDBCUtil jdbcUtil = null;		// JDBCUtil ��ü�� �����ϱ� ���� ����
	
	// P_Turnover �� �⺻ ������ �����ϴ� query ��
	private static String query = "SELECT Preparation_for_Turnover.p_num AS p_num, " +
								         "Preparation_for_Turnover.id AS id, " +
								         "Preparation_for_Turnover.pw AS pw, " +
								         "Preparation_for_Turnover.name AS name, " +
								         "Preparation_for_Turnover.emp_num AS emp_num " +
								         "Preparation_for_Turnover.company_email AS company_email " +
								         "Preparation_for_Turnover.matching_result AS matching_result, ";		
		
	// P_TurnoverDAOImpl ������
	public P_TurnoverDAOImpl() {			
		jdbcUtil = new JDBCUtil();		// P_TurnoverDAOImpl ��ü ���� �� JDBCUtil ��ü ����
	}
	
	// ��ü �����غ��� ������ ȹ��
	public List<P_TurnoverDTO> getP_TurnoverList(){
		String allQuery = query + ", " + "pt.c_num AS c_num  " + 
				"pt.cf_num AS cf_num " +
				"pt.cfd_num AS cfd_num " +
		    "FROM Preparation_for_Turnover AS pt ORDER BY P_Turnover.p_num ASC ";	
		
	jdbcUtil.setSql(allQuery);		// JDBCUtil �� query ����
	
	try { 
		ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
		List<P_TurnoverDTO> list = new ArrayList<P_TurnoverDTO>();		// P_TurnoverDTO ��ü���� ������� list ��ü
		while (rs.next()) {	
			P_TurnoverDTO dto = new P_TurnoverDTO();		// �ϳ��� P_TurnoverDTO ��ü ���� �� ���� ����
		dto.setP_num(rs.getString("p_num"));
		dto.setId(rs.getString("id"));
		dto.setPw(rs.getString("pw"));
		dto.setName(rs.getString("name"));
		dto.setEmp_num(rs.getString("emp_num"));
		dto.setcompany_email(rs.getString("company_email"));
		dto.setmatching_result(rs.getString("matching_result"));
		dto.setc_num(rs.getString("c_num"));
		dto.setcf_num(rs.getString("cf_num"));
		dto.setcfd_num(rs.getString("cfd_num"));
		
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
	public int insertP_Turnover(training.P_TurnoverDTO pt) {
		int result = 0;
		//c_num, c_name �� �� �ʿ�
		//c_num�� fk��
		//c_name�� c_num�� ���ϱ� ���� string����
		//company���̺� �Է��� c_name�� ������ c_num�� sequence�� �����ϰ�, c_name���� �߰�
		//company���̺� �Է��� c_name�� ������ c_num�� ������ pt�� �Է�
		//c_num�� �Է¹����� ���ʷ� �Է� ���� c_num�� ������ ����� ��, � ȸ���̸����� ����� ���� ����.
		//companyfield�� c_num�� primarykey��
		
		String insertQuery = "INSERT INTO Preparation_for_Turnover (p_num, id, pw, name, emp_num, company_email, matching_result,"
				+ "c_num, cf_num, cfd_num) " +
							 "VALUES (?, ?, ?, ?, ?, ?, 0, ?, ?, ?) ";
		
		DAOFactory factory = new DAOFactory();		// ȸ�������� �ʵ������� �μ������� �˾ƿ��� ���� DAO ��ü�� �����ϴ� factory ��ü ����
		
		// CompanyDAO ��ü�� �����Ͽ� �����غ��� ������ ���ԵǾ� �ִ� ȸ���� c_num�� �˾ƿ�
		CompanyDAO companyDAO = factory.getCompanyDAO();		// factory �� ���� ȸ�翡 ���� DAO ȹ��
		CompanyDTO companyDTO = companyDAO.getCompanyByC_num(pt.getC_name());		// ȸ�� DAO �� �̸��� ����Ͽ� �����ڵ带 ������ �޼ҵ� ���
		String c_num = companyDTO.getC_num();		// ȸ���ȣ�� ����
		if (c_num == null) {
			companyDTO.setC_Num(pt.getC_name());
			return 0;
		}
		
		// FieldDAO ��ü�� �����Ͽ� �����غ��� ������ ���ԵǾ� �ִ� field�� cf_num�� �˾ƿ�
		// Field�� �����ϰ��� �ϴ� �о߸� ����
		FieldDAO fieldDAO = factory.getFieldDAO();		// factory �� ���� �ʵ忡 ���� DAO ȹ��
		FieldDTO fieldDTO = fieldDAO.getFieldByCf_num(pt.getCf_name()); // �ʵ� DAO �� �ʵ���� ����Ͽ� cf_num�� ������ �޼ҵ� ���
		String cf_num = fieldDTO.getCf_num();			// cf_num�� ����
		if (cf_num == null) {
			fieldDTO.setCf_Num(pt.getCf_name());
			return 0;
		}
		
		// DepartmentDAO ��ü�� �����Ͽ� �����غ��� ������ ���ԵǾ� �ִ� �μ��� cfd_num�� �˾ƿ�
		DepartmentDAO departmentDAO = factory.getDepartmentDAO();		// factory �� ���� �μ��� ���� DAO ȹ��
		DepartmentDTO departmentDTO = departmentDAO.getDepartmentByCfd_num(pt.getCfd_name());		// departmentDAO �� �̸��� ����Ͽ� cfd�� ������ �޼ҵ� ���
		String cfd_num = departmentDTO.getCfd_num();		// �μ���ȣ�� ����
		if (cfd_num == null) {
			departmentDTO.setCfd_Num(pt.getCfd_name());
			return 0;
		}
		
		// query ���� ����� �Ű����� ���� ���� �Ű����� �迭 ����
		Object[] param = new Object[] {pt.getP_num(), pt.getId(), pt.getPw(), pt.getName(), 
				pt.getEmp_num(), pt.getCompany_email(), pt.getC_num(), pt.getCf_num(), pt.getCfd_num()};		
		jdbcUtil.setSql(insertQuery);			// JDBCUtil �� insert �� ����
		jdbcUtil.setParameters(param);			// JDBCUtil �� �Ű����� ����
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert �� ����
			System.out.println(pt.getP_num() + " �й��� �����غ��������� ���ԵǾ����ϴ�.");
		} catch (SQLException ex) {
			System.out.println("�Է¿��� �߻�!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("������ �����غ��������� �̹� �����մϴ�."); 
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
	public int updateP_Turnover(P_TurnoverDTO pt) {
		String updateQuery = "UPDATE Preparation_for_Turnover SET ";
		int index = 0;
		Object[] tempParam = new Object[10];		// update ���� ����� �Ű������� ������ �� �ִ� �ӽ� �迭
		
		if (pt.getId() != null) {		// id�� �����Ǿ� ���� ���
			updateQuery += "ID = ?, ";		// update ���� �н����� ���� �κ� �߰�
			tempParam[index++] = pt.getId();		// �Ű������� ������ �н����� �߰�
		}
		if (pt.getPw() != null) {		// pw�� �����Ǿ� ���� ���
			updateQuery += "PW = ?, ";		// update ���� �޴��� ���� �κ� �߰�
			tempParam[index++] = pt.getPw();		// �Ű������� ������ �޴��� �߰�
		}
		if (pt.getName() != null) {		// �̸��� �����Ǿ� ���� ���
			updateQuery += "NAME = ?, ";		// update ���� �г� ���� �κ� �߰�
			tempParam[index++] = pt.getName();		// �Ű������� ������ �г� �߰�
		}		
		if (pt.getEmp_num() != null) {		// �����ȣ�� �����Ǿ� ���� ���
			updateQuery += "EMP_NUM = ?, ";		// update ���� �����ȣ ���� �κ� �߰�
			tempParam[index++] = pt.getEmp_num();		// �Ű������� ������ �����ȣ �߰�
		}
		if (pt.getCompany_email() != null) {		// EMAIL�� �����Ǿ� ���� ���
			updateQuery += "COMPANY_EMAIL = ?, ";		// update ���� EMAIL ���� �κ� �߰�
			tempParam[index++] = pt.getCompany_email();		// �Ű������� ������ EMAIL �߰�
		}
		if (pt.getMatching_result() != null) {		// ��Ī����� �����Ǿ� ���� ���
			updateQuery += "MATCHING_RESULT = ?, ";		// update ���� ��Ī��� ���� �κ� �߰�
			tempParam[index++] = pt.getMatching_result();		// �Ű������� ������ ��Ī��� �߰�
		}
		if (pt.getC_num() != null) {		// ȸ�簡 �����Ǿ� ���� ���
			updateQuery += "C_NUM = ?, ";		// update ���� ȸ�� ���� �κ� �߰�
			tempParam[index++] = pt.getC_num();		// �Ű������� ������ ȸ�� �߰�
		}
		if (pt.getCf_num() != null) {		// �ʵ尡 �����Ǿ� ���� ���
			updateQuery += "CF_NUM = ?, ";		// update ���� �ʵ� ���� �κ� �߰�
			tempParam[index++] = pt.getCf_num();		// �Ű������� ������ �ʵ� �߰�
		}
		if (pt.getCfd_num() != null) {		// �μ��� �����Ǿ� ���� ���
			updateQuery += "CFD_NUM = ?, ";		// update ���� �μ� ���� �κ� �߰�
			tempParam[index++] = pt.getCf_num();		// �Ű������� ������ �μ� �߰�
		}
		updateQuery += "WHERE id = ? ";		// update ���� ���� ����
		updateQuery = updateQuery.replace(", WHERE", " WHERE");		// update ���� where �� �տ� ���� �� �ִ� , ����
		
		tempParam[index++] = pt.getId();		// ã�� ���ǿ� �ش��ϴ� id�� ���� �Ű����� �߰�
		
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
	public int deleteP_Turnover(int p_num) {
String deleteQuery = "DELETE FROM P_Turnover WHERE P_NUM = ?";
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil �� query �� ����
		Object[] param = new Object[] {p_num};
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
	public P_TurnoverDTO getP_TurnoverByName(String name) {
		// �⺻ ������ ������ ȸ�����̺��� ȸ���, �ʵ����̺��� �ʵ��, �μ����̺��� �μ����� �������� ���̺�
		String searchQuery = query + ", " + "COMPANY.C_NAME AS company_name, " +
				  							"FIELD.CF_NAME AS field_name " +
				  							"DEPARTMENT.CFD_NAME, AS department_name " + 
				  							"FROM preparation_for_turnover AS pt, company, field, department " +
				  							"WHERE pt.name = ? AND " +
				  							"pt.c_num = company.c_num AND " + 
				  							"pt.cf_num = field.cf_num AND " + 
				  							"pt.cfd_num = department.cfd_num ";	 
		jdbcUtil.setSql(searchQuery);				// JDBCUtil �� query �� ����
		Object[] param = new Object[] { name };		// �����ڸ� ã�� ���� �������� �̸��� ����
		jdbcUtil.setParameters(param);				// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
				
				try {
					ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
					P_TurnoverDTO pt = null;
					if (rs.next()) {						// ã�� �������� ������ StudentDTO ��ü�� ����
						pt = new P_TurnoverDTO();
						pt.setP_num(rs.getString("p_num"));
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
	public P_TurnoverDTO getP_TurnoverById(String id) {
		// �⺻ ������ ������ ȸ�����̺��� ȸ���, �ʵ����̺��� �ʵ��, �μ����̺��� �μ����� �������� ���̺�
				String searchQuery = query + ", " + "COMPANY.C_NAME AS company_name, " +
						  							"FIELD.CF_NAME AS field_name " +
						  							"DEPARTMENT.CFD_NUM, AS department_name " + 
						  							"FROM preparation_for_turnover AS pt, company, field, department " +
						  							"WHERE pt.id = ? AND " +
						  							"pt.c_num = company.c_num AND " + 
						  							"pt.cf_num = field.cf_num AND " + 
						  							"pt.cfd_num = department.cfd_num ";	 
				jdbcUtil.setSql(searchQuery);				// JDBCUtil �� query �� ����
				Object[] param = new Object[] { id };		// ������������ ã�� ���� �������� id�� ����
				jdbcUtil.setParameters(param);				// JDBCUtil �� query ���� �Ű����� ������ ����� �Ű����� ����
						
						try {
							ResultSet rs = jdbcUtil.executeQuery();		// query �� ����
							P_TurnoverDTO pt = null;
							if (rs.next()) {						// ã�� ������������ P_TurnoverDTO ��ü�� ����
								pt = new P_TurnoverDTO();
								pt.setP_num(rs.getString("p_num"));
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

}
