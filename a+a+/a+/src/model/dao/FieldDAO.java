package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.dto.FieldDTO;

public interface FieldDAO {
	public Integer getCF_NUMByCF_NAME(String cf_name);
	public List<FieldDTO> getFieldList() throws SQLException;
}
