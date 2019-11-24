package controller.user;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.P_TurnoverDTO;
import model.service.UserManager_PT;
import controller.user.UpdateUser_PTController;

public class UpdatePTFormController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateUser_PTController.class);

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String updateId = request.getParameter("userId");
		
		log.debug("UpdateForm Request : {}", updateId);

		UserManager_PT manager = UserManager_PT.getInstance();
		P_TurnoverDTO user = manager.findUser(updateId);	// ����� ���� �˻�
		request.setAttribute("user", user);						
		
		HttpSession session = request.getSession();
		if (UserSessionUtils.isLoginUser(updateId, session) ||
			UserSessionUtils.isLoginUser("admin", session)) {
			// ���� �α����� ����ڰ� ���� ��� ������̰ų� �������� ��� -> ���� ����
			
			return "/user/updateForm_PT.jsp";   // �˻��� ����� ������ update form���� ����     
		}
		
		// else (���� �Ұ����� ���) ����� ���� ȭ������ ���� �޼����� ����
		request.setAttribute("updateFailed", true);
		request.setAttribute("exception", 
			new IllegalStateException("타인의 정보는 수정할 수 없습니다."));            
		return "/user/view_pt.jsp";	// ����� ���� ȭ������ �̵� (forwarding)
    }

}
