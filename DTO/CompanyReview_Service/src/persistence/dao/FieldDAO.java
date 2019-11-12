package persistence.dao;

import dto.FieldDTO;

public interface FieldDAO {
	public int insertField(FieldDTO field);
	public int updateField (FieldDTO field, String change);
}
