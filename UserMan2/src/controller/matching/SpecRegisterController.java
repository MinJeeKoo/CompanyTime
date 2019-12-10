package controller.matching;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import model.SpecDTO;
import model.service.ExistingUserException;
import model.service.SpecManager;


public class SpecRegisterController implements Controller {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	
		String userId = request.getParameter("userId");
		String userType = request.getParameter("userType");
	
		String certification = request.getParameter("certification");
		String g = request.getParameter("grade"); // float
		String internship = request.getParameter("internship");
		String t = request.getParameter("toeic"); // Integer
		String toeic_speaking = request.getParameter("toeic_speaking");
		String opic = request.getParameter("opic");
		String contest = request.getParameter("contest");
		String awards = request.getParameter("awards");
		String study_abroad = request.getParameter("study_abroad");
		String volun = request.getParameter("volun");

		SpecDTO spec;

		Float grade = Float.parseFloat(g);
		Integer toeic = Integer.parseInt(t);
		
		if(userType.equals("w")) {
			spec = new SpecDTO(
					null, userId, null, certification, grade, 
					internship,	toeic, toeic_speaking, opic, contest, awards,
					study_abroad, volun	);
					
		}else if(userType.equals("pt")) {
			spec = new SpecDTO(
					userId, null, null, certification, grade, 
					internship,	toeic, toeic_speaking, opic, contest, awards,
					study_abroad, volun	);
			
		}else {
			spec = new SpecDTO(
					 null, null, userId, certification, grade, 
					internship,	toeic, toeic_speaking, opic, contest, awards,
					study_abroad, volun	);
		}
	
		SpecManager manager = SpecManager.getInstance();

		try {
			logger.debug("Create Spec: {}", spec);
			manager.create(spec);	// input data to Spec Table
		
	        return "/user/main_login/form";	// 성공 시 사용자 리스트 화면으로 redirect
	        
		} catch (Exception e) {		// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("spec", spec);
			return "/user/main_afterLogin.jsp";
		}
	}

}