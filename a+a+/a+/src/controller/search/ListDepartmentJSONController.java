package controller.search;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import controller.DispatcherServlet;
import model.service.SearchManager;

public class ListDepartmentJSONController implements Controller {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String cf_name = request.getParameter("cf_name");
		
		SearchManager manager = SearchManager.getInstance();
		
		List<String> departmentList = manager.findDepartmentListByCf_name(cf_name);

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(departmentList);
		logger.debug("departmentList in JSON : {}", jsonString);
		
		response.setContentType("applicatoin/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jsonString);

		return null;
	}
}
