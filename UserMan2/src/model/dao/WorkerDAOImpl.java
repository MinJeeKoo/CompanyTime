package model.dao;

import model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;

public class WorkerDAOImpl {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	
	private JDBCUtil jdbcUtil = null;		// JDBCUtil 객체를 지정하기 위한 변수
	//worker 의 기본 정보를 포함하는 query 문
	private static String query = "SELECT "  +
								         "w_id AS id, " +
								         "pw AS pw, " +
								         "name AS name, " +
								         "emp_num AS emp_num " +
								         "company_email AS company_email " +
								         "matching_result AS matching_result ";		
		
	// WorkerDAOImpl 생성자
	public WorkerDAOImpl() {			
		jdbcUtil = new JDBCUtil();		// WorkerDAOImpl 객체 생성 시 JDBCUtil 객체 생성
	}	
	/**
	 * worker 테이블에 새로운 사용자 생성.
	 */
	public int create(WorkerDTO user) throws SQLException {
		String sql = "INSERT INTO worker (w_id, pw, name, emp_num, company_email, c_num, cf_num, cfd_num, matching_result )"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {user.getW_id(), user.getPw(), user.getName(), user.getEmpno(), user.getCompany_email(),
										user.getC_num(), user.getCf_num(), user.getCfd_num(), user.getMatching_result()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
	/**
	 * 기존의 사용자 정보를 수정.
	 */
	public int update(WorkerDTO user) throws SQLException {
		String sql = "UPDATE Worker "
				+ "SET c_num=?, cf_num=?, cfd_num=?, name=?, company_email=?, pw=? "
				+ "WHERE p_id=?";
		Object[] param = new Object[] {user.getC_num(), user.getCf_num(), user.getCfd_num(), user.getName(), user.getCompany_email(), 
				user.getPw(), user.getW_id()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	/**
	 * 사용자 ID에 해당하는 사용자를 삭제.
	 */
	public int remove(String userId) throws SQLException {
		String sql = "DELETE FROM Worker WHERE userid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}


	
	// 전체 이직준비자 정보를 획득
	public List<WorkerDTO> getWorkerList(){
		String allQuery = query + ", " + "c_num AS c_num  " + 
				"cf_num AS cf_num " +
				"cfd_num AS cfd_num " +
		    "FROM Preparation_for_Turnover ";	
		
	jdbcUtil.setSql(allQuery);		// JDBCUtil 에 query 설정
	
	try { 
		ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행			
		List<WorkerDTO> list = new ArrayList<WorkerDTO>();		// P_TurnoverDTO 객체들을 담기위한 list 객체
		while (rs.next()) {	
			WorkerDTO dto = new WorkerDTO();		// 하나의 P_TurnoverDTO 객체 생성 후 정보 설정
			dto.setW_id(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setName(rs.getString("name"));
			dto.setCompany_email(rs.getString("company_email"));
			dto.setMatching_result(rs.getInt("matching_result"));
			dto.setC_num(rs.getInt("c_num"));
			dto.setCf_num(rs.getInt("cf_num"));
			dto.setCfd_num(rs.getInt("cfd_num"));
		
			list.add(dto);		// list 객체에 정보를 설정한 WorkerDTO 객체 저장
		}
			return list;		// worker 정보를 저장한 dto 들의 목록을 반환
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
	}		
		return null;	
	}
	
	// worker정보를 추가
//	public int create(P_TurnoverDTO pt, String c_name, String cf_name, String cfd_name) throws SQLException {
//		int result = 0;
//		
//		String insertQuery = "INSERT INTO Preparation_for_Turnover (p_id, pw, name, company_email, matching_result, "
//							+ "c_num, cf_num, cfd_num) " +
//							 "VALUES (?, ?, ?, ?, 0, ?, ?, ?)";		
//		DAOFactory factory = new DAOFactory();		// 회사정보와 필드정보와 부서정보를 알아오기 위해 DAO 객체를 생성하는 factory 객체 생성
//		
//		// CompanyDAO 객체를 생성하여 이직준비자 정보에 포함되어 있는 회사의 c_num을 알아옴
//		CompanyDAO companyDAO = factory.getCompanyDAO();		// factory 를 통해 회사에 대한 DAO 획득
//		Integer c_num = companyDAO.getC_NUMByC_NAME(c_name);    // 회사 DAO 의 이름을 사용하여 교수코드를 얻어오는 메소드 사용	// 회사번호를 설정
//		if (c_num == null) {
//			CompanyDTO newCompany = new CompanyDTO(null, c_name, null);
//			companyDAO.insertCompany(newCompany);
//			return result;
//		} 
//		logger.debug(c_num.toString());
//	// FieldDAO 객체를 생성하여 이직준비자 정보에 포함되어 있는 field의 cf_num을 알아옴
//	// Field는 이직하고자 하는 분야를 뜻함
//		FieldDAO fieldDAO = factory.getFieldDAO();		// factory 를 통해 필드에 대한 DAO 획득
//		Integer cf_num = fieldDAO.getCF_NUMByCF_NAME(cf_name); // 필드 DAO 의 필드명을 사용하여 cf_num을 얻어오는 메소드 사용			// cf_num�� ����
//		if (cf_num == null) {
//			logger.debug("cf_num is null");
////			return result;
//		}
////		logger.debug(cf_num.toString());
//		// DepartmentDAO 객체를 생성하여 이직준비자 정보에 포함되어 있는 부서의 cfd_num을 알아옴
//		DepartmentDAO departmentDAO = factory.getDepartmentDAO();		// factory 를 통해 부서에 대한 DAO 획득
//		Integer cfd_num = departmentDAO.getCFD_NUMByCFD_NAME(cfd_name);	// departmentDAO 의 이름을 사용하여 cfd를 얻어오는 메소드 사용		// 부서번호를 설정
//		if (cfd_num == null) {
//			logger.debug("cfd_num is null");
////			return result;
//		}
////		logger.debug(cfd_num.toString());
//		// query 문에 사용할 매개변수 값을 갖는 매개변수 배열 생성
//		Object[] param = new Object[] {pt.getP_id(), pt.getPw(), pt.getName(), 
//				pt.getCompany_email(), c_num, cf_num, cfd_num};		
//		jdbcUtil.setSqlAndParameters(insertQuery, param);			// JDBCUtil 에 insert 문 설정 // JDBCUtil 에 매개변수 설정
//				
////		try {				
////			result = jdbcUtil.executeUpdate();		// insert 문 실행
////		} catch (SQLException ex) {
////			System.out.println("입력오류 발생!!!");
////			if (ex.getErrorCode() == 1)
////				System.out.println("동일한 현직자정보가 이미 존재합니다."); 
////		} catch (Exception ex) {
////			jdbcUtil.rollback();
////			ex.printStackTrace();
////		} finally {		
////			jdbcUtil.commit();
////			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
////		}		
////		return result;		// insert 에 의해 반영된 레코드 수 반환	
//		try {				
//			result = jdbcUtil.executeUpdate();	// update
//			return result;
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		}
//		finally {
//			jdbcUtil.commit();
//			jdbcUtil.close();	// resource 반환
//		}		
//		return 0;
//	}

	// 현직자정보를 수정
	public int updateWorker(WorkerDTO w) {
		String updateQuery = "UPDATE Worker SET "
				+ "c_num = ?, cfd_num = ?, name = ?, company_email = ?, pw = ?, "
				+ "cf_num = ?, matching_result = ? "
				+ "WHERE w_id = ? ";
		Object[] param = new Object[] {w.getC_num(), w.getCfd_num(),
									w.getName(), w.getCompany_email(), w.getPw(), 
									w.getCf_num(), w.getMatching_result(), w.getW_id()};
		// update 문에 사용할 매개변수를 저장할 수 있는 임시 배열
		jdbcUtil.setSql(updateQuery);
		jdbcUtil.setParameters(param);

		try {
			int result = jdbcUtil.executeUpdate();		// update 문 실행
			return result;			// update 에 의해 반영된 레코드 수 반환
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return 0;
	}
	
	// 현직자정보를 삭제
	public int deleteWorker(int w_id) {
		String deleteQuery = "DELETE FROM Worker WHERE W_ID = ?";
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] {w_id};
		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
		
		try {
			int result = jdbcUtil.executeUpdate();		// delete 문 실행
			return result;						// delete 에 의해 반영된 레코드 수 반환
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();		
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		/// ResultSet, PreparedStatement, Connection 반환
		}
		return 0;
	}
	// 현직자정보를 이름으로 찾음
	// 동명이인 처리!
	
	public WorkerDTO getWorkerByName(String name) {
		// 기본 쿼리와 합쳐져 회사테이블에서 회사명, 필드테이블에서 필드명, 부서테이블에서 부서명을 가져오는 테이블
		String searchQuery = query + ", " + "c_num AS c_num, " +
				  							"cf_num AS cf_num, " +
				  							"cfd_num AS cfd_num " + 
				  							"FROM Worker " +
				  							"WHERE name = ? "; 
//				  							
		jdbcUtil.setSql(searchQuery);				// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] { name };		// 이직자를 찾기 위한 조건으로 이름을 설정
		jdbcUtil.setParameters(param);				// JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정
				
				try {
					ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
					WorkerDTO pt = null;
					if (rs.next()) {						// 찾은 현직자의 정보를 WorkerDTO 객체에 설정
						pt = new WorkerDTO();
						pt.setW_id(rs.getString("id"));
						pt.setPw(rs.getString("pw"));
						pt.setName(rs.getString("name"));
						pt.setCompany_email(rs.getString("company_email"));
						pt.setMatching_result(Integer.valueOf(0));
						pt.setC_num(rs.getInt("c_num"));
						pt.setCf_num(rs.getInt("cf_num"));
						pt.setCfd_num(rs.getInt("cfd_num"));
					}
					return pt;				// 찾은 현직자의 정보를 담고 있는  WorkerDTO 객체 반환
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					jdbcUtil.close();		// ResultSet, PreparedStatement, Connection반환
				}
				return null;
	}
	
	// 현직자정보를 id로 찾음
	public WorkerDTO getWorkerById(String w_id) {
		// 기본 쿼리와 합쳐져 회사테이블에서 회사명, 필드테이블에서 필드명, 부서테이블에서 부서명을 가져오는 테이블
				String searchQuery = query + ", " + "c_num AS c_num, " +
						  							"cf_num AS cf_num, " +
						  							"cfd_num AS cfd_num " + 
						  							"FROM Worker " +
						  							"WHERE id = ? ";
				jdbcUtil.setSql(searchQuery);				// JDBCUtil 에 query 문 설정
				Object[] param = new Object[] { w_id };		// 현직자정보를 찾기 위한 조건으로 id을 설정
				jdbcUtil.setParameters(param);				// JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정
						
						try {
							ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
							WorkerDTO pt = null;
							if (rs.next()) {						// 찾은 현직자정보를 P_TurnoverDTO 객체에 설정
								pt = new WorkerDTO();
								pt.setW_id(rs.getString("id"));
								pt.setPw(rs.getString("pw"));
								pt.setName(rs.getString("name"));
								pt.setCompany_email(rs.getString("company_email"));
								pt.setMatching_result(Integer.valueOf(0));
								pt.setC_num(rs.getInt("c_num"));
								pt.setCf_num(rs.getInt("cf_num"));
								pt.setCfd_num(rs.getInt("cfd_num"));
							}
							return pt;				// 찾은 현직자의 정보를 담고 있는 P_TurnoverDTO 객체 반환
						} catch (Exception ex) {
							ex.printStackTrace();
						} finally {
							jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
						}
				return null;
	}
	
	/**
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean existingUser(String userId) throws SQLException {
		String sql = "SELECT count(*) FROM Worker WHERE w_id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query문 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
	/**
	 * 전체 사용자 정보를 검색하여 List에 저장 및 반환
	 **/
	public List<WorkerDTO> findUserList() throws SQLException {
        String sql = "SELECT w_id, pw, name, c_num, cf_num, cfd_num, company_email, matching_result, emp_num " 
     		   + "FROM Worker "
     		   + "ORDER BY w_id";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행				
			List<WorkerDTO> userList = new ArrayList<WorkerDTO>();	// Worker들의 리스트 생성
			while (rs.next()) {
				WorkerDTO user = new WorkerDTO(			// Worker 객체를 생성하여 현재 행의 정보를 저장
						rs.getString("w_id"),
						rs.getString("pw"),
						rs.getString("name"),
						rs.getInt("c_num"),
						rs.getInt("cf_num"),
						rs.getInt("cfd_num"),
						rs.getString("company_email"),
						Integer.valueOf(0),
						rs.getString("empno")
						);	
				userList.add(user);				// List에 User 객체 저장
			}
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	 /**
		 * 전체 사용자 정보를 검색한 후 현재 페이지와 페이지당 출력할 사용자 수를 이용하여
		 * 해당하는 사용자 정보만을 List에 저장하여 반환.
		 */
	public List<WorkerDTO> findUserList(int currentPage, int countPerPage) throws SQLException {
        String sql = "SELECT w_id, pw, name, c_num, cf_num, cfd_num, company_email, matching_result, emp_num " 
        		   + "FROM Worker "
        		   + "ORDER BY w_id";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil에 query문 설정
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
				List<WorkerDTO> userList = new ArrayList<WorkerDTO>();	// User들의 리스트 생성
				do {
					WorkerDTO user = new WorkerDTO(		// Worker 객체를 생성하여 현재 행의 정보를 저장
						rs.getString("w_id"),
						rs.getString("pw"),
						rs.getString("name"),
						rs.getInt("c_num"),
						rs.getInt("cf_num"),
						rs.getInt("cfd_num"),
						rs.getString("company_email"),
						Integer.valueOf(0),
						rs.getString("empno")
						);	
					userList.add(user);							// 리스트에 User 객체 저장
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
	 * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 User 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public WorkerDTO findUser(String w_id) throws SQLException {
        String sql = "SELECT w_id, c_num, cf_num, cfd_num, name, company_email, pw, matching_result, emp_num "
        			+ "FROM Worker "
        			+ "WHERE w_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {w_id});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 현직자 정보 발견
				WorkerDTO user = new WorkerDTO(		// Worker 객체를 생성하여 학생 정보를 저장
						rs.getString("w_id"),
						rs.getString("pw"),
						rs.getString("name"),
						rs.getInt("c_num"),
						rs.getInt("cf_num"),
						rs.getInt("cfd_num"),
						rs.getString("company_email"),
						Integer.valueOf(0),
						rs.getString("empno")
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
}