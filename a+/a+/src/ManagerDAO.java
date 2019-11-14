import java.util.List;

public interface ManagerDAO {
	public ManagerDTO getManagerById(String id);
	public int insertManager(ManagerDTO mgr_num);
	public List<ManagerDTO> getManagerList();
}
