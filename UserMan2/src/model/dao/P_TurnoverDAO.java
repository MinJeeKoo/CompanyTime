package model.dao;

import model.*;

import java.sql.SQLException;
import java.util.List;

public interface P_TurnoverDAO {
	public List<P_TurnoverDTO> getP_TurnoverList();
	public int create(P_TurnoverDTO pt) throws SQLException;
//	public int insertP_Turnover(P_TurnoverDTO pt);
	public int updateP_Turnover(P_TurnoverDTO pt);
	public int deleteP_Turnover(int p_id);
	public P_TurnoverDTO getP_TurnoverByName(String name);
	public P_TurnoverDTO getP_TurnoverById(String p_id);
}