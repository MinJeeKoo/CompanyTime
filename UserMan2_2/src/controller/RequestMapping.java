package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.user.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// �� uri�� �����Ǵ� controller ��ü�� ���� �� ����
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/list", new ListUserController());
        mappings.put("/user/view", new ViewUserController());
        
        mappings.put("/user/main/form", new ForwardController("/user/main.jsp"));
        
        mappings.put("/user/registerW/form", new ForwardController("/user/registerForm_Worker.jsp"));
        mappings.put("/user/registerPT/form", new ForwardController("/user/registerForm_PreparationForTurnover.jsp"));
        mappings.put("/user/registerJS/form", new ForwardController("/user/registerForm_JobSeeker.jsp"));
        mappings.put("/user/register", new RegisterUserController());
        
        mappings.put("/user/update/form", new UpdateUserFormController());
        mappings.put("/user/update", new UpdateUser_JobSeekerController());
        mappings.put("/user/delete", new DeleteUserController());
        mappings.put("/user/selectType/form", new ForwardController("/user/selectTypeForm.jsp"));
//        mappings.put("/user/selectType", new SelectTypeUserController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
        return mappings.get(uri);
    }
}
