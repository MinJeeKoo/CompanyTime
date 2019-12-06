package model.dao;

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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Matching_jwDTO getMatchingP_ByW_ID(String w_id) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Matching_jwDAOImpl 테이블에 새로운 매칭정보 추가.
	 */
	@Override
	public int insertMatchingJW(Matching_jwDTO jw) throws SQLException{
		String sql = "INSERT INTO RECOMMEND_MATCHING_JW " 
					+ "VALUES (?, ?)";
		Object[] param = new Object[] {jw.getP_ID(), jw.getW_ID()};
		
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
}
