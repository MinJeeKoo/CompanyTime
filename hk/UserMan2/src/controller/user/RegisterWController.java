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
import model.WorkerDTO;
import model.service.ExistingUserException;
import model.service.InfoManager;
import model.service.SearchManager;

import model.service.UserManager_W;

public class RegisterWController implements Controller {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String cName = request.getParameter("company");
		String cfName = request.getParameter("cf_name");
		String cfdName = request.getParameter("cfd_name");
		
		String income = request.getParameter("annual_income");
		String mood = request.getParameter("department_mood");
		String satisfaction = request.getParameter("job_satisfaction");
		String cafe = request.getParameter("cafeteria");
		String t_convenience = request.getParameter("traffic_convenience");
		String wellfare = request.getParameter("employee_wellfare");
		logger.debug("wellfare: " + wellfare);
		
		SearchManager smanager = SearchManager.getInstance();
		UserManager_W wmanager = UserManager_W.getInstance();
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
		
		WorkerDTO w = new WorkerDTO(
				request.getParameter("userId"),
				request.getParameter("password"),
				request.getParameter("name"),
				c_num, cf_num, cfd_num,
				request.getParameter("email"),
				Integer.valueOf(0),
				request.getParameter("empno")
		);
		
		InfoDTO info = new InfoDTO(
				null, request.getParameter("userId"), 
				cName, cfdName,
				annual_income, department_mood, job_satisfaction, 
				cafeteria, traffic_convenience, employee_wellfare
		);
				
		logger.debug("Create w: {}", w);
		logger.debug("Create info: {}", info);
		
		try {
			wmanager.create(w);	// input data to PT Table
			logger.debug("Create : W created");
			imanager.insertInfo(info);	// input data to Info Table
			logger.debug("Create: INFO created");
	        return "redirect:/user/list_w";	// ���� �� ����� ����Ʈ ȭ������ redirect
	        
		} catch (ExistingUserException e) {		// ���� �߻� �� ȸ������ form���� forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("w", w);
			return "/user/registerForm_Worker.jsp";
		}
	}

}
