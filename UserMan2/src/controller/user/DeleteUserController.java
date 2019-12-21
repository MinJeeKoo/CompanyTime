package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.P_TurnoverDTO;
import model.JobSeekerDTO;
import model.WorkerDTO;
import model.service.*;

public class DeleteUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)   throws Exception {
      String p_id = request.getParameter("p_id");
      String w_id = request.getParameter("w_id");
      String js_id = request.getParameter("js_id");

      InfoManager iManager = InfoManager.getInstance();

      HttpSession session = request.getSession();   

      if (p_id != null) {
         log.debug("Delete User : {}", p_id);

         UserManager_PT pt_manager = UserManager_PT.getInstance();


         if ((UserSessionUtils.isLoginUser("admin", session) &&    // 로그인한 사용자가 관리자이고    
               !p_id.equals("admin"))                     // 삭제 대상이 일반 사용자인 경우, 
               ||                                     // 또는 
               (!UserSessionUtils.isLoginUser("admin", session) &&  // 로그인한 사용자가 관리자가 아니고 
                     UserSessionUtils.isLoginUser(p_id, session))) { // 로그인한 사용자가 삭제 대상인 경우 (자기 자신을 삭제)

            Integer infoId = iManager.getI_numByP_id(p_id);
            iManager.deleteInfo(infoId);
            log.debug("Delete info : {}", infoId);
            pt_manager.remove(p_id);            // 사용자 정보 삭제
            if (UserSessionUtils.isLoginUser("admin", session))   // 로그인한 사용자가 관리자    
               return "redirect:/user/list_pt";      // 사용자 리스트로 이동
            else                            // 로그인한 사용자는 이미 삭제됨
               return "redirect:/user/logout";      // logout 처리
         }

         P_TurnoverDTO pt = pt_manager.findUser(p_id);   // 사용자 정보 검색
         request.setAttribute("pt", pt);                  
         request.setAttribute("deleteFailed", true);
         String msg = (UserSessionUtils.isLoginUser("admin", session)) 
               ? "시스템 관리자 정보는 삭제할 수 없습니다."      
                     : "타인의 정보는 삭제할 수 없습니다.";                                       
         request.setAttribute("exception", new IllegalStateException(msg));            
         return "/user/view_pt.jsp";
      }
      else if (w_id != null) {
         log.debug("Delete User : {}", w_id);

         UserManager_W w_manager = UserManager_W.getInstance();


         if ((UserSessionUtils.isLoginUser("admin", session) &&    // 로그인한 사용자가 관리자이고    
               !w_id.equals("admin"))                     // 삭제 대상이 일반 사용자인 경우, 
               ||                                     // 또는 
               (!UserSessionUtils.isLoginUser("admin", session) &&  // 로그인한 사용자가 관리자가 아니고 
                     UserSessionUtils.isLoginUser(w_id, session))) { // 로그인한 사용자가 삭제 대상인 경우 (자기 자신을 삭제)
        	log.debug("before iManager");
            Integer infoId = iManager.getI_numByW_id(w_id);
            log.debug("Delete info : {}", infoId);
            iManager.deleteInfo(infoId);
            w_manager.remove(w_id);            // 사용자 정보 삭제
            if (UserSessionUtils.isLoginUser("admin", session))   // 로그인한 사용자가 관리자    
               return "redirect:/user/list_w";      // 사용자 리스트로 이동
            else                            // 로그인한 사용자는 이미 삭제됨
               return "redirect:/user/logout";      // logout 처리
         }

         WorkerDTO w = w_manager.findUser(w_id);   // 사용자 정보 검색
         request.setAttribute("w", w);                  
         request.setAttribute("deleteFailed", true);
         String msg = (UserSessionUtils.isLoginUser("admin", session)) 
               ? "시스템 관리자 정보는 삭제할 수 없습니다."      
                     : "타인의 정보는 삭제할 수 없습니다.";                                       
         request.setAttribute("exception", new IllegalStateException(msg));            
         return "/user/view_w.jsp";
      }
      else if (js_id != null) {
         log.debug("Delete User : {}", js_id);

         UserManager_JS js_manager = UserManager_JS.getInstance();


         if ((UserSessionUtils.isLoginUser("admin", session) &&    // 로그인한 사용자가 관리자이고    
               !js_id.equals("admin"))                     // 삭제 대상이 일반 사용자인 경우, 
               ||                                     // 또는 
               (!UserSessionUtils.isLoginUser("admin", session) &&  // 로그인한 사용자가 관리자가 아니고 
                     UserSessionUtils.isLoginUser(js_id, session))) { // 로그인한 사용자가 삭제 대상인 경우 (자기 자신을 삭제)

            js_manager.remove(js_id);            // 사용자 정보 삭제
            if (UserSessionUtils.isLoginUser("admin", session))   // 로그인한 사용자가 관리자    
               return "redirect:/user/list_js";      // 사용자 리스트로 이동
            else                            // 로그인한 사용자는 이미 삭제됨
               return "redirect:/user/logout";      // logout 처리
         }

         JobSeekerDTO js = js_manager.findUser(js_id);   // 사용자 정보 검색
         request.setAttribute("js", js);                  
         request.setAttribute("deleteFailed", true);
         String msg = (UserSessionUtils.isLoginUser("admin", session)) 
               ? "시스템 관리자 정보는 삭제할 수 없습니다."      
                     : "타인의 정보는 삭제할 수 없습니다.";                                       
         request.setAttribute("exception", new IllegalStateException(msg));            
         return "/user/view_js.jsp";
      }
      return "/user/main.jsp";
    }
}