package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import model.P_TurnoverDTO;
import model.service.UserManager_PT;

public class MainLoginController implements Controller {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
    	String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
    	
    	
		UserManager_PT manager = UserManager_PT.getInstance();
		List<P_TurnoverDTO> userList = manager.findUserList();
//		List<P_TurnoverDTO> userList = manager.findUserList(currentPage, countPerPage);

		// userList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("userList", userList);				
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));	
		logger.debug("id: {}", UserSessionUtils.getLoginUserId(request.getSession()));
		
		// 로그인한 사용자 type을 request에 저장하여 전달
		request.setAttribute("curUserType", 
				UserSessionUtils.getLoginUserType(request.getSession()));
		

		// 사용자 리스트 화면으로 이동(forwarding)
		return "/user/main_afterLogin.jsp";
	}

}
