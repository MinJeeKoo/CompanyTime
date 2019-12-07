package model.dao;

import java.sql.ResultSet;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;
import model.Matching_jwDTO;

public class Matching_jwDAOImpl implements Matching_jwDAO {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	
	private JDBCUtil jdbcUtil = null;		// JDBCUtil 객체를 지정하기 위한 변수
	
	private static String query = "SELECT W_ID AS '현직자ID', " +
									"JS_ID AS '취준생ID' ";
	
	//Matching_jwDAOImpl 생성자
	public Matching_jwDAOImpl() {
		jdbcUtil = new JDBCUtil();	// Matching_jwDAOImpl 객체 생성 시 JDBCUtil 객체 생성
	}
	
	@Override
	public Matching_jwDTO getMatchingW_ByJS_ID(String js_id) {
		String allQuery = query + "FROM RECOMMEND_MATCHING_JW WHERE JS_ID = ? ";
		jdbcUtil.setSql(allQuery); // JDBCUtil 에 query 문 설정
		Object[] param = new Object[] { js_id }; // 매칭결과를 찾기 위한 조건으로 이름을 설정
		jdbcUtil.setParameters(param); // JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 문 실행
			Matching_jwDTO jw = null;
			if (rs.next()) { // 찾은 매칭결과를 Matching_twDTO 객체에 설정
				jw = new Matching_jwDTO();
				jw.setJS_ID(rs.getString("js_id"));
				jw.setW_ID(rs.getString("w_id"));
			}
			return jw; // 찾은 이직자의 정보를 담고 있는 P_TurnoverDTO 객체 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		}
		return null;
	}
	@Override
	public Matching_jwDTO getMatchingP_ByW_ID(String w_id) {
		String allQuery = query + "FROM RECOMMEND_MATCHING_JW WHERE W_ID = ? ";
		jdbcUtil.setSql(allQuery); // JDBCUtil 에 query 문 설정
		Object[] param = new Object[] { w_id }; // 매칭결과를 찾기 위한 조건으로 이름을 설정
		jdbcUtil.setParameters(param); // JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 문 실행
			Matching_jwDTO jw = null;
			if (rs.next()) { // 찾은 매칭결과를 Matching_twDTO 객체에 설정
				jw = new Matching_jwDTO();
				jw.setJS_ID(rs.getString("js_id"));
				jw.setW_ID(rs.getString("w_id"));
			}
			return jw; // 찾은 이직자의 정보를 담고 있는 P_TurnoverDTO 객체 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		}
		return null;
	}
//	/**
//	 * Matching_jwDAOImpl 테이블에 새로운 매칭정보 추가.
//	 */
//	@Override
//	public int insertMatchingJW(Matching_jwDTO jw) throws SQLException{
//		String sql = "INSERT INTO RECOMMEND_MATCHING_JW " 
//					+ "VALUES (?, ?)";
//		Object[] param = new Object[] {jw.getJS_ID(), jw.getW_ID()};
//		
//		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
//		
//		try {				
//			int result = jdbcUtil.executeUpdate();	// insert 문 실행
//			return result;
//		} catch (Exception ex) {
//			jdbcUtil.rollback();
//			ex.printStackTrace();
//		} finally {		
//			jdbcUtil.commit();
//			jdbcUtil.close();	// resource 반환
//		}		
//		return 0;
//	}
	/**
	 * Matching_twDAOImpl 테이블에 새로운 매칭정보 추가.
	 */
	//cf_num이 같으면 랜덤 매칭
	@Override
	public int insertMatchingJW() throws SQLException {
		String sql = "INSERT INTO RECOMMEND_MATCHING_TW VALUES (p_id, w_id) "
				+ "SELECT DISTINCT mtee.p_id AS p_id, mto.w_id AS w_id "
				+ "FROM waiting_mentee AS mtee, waiting_mento AS mto "
				+ "WHERE mtee.cf_num = mto.cf_num";

		jdbcUtil.setSql(sql); // JDBCUtil 에 insert문 설정

		try {
			int result = jdbcUtil.executeUpdate(); // insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}
	@Override
	public int deleteMatchingJW_ByJS_ID(String js_id) throws SQLException {
		String sql = "DELETE FROM RECOMMEND_MATCHING_JW WHERE JS_ID=?";
		
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

	@Override
	public int deleteMatchingJW_ByW_ID(String w_id) throws SQLException {
		String sql = "DELETE FROM RECOMMEND_MATCHING_JW WHERE W_ID=?";
		
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
	public boolean existingUserJS(String js_id) throws SQLException {
		String sql = "SELECT count(*) FROM recommend_matching_jw WHERE js_id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {js_id});	// JDBCUtil에 query문과 매개 변수 설정

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
	
	public boolean existingUserW(String w_id) throws SQLException {
		String sql = "SELECT count(*) FROM recommend_matching_jw WHERE w_id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {w_id});	// JDBCUtil에 query문과 매개 변수 설정

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
}
