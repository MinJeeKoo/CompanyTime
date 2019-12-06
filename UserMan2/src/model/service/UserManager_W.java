package model.service;

import java.sql.SQLException;
import java.util.List;

import model.WorkerDTO;
import model.dao.SpecDAOImpl;
import model.dao.WorkerDAOImpl;
//pt(������)�� �α����Ҷ� �ʿ��� manager
public class UserManager_W {
	private static UserManager_W userMan = new UserManager_W();
	private WorkerDAOImpl userDAO;
	private UserAnalysis_W userAnalysis;
	private SpecDAOImpl userSpec;
	
	private UserManager_W() {
		try {
			userDAO = new WorkerDAOImpl();
			userAnalysis = new UserAnalysis_W(userDAO);
			userSpec = new SpecDAOImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager_W getInstance() {
		return userMan;
	}
	
	public int create(WorkerDTO w) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(w.getW_id()) == true) {
			throw new ExistingUserException(w.getW_id() + "는 존재하는 아이디입니다.");
		}
		return userDAO.create(w);
	}

	public int update(WorkerDTO user) throws SQLException {
		return userDAO.update(user);
	}	

	public int remove(String userId) throws SQLException {
		return userDAO.remove(userId);
	}

	public WorkerDTO findUser(String userId)
		throws SQLException, UserNotFoundException {
		WorkerDTO user = userDAO.findUser(userId);
		
		if (user == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}		
		return user;
	}

	public List<WorkerDTO> findUserList() throws SQLException {
			return userDAO.findUserList();
	}
	
	public List<WorkerDTO> findUserList(int currentPage, int countPerPage)
		throws SQLException {
		return userDAO.findUserList(currentPage, countPerPage);
	}

	public boolean login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		WorkerDTO user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

//	public List<P_TurnoverDTO> makeFriends(String userId) throws Exception {
//		return userAanlysis.recommendFriends(userId);
//	}
	
	public WorkerDAOImpl getUserDAO() {
		return this.userDAO;
	}

	public int check_WId(String wId) {
		return userSpec.getSpecNumByW_num(wId);
	}

}