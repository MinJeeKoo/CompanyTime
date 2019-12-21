package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import model.CompanyDTO;
import model.InfoDTO;
import model.P_TurnoverDTO;
import model.service.ExistingUserException;
import model.service.InfoManager;
import model.service.SearchManager;
import model.service.UserManager;
import model.service.UserManager_PT;

public class RegisterPTController implements Controller {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String cName = request.getParameter("company");
		String cfName = request.getParameter("cf_name_hope");
		String cfdName = request.getParameter("cfd_name");
		
		String income = request.getParameter("annual_income");
		String mood = request.getParameter("department_mood");
		String satisfaction = request.getParameter("job_satisfaction");
		String cafe = request.getParameter("cafeteria");
		String t_convenience = request.getParameter("traffic_convenience");
		String wellfare = request.getParameter("employee_wellfare");
		logger.debug("wellfare: " + wellfare);
		
		SearchManager smanager = SearchManager.getInstance();
		UserManager_PT umanager = UserManager_PT.getInstance();
		InfoManager imanager = InfoManager.getInstance();
		
		Integer c_num = smanager.getC_NUMByC_NAME(cName);
		Integer cf_num = smanager.getCF_NUMByCF_NAME(cfName);
		Integer cfd_num = smanager.getCFD_NUMByCFD_NAME(cfdName);
		
		if (c_num == null) {
			CompanyDTO comp = new CompanyDTO(null, cName, null);
			smanager.insertCompany(comp);
			c_num = smanager.getC_NUMByC_NAME(cName);
		}
		logger.debug("c_num :  " + c_num.toString());
		
		Integer annual_income = Integer.parseInt(income);
		Integer department_mood = Integer.parseInt(mood);
		Integer job_satisfaction = Integer.parseInt(satisfaction);
		Integer cafeteria = Integer.parseInt(cafe);
		Integer traffic_convenience = Integer.parseInt(t_convenience);
		Integer employee_wellfare = Integer.parseInt(wellfare);
		logger.debug("wellfare: " + employee_wellfare.toString());
		
		P_TurnoverDTO pt = new P_TurnoverDTO(
				request.getParameter("userId"),
				request.getParameter("password"),
				request.getParameter("name"),
				c_num, cf_num, cfd_num,
				request.getParameter("email"),
				Integer.valueOf(0)
		);
		
		/* pid, wid have to get from register select button */
		/* how to get sequence iNo(pk)? 
		 * 
		 * exclude iNo from THIS constructor*/
		InfoDTO info = new InfoDTO(
				request.getParameter("userId"), null, 
				cName, cfdName,
				annual_income, department_mood, job_satisfaction, 
				cafeteria, traffic_convenience, employee_wellfare
		);
				
		logger.debug("Create pt: {}", pt);
		logger.debug("Create info: {}", info);
		
		try {
			umanager.create(pt);	// input data to PT Table
			logger.debug("Create : PT created");
			imanager.insertInfo(info);	// input data to Info Table
			logger.debug("Create: INFO created");
	        return "redirect:/user/list_pt";	// ���� �� ����� ����Ʈ ȭ������ redirect
	        
		} catch (ExistingUserException e) {		// ���� �߻� �� ȸ������ form���� forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("pt", pt);
			return "/user/registerForm_PreparationForTurnover.jsp";
		}
	}

}
