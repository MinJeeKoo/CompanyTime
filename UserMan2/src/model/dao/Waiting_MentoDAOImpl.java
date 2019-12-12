package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;
import model.Waiting_MentoDTO;

public class Waiting_MentoDAOImpl implements Waiting_MentoDAO {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	private JDBCUtil jdbcUtil = null; // JDBCUtil 객체를 지정하기 위한 변수
	
	
	// Waiting_MentoDAOImpl 생성자
	public Waiting_MentoDAOImpl() {
		jdbcUtil = new JDBCUtil(); // Waiting_MentoDAOImpl 객체 생성 시 JDBCUtil 객체 생성
	}

	/*
	 * spec을 새로 입력하면 waitinglist에 추가
	 */
	@Override
	public int createWaitingList(Waiting_MentoDTO mto) {
		String sql = "INSERT INTO waiting_mento "
				+ "VALUES (sequence_waiting_mento.nextval, ?, ?, SYSDATE)";		
		Object[] param = new Object[] {mto.getW_id(), mto.getCf_num()};				
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
	public boolean existingUserW(String w_id) throws SQLException {
		String sql = "SELECT count(*) FROM waiting_mento WHERE w_id=?";      
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

	@Override
	public int deleteWaiting(String w_id) {
		String query = "DELETE FROM waiting_mento WHERE w_id = ?";
		
		Object[] param = new Object[] { w_id };
		jdbcUtil.setSqlAndParameters(query, param);
	
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		
		return 0;
	}

	@Override
	public int getWaitingNumber(String w_id) {
		String query = "SELECT ROWNUM, w_id "
				+ "FROM (SELECT w_id "
						+ "FROM waiting_mento "
						+ "ORDER BY waiting_date)"
				+ "WHERE w_id = ?";
				
		Object[] param = new Object[] { w_id };
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt("ROWNUM");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return -1;
	}

}
