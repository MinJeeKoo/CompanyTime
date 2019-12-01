package model.service;

import java.sql.SQLException;
import java.util.List;

import model.WorkerDTO;
import model.dao.WorkerDAOImpl;
//pt(������)�� �α����Ҷ� �ʿ��� manager
public class UserManager_W {
	private static UserManager_W userMan = new UserManager_W();
	private WorkerDAOImpl userDAO;
	private UserAnalysis_W userAanlysis;

	private UserManager_W() {
		try {
			userDAO = new WorkerDAOImpl();
			userAanlysis = new UserAnalysis_W(userDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager_W getInstance() {
		return userMan;
	}
	
	public int create(WorkerDTO w) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(w.getW_id()) == true) {
			throw new ExistingUserException(w.getW_id() + "�� �����ϴ� ���̵��Դϴ�.");
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
			throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
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
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}

//	public List<P_TurnoverDTO> makeFriends(String userId) throws Exception {
//		return userAanlysis.recommendFriends(userId);
//	}
	
	public WorkerDAOImpl getUserDAO() {
		return this.userDAO;
	}

}
