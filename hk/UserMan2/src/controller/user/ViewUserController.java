package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager_JS;
import model.service.UserNotFoundException;
import model.JobSeekerDTO;

public class ViewUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
		UserManager_JS manager = UserManager_JS.getInstance();
		String userId = request.getParameter("userId");
		
    	JobSeekerDTO user = null;
		try {
			user = manager.findUser(userId);	// ����� ���� �˻�
		} catch (UserNotFoundException e) {				
	        return "redirect:/user/list";
		}	
		
		request.setAttribute("user", user);		// ����� ���� ����				
		return "/user/view.jsp";				// ����� ���� ȭ������ �̵�
    }
}
