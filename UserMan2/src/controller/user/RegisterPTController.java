package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import model.CompanyDTO;
import model.P_TurnoverDTO;
import model.service.ExistingUserException;
import model.service.SearchManager;
import model.service.UserManager;
import model.service.UserManager_PT;

public class RegisterPTController implements Controller {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String cName = request.getParameter("company");
		String cfName = request.getParameter("field");
		String cfdName = request.getParameter("dept");
		
		SearchManager smanager = SearchManager.getInstance();
		UserManager_PT umanager = UserManager_PT.getInstance();
		Integer c_num = smanager.getC_NUMByC_NAME(cName);
		Integer cf_num = smanager.getCF_NUMByCF_NAME(cfName);
		Integer cfd_num = smanager.getCFD_NUMByCFD_NAME(cfdName);
		
		if (c_num == null) {
			CompanyDTO comp = new CompanyDTO(null, cName, null);
			smanager.insertCompany(comp);
		}
		
//		if (cf_num == null) {
//			request.setAttribute("registerFailed", true);
//			return "/user/registerForm_PreparationForTurnover.jsp";
//		}
//		
		if (cfd_num == null) {
			request.setAttribute("registerFailed", true);
			return "/user/registerForm_PreparationForTurnover.jsp";
		}
		
		P_TurnoverDTO pt = new P_TurnoverDTO(
				request.getParameter("userId"),
				request.getParameter("password"),
				request.getParameter("name"),
				c_num, cf_num, cfd_num, 
				request.getParameter("email"),
				Integer.valueOf(0)
		);
		
		logger.debug("Create User: {}", pt);
		
		try {
			umanager.create(pt);
			logger.debug("Create User: {}", pt);
	        return "redirect:/user/list_pt";	// ���� �� ����� ����Ʈ ȭ������ redirect
	        
		} catch (ExistingUserException e) {		// ���� �߻� �� ȸ������ form���� forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("pt", pt);
			return "/user/registerForm_PreparationForTurnover.jsp";
		}
	}

}
