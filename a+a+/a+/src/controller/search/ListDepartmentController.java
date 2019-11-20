package controller.search;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dto.FieldDTO;
import model.service.SearchManager;

public class ListDepartmentController implements Controller {
	private final static Logger logger = Logger.getGlobal();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SearchManager manager = SearchManager.getInstance();
		String cf_num = request.getAttribute("cf_name");
		
		
//		List<FieldDTO> fieldList = manager.getFieldList();
//
//		for (int i = 0; i < fieldList.size(); i++) {
//			String name = fieldList.get(i).getCF_NAME();
//			request.setAttribute(name, manager.findDepartmentListByCf_name(name));
//		}

		return "/search/rankingSearch.jsp";
	}
}
