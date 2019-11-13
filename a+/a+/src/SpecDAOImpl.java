import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SpecDAOImpl implements SpecDAO {

	private JDBCUtil jdbcUtil = null;
//	private static String query = "SELECT "
	
	public SpecDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}
	
//	@Override
//	public List<SpecDTO> getSpecList() {
//		// TODO Auto-generated method stub
//		String allQuery = 
//		
//		return null;
//	}

	@Override
	public SpecDTO getSpecByNum(String specNo) {
		// TODO Auto-generated method stub
		String query = "SELECT certification AS certification, grade AS grade, internship AS internship, "
				+ "toeic AS toeic, opic AS opic, contest AS contest, "
				+ "awards AS awards, study_abroad AS study_abroad, volun AS volun, toeic_speaking AS toeic_speaking "
						+ " FROM spec WHERE spec_num = ? ";
		
		SpecDTO s = null;
		jdbcUtil.setSql(query);
		Object[] param = new Object[] { specNo };
		jdbcUtil.setParameters(param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				s = new SpecDTO();
				s.setCertification(rs.getString("certification"));
				s.setGrade(rs.getFloat("grade"));
				s.setInternship(rs.getString("internship"));
				s.setToeic(rs.getInt("toeic"));
				s.setOpic(rs.getString("opic"));
				s.setContest(rs.getString("contest"));
				s.setAwards(rs.getString("awards"));
				s.setStudy_abroad(rs.getString("study_abroad"));
				s.setVolun(rs.getString("volun"));
				s.setToeic_speaking(rs.getInt("toeic_speaking"));
			}
			return s;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return null;
	}

	@Override
	public int insertSpec(SpecDTO sp) {
		// TODO Auto-generated method stub
		int result = 0;
		String query = "INSERT INTO SPEC (spec_num, p_num, w_num, js_num, certification, grade, internship toeic, opic, contest, awards, study_abroad, volun, toeic_speaking)"
						+ " VALUES (sequence_spec.nextval(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		DAOFactory factory = new DAOFactory();
		
		Object[] param = new Object[] { sp.getP_num(), sp.getW_num(), sp.getJs_num(), sp.getCertification(), sp.getGrade(), sp.getInternship(), sp.getToeic(), sp.getOpic(), sp.getContest(), sp.getAwards(), sp.getStudy_abroad(), sp.getVolun(), sp.getToeic_speaking() };
		
		jdbcUtil.setSql(query);
		jdbcUtil.setParameters(param);
		try {
			result = jdbcUtil.executeUpdate();
		} catch(SQLException e) {
			if (e.getErrorCode() == 1) {
				System.out.println("중복된 정보");
			}
		} catch(Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		
		return result;
	}

	//숫자 널처리 -1?
	@Override
	public int updateSpec(SpecDTO sp) {
		// TODO Auto-generated method stub
		String query = "UPDATE SPEC SET ";
		int index = 0;
		// 왜 14갠지 모름
		Object[] tempParam = new Object[14];
		
		if (sp.getCertification() != null) {
			query += "certification = ?, ";
			tempParam[index++] = sp.getCertification();
		}
		if (sp.getGrade() != null) {
			query += "grade = ?, ";
			tempParam[index++] = sp.getGrade();
		}
		if (sp.getInternship() != null) {
			query += "internship = ?, ";
			tempParam[index++] = sp.getInternship();
		}
		if (sp.getToeic() != null) {
			query += "toeic = ?, ";
			tempParam[index++] = sp.getToeic();
		}
		if (sp.getOpic() != null) {
			query += "opic = ?, ";
			tempParam[index++] = sp.getOpic();
		}
		if (sp.getContest() != null) {
			query += "contest = ?, ";
			tempParam[index++] = sp.getContest();
		}
		if (sp.getAwards() != null) {
			query += "awards = ?, ";
			tempParam[index++] = sp.getAwards();
		}
		if (sp.getStudy_abroad() != null) {
			query += "study_abroad = ?, ";
			tempParam[index++] = sp.getStudy_abroad();
		}
		if (sp.getVolun() != null) {
			query += "volun = ?, ";
			tempParam[index++] = sp.getVolun();
		}
		if (sp.getToeic_speaking() != null) {
			query += "toeic_speaking = ?, ";
			tempParam[index++] = sp.getToeic_speaking();
		}
		query += "WHERE spec_num = ? ";
		query += query.replace(", WHERE", " WHERE");
		
		tempParam[index++] = sp.getSpec_num();
		
		Object[] newParam = new Object[index];
		for (int i = 0; i < newParam.length; i++) {
			newParam[i] = tempParam[i];
		}
		
		jdbcUtil.setSql(query);
		jdbcUtil.setParameters(newParam);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	@Override
	public int deleteSpec(int spec_num) {
		// TODO Au to-generated method stub
		String query = "DELETE FROM spec WHERE spec_num = ?";
		
		jdbcUtil.setSql(query);
		Object[] param = new Object[] { spec_num };
		jdbcUtil.setParameters(param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		
		return 0;
	}
	
	public int getSpecNumByP_num(String pNum) {
		String query = "SELECT spec_num AS spec_num "
				+ "FROM spec WHERE p_Id = ? ";

		jdbcUtil.setSql(query);
		Object[] param = new Object[] { pNum };
		jdbcUtil.setParameters(param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt("spec_num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return -1;
	}
	
	public int getSpecNumByW_num(String wId) {
		String query = "SELECT spec_num AS spec_num "
				+ "FROM spec WHERE w_Id = ? ";

		jdbcUtil.setSql(query);
		Object[] param = new Object[] { wId };
		jdbcUtil.setParameters(param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt("spec_num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return -1;
	}
	
	public int getSpecNumByJS_num(String jsId) {
		String query = "SELECT spec_num AS spec_num"
				+ "FROM spec WHERE pId = ? ";

		jdbcUtil.setSql(query);
		Object[] param = new Object[] { jsId };
		jdbcUtil.setParameters(param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt("spec_num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}

		return -1;
	}
}
