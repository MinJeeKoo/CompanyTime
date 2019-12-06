package model.dao;

import java.sql.SQLException;

import model.*;

public interface Matching_jwDAO {
	public Matching_jwDTO getMatchingW_ByJS_ID(String js_id);
	public Matching_jwDTO getMatchingP_ByW_ID(String w_id);
	
	public int insertMatchingJW(Matching_jwDTO jw) throws SQLException;
	public int deleteMatchingJW_ByJS_ID(String js_id) throws SQLException;
	public int deleteMatchingJW_ByW_ID(String w_id) throws SQLException;
}
