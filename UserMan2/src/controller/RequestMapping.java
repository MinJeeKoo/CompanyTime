package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.search.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 占쏙옙 占쏙옙청 uri占쏙옙 占쏙옙占쏙옙 controller 占쏙옙체占쏙옙 占쏙옙占쏙옙占쏙옙 HashMap 占쏙옙占쏙옙
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 占쏙옙 uri占쏙옙 占쏙옙占쏙옙占실댐옙 controller 占쏙옙체占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/list", new ListUserController());
        mappings.put("/user/list_pt", new ListPTController());
        //mappings.put("/user/view", new ViewUserController());
        mappings.put("/user/view_pt", new ViewPTController());
        mappings.put("/user/main/form", new ForwardController("/user/main.jsp"));
        mappings.put("/user/main_login", new ForwardController("/user/main_afterLogin.jsp"));
        mappings.put("/user/main_login/form", new MainLoginController());
        mappings.put("/user/registerW/form", new ForwardController("/user/registerForm_Worker.jsp"));
        mappings.put("/user/registerPT/form", new ForwardController("/user/registerForm_PreparationForTurnover.jsp"));
        mappings.put("/user/registerJS/form", new ForwardController("/user/registerForm_JobSeeker.jsp"));
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/register_pt", new RegisterPTController());
//        mappings.put("/user/update/form", new UpdateUserFormController());
        mappings.put("/user/updatePT/form", new UpdatePTFormController());
        mappings.put("/user/update", new UpdateUser_JobSeekerController());
        mappings.put("/user/updatePT", new UpdateUser_PTController());
        mappings.put("/user/delete", new DeleteUserController());
        mappings.put("/user/selectType/form", new ForwardController("/user/selectTypeForm.jsp"));
        mappings.put("/search/rankingSearch", new ForwardController("/search/rankingSearch.jsp"));
    	mappings.put("/search/rankingSearch/json", new ListDepartmentJSONController());
    	mappings.put("/search/google_api", new Api_MapController());
        
    	//誘쇱��� �곌껐��硫� 諛�袁멸린
    	mappings.put("/search/rankingSearchResult", new RankingSearchController());
    	
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 占쌍억옙占쏙옙 uri占쏙옙 占쏙옙占쏙옙占실댐옙 controller 占쏙옙체占쏙옙 찾占쏙옙 占쏙옙환
        return mappings.get(uri);
    }
}
