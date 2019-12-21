package controller.matching;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import controller.user.UserSessionUtils;
import model.JobSeekerDTO;
import model.Matching_jwDTO;
import model.Matching_twDTO;
import model.P_TurnoverDTO;
import model.Waiting_MenteeDTO;
import model.Waiting_MentoDTO;
import model.WorkerDTO;
import model.service.MatchingManager;
import model.service.SearchManager;
import model.service.UserManager_JS;
import model.service.UserManager_PT;
import model.service.UserManager_W;
import model.service.WaitingMatchingException;

public class MatchingResultController implements Controller{
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        String userId = UserSessionUtils.getLoginUserId(session);
        String userType = UserSessionUtils.getLoginUserType(session);
		
		MatchingManager manager = MatchingManager.getInstance();
		UserManager_W wmanager = UserManager_W.getInstance();
		UserManager_PT ptmanager = UserManager_PT.getInstance();
		UserManager_JS jsmanager = UserManager_JS.getInstance();
		SearchManager smanager = SearchManager.getInstance();
		
		logger.debug("userType: {}", userType);
		
		if (userType.equals("pt")) {
			Matching_twDTO tw = null;
			WorkerDTO mento = null;
			try {
				tw = manager.getMatchingW_ByP_ID(userId);
				mento = wmanager.findUser(tw.getW_ID());
			} catch (WaitingMatchingException e) {
				e.getStackTrace();
			}
			request.setAttribute("userType", userType);
			request.setAttribute("userId", userId);
			request.setAttribute("mento", mento);
			return "/matching/showMatchingResult.jsp";
		} else if (userType.equals("js")) {
			Matching_jwDTO jw = null;
			WorkerDTO mento = null;
			try {
				jw = manager.getMatchingW_ByJS_ID(userId);
				mento = wmanager.findUser(jw.getW_ID());
			} catch (WaitingMatchingException e) {
				e.getStackTrace();
			}
			request.setAttribute("userType", userType);
			request.setAttribute("userId", userId);
			request.setAttribute("mento", mento);
			return "/matching/showMatchingResult.jsp";
		} else {
			
//			if (!manager.w_isMatching(userId)) {
//				return "/user/main";
//			}
			
			String menteeID = null;
			P_TurnoverDTO pt = null;
			JobSeekerDTO js = null;
			try {
				menteeID = manager.getMentee(userId);
				js = jsmanager.findUser(menteeID);
				if (js == null) {
					pt = ptmanager.findUser(menteeID);
				}
			} catch (WaitingMatchingException e) {
				e.getStackTrace();
			}
			request.setAttribute("userType", userType);
			request.setAttribute("userId", userId);
			request.setAttribute("mentee", menteeID);
			if (js != null) {
				request.setAttribute("js", js);
			} else if (pt != null) {
				request.setAttribute("pt", pt);
			}
			return "/matching/showMatchingResult.jsp";
		}
	}

}
