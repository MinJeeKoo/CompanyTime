package controller.search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class tempController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String category = request.getParameter("category");
		String field = request.getParameter("cf_name");
		String dept = request.getParameter("cfd_name");
		
		request.setAttribute("category", category);
		request.setAttribute("field", field);
		request.setAttribute("dept", dept);
		
		return "/temp.jsp";
	}

}
