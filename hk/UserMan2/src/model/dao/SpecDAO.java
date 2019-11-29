package model.dao;

import model.*;

public interface SpecDAO {
	public SpecDTO getSpecByNum(String specNo);
	public int insertSpec(SpecDTO sp);
	public int updateSpec(SpecDTO sp);
	public int deleteSpec(int spec_num);
	public int getSpecNumByP_num(String pId);
	public int getSpecNumByW_num(String wId);
	public int getSpecNumByJS_num(String jsId);
}
