package model.dao;

import model.*;

import java.sql.SQLException;
import java.util.List;

public interface WorkerDAO {
	public List<WorkerDTO> getWorkerList();
	public int create(WorkerDTO w) throws SQLException;
//	public int insertP_Turnover(WorkerDTO w);
	public int updateWorker(WorkerDTO w);
	public int deleteWorker(int w_id);
	public WorkerDTO getWorkerByName(String name);
	public WorkerDTO getWorkerById(String w_id);
}