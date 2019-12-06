package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.JobSeekerDTO;
import model.service.UserManager_JS;

public class ListJSController implements Controller {
	// private static final int countPerPage = 100;	// 한 화면에 출력할 사용자 수
	private static final Logger log = LoggerFactory.getLogger(RegisterJSController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	
    	String currentPageStr = request.getParameter("currentPage");	
		int currentPage = 1;
		if (currentPageStr != null && !currentPageStr.equals("")) {
			currentPage = Integer.parseInt(currentPageStr);
		}		
		
        log.debug("list");
    	
		UserManager_JS manager = UserManager_JS.getInstance();
		List<JobSeekerDTO> userList = manager.findUserList();
//		List<User_JobSeeker> userList = manager.findUserList(currentPage, countPerPage);

		// userList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("userList", userList);				
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		
	
		// 로그인한 사용자 type을 request에 저장하여 전달
		request.setAttribute("curUserType", 
				UserSessionUtils.getLoginUserType(request.getSession()));
				
		// 사용자 리스트 화면으로 이동(forwarding)
		return "/user/list_js.jsp";        
    }
}