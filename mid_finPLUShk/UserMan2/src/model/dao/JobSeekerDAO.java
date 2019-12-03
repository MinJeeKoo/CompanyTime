package model.dao;

import model.*;

import java.sql.SQLException;
import java.util.List;

public interface JobSeekerDAO {
	public List<JobSeekerDTO> getJobSeekerList();
	public int create(JobSeekerDTO js) throws SQLException;
//	public int insertP_Turnover(JobSeekerDTO pt);
	public int updateJobSeeker(JobSeekerDTO js);
	public int deleteJobSeeker(int js_id);
	public JobSeekerDTO getJobSeekerByName(String name);
	public JobSeekerDTO getJobSeekerById(String js_id);
}