package model.dao;

import java.sql.SQLException;

import model.*;

public interface Matching_jwDAO {
	public Matching_jwDTO getMatchingW_ByJS_ID(String js_id);
	public Matching_jwDTO getMatchingJS_ByW_ID(String w_id);
	
	public int deleteMatchingJW_ByJS_ID(String js_id) throws SQLException;
	public int deleteMatchingJW_ByW_ID(String w_id) throws SQLException;
	
	public boolean existingUserJS(String js_id) throws SQLException;
	public boolean existingUserW(String w_id) throws SQLException;
}
