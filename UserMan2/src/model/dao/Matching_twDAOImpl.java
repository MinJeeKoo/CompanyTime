package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;
import model.Matching_twDTO;

public class Matching_twDAOImpl implements Matching_twDAO {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	private JDBCUtil jdbcUtil = null; // JDBCUtil 객체를 지정하기 위한 변수

	private static String query = "SELECT W_ID AS '현직자ID', " + "P_ID AS '이직준비자ID' ";

	// Matching_jwDAOImpl 생성자
	public Matching_twDAOImpl() {
		jdbcUtil = new JDBCUtil(); // Matching_twDAOImpl 객체 생성 시 JDBCUtil 객체 생성
	}

	@Override
	public Matching_twDTO getMatchingW_ByP_ID(String p_id) {
		String allQuery = query + "FROM RECOMMEND_MATCHING_TW WHERE P_ID = ? ";
		jdbcUtil.setSql(allQuery); // JDBCUtil 에 query 문 설정
		Object[] param = new Object[] { p_id }; // 매칭결과를 찾기 위한 조건으로 이름을 설정
		jdbcUtil.setParameters(param); // JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 문 실행
			Matching_twDTO tw = null;
			if (rs.next()) { // 찾은 매칭결과를 Matching_twDTO 객체에 설정
				tw = new Matching_twDTO();
				tw.setP_ID(rs.getString("p_id"));
				tw.setW_ID(rs.getString("w_id"));
			}
			return tw; // 찾은 이직자의 정보를 담고 있는 P_TurnoverDTO 객체 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		}
		return null;
	}

	@Override
	public Matching_twDTO getMatchingP_ByW_ID(String w_id) {
		String allQuery = query + "FROM RECOMMEND_MATCHING_TW WHERE W_ID = ? ";
		jdbcUtil.setSql(allQuery); // JDBCUtil 에 query 문 설정
		Object[] param = new Object[] { w_id }; // 매칭결과를 찾기 위한 조건으로 이름을 설정
		jdbcUtil.setParameters(param); // JDBCUtil 에 query 문의 매개변수 값으로 사용할 매개변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 문 실행
			Matching_twDTO tw = null;
			if (rs.next()) { // 찾은 매칭결과를 Matching_twDTO 객체에 설정
				tw = new Matching_twDTO();
				tw.setP_ID(rs.getString("p_id"));
				tw.setW_ID(rs.getString("w_id"));
			}
			return tw; // 찾은 이직자의 정보를 담고 있는 P_TurnoverDTO 객체 반환
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 반환
		}
		return null;
	}

	/**
	 * Matching_twDAOImpl 테이블에 새로운 매칭정보 추가.
	 */

	@Override
	public int insertMatchingTW(Matching_twDTO tw) throws SQLException {
		String sql = "INSERT INTO RECOMMEND_MATCHING_TW " + "VALUES (?, ?)";
		Object[] param = new Object[] { tw.getP_ID(), tw.getW_ID() };

		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정

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
	public int deleteMatchingTW_ByP_ID(String p_id) throws SQLException {
		String sql = "DELETE FROM RECOMMEND_MATCHING_TW WHERE P_ID=?";

		try {
			int result = jdbcUtil.executeUpdate(); // delete 문 실행
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
	public int deleteMatchingTW_ByW_ID(String w_id) throws SQLException {
		String sql = "DELETE FROM RECOMMEND_MATCHING_TW WHERE W_ID=?";

		try {
			int result = jdbcUtil.executeUpdate(); // delete 문 실행
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

}
