package controller;

import java.util.HashMap;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;

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
        mappings.put("/user/list", new ListUserController());
        mappings.put("/user/list_pt", new ListPTController());
        mappings.put("/user/view", new ViewUserController());
        mappings.put("/user/view_pt", new ViewPTController());
        mappings.put("/user/main/form", new ForwardController("/user/main.jsp"));
//        mappings.put("/user/main_login/form", new ForwardController("/user/main_afterLogin.jsp"));
        mappings.put("/user/main_login/form", new MainLoginController());
        mappings.put("/user/registerW/form", new ForwardController("/user/registerForm_Worker.jsp"));
        mappings.put("/user/registerPT/form", new ForwardController("/user/registerForm_PreparationForTurnover.jsp"));
        mappings.put("/user/registerJS/form", new ForwardController("/user/registerForm_JobSeeker.jsp"));
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/update/form", new UpdateUserFormController());
        mappings.put("/user/update", new UpdateUser_JobSeekerController());
        mappings.put("/user/delete", new DeleteUserController());
        mappings.put("/user/selectType/form", new ForwardController("/user/selectTypeForm.jsp"));
        mappings.put("/search/rankingSearch", new ForwardController("/search/rankingSearch.jsp"));
    	mappings.put("/search/rankingSearch/json", new ListDepartmentJSONController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}
