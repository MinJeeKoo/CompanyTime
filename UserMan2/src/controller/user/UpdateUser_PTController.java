//package controller.user;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import controller.Controller;
//import model.P_TurnoverDTO;
//import model.service.UserManager_PT;
//
//public class UpdateUser_PTController implements Controller {
//	private static final Logger log = LoggerFactory.getLogger(UpdateUser_PTController.class);
//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
//    	P_TurnoverDTO updateUser = new P_TurnoverDTO(
//    		request.getParameter("p_id"),
//    		request.getParameter("password"),
//    		request.getParameter("name"),
//    		request.getParameter("company"),
//    		request.getParameter("field"),
//    		request.getParameter("dept"), 
//    		request.getParameter("email"));
//    	
//    	log.debug("Update User : {}", updateUser);
//
//		UserManager_PT manager = UserManager_PT.getInstance();
//		manager.update(updateUser);			
//        return "redirect:/user/list";			
//    }
//}
