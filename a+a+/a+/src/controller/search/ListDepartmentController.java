package controller.search;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.SearchManager;

public class ListDepartmentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String cf_name = request.getParameter("cf_name");

		if (cf_name != null) {
			SearchManager manager = SearchManager.getInstance();
			List<String> departmentList = manager.findDepartmentListByCf_name(cf_name);
			
			request.setAttribute("departmentList", departmentList);
		}
		
		return "/search/rankingSearch.jsp";
	}
}
