package DAO;

import java.util.List;

import service.dto.ManagerDTO;

public class ManagerDAO {
	public List<ManagerDTO> getManagerList();		// ��ü �Ŵ��� ������ ȹ��
	public int insertManager(ManagerDTO mgr_num);	// �Ŵ��������� �߰�
	public ManagerDTO getManagerById(String id);	// �Ŵ��������� id�� ã��

}