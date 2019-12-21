package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.service.SearchManager;
import model.service.UserManager_JS;
import model.JobSeekerDTO;

public class UpdateUser_JSController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUser_JSController.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
        String cf_name = request.getParameter("cf_name_hope");
    	
        SearchManager smanager = SearchManager.getInstance();
        
        Integer cf_num = smanager.getCF_NUMByCF_NAME(cf_name);
        
    	JobSeekerDTO updateUser = new JobSeekerDTO(
    		request.getParameter("userId"),
    		request.getParameter("password"),
    		request.getParameter("name"),
    		request.getParameter("school"),
    		request.getParameter("major"),
    		request.getParameter("email"),
    		cf_num
    	);    
    	
    	log.debug("Update User : {}", updateUser);

		UserManager_JS manager = UserManager_JS.getInstance();
		manager.update(updateUser);			
        return "redirect:/user/list_js";			
    }
}