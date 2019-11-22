package model.dao;

import model.dto.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobSeekerDAOImpl {
	private JDBCUtil jdbcUtil = null;		// JDBCUtil 객체를 지정하기 위한 변수
//	private int js_num;
//	private String id = null;			// id
//	private String pw = null; 			// pw
//	private String name = null;			// 이름
//	private String school = null;		// 학교
//	private String major = null;		// 전공
//	private String personal_email = null; // 개인 이메일주소
//	private int cf_num;					// 필드 정보 (맨토링 희망 분야 정보)
//	private int matching_result;		// 맨토링 매칭 결과
	// Student 의 기본 정보를 포함하는 query 문
	private static String query = "SELECT js_id AS id, " +
								         "pw AS pw, " +
								         "name AS name, " +
								         "school AS school " +
								         "major AS major " +
								         "personal_email AS personal_email ";		
		
	// StudentDAOImpl 생성자
	public JobSeekerDAOImpl() {			
		jdbcUtil = new JDBCUtil();		// StudentDAOImpl 객체 생성 시 JDBCUtil 객체 생성
	}
	
	// 전체 취준생 정보를 획득	
	public List<JobSeekerDTO> getJobSeekerList(){
				String allQuery = query + ", " + "CF_NUM AS CF_NUM, " +
										         "matching_result AS matching_result  " + 
										    "FROM JobSeeker ";		
				jdbcUtil.setSqlAndParameters(allQuery, null);		// JDBCUtil 에 query 설정
				
				try { 
					ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행			
					List<JobSeekerDTO> list = new ArrayList<JobSeekerDTO>();		// JobSeekerDTO 객체들을 담기위한 list 객체
					while (rs.next()) {	
						JobSeekerDTO dto = new JobSeekerDTO();		// 하나의 JobSeekerDTO 객체 생성 후 정보 설정
						dto.setJs_id(rs.getString("id"));
						dto.setPw(rs.getString("pw"));
						dto.setName(rs.getString("name"));
						dto.setSchool(rs.getString("school"));
						dto.setMajor(rs.getString("major"));
						dto.setPersonal_email(rs.getString("personal_email"));
						dto.setCf_num(rs.getInt("cf_num"));
						dto.setMatching_result(rs.getInt("matching_result"));
						list.add(dto);		// list 객체에 정보를 설정한 JobSeekerDTO 객체 저장
					}
					return list;		// 취준생정보를 저장한 dto 들의 목록을 반환
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
				}		
				return null;	
	}
	
	// 취준생정보를 추가
	// 매개변수로 cfName 받아와야하는거아닌가..?
	public int insertJobSeeker(JobSeekerDTO js, String cf_name) {
		int result = 0;
		String insertQuery = "INSERT INTO JobSeeker (js_id, pw, name, school, major, personal_email, cf_num, matching_result) " +
							 "VALUES (?, ?, ?, ?, ?, ?, ?, 0) ";
		
		DAOFactory factory = new DAOFactory();		// 취준생정보와 cf(필드)정보를 알아오기 위해 DAO 객체를 생성하는 factory 객체 생성
		
		// JobSeekerDAO 객체를 생성하여 취준생 에 포함되어 있는 field의 cf_num을 알아옴
		//***********CfDAO -> 이름 확인 필요, getCfById메소드 존재하는지 확인 필요
		FieldDAO cfDAO = factory.getFieldDAO();		
		Integer cf_num = cfDAO.getCF_NUMByCF_NAME(cf_name);	//민지꺼랑맞추기	// cf_num을 설정
		if (cf_num == null) {
			return 0;
		}
	
		// query 문에 사용할 매개변수 값을 갖는 매개변수 배열 생성
		Object[] param = new Object[] { js.getJs_id(), js.getPw(), js.getName(), js.getSchool(), js.getMajor(), js.getPersonal_email(), cf_num};		
		jdbcUtil.setSqlAndParameters(insertQuery, param);			// JDBCUtil 에 insert 문 설정		// JDBCUtil 에 매개변수 설정
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert 문 실행
			
		} catch (SQLException ex) {
			System.out.println("입력오류 발생!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("동일한 취준생정보가 이미 존재합니다."); 
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return result;		// insert 에 의해 반영된 레코드 수 반환	
	}
	
	// 취준생정보를 수정
	public int updateJobSeeker(JobSeekerDTO js) {
		String updateQuery = "UPDATE JobSeeker SET "
							+ "pw = ?, name = ?, school = ?, major = ?, personal_email = ?, "
							+ "cf_num = ?, matching_result = ? "
							+ "WHERE js_id = ? ";
		Object[] param = new Object[] {js.getPw(), js.getName(), js.getSchool(), js.getMajor(),
									js.getPersonal_email(), js.getCf_num(), js.getMatching_result(), 
									js.getJs_id()};
				// update 문에 사용할 매개변수를 저장할 수 있는 임시 배열
		jdbcUtil.setSqlAndParameters(updateQuery, param);
		
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
	
	// 취준생정보를 삭제
	public int deleteJobSeeker(int js_id) {
		String deleteQuery = "DELETE FROM JobSeeker WHERE js_id = ?";
		
		Object[] param = new Object[] {js_id};
		jdbcUtil.setSqlAndParameters(deleteQuery, param);			// JDBCUtil 에 query 문 설정
		// JDBCUtil 에 매개변수 설정
		
		try {
			int result = jdbcUtil.executeUpdate();		// delete 문 실행
			return result;						// delete 에 의해 반영된 레코드 수 반환
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();		
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return 0;
	}	
	
	// 취준생 정보를 이름으로 찾음
	public JobSeekerDTO getJobSeekerByName(String name) {
		String searchQuery = query + ", " + "FROM JobSeeker " +
			  "WHERE NAME = ? ";	
		
		Object[] param = new Object[] { name };	
		jdbcUtil.setSqlAndParameters(searchQuery, param);				// JDBCUtil 에 query 문 설정			// JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
			JobSeekerDTO js = null;
			if (rs.next()) {						// 찾은 취준생의 정보를 StudentDTO 객체에 설정
			js = new JobSeekerDTO();
			js.setJs_id(rs.getString("id"));
			js.setPw(rs.getString("pw"));
			js.setName(rs.getString("name"));
			js.setSchool(rs.getString("school"));
			js.setMajor(rs.getString("major"));
			js.setPersonal_email(rs.getString("personal_email"));
			}
			return js;				// 찾은 취준생의 정보를 담고 있는 StudentDTO 객체 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return null;
		
	}
	
	// 취준생정보를 id로 찾음
	public JobSeekerDTO getJobSeekerById(String js_id) {
		String searchQuery = query + ", " + "FROM JobSeeker " +
				  "WHERE id = ? ";	 
		
		Object[] param = new Object[] { js_id };
		jdbcUtil.setSqlAndParameters(searchQuery, param);				// JDBCUtil 에 query 문 설정
				// 취준생을 찾기 위한 조건으로 이름을 설정			// JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
				JobSeekerDTO js = null;
				if (rs.next()) {						// 찾은 취준생의 정보를 StudentDTO 객체에 설정
				js = new JobSeekerDTO();
				js.setJs_id(rs.getString("id"));
				js.setPw(rs.getString("pw"));
				js.setName(rs.getString("name"));
				js.setSchool(rs.getString("school"));
				js.setMajor(rs.getString("major"));
				js.setPersonal_email(rs.getString("personal_email"));
				}
				return js;				// 찾은 취준생의 정보를 담고 있는 StudentDTO 객체 반환
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
			}
			return null;
	}

}
