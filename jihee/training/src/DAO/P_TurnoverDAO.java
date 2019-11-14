package DAO;

import java.util.List;

import service.dto.P_TurnoverDTO;

public class P_TurnoverDAO {
	public List<P_TurnoverDTO> getP_TurnoverList();		// ��ü ������ ������ ȹ��
	public int insertP_Turnover(P_TurnoverDTO pt);	// ������������ �߰�
	public int updateP_Turnover(P_TurnoverDTO pt);	// ������������ ����
	public int deleteP_Turnover(int p_num);		// ������������ ����
	public P_TurnoverDTO getP_TurnoverByName(String name);		// ������������ �̸����� ã��
	public P_TurnoverDTO getP_TurnoverById(String id);		// ������������ id�� ã��

}