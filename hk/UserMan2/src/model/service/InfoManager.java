package model.service;

import java.sql.SQLException;
import java.util.List;

import model.InfoDTO;
import model.dao.InfoDAO;
import model.dao.InfoDAOImpl;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */
public class InfoManager {
	private static InfoManager infoMan = new InfoManager();
	private InfoDAOImpl infoDAOImpl;
//	private InfoAnalysis infoAanlysis;

	private InfoManager() {
		try {
			infoDAOImpl = new InfoDAOImpl();
//			infoAanlysis = new InfoAnalysis(infoDAOImpl);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static InfoManager getInstance() {
		return infoMan;
	}
	
	public int insertInfo(InfoDTO info) throws SQLException, ExistingUserException {
		if (infoDAOImpl.existingInfo(info.getiNo()) == true) {
			throw new ExistingUserException(info.getiNo() + "이미 존재합니다.");
		}
		return infoDAOImpl.insertInfo(info);
	}

	public int update(InfoDTO info) throws SQLException {
		return infoDAOImpl.updateInfo(info);
	}	

	public int deleteInfo(Integer infoId) throws SQLException {
		return infoDAOImpl.deleteInfo(infoId);
	}
	
	public Integer getI_numByP_id(String p_id) throws SQLException {
		return infoDAOImpl.getI_numByP_id(p_id);
	}
	/* TODO: change */
//
//	public InfoDAO findUser(String userId)
//		throws SQLException, UserNotFoundException {
//		InfoDAOImpl user = infoDAOImpl.findUser(userId);
//		
//		if (user == null) {
//			throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
//		}		
//		return user;
//	}
//
//	public List<InfoDAO> findUserList() throws SQLException {
//			return infoDAOImpl.findUserList();
//	}
//	
//	public List<InfoDAO> findUserList(int currentPage, int countPerPage)
//		throws SQLException {
//		return infoDAOImpl.findUserList(currentPage, countPerPage);
//	}
//
//	public boolean login(String userId, String password)
//		throws SQLException, UserNotFoundException, PasswordMismatchException {
//		InfoDAO user = findUser(userId);
//
//		if (!user.matchPassword(password)) {
//			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
//		}
//		return true;
//	}

//	public List<User_JobSeeker> makeFriends(String userId) throws Exception {
//		return userAanlysis.recommendFriends(userId);
//	}
	
	public InfoDAO getUserDAO() {
		return this.infoDAOImpl;
	}

	public Integer getI_numByW_id(String w_id) throws SQLException {
		// TODO Auto-generated method stub
		return infoDAOImpl.getI_numByW_id(w_id);
	}

}
