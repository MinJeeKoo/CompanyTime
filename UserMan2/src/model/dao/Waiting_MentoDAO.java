package model.dao;

import java.sql.SQLException;

import model.Waiting_MentoDTO;

public interface Waiting_MentoDAO {
	public int createWaitingList(Waiting_MentoDTO mto);	//sysdate로 넣고 orderby
	public boolean existingUserW(String w_id) throws SQLException;
	public int deleteWaiting(String w_id);
	public int getWaitingNumber(String w_id);
}
