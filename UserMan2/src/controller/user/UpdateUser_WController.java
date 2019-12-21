package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.P_TurnoverDTO;
import model.WorkerDTO;
import model.service.SearchManager;
import model.service.UserManager_PT;
import model.service.UserManager_W;

public class UpdateUser_WController implements Controller {
   private static final Logger log = LoggerFactory.getLogger(UpdateUser_WController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String cName = request.getParameter("company");
		String cfName = request.getParameter("cf_name");
		String cfdName = request.getParameter("cfd_name");
    	
		SearchManager smanager = SearchManager.getInstance();
    	
		Integer c_num = smanager.getC_NUMByC_NAME(cName);
		Integer cf_num = smanager.getCF_NUMByCF_NAME(cfName);
		Integer cfd_num = smanager.getCFD_NUMByCFD_NAME(cfdName);
    	
    	WorkerDTO updateUser = new WorkerDTO(
          request.getParameter("userId"),
          request.getParameter("password"),
          request.getParameter("name"),
          c_num, cf_num, cfd_num, 
          request.getParameter("email"));
       
       log.debug("Update User : {}", updateUser);

      UserManager_W manager = UserManager_W.getInstance();
      manager.update(updateUser);         
        return "redirect:/user/list_w";         
    }
}