package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.JobSeekerDTO;
import model.service.ExistingUserException;
import model.service.SearchManager;
import model.service.UserManager_JS;

public class RegisterJSController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterJSController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String cf_name = request.getParameter("field");
    	
    	SearchManager smanager = SearchManager.getInstance();
    	
    	Integer cf_num = smanager.getCF_NUMByCF_NAME(cf_name);
    	
		JobSeekerDTO user = new JobSeekerDTO(
			request.getParameter("userId"),
			request.getParameter("password"),
			request.getParameter("name"),
			request.getParameter("school"),
			request.getParameter("major"),
			request.getParameter("email"),
			cf_num, Integer.valueOf(0)
			 );
		
        log.debug("Create User : {}", user);



		try {
			UserManager_JS manager = UserManager_JS.getInstance();
			manager.create(user);
	        return "redirect:/user/list_js";		// 성공 시 사용자 리스트 화면으로 redirect
	        
		} catch (ExistingUserException e) {		// 예외 발생 시 회원가입 form으로 forwarding
	        request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm_JobSeeker.jsp";
		}
    }
}