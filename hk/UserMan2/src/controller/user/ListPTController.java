package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.P_TurnoverDTO;
import model.service.UserManager_PT;

public class ListPTController implements Controller {

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
    	
    	
		UserManager_PT manager = UserManager_PT.getInstance();
		List<P_TurnoverDTO> userList = manager.findUserList();
//		List<P_TurnoverDTO> userList = manager.findUserList(currentPage, countPerPage);

		// userList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("userList", userList);				
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		

		// 사용자 리스트 화면으로 이동(forwarding)
		return "/user/list_pt.jsp";
    }

}