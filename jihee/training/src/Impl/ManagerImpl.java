package Impl;

import java.util.List;

import DAO.ManagerDTO;

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

	 	jdbcUtil.setSql(allQuery);
		try {
			ResultSet rs = jdbc.executeQuery();
			List<ManagerDTO> list = new ArrayList<ManagerDTO>();		// StudentDTO 객체들을 담기위한 list 객체
			while (rs.next()) {	
				ManagerDTO dto = new ManagerDTO();		// 하나의 StudentDTO 객체 생성 후 정보 설정
				dto.setMgr_num(rs.getString("mgr_num"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				list.add(dto);		// list 객체에 정보를 설정한 StudentDTO 객체 저장
			}
			return list;		// 학생정보를 저장한 dto 들의 목록을 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return null;	
		}// 전체 매니저 정보를 획득
	
	// 매니저정보를 추가
	public int insertManager(ManagerDTO mgr_num) {
		int result = 0;
		String insertQuery = "INSERT INTO MANAGER (mgr_num, id, pw) " +
							 "VALUES (?, ?, ?) ";
		
		DAOFactory factory = new DAOFactory();		// 교수정보와 학과정보를 알아오기 위해 DAO 객체를 생성하는 factory 객체 생성
		
		
		// query 문에 사용할 매개변수 값을 갖는 매개변수 배열 생성
		Object[] param = new Object[] {mgr_num.getMgr_num(), stu.getId(), stu.getPw()};		
		jdbcUtil.setSql(insertQuery);			// JDBCUtil 에 insert 문 설정
		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
				
		try {				
			result = jdbcUtil.executeUpdate();		// insert 문 실행
			System.out.println(mgr_num.getMgr_num() + " 매니저번호의 매니저정보가 삽입되었습니다.");
		} catch (SQLException ex) {
			System.out.println("입력오류 발생!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("동일한 매니저정보가 이미 존재합니다."); 
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}		
		return result;		// insert 에 의해 반영된 레코드 수 반환	
	}
	
	// 매니저정보를 id로 찾음
	public ManagerDTO getManagerById(String id) {
		// 기본 쿼리와 합쳐져 교수테이블에서 지도교수명, 학과 테이블에서 학과명을 가져오는 테이블
				String searchQuery = query + ", " + "FROM Manager " +
				  							  "WHERE Manager.id = ? ";	 
				jdbcUtil.setSql(searchQuery);				// JDBCUtil 에 query 문 설정
				Object[] param = new Object[] { id };		// 관리자를 찾기 위한 조건으로 id를 설정
				jdbcUtil.setParameters(param);				// JDBCUtil 에 query문의 매개변수 값으로 사용할 매개변수 설정
				
				try {
					ResultSet rs = jdbcUtil.executeQuery();		// query 문 실행
					ManagerDTO mgr = null;
					if (rs.next()) {						// 찾은 관리자의 정보를 StudentDTO 객체에 설정
						mgr = new ManagerDTO();
						mgr.setMgr_num(rs.getString("mgr_num"));
						mgr.setId(rs.getString("id"));
						mgr.setPw(rs.getString("pw"));
					}
					return mgr;				// 찾은 관리자의 정보를 담고 있는 StudentDTO 객체 반환
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
				}
				return null;
	}
	

	
	
		
	}		
	

