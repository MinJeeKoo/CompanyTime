package model.dao;

import model.dto.*;
import java.util.List;

public interface JobSeekerDAO {
	public List<JobSeekerDTO> getJobSeekerList();		// ��ü ���ػ� ������ ȹ��
	public int insertJobSeeker(JobSeekerDTO js);	// ���ػ������� �߰�
	public int updateJobSeeker(JobSeekerDTO js);	// ���ػ������� ����
	public int deleteJobSeeker(int js_num);		// ���ػ������� ����
	public JobSeekerDTO getJobSeekerByName(String name);		// ���ػ� ������ �̸����� ã��
	public JobSeekerDTO getJobSeekerById(String id);		// ���ػ������� id�� ã��
}