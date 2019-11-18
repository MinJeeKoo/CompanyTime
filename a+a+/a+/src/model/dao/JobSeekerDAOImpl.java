//package model.dao;
//
//import model.dto.*;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class JobSeekerDAOImpl {
//	private JDBCUtil jdbcUtil = null;		// JDBCUtil 객체를 지정하기 위한 변수
////	private int js_num;
////	private String id = null;			// id
////	private String pw = null; 			// pw
////	private String name = null;			// 이름
////	private String school = null;		// 학교
////	private String major = null;		// 전공
////	private String personal_email = null; // 개인 이메일주소
////	private int cf_num;					// 필드 정보 (맨토링 희망 분야 정보)
////	private int matching_result;		// 맨토링 매칭 결과
//	// Student 의 기본 정보를 포함하는 query 문
//	private static String query = "SELECT js_id AS id, " +
//								         "pw AS pw, " +
//								         "name AS name, " +
//								         "school AS school " +
//								         "major AS major " +
//								         "personal_email AS personal_email ";		
//		
//	// StudentDAOImpl 생성자
//	public JobSeekerDAOImpl() {			
//		jdbcUtil = new JDBCUtil();		// StudentDAOImpl 객체 생성 시 JDBCUtil 객체 생성
//	}
//	
//	// 전체 취준생 정보를 획득	
//	public List<JobSeekerDTO> getJobSeekerList(){
//				String allQuery = query + ", " + "CF_NUM AS CF_NUM, " +
//										         "matching_result AS matching_result  " + 
//										    "FROM JobSeeker ";		
//				jdbcUtil.setSql(allQuery);		// JDBCUtil 에 query 설정
//				
//				try { 
//					ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행			
//					List<JobSeekerDTO> list = new ArrayList<JobSeekerDTO>();		// JobSeekerDTO 객체들을 담기위한 list 객체
//					while (rs.next()) {	
//						JobSeekerDTO dto = new JobSeekerDTO();		// 하나의 JobSeekerDTO 객체 생성 후 정보 설정
//						dto.setJs_id(rs.getString("id"));
//						dto.setPw(rs.getString("pw"));
//						dto.setName(rs.getString("name"));
//						dto.setSchool(rs.getString("school"));
//						dto.setMajor(rs.getString("major"));
//						dto.setPersonal_email(rs.getString("personal_email"));
//						dto.setCf_num(rs.getInt("cf_num"));
//						dto.setMatching_result(rs.getInt("matching_result"));
//						list.add(dto);		// list 객체에 정보를 설정한 JobSeekerDTO 객체 저장
//					}
//					return list;		// 취준생정보를 저장한 dto 들의 목록을 반환
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				} finally {
//					jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
//				}		
//				return null;	
//	}
//	
//	// 취준생정보를 추가
//	// 매개변수로 cfName 받아와야하는거아닌가..?
//	public int insertJobSeeker(JobSeekerDTO js, String cf_name) {
//		int result = 0;
//		String insertQuery = "INSERT INTO JobSeeker (js_id, pw, name, school, major, personal_email, cf_num, matching_result) " +
//							 "VALUES (?, ?, ?, ?, ?, ?, ?, 0) ";
//		
//		DAOFactory factory = new DAOFactory();		// 취준생정보와 cf(필드)정보를 알아오기 위해 DAO 객체를 생성하는 factory 객체 생성
//		
//		// JobSeekerDAO 객체를 생성하여 취준생 에 포함되어 있는 field의 cf_num을 알아옴
//		//***********CfDAO -> 이름 확인 필요, getCfById메소드 존재하는지 확인 필요
//		FieldDAO cfDAO = factory.getFieldDAO();		
//		Integer cf_num = cfDAO.getCf_numByCf_name(cf_name);	//민지꺼랑맞추기	// cf_num을 설정
//		if (cf_num == null) {
//			return 0;
//		}
//	
//		// query 문에 사용할 매개변수 값을 갖는 매개변수 배열 생성
//		Object[] param = new Object[] { js.getJs_id(), js.getPw(), js.getName(), js.getSchool(), js.getMajor(), js.getPersonal_email(), cf_num};		
//		jdbcUtil.setSql(insertQuery);			// JDBCUtil 에 insert 문 설정
//		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
//				
//		try {				
//			result = jdbcUtil.executeUpdate();		// insert 문 실행
//			
//		} catch (SQLException ex) {
//			System.out.println("입력오류 발생!!!");
//			if (ex.getErrorCode() == 1)
//				System.out.println("동일한 취준생정보가 이미 존재합니다."); 
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
//	// 취준생정보를 수정
//	public int updateJobSeeker(JobSeekerDTO js) {
//		String updateQuery = "UPDATE JobSeeker SET ";
//		int index = 0;
//		Object[] tempParam = new Object[8];		// update 문에 사용할 매개변수를 저장할 수 있는 임시 배열
//		
//		if (js.getName() != null) {		// 이름이 설정되어 있을 경우
//			updateQuery += "NAME = ?, ";		// update 문에 이름 수정 부분 추가
//			tempParam[index++] = js.getName();		// 매개변수에 수정할 이름 추가
//		}
//		if (js.getJs_id() != null) {		// id가 설정되어 있을 경우
//			updateQuery += "js_id = ?, ";		// update 문에 패스워드 수정 부분 추가
//			tempParam[index++] = js.getJs_id();		// 매개변수에 수정할 패스워드 추가
//		}
//		if (js.getPw() != null) {		// pw가 설정되어 있을 경우
//			updateQuery += "PW = ?, ";		// update 문에 휴대폰 수정 부분 추가
//			tempParam[index++] = js.getPw();		// 매개변수에 수정할 휴대폰 추가
//		}
//		if (js.getName() != null) {		// 이름이 설정되어 있을 경우
//			updateQuery += "NAME = ?, ";		// update 문에 학년 수정 부분 추가
//			tempParam[index++] = js.getName();		// 매개변수에 수정할 학년 추가
//		}
//		if (js.getSchool() != null) {		// 학교가 설정되어 있을 경우
//			updateQuery += "SCHOOL = ?, ";		// update 문에 지도교수 수정 부분 추가
//			tempParam[index++] = js.getSchool();		// 매개변수에 수정할 지도교수코드 추가
//		}
//		if (js.getMajor() != null) {		// 전공이 설정되어 있을 경우
//			updateQuery += "MAJOR = ?, ";		// update 문에 학과 수정 부분 추가
//			tempParam[index++] = js.getMajor();		// 매개변수에 수정할 학과코드 추가
//		}
//		if (js.getPersonal_email() != null) {		// 개인이메일이 설정되어 있을 경우
//			updateQuery += "PERSONAL_EMAIL = ?, ";		// update 문에 학과 수정 부분 추가
//			tempParam[index++] = js.getPersonal_email();		// 매개변수에 수정할 학과코드 추가
//		}
//		if (js.getCf_num() != null) {		// 필드가 설정되어 있을 경우
//			updateQuery += "CF_NUM = ?, ";		// update 문에 필드 수정 부분 추가
//			tempParam[index++] = js.getCf_num();		// 매개변수에 수정할 필드 추가
//		}
//		updateQuery += "WHERE js_id = ? ";		// update 문에 조건 지정
//		updateQuery = updateQuery.replace(", WHERE", " WHERE");		// update 문의 where 절 앞에 있을 수 있는 , 제거
//		
//		tempParam[index++] = js.getJs_id();		// 찾을 조건에 해당하는 학번에 대한 매개변수 추가
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
//	// 취준생정보를 삭제
//	public int deleteJobSeeker(int js_id) {
//		String deleteQuery = "DELETE FROM JobSeeker WHERE js_id = ?";
//		
//		jdbcUtil.setSql(deleteQuery);			// JDBCUtil 에 query 문 설정
//		Object[] param = new Object[] {js_id};
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
//	// 취준생 정보를 이름으로 찾음
//	public JobSeekerDTO getJobSeekerByName(String name) {
//		String searchQuery = query + ", " + "FROM JobSeeker " +
//			  "WHERE NAME = ? ";	 
//		jdbcUtil.setSql(searchQuery);				// JDBCUtil 에 query 문 설정
//		Object[] param = new Object[] { name };		// 취준생을 찾기 위한 조건으로 이름을 설정
//		jdbcUtil.setParameters(param);				// JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정
//		
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
//			JobSeekerDTO js = null;
//			if (rs.next()) {						// 찾은 취준생의 정보를 StudentDTO 객체에 설정
//			js = new JobSeekerDTO();
//			js.setJs_id(rs.getString("id"));
//			js.setPw(rs.getString("pw"));
//			js.setName(rs.getString("name"));
//			js.setSchool(rs.getString("school"));
//			js.setMajor(rs.getString("major"));
//			js.setPersonal_email(rs.getString("personal_email"));
//			}
//			return js;				// 찾은 취준생의 정보를 담고 있는 StudentDTO 객체 반환
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
//		}
//		return null;
//		
//	}
//	
//	// 취준생정보를 id로 찾음
//	public JobSeekerDTO getJobSeekerById(String js_id) {
//		String searchQuery = query + ", " + "FROM JobSeeker " +
//				  "WHERE id = ? ";	 
//			jdbcUtil.setSql(searchQuery);				// JDBCUtil 에 query 문 설정
//			Object[] param = new Object[] { js_id };		// 취준생을 찾기 위한 조건으로 이름을 설정
//			jdbcUtil.setParameters(param);				// JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정
//			
//			try {
//				ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
//				JobSeekerDTO js = null;
//				if (rs.next()) {						// 찾은 취준생의 정보를 StudentDTO 객체에 설정
//				js = new JobSeekerDTO();
//				js.setJs_id(rs.getString("id"));
//				js.setPw(rs.getString("pw"));
//				js.setName(rs.getString("name"));
//				js.setSchool(rs.getString("school"));
//				js.setMajor(rs.getString("major"));
//				js.setPersonal_email(rs.getString("personal_email"));
//				}
//				return js;				// 찾은 취준생의 정보를 담고 있는 StudentDTO 객체 반환
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			} finally {
//				jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
//			}
//			return null;
//	}
//
//}
