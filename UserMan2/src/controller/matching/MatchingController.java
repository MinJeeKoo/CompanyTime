package controller.matching;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.DispatcherServlet;
import controller.user.UserSessionUtils;
import model.Waiting_MenteeDTO;
import model.Waiting_MentoDTO;
import model.service.MatchingManager;
import model.service.UserManager_JS;
import model.service.UserManager_PT;
import model.service.UserManager_W;

public class MatchingController implements Controller {
	private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//스펙작성 여부 확인
		String userId = request.getParameter("userId");
		String userType = request.getParameter("userType");
		logger.debug("userType: {}", userType);


		MatchingManager mmanager = MatchingManager.getInstance();
		
		if (userType.equals("pt")) { //이직자 - 멘티
			UserManager_PT manager_pt = UserManager_PT.getInstance();
			if(manager_pt.check_PId(userId) != -1) { //스펙작성을 했으면
				/*
				 * 매칭버튼누름 -> 
					스펙작성했으면 -> 
					본인을 매칭대기리스트에 넣고(controller에서) 
					(이미 매칭대기리스트에 있으면 return 0;)
					(멘토 : waiting_mento / 멘티 : waiting_mentee) 
					매칭하기 ->
				 */
				Waiting_MenteeDTO mt = new Waiting_MenteeDTO(userId, null, manager_pt.findUser(userId).getCf_num());
				manager_pt.createWaitingList(mt);
				
				//분야 같은 멘토-멘티 랜덤 매칭하기
				
				if (mmanager.pt_isMatching(userId)) {
					return "/matching/recommend/Result";
				}
				
				mmanager.insert();
				
				//매칭결과 보여주는 창으로 넘어가기 - 연제
			
				return "/matching/recommend/Result";
			}
		} else if (userType.equals("js")) { //취준생 - 멘티
			UserManager_JS manager_js = UserManager_JS.getInstance();
			if(manager_js.check_JSId(userId) != -1) {
				Waiting_MenteeDTO mt = new Waiting_MenteeDTO(null, userId, manager_js.findUser(userId).getCf_num());
				manager_js.createWaitingList(mt);
				
				if (mmanager.js_isMatching(userId)) {
					return "/matching/recommend/Result";
				}
				
				mmanager.insert();				

				//매칭결과 보여주는 창으로 넘어가기 - 연제
			
				return "/matching/recommend/Result";
			}
		} else { //현직자 - 멘토
			UserManager_W manager_w = UserManager_W.getInstance();
			if(manager_w.check_WId(userId) != -1) {
				Waiting_MentoDTO mto = new Waiting_MentoDTO(userId, manager_w.findUser(userId).getCf_num());
				manager_w.createWaitingList(mto);
				//매칭결과 보여주는 창으로 넘어가기 - 연제
				
				return "/matching/recommend/Result";
			}
		}

		// 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));	
		
		// 로그인한 사용자 type을 request에 저장하여 전달
		request.setAttribute("curUserType", 
				UserSessionUtils.getLoginUserType(request.getSession()));
		
		
		//스펙작성을 안했으면 스펙작성하는 화면으로 넘어가기 - 혜경
		return "/matching/register/form";

	}

}

