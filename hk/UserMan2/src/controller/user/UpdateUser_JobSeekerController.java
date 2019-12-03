package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.service.UserManager_JS;
import model.JobSeekerDTO;

public class UpdateUser_JobSeekerController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUser_JobSeekerController.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
        String cf_num = request.getParameter("cf_num");
    	String matching = request.getParameter("matching_result");
    	
    	JobSeekerDTO updateUser = new JobSeekerDTO(
    		request.getParameter("userId"),
    		request.getParameter("password"),
    		request.getParameter("name"),
    		request.getParameter("school"),
    		request.getParameter("major"),
    		request.getParameter("email"),
    		Integer.parseInt(cf_num), Integer.parseInt(matching)
    	);    
    	
    	log.debug("Update User : {}", updateUser);

		UserManager_JS manager = UserManager_JS.getInstance();
		manager.update(updateUser);			
        return "redirect:/user/list";			
    }
}