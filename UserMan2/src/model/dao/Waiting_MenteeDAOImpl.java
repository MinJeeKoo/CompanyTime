package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;
import model.*;

public class Waiting_MenteeDAOImpl implements Waiting_MenteeDAO{
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	
	private JDBCUtil jdbcUtil = null;		// JDBCUtil 객체를 지정하기 위한 변수
		
	// Waiting_MenteeDAOImpl 생성자
	public Waiting_MenteeDAOImpl() {			
		jdbcUtil = new JDBCUtil();		// Waiting_MenteeDAOImpl 객체 생성 시 JDBCUtil 객체 생성
	}	

	/*
	 * spec을 새로 입력하면 waitinglist에 추가
	 * */
	@Override
	public int createWaitingList(Waiting_MenteeDTO mt) throws SQLException {
		String sql = "INSERT INTO waiting_mentee "
				+ "VALUES (sequence_waiting_mentee.nextval, ?, ?, ?, SYSDATE)";		
		Object[] param = new Object[] {mt.getP_id(), mt.getJs_id(), mt.getCf_num()};				
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
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	@Override
	public boolean existingUserPT(String p_id) throws SQLException {
		String sql = "SELECT count(*) FROM waiting_mentee WHERE p_id=?";      
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
	
	@Override
	public boolean existingUserJS(String js_id) throws SQLException {
		String sql = "SELECT count(*) FROM waiting_mentee WHERE js_id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {js_id});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query문 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				logger.debug("count: {}", count);
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
	public int deleteWaitingByPid(String p_id) {
		String query = "DELETE FROM waiting_mentee WHERE p_id = ?";
		
		Object[] param = new Object[] { p_id };
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
	public int deleteWaitingByJSid(String js_id) {
		String query = "DELETE FROM waiting_mentee WHERE js_id = ?";
		
		Object[] param = new Object[] { js_id };
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
	public int getWaitingNumberPT(String p_id) {
		String query = "SELECT ROWNUM, p_id "
				+ "FROM (SELECT p_id "
						+ "FROM waiting_mentee "
						+ "ORDER BY waiting_date)"
				+ "WHERE p_id = ?";
				
		Object[] param = new Object[] { p_id };
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
	
	@Override
	public int getWaitingNumberJS(String js_id) {
		String query = "SELECT ROWNUM, js_id "
				+ "FROM (SELECT js_id "
						+ "FROM waiting_mentee "
						+ "ORDER BY waiting_date)"
				+ "WHERE js_id = ?";
				
		Object[] param = new Object[] { js_id };
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
