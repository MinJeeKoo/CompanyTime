package model.service;

import model.dao.WorkerDAOImpl;

public class UserAnalysis_W {
	private WorkerDAOImpl dao;
	
	public UserAnalysis_W() {}
	
	public UserAnalysis_W(WorkerDAOImpl dao) {
		super();
		this.dao = dao;
	}

//	// an example business method
//	public List<P_TurnoverDTO> recommendFriends(String p_id) throws Exception {
//		P_TurnoverDTO thisuser = dao.findUser(p_id);
//		if (thisuser == null) {
//			throw new Exception("invalid user ID!");
//		}
//		int m1 = p_id.indexOf('@');
//		if (m1 == -1) return null;
//		String mserver1 = thisuser.getEmail().substring(m1);
//		
//		List<P_TurnoverDAOImpl> friends = new ArrayList<P_TurnoverDAOImpl>();
//		
//		List<P_TurnoverDAOImpl> userList = dao.findUserList(1, 10000);
//		Iterator<P_TurnoverDAOImpl> userIter = userList.iterator();		
//		while (userIter.hasNext()) {
//			P_TurnoverDAOImpl user = (P_TurnoverDAOImpl)userIter.next();
//			
//			if (user.getUserId().equals(userId)) continue;
//			int m2 = user.getEmail().indexOf('@');
//			if (m2 == -1) continue;
//			String mserver2 = user.getEmail().substring(m2);
//
//			if (mserver1.equals(mserver2)) 
//				friends.add(user);		
//		}
//		return friends;
//	}

}