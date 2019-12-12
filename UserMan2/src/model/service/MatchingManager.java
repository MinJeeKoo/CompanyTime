package model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;
import model.Matching_jwDTO;
import model.Matching_twDTO;
import model.dao.Matching_jwDAOImpl;
import model.dao.Matching_twDAOImpl;
import model.dao.Waiting_MenteeDAOImpl;
import model.dao.Waiting_MentoDAOImpl;

public class MatchingManager {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	public static MatchingManager matchingMan = new MatchingManager();
	private Matching_jwDAOImpl matching_jwDAOImpl;
	private Matching_twDAOImpl matching_twDAOImpl;
	private Waiting_MenteeDAOImpl waiting_menteeDAOImpl;
	private Waiting_MentoDAOImpl waiting_mentoDAOImpl;
	
	private MatchingManager() {
		try {
			matching_jwDAOImpl = new Matching_jwDAOImpl();
			matching_twDAOImpl = new Matching_twDAOImpl();
			waiting_menteeDAOImpl = new Waiting_MenteeDAOImpl();
			waiting_mentoDAOImpl = new Waiting_MentoDAOImpl();
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
	
	public void insert() throws SQLException {
		
		ArrayList<String> result = new ArrayList<String>();
		result = matching_twDAOImpl.selectMatching();
		logger.debug("result: {}", result);
		while (result != null && result.size() > 0) {
			
			logger.debug("result1: {}", result);
			int rs = matching_twDAOImpl.insertMatching(result);
			
			int deleteMentee = 0;
			if (result.get(1) != null) {
				deleteMentee =  waiting_menteeDAOImpl.deleteWaitingByPid(result.get(1));
			} else if (result.get(2) != null) {
				deleteMentee = waiting_menteeDAOImpl.deleteWaitingByJSid(result.get(2));
			}
			int deleteMento = waiting_mentoDAOImpl.deleteWaiting(result.get(0));
			
			result = matching_twDAOImpl.selectMatching();
		}
	}

}
