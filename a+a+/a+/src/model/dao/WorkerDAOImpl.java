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
//	private JDBCUtil jdbcUtil = null;		// JDBCUtil 객체를 지정하기 위한 변수
//	
//	// WorkerDAO 의 기본 정보를 포함하는 query 문
//	private static String query = "SELECT " +
//								         "w_id AS id, " +
//								         "pw AS pw, " +
//								         "name AS name, " +
//								         "emp_num AS emp_num, " +
//								         "company_email AS company_email, " +
//								         "matching_result AS matching_result ";		
//		
//	// WorkerDAOImpl 생성자
//	public WorkerDAOImpl() {			
//		jdbcUtil = new JDBCUtil();		// WorkerDAOImpl 객체 생성 시 JDBCUtil 객체 생성
//	}
//	
//	// 전체 현직자 정보를 획득
//	public List<WorkerDTO> getWorkerList(){
//		String allQuery = query + ", " + "c_num AS c_num  " + 
//				"cf_num AS cf_num, " +
//				"cfd_num AS cfd_num, " +
//		    "FROM Worker ";	
//		
//		jdbcUtil.setSql(allQuery);		// JDBCUtil 에 query 설정
//		
//		try { 
//			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행			
//			List<WorkerDTO> list = new ArrayList<WorkerDTO>();		// WorkerDTO 객체들을 담기위한 list 객체
//			while (rs.next()) {	
//				WorkerDTO dto = new WorkerDTO();		// 하나의 WorkerDTO 객체 생성 후 정보 설정
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
//			list.add(dto);		// list 객체에 정보를 설정한 WorkerDTO 객체 저장
//		}
//			return list;		// 현직자 정보를 저장한 dto 들의 목록을 반환
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
//		}		
//			return null;	
//	}
//	
//	// 현직자정보를 추가
//	public int insertWorker(WorkerDTO w, String c_name, String cf_name, String cfd_name) {
//		int result = 0;
//		String insertQuery = "INSERT INTO Worker (w_id, pw, name, emp_num, company_email, matching_result, "
//				+ "c_num, cf_num, cfd_num) " +
//							 "VALUES (?, ?, ?, ?, ?, 0, ?, ?, ?) ";
//		
//		DAOFactory factory = new DAOFactory();		// 회사정보와 필드정보와 부서정보를 알아오기 위해 DAO 객체를 생성하는 factory 객체 생성
//		
//		// CompanyDAO 객체를 생성하여 이직준비자 정보에 포함되어 있는 회사의 c_num을 알아옴
//		CompanyDAO companyDAO = factory.getCompanyDAO();		// factory 를 통해 회사에 대한 DAO 획득
//		Integer c_num = companyDAO.getC_numByC_name(c_name);		// 회사 DAO 의 이름을 사용하여 교수코드를 얻어오는 메소드 사용
//		if (c_num == null) {
//			CompanyDTO newCompany = new CompanyDTO(null, c_name, null);
//			companyDAO.insertCompany(newCompany);
//			return 0;
//		} //민지랑 맞추기
//		
//		// WorkerDAO 객체를 생성하여 이직준비자 정보에 포함되어 있는 field의 cf_num을 알아옴
//		// Field는 재직중인 부서의 분야를 뜻함
//		FieldDAO fieldDAO = factory.getFieldDAO();		// factory 를 통해 필드에 대한 DAO 획득
//		Integer cf_num = fieldDAO.getCf_numByCf_name(cf_name); // 필드 DAO 의 필드명을 사용하여 cf_num을 얻어오는 메소드 사용
//		if (cf_num == null) {
//			return 0;
//		}
//		
//		// DepartmentDAO 객체를 생성하여 현직자 정보에 포함되어 있는 부서의 cfd_num을 알아옴
//		DepartmentDAO departmentDAO = factory.getDepartmentDAO();		// factory 를 통해 부서에 대한 DAO 획득
//		Integer cfd_num = departmentDAO.getCfd_numByCfd_name(cfd_name);		// DepartmentDAO의 이름을 사용하여 cfd를 얻어오는 메소드 사용
//		if (cfd_num == null) {
//			return 0;
//		}
//		
//		// query 문에 사용할 매개변수 값을 갖는 매개변수 배열 생성
//		Object[] param = new Object[] { w.getW_id(), w.getPw(), w.getName(), 
//				w.getEmp_num(), w.getCompany_email(), c_num, cf_num, cfd_num};		
//		jdbcUtil.setSql(insertQuery);			// JDBCUtil 에 insert 문 설정
//		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
//				
//		try {				
//			result = jdbcUtil.executeUpdate();		// insert 문 실행
//		} catch (SQLException ex) {
//			System.out.println("입력오류 발생!!!");
//			if (ex.getErrorCode() == 1)
//				System.out.println("동일한 현직자정보가 이미 존재합니다."); 
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		} finally {		
//			jdbcUtil.commit();
//			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
//		}		
//		return result;		// insert 에 의해 반영된 레코드 수 반환	
//	}
//	
//	// 현직자정보를 수정
//	public int updateWorker(WorkerDTO w) {
//		String updateQuery = "UPDATE Worker SET ";
//		int index = 0;
//		Object[] tempParam = new Object[9];		// update 문에 사용할 매개변수를 저장할 수 있는 임시 배열
//		
//		if (w.getW_id() != null) {		// id가 설정되어 있을 경우
//			updateQuery += "ID = ?, ";		// update 문에 패스워드 수정 부분 추가
//			tempParam[index++] = w.getW_id();		// 매개변수에 수정할 패스워드 추가
//		}
//		if (w.getPw() != null) {		// pw가 설정되어 있을 경우
//			updateQuery += "PW = ?, ";		// update 문에 휴대폰 수정 부분 추가
//			tempParam[index++] = w.getPw();		// 매개변수에 수정할 휴대폰 추가
//		}
//		if (w.getName() != null) {		// 이름이 설정되어 있을 경우
//			updateQuery += "NAME = ?, ";		// update 문에 학년 수정 부분 추가
//			tempParam[index++] = w.getName();		// 매개변수에 수정할 학년 추가
//		}		
//		if (w.getEmp_num() != null) {		// 사원번호가 설정되어 있을 경우
//			updateQuery += "EMP_NUM = ?, ";		// update 문에 사원번호 수정 부분 추가
//			tempParam[index++] = w.getEmp_num();		// 매개변수에 수정할 사원번호 추가
//		}
//		if (w.getCompany_email() != null) {		// EMAIL이 설정되어 있을 경우
//			updateQuery += "COMPANY_EMAIL = ?, ";		// update 문에 EMAIL 수정 부분 추가
//			tempParam[index++] = w.getCompany_email();		// 매개변수에 수정할 EMAIL 추가
//		}
//		if (w.getMatching_result() != null) {		// 매칭결과가 설정되어 있을 경우
//			updateQuery += "MATCHING_RESULT = ?, ";		// update 문에 매칭결과 수정 부분 추가
//			tempParam[index++] = w.getMatching_result();		// 매개변수에 수정할 매칭결과 추가
//		}
//		if (w.getC_num() != null) {		// 회사가 설정되어 있을 경우
//			updateQuery += "C_NUM = ?, ";		// update 문에 회사 수정 부분 추가
//			tempParam[index++] = w.getC_num();		// 매개변수에 수정할 회사 추가
//		}
//		if (w.getCf_num() != null) {		// 필드가 설정되어 있을 경우
//			updateQuery += "CF_NUM = ?, ";		// update 문에 필드 수정 부분 추가
//			tempParam[index++] = w.getCf_num();		// 매개변수에 수정할 필드 추가
//		}
//		if (w.getCfd_num() != null) {		// 부서가 설정되어 있을 경우
//			updateQuery += "CFD_NUM = ?, ";		// update 문에 부서 수정 부분 추가
//			tempParam[index++] = w.getCfd_num();		// 매개변수에 수정할 부서 추가
//		}
//		updateQuery += "WHERE id = ? ";		// update 문에 조건 지정
//		updateQuery = updateQuery.replace(", WHERE", " WHERE");		// update 문의 where 절 앞에 있을 수 있는 , 제거
//		
//		tempParam[index++] = w.getW_id();		// 찾을 조건에 해당하는 학번에 대한 매개변수 추가
//		
//		Object[] newParam = new Object[index];
//		for (int i=0; i < newParam.length; i++)		// 매개변수의 개수만큼의 크기를 갖는 배열을 생성하고 매개변수 값 복사
//			newParam[i] = tempParam[i];
//		
//		jdbcUtil.setSql(updateQuery);			// JDBCUtil에 update 문 설정
//		jdbcUtil.setParameters(newParam);		// JDBCUtil 에 매개변수 설정
//		
//		try {
//			int result = jdbcUtil.executeUpdate();		// update 문 실행
//			return result;			// update 에 의해 반영된 레코드 수 반환
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		}
//		finally {
//			jdbcUtil.commit();
//			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
//		}		
//		return 0;
//	}
//	
//	// 현직자정보를 삭제
//	public int deleteWorker(int w_id) {
//		jdbcUtil.setSql(deleteQuery);			// JDBCUtil 에 query 문 설정
//		Object[] param = new Object[] {w_id};
//		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
//		
//		try {
//			int result = jdbcUtil.executeUpdate();		// delete 문 실행
//			return result;						// delete 에 의해 반영된 레코드 수 반환
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();		
//		} finally {
//			jdbcUtil.commit();
//			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
//		}
//		return 0;
//	}
//	
//	// 현직자정보를 이름으로 찾음
//	public WorkerDTO getWorkerByName(String name) {
//				String searchQuery = query + ", " + "c_num AS c_num, " +
//						  							"cf_num AS cf_num, " +
//						  							"cfd_num AS cfd_num " + 
//						  							"FROM worker " +
//						  							"WHERE name = ? ";	 
//				jdbcUtil.setSql(searchQuery);				// JDBCUtil 에 query 문 설정
//				Object[] param = new Object[] { name };		// 이직자를 찾기 위한 조건으로 이름을 설정
//				jdbcUtil.setParameters(param);				// JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정
//						
//						try {
//							ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
//							WorkerDTO pt = null;
//							if (rs.next()) {						// 찾은 이직자의 정보를 StudentDTO 객체에 설정
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
//							return pt;				// 찾은 이직자의 정보를 담고 있는 P_TurnoverDTO 객체 반환
//						} catch (Exception ex) {
//							ex.printStackTrace();
//						} finally {
//							jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
//						}
//						return null;
//	}
//	
//	// 현직자정보를 id로 찾음
//	public WorkerDTO getWorkerById(String w_id) {
//		// 기본 쿼리와 합쳐져 회사테이블에서 회사명, 필드테이블에서 필드명, 부서테이블에서 부서명을 가져오는 테이블
//		String searchQuery = query + ", " + "c_num AS c_num, " +
//				  							"cf_num AS cf_num " +
//				  							"cfd_num AS cfd_num " + 
//				  							"FROM worker " +
//				  							"WHERE id = ? ";
//		jdbcUtil.setSql(searchQuery);				// JDBCUtil 에 query 문 설정
//		Object[] param = new Object[] { w_id };		// 학생을 찾기 위한 조건으로 id를 설정
//		jdbcUtil.setParameters(param);				// JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정
//				
//				try {
//					ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
//					WorkerDTO w = null;
//					if (rs.next()) {						// 찾은 학생의 정보를 StudentDTO 객체에 설정
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
//					return w;				// 찾은 학생의 정보를 담고 있는 StudentDTO 객체 반환
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				} finally {
//					jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
//				}
//				return null;
//	}
//
//}
