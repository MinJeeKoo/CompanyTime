package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.SearchManager;
import model.service.UserManager_JS;
import model.P_TurnoverDTO;
import model.service.UserManager_PT;
import model.WorkerDTO;
import model.service.UserManager_W;
import model.service.UserNotFoundException;
import model.JobSeekerDTO;

public class ViewUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {         
       // 로그인 여부 확인
       if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";      // login form 요청으로 redirect
        }
   
      String userId = request.getParameter("userId");
      String userType = request.getParameter("userType");
      
       UserManager_W w_manager = UserManager_W.getInstance();
       WorkerDTO w_user = null;
       UserManager_PT pt_manager = UserManager_PT.getInstance();
       P_TurnoverDTO pt_user = null;
       UserManager_JS js_manager = UserManager_JS.getInstance();
       JobSeekerDTO js_user = null;
       
       SearchManager smanager = SearchManager.getInstance();
       
      try {
         if(userType.equals("w")) {
            w_user = w_manager.findUser(userId);
         }else if(userType.equals("pt")) {
            pt_user = pt_manager.findUser(userId);
         }else {
            js_user = js_manager.findUser(userId);
         }
            // 사용자 정보 검색
      } catch (UserNotFoundException e) {            
           return "redirect:/user/list";
      }   
      if(userType.equals("w")) {
         String c_name = smanager.getC_NAMEByC_NUM(w_user.getC_num());
          String cf_name = smanager.getCF_NAMEByCF_NUM(w_user.getCf_num());
          String cfd_name = smanager.getCFD_NAMEByCFD_NUM(w_user.getCfd_num());
          request.setAttribute("c_name", c_name);
          request.setAttribute("cf_name", cf_name);
          request.setAttribute("cfd_name", cfd_name);
         request.setAttribute("user", w_user);
         return "/user/view_w.jsp";
      }else if(userType.equals("pt")) {
         String c_name = smanager.getC_NAMEByC_NUM(pt_user.getC_num());
          String cf_name = smanager.getCF_NAMEByCF_NUM(pt_user.getCf_num());
          String cfd_name = smanager.getCFD_NAMEByCFD_NUM(pt_user.getCfd_num());
          request.setAttribute("c_name", c_name);
          request.setAttribute("cf_name", cf_name);
          request.setAttribute("cfd_name", cfd_name);
         request.setAttribute("user", pt_user);   
         return "/user/view_pt.jsp";
      }else {
         request.setAttribute("user", js_user);   
          String cf_name = smanager.getCF_NAMEByCF_NUM(js_user.getCf_num());

          request.setAttribute("cf_name", cf_name);
         return "/user/view_js.jsp";
      }
         // 사용자 정보 저장      
                  // 사용자 보기 화면으로 이동
    }
}