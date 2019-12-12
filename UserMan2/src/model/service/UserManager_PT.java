package model.service;

import java.sql.SQLException;
import java.util.List;

import model.*;
import model.dao.*;
//pt(이직자) manager
public class UserManager_PT {
	private static UserManager_PT userMan = new UserManager_PT();
	private P_TurnoverDAOImpl userDAO;
	private UserAnalysis_PT userAnalysis;
	private SpecDAOImpl userSpec;
	private Waiting_MenteeDAOImpl menteeDAO;
	private Matching_twDAOImpl matchingDAO;

	private UserManager_PT() {
		try {
			userDAO = new P_TurnoverDAOImpl();
			userAnalysis = new UserAnalysis_PT(userDAO);
			userSpec = new SpecDAOImpl();
			menteeDAO = new Waiting_MenteeDAOImpl();
			matchingDAO = new Matching_twDAOImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager_PT getInstance() {
		return userMan;
	}
	
	public int create(P_TurnoverDTO pt) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(pt.getP_id()) == true) {
			throw new ExistingUserException(pt.getP_id() + "는 존재하는 아이디입니다.");
		}
		return userDAO.create(pt);
	}

	public int update(P_TurnoverDTO user) throws SQLException {
		return userDAO.update(user);
	}	

	public int remove(String userId) throws SQLException {
		return userDAO.remove(userId);
	}

	public P_TurnoverDTO findUser(String userId)
		throws SQLException, UserNotFoundException {
		P_TurnoverDTO user = userDAO.findUser(userId);
		
		if (user == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}		
		return user;
	}

	public List<P_TurnoverDTO> findUserList() throws SQLException {
			return userDAO.findUserList();
	}
	
	public List<P_TurnoverDTO> findUserList(int currentPage, int countPerPage)
		throws SQLException {
		return userDAO.findUserList(currentPage, countPerPage);
	}

	public boolean login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		P_TurnoverDTO user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

//	public List<P_TurnoverDTO> makeFriends(String userId) throws Exception {
//		return userAanlysis.recommendFriends(userId);
//	}
	
	public P_TurnoverDAOImpl getUserDAO() {
		return this.userDAO;
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
