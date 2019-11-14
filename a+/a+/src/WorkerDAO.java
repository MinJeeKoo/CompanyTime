import java.util.List;

public interface WorkerDAO {
	public List<WorkerDTO> getWorkerList();
	public int insertWorker(WorkerDTO w);
	public int updateWorker(WorkerDTO w);
	public int deleteWorker(int w_num);
	public WorkerDTO getWorkerByName(String name);
	public WorkerDTO getWorkerById(String id);
}
