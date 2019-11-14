import java.util.List;

public interface JobSeekerDAO {
	public List<JobSeekerDTO> getJobSeekerList();		// 전체 취준생 정보를 획득
	public int insertJobSeeker(JobSeekerDTO js);	// 취준생정보를 추가
	public int updateJobSeeker(JobSeekerDTO js);	// 취준생정보를 수정
	public int deleteJobSeeker(int js_num);		// 취준생정보를 삭제
	public JobSeekerDTO getJobSeekerByName(String name);		// 취준생 정보를 이름으로 찾음
	public JobSeekerDTO getJobSeekerById(String id);		// 취준생정보를 id로 찾음
}
