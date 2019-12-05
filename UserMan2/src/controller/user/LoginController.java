package controller.user;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.UserManager_JS;
import model.service.UserManager_PT;
import model.service.UserManager_W;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String type = request.getParameter("user_type");
		
		try {
			// 모델에 로그인 처리를 위임
			
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            session.setAttribute(UserSessionUtils.USER_SESSION_TYPE, type);
            
			if(type.equals("pt")) {
				UserManager_PT manager_pt = UserManager_PT.getInstance();
				manager_pt.login(userId, password);
				return "redirect:/user/list_pt";
			}else if(type.equals("js")) {
				UserManager_JS manager_js = UserManager_JS.getInstance();
				manager_js.login(userId, password);
				return "redirect:/user/list_js";
			}else {
				UserManager_W manager_w = UserManager_W.getInstance();
				manager_w.login(userId, password);
				return "redirect:/user/list_w";
			}
			
			// TODO: 위에 else if로 바꾸고 else 아래에 오류시 처리
			// ���ǿ� ����� ���̵� ����
			
            
            			
		} catch (Exception e) {
			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/loginForm.jsp";			
		}	
    }
}