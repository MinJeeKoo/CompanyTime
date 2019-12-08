package model.service;

import java.sql.SQLException;

import model.Matching_jwDTO;
import model.Matching_twDTO;
import model.dao.Matching_jwDAOImpl;
import model.dao.Matching_twDAOImpl;

public class MatchingManager {
	public static MatchingManager matchingMan = new MatchingManager();
	private Matching_jwDAOImpl matching_jwDAOImpl;
	private Matching_twDAOImpl matching_twDAOImpl;
	
	private MatchingManager() {
		try {
			matching_jwDAOImpl = new Matching_jwDAOImpl();
			matching_twDAOImpl = new Matching_twDAOImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MatchingManager getInstance() {
		return matchingMan;
	}
	
	public Matching_jwDTO getMatchingW_ByJS_ID(String js_id) 
			throws SQLException, WaitingMatchingException {
		Matching_jwDTO jw = matching_jwDAOImpl.getMatchingW_ByJS_ID(js_id);
		
		if (jw == null) {
			throw new WaitingMatchingException(js_id + "님은 아직 매칭 대기중입니다.");
		}
		return jw;
	}
	
	public String getMentee(String w_id) 
			throws SQLException, WaitingMatchingException {
		Matching_jwDTO jw = matching_jwDAOImpl.getMatchingJS_ByW_ID(w_id);
		Matching_twDTO tw = matching_twDAOImpl.getMatchingP_ByW_ID(w_id);
		
		if (jw == null && tw == null) {
			throw new WaitingMatchingException(w_id + "님은 아직 매칭 대기중입니다.");
		} else if (jw != null) {
			return jw.getJS_ID();
		} else {
			return tw.getP_ID();
		}
	}
	
	public Matching_twDTO getMatchingW_ByP_ID(String p_id) 
			throws SQLException, WaitingMatchingException {
		Matching_twDTO tw = matching_twDAOImpl.getMatchingW_ByP_ID(p_id);
		
		if (tw == null) {
			throw new WaitingMatchingException(p_id + "님은 아직 매칭 대기중입니다.");
		}
		return tw;
	}

}
