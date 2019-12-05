package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.*;

public class Matching_jwDAO {
	public Matching_jwDTO getMatchingW_ByP_ID(String p_id);
	public Matching_jwDTO getMatchingP_ByW_ID(String w_id);
	
	public int insertMatchingJW(Matching_jwDTO jw);
	public int deleteMatchingJW_ByP_ID(String p_id);
	public int deleteMatchingJW_ByW_ID(String w_id);
}
