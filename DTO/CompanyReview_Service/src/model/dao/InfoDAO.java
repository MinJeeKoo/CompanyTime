package model.dao;
import java.util.List;
import dto.InfoDTO;

public interface InfoDAO {

	public List<InfoDTO> getInfoList();		
	public Integer insertInfo(InfoDTO Info);	
	public Integer updateInfo(InfoDTO Info);
	public Integer deleteInfo(Integer iCode);		
	public InfoDTO getInfoByName(String iName);		
	public InfoDTO getInfoByCode(String iCode);		
	

}