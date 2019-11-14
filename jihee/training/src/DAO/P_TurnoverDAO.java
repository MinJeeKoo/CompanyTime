package DAO;

import java.util.List;

import service.dto.P_TurnoverDTO;

public class P_TurnoverDAO {
	public List<P_TurnoverDTO> getP_TurnoverList();		// 전체 이직자 정보를 획득
	public int insertP_Turnover(P_TurnoverDTO pt);	// 이직자정보를 추가
	public int updateP_Turnover(P_TurnoverDTO pt);	// 이직자정보를 수정
	public int deleteP_Turnover(int p_num);		// 이직자정보를 삭제
	public P_TurnoverDTO getP_TurnoverByName(String name);		// 이직자정보를 이름으로 찾음
	public P_TurnoverDTO getP_TurnoverById(String id);		// 이직자정보를 id로 찾음

}
