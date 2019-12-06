package model.dao;

import java.sql.SQLException;

import model.Matching_twDTO;

public interface Matching_twDAO {
	public Matching_twDTO getMatchingW_ByP_ID(String p_id);
	public Matching_twDTO getMatchingP_ByW_ID(String w_id);
	
	public int insertMatchingTW(Matching_twDTO tw) throws SQLException;
	public int deleteMatchingTW_ByP_ID(String p_id) throws SQLException;
	public int deleteMatchingTW_ByW_ID(String w_id) throws SQLException;
}
