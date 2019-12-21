package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.matching.*;
import controller.search.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
 // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        
        mappings.put("/user/list_w", new ListWController());
        mappings.put("/user/list_pt", new ListPTController());
        mappings.put("/user/list_js", new ListJSController());
        
        mappings.put("/user/view", new ViewUserController());
   
        mappings.
        mappings.put("/user/main/form", new ForwardController("/user/main.jsp"));
        mappings.put("/user/main_login", new ForwardController("/user/main_afterLogin.jsp"));
        mappings.put("/user/main_login/form", new MainLoginController());
        
        mappings.put("/user/registerW/form", new ForwardController("/user/registerForm_Worker.jsp"));
        mappings.put("/user/registerPT/form", new ForwardController("/user/registerForm_PreparationForTurnover.jsp"));
        mappings.put("/user/registerJS/form", new ForwardController("/user/registerForm_JobSeeker.jsp"));
       
        mappings.put("/user/register_w", new RegisterWController());
        mappings.put("/user/register_pt", new RegisterPTController());
        mappings.put("/user/register_js", new RegisterJSController());

//        mappings.put("/user/update/form", new UpdateUserFormController());
        mappings.put("/user/updatePT/form", new UpdatePTFormController());
        mappings.put("/user/updateJS/form", new UpdateJSFormController());
        mappings.put("/user/updateW/form", new UpdateWFormController());
        mappings.put("/user/updateJS", new UpdateUser_JSController());
        mappings.put("/user/updatePT", new UpdateUser_PTController());
        mappings.put("/user/updateW", new UpdateUser_WController());
        mappings.put("/user/delete", new DeleteUserController());
        mappings.put("/user/selectType/form", new ForwardController("/user/selectTypeForm.jsp"));
        mappings.put("/search/rankingSearch", new ForwardController("/search/rankingSearch.jsp"));
    	mappings.put("/search/rankingSearch/json", new ListDepartmentJSONController());
        
    	//랭킹출력
    	mappings.put("/search/rankingSearchResult", new RankingSearchController());
    	
    	//matching controller
    	mappings.put("/matching/recommend", new MatchingController());
    	mappings.put("/matching/recommend/Result", new MatchingResultController());
    	mappings.put("/matching/register/form", new ForwardController("/matching/registerForm_Matching.jsp"));
    	
    	mappings.put("/matching/register", new SpecRegisterController());
    	
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
