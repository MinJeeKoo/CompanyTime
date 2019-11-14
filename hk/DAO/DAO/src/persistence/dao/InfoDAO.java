package persistence.dao;
import java.util.List;
import service.dto.InfoDTO;

public interface InfoDAO {

	public List<InfoDTO> getInfoList();		
	public Integer insertInfo(InfoDTO Info);	
	public Integer updateInfo(InfoDTO Info);	
	public Integer deleteInfo(Integer iCode);		
	public InfoDTO getInfoByName(String iName);		
	public InfoDTO getInfoByCode(String iCode);		
	

}
