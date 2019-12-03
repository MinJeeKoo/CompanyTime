package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.*;

public interface InfoDAO {
	public int insertInfo(InfoDTO Info) throws SQLException;	
	public Integer updateInfo(InfoDTO Info);	
	public Integer deleteInfo(Integer i_num);		
	public InfoDTO getInfoByName(String iName);
	public InfoDTO getInfoByP_id(String p_id);
	public InfoDTO getInfoByW_id(String w_id);
	public Integer getI_numByP_id(String p_id);
	public Integer getI_numByW_id(String w_id);
	public List<ForSearchList> getSearchList(String cfd_name, String category) throws SQLException;
}
