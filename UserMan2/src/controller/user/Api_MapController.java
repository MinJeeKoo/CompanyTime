package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;

public class Api_MapController implements Controller{
   private static final Logger log = LoggerFactory.getLogger(Api_MapController.class);
   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String companyName = request.getParameter("companyname");
      log.debug("This is company_name : {}", companyName);
      
      request.setAttribute("company_name", companyName);
      return "/search/google_api.jsp";
   }

}