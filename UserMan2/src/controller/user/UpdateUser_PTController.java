package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.P_TurnoverDTO;
import model.service.SearchManager;
import model.service.UserManager_PT;

public class UpdateUser_PTController implements Controller {
   private static final Logger log = LoggerFactory.getLogger(UpdateUser_PTController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String cName = request.getParameter("company");
		String cfName = request.getParameter("cf_name_hope");
		String cfdName = request.getParameter("cfd_name");
    	
		SearchManager smanager = SearchManager.getInstance();
    	
		Integer c_num = smanager.getC_NUMByC_NAME(cName);
		Integer cf_num = smanager.getCF_NUMByCF_NAME(cfName);
		Integer cfd_num = smanager.getCFD_NUMByCFD_NAME(cfdName);
		
    	P_TurnoverDTO updateUser = new P_TurnoverDTO(
          request.getParameter("userId"),
          request.getParameter("password"),
          request.getParameter("name"),
          c_num, cf_num, cfd_num,
          request.getParameter("email"));
       
       log.debug("Update User : {}", updateUser);

      UserManager_PT manager = UserManager_PT.getInstance();
      manager.update(updateUser);         
        return "redirect:/user/list_pt";         
    }
}