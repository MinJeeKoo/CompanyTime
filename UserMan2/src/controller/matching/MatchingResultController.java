package controller.matching;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.JobSeekerDTO;
import model.Matching_jwDTO;
import model.Matching_twDTO;
import model.P_TurnoverDTO;
import model.Waiting_MenteeDTO;
import model.Waiting_MentoDTO;
import model.WorkerDTO;
import model.service.MatchingManager;
import model.service.UserManager_JS;
import model.service.UserManager_PT;
import model.service.UserManager_W;
import model.service.WaitingMatchingException;

public class MatchingResultController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String userId = (String) request.getAttribute("userId");
		String userType = (String) request.getAttribute("userType");
		
		MatchingManager manager = MatchingManager.getInstance();
		UserManager_W wmanager = UserManager_W.getInstance();
		UserManager_PT ptmanager = UserManager_PT.getInstance();
		UserManager_JS jsmanager = UserManager_JS.getInstance();
		
		
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
			request.setAttribute("mt", mento);
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
			request.setAttribute("mt", mento);
			return "/matching/showMatchingResult.jsp";
		} else {
			String menteeID = null;
			P_TurnoverDTO pt = null;
			JobSeekerDTO js = null;
			try {
				menteeID = manager.getMentee(userId);
				js = jsmanager.findUser(menteeID);
				pt = ptmanager.findUser(menteeID);
			} catch (WaitingMatchingException e) {
				e.getStackTrace();
			}
			request.setAttribute("userType", userType);
			request.setAttribute("userId", userId);
			if (js != null) {
				request.setAttribute("js", js);
			} else if (pt != null) {
				request.setAttribute("pt", pt);
			}
			return "/matching/showMatchingResult.jsp";
		}
	}

}
