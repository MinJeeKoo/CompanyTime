package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.service.UserManager;
import model.User_JobSeeker;

public class UpdateUser_JobSeekerController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUser_JobSeekerController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	User_JobSeeker updateUser = new User_JobSeeker(
    		request.getParameter("userId"),
    		request.getParameter("password"),
    		request.getParameter("name"),
    		request.getParameter("school"),
    		request.getParameter("major"),
    		request.getParameter("email"));    
    	
    	log.debug("Update User : {}", updateUser);

		UserManager manager = UserManager.getInstance();
		manager.update(updateUser);			
        return "redirect:/user/list";			
    }
}
