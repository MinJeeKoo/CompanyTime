package controller.matching;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.P_TurnoverDTO;
import model.service.UserManager_JS;
import model.service.UserManager_PT;
import model.service.UserManager_W;
import model.service.UserNotFoundException;

public class MatchingController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
		String userType = request.getParameter("userType");

		if (userType.equals("pt")) { //이직자 - 멘티
			UserManager_PT manager_pt = UserManager_PT.getInstance();
			if(manager_pt.check_PId(userId) != -1) { //스펙작성을 했으면
				return "/matching/showMatchingResult.jsp";
			}
		} else if (userType.equals("js")) { //취준생 - 멘티
			UserManager_JS manager_js = UserManager_JS.getInstance();
			if(manager_js.check_JSId(userId) != -1) {
				return "/matching/showMatchingResult.jsp";
			}
		} else { //현직자 - 멘토
			UserManager_W manager_w = UserManager_W.getInstance();
			if(manager_w.check_WId(userId) != -1) {
				return "/matching/showMatchingResult.jsp";
			}
		}

		//스펙작성을 안했으면 
		return "redirect:/user/list_w";

	}

}
