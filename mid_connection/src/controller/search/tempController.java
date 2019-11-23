package controller.search;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager_search_rs;
import model.*;

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
		
		UserManager_search_rs manager = UserManager_search_rs.getInstance();
		
		List<ForSearchList> getSearchList = manager.get_SearchList(dept, category);
		
		request.setAttribute("getSearchList", getSearchList);
		
		return "/temp.jsp";
	}

}
