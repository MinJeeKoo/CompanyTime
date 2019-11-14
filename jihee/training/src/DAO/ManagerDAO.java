package DAO;

import java.util.List;

import service.dto.ManagerDTO;

public class ManagerDAO {
	public List<ManagerDTO> getManagerList();		// 전체 매니저 정보를 획득
	public int insertManager(ManagerDTO mgr_num);	// 매니저정보를 추가
	public ManagerDTO getManagerById(String id);	// 매니저정보를 id로 찾음

}
