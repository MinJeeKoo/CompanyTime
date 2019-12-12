package model.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;
import model.*;
import model.dao.*;
//pt(이직자) manager
public class SpecManager {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	
	private static SpecManager userMan = new SpecManager();
	private SpecDAOImpl specDAO;
	private SpecDAOImpl userSpec;
	private Waiting_MenteeDAOImpl menteeDAO;
	private Matching_twDAOImpl matchingDAO;

	private SpecManager() {
		try {
			specDAO = new SpecDAOImpl();
			userSpec = new SpecDAOImpl();
			menteeDAO = new Waiting_MenteeDAOImpl();
			matchingDAO = new Matching_twDAOImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static SpecManager getInstance() {
		
		return userMan;
	}
	
	/* 스펙 정보 생성 */
	public int create(SpecDTO sp) throws SQLException{
		logger.debug("SpecDTO : {}", sp);
		return specDAO.insertSpec(sp);
	}

	public int update(SpecDTO sp) throws SQLException {
		return specDAO.updateSpec(sp);
	}	

	public int remove(Integer spec_num) throws SQLException {
		return specDAO.deleteSpec(spec_num);
	}

//	public SpecDTO findPid(String p)
//		throws SQLException, UserNotFoundException {
//		SpecDTO user = specDAO.findUser(userId);
//		
//		if (user == null) {
//			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
//		}		
//		return user;
//	}
//
//	public List<SpecDTO> findUserList() throws SQLException {
//			return specDAO.findUserList();
//	}
//	
//	public List<SpecDTO> findUserList(int currentPage, int countPerPage)
//		throws SQLException {
//		return specDAO.findUserList(currentPage, countPerPage);
//	}
//
//	public boolean login(String userId, String password)
//		throws SQLException, UserNotFoundException, PasswordMismatchException {
//		SpecDTO user = findUser(userId);
//
//		if (!user.matchPassword(password)) {
//			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
//		}
//		return true;
//	}

//	public List<SpecDTO> makeFriends(String userId) throws Exception {
//		return userAanlysis.recommendFriends(userId);
//	}
	
	public SpecDAOImpl getUserDAO() {
		return this.specDAO;
	}
	
	//스펙 썼는지 안썼는지 검사
	public int check_PId(String p_id) {
		return userSpec.getSpecNumByP_num(p_id);
	}
	
	//p_id 를 waiting_mentee에 넣기
	public int createWaitingList(Waiting_MenteeDTO mt) throws SQLException {
		if (menteeDAO.existingUserPT(mt.getP_id()) == true || 
				matchingDAO.existingUserPT(mt.getP_id()) == true)  {
			return 0;
		}
		return menteeDAO.createWaitingList(mt);
	}
	
	//대기자 명단에 있는 사람들 중 분야가 같은 사람 matching 하기

}
