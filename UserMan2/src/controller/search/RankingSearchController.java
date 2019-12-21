package controller.search;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import model.service.UserManager_search_rs;
import model.*;

public class RankingSearchController implements Controller {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
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
		
		logger.debug("getSearchList : {}", getSearchList);
		
		request.setAttribute("getSearchList", getSearchList);
		
		return "/search/rankingSearchResult.jsp";
	}

}
