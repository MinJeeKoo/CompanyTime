package model.dao;

import model.dto.*;
import java.util.List;

public interface P_TurnoverDAO {
	public List<P_TurnoverDTO> getP_TurnoverList();
	public int insertP_Turnover(P_TurnoverDTO pt);
	public int updateP_Turnover(P_TurnoverDTO pt);
	public int deleteP_Turnover(int p_id);
	public P_TurnoverDTO getP_TurnoverByName(String name);
	public P_TurnoverDTO getP_TurnoverById(String p_id);
}
