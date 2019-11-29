package controller.user;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.UserManager_JS;
import model.service.UserManager_PT;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String type = request.getParameter("user_type");
		
		try {
			// �𵨿� �α��� ó���� ����
			
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
			if(type.equals("pt")) {
				UserManager_PT manager_pt = UserManager_PT.getInstance();
				manager_pt.login(userId, password);
				return "redirect:/user/list_pt";
			}else if(type.equals("js")) {
				UserManager_JS manager_js = UserManager_JS.getInstance();
				manager_js.login(userId, password);
				return "redirect:/user/list_js";
			}else {
				return "redirect:/user/list_w";
			}
			
			// TODO: 위에 else if로 바꾸고 else 아래에 오류시 처리
			// ���ǿ� ����� ���̵� ����
			
            
            			
		} catch (Exception e) {
			/* UserNotFoundException�̳� PasswordMismatchException �߻� ��
			 * �ٽ� login form�� ����ڿ��� �����ϰ� ���� �޼����� ���
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/loginForm.jsp";			
		}	
    }
}
