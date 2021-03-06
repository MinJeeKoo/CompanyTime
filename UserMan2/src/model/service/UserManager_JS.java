package model.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;
import model.JobSeekerDTO;
import model.Waiting_MenteeDTO;
import model.dao.*;


/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
//js(취업준비생) manager
public class UserManager_JS {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	
	private static UserManager_JS userMan = new UserManager_JS();
	private JobSeekerDAOImpl userDAO;
	private SpecDAOImpl userSpec;
	private Waiting_MenteeDAOImpl menteeDAO;
	private Matching_jwDAOImpl matchingDAO;

	private UserManager_JS() {
		try {
			userDAO = new JobSeekerDAOImpl();
			userSpec = new SpecDAOImpl();
			menteeDAO = new Waiting_MenteeDAOImpl();
			matchingDAO = new Matching_jwDAOImpl();
			
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager_JS getInstance() {
		return userMan;
	}
	
	public int create(JobSeekerDTO user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
		}
		return userDAO.create(user);
	}

	public int update(JobSeekerDTO user) throws SQLException {
		return userDAO.update(user);
	}	

	public int remove(String userId) throws SQLException {
		return userDAO.remove(userId);
	}

	public JobSeekerDTO findUser(String userId)
		throws SQLException, UserNotFoundException {
		JobSeekerDTO user = userDAO.findUser(userId);
		
		if (user == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}		
		return user;
	}
	
	public JobSeekerDTO findUser_matching(String userId)
			throws SQLException {
			JobSeekerDTO user = userDAO.findUser(userId);
					
			return user;
		}

	public List<JobSeekerDTO> findUserList() throws SQLException {
			return userDAO.findUserList();
	}
	
	public List<JobSeekerDTO> findUserList(int currentPage, int countPerPage)
		throws SQLException {
		return userDAO.findUserList(currentPage, countPerPage);
	}

	public boolean login(String userId, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		JobSeekerDTO user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

//	public List<User_JobSeeker> makeFriends(String userId) throws Exception {
//		return userAanlysis.recommendFriends(userId);
//	}
	
	public JobSeekerDAOImpl getUserDAO() {
		return this.userDAO;
	}
	
	//스펙 썼는지 안썼는지 검사
	public int check_JSId(String jsId) {
		return userSpec.getSpecNumByJS_num(jsId);
	}
	
	//p_id 를 waiting_mentee에 넣기
	public int createWaitingList(Waiting_MenteeDTO mt) throws SQLException {
		logger.debug("js_id: " + mt.getJs_id());
		if (menteeDAO.existingUserJS(mt.getJs_id()) || matchingDAO.existingUserJS(mt.getJs_id()))  {
			logger.debug("존재");
			return 0;
		}
		return menteeDAO.createWaitingList(mt);
	}
		
	//대기자 명단에 있는 사람들 중 분야가 같은 사람 matching 하기
	
	public boolean existingUserJS(String js_id) throws SQLException {
		return menteeDAO.existingUserJS(js_id);
	}
	
	public int deleteWaitingByJSid(String js_id) {
		return menteeDAO.deleteWaitingByJSid(js_id);
	}
	
}