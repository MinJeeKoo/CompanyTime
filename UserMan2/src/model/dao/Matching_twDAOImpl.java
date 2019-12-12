package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;
import model.Matching_twDTO;

public class Matching_twDAOImpl implements Matching_twDAO {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	private JDBCUtil jdbcUtil = null; // JDBCUtil 객체를 지정하기 위한 변수

	private static String query = "SELECT W_ID, " + "P_ID ";

	// Matching_jwDAOImpl 생성자
	public Matching_twDAOImpl() {
		jdbcUtil = new JDBCUtil(); // Matching_twDAOImpl 객체 생성 시 JDBCUtil 객체 생성
	}

	@Override
	public Matching_twDTO getMatchingW_ByP_ID(String p_id) {
		String allQuery = query + "FROM RECOMMEND_MATCHING WHERE P_ID = ? ";
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
		String allQuery = query + "FROM RECOMMEND_MATCHING WHERE W_ID = ? ";
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

	//select, insert, delete
	
	public ArrayList<String> selectMatching() throws SQLException {
		ArrayList<String> result = new ArrayList<String>();
		String sql = "SELECT w_id, js_id, p_id "
				+ "FROM (SELECT w_id, js_id, p_id "
						+ "FROM waiting_mento mto, waiting_mentee mtee "
						+ "WHERE mtee.cf_num = mto.cf_num "
						+ "ORDER BY mtee.waiting_date, mto.waiting_date) "
				+ "WHERE ROWNUM = 1";
		
	      jdbcUtil.setSql(sql);
	      
	      try {
	          ResultSet rs = jdbcUtil.executeQuery();
	          while (rs.next()) {
	             result.add(rs.getString("w_id"));
	             logger.debug("result***: {}", result);
	             result.add(rs.getString("js_id"));
	             result.add(rs.getString("p_id"));
	          }
	          logger.debug("result: {}", result);
	          return result;
	       } catch (Exception ex) {
	          ex.printStackTrace();
	       } finally {
	          jdbcUtil.close();
	       }
	      return null;
	      
	}
	/**
	 * Matching_twDAOImpl 테이블에 새로운 매칭정보 추가.
	 */

	//cf_num이 같으면 랜덤 매칭
	@Override
	public int insertMatching(ArrayList<String> result) throws SQLException {
		String sql = "INSERT INTO RECOMMEND_MATCHING(w_id, p_id, js_id) VALUES(?, ?, ?) ";
		Object[] param = new Object[] { result.get(0), result.get(1), result.get(2) };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문 설정

		try {
			int rs = jdbcUtil.executeUpdate(); // insert 문 실행
			return rs;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		//
		return 0;
	}

	@Override
	public int deleteMatchingTW_ByP_ID(String p_id) throws SQLException {
		String sql = "DELETE FROM RECOMMEND_MATCHING WHERE P_ID=?";

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
		String sql = "DELETE FROM RECOMMEND_MATCHING WHERE W_ID=?";

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
	
	public boolean existingUserPT(String p_id) throws SQLException {
		String sql = "SELECT count(*) FROM recommend_matching WHERE p_id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {p_id});	// JDBCUtil에 query문과 매개 변수 설정

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
		String sql = "SELECT count(*) FROM recommend_matching WHERE w_id=?";      
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
