package model.dao;

import java.sql.SQLException;
import model.Waiting_MenteeDTO;

public interface Waiting_MenteeDAO {
	public int createWaitingList(Waiting_MenteeDTO mt);	//sysdate로 넣고 orderby
	public boolean existingUserPT(String p_id) throws SQLException;
	public boolean existingUserJS(String js_id) throws SQLException;
	public int deleteWaitingByPid(String p_id);
	public int deleteWaitingByJSid(String js_id);
	public int getWaitingNumberPT(String p_id);
	public int getWaitingNumberJS(String js_id);
}
