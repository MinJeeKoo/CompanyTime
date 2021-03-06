package model.dao;
import java.util.List;
import model.CompanyDTO;
import java.util.ArrayList;
import java.sql.*;
import model.dao.CompanyDAO;

public class CompanyDAOImpl implements CompanyDAO{

   
      JDBCUtil jdbcUtil = null;
      
      public CompanyDAOImpl(){
         jdbcUtil = new JDBCUtil();
      }
      
      

   public int insertCompany(CompanyDTO comp) throws SQLException {
      String insertQuery = "INSERT INTO COMPANY " + 
            "VALUES (sequence_company.NEXTVAL, ?)";
      String compName = comp.getC_NAME();

      
      Object[] param = new Object[] {compName};
      jdbcUtil.setSqlAndParameters(insertQuery, param);
      
      try {
         int result =jdbcUtil.executeUpdate();
         return result;
      }catch(Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }finally{
         jdbcUtil.commit();
         jdbcUtil.close();
      }
      return 0;
      
   }
   
   /*
   private Integer C_NUM = null;
   private String C_NAME = null;
   private String ADDRESS = null;
    */
   public Integer getC_NUMByC_NAME(String c_name) {
      
      Integer c_num = null;
      String query = "SELECT C_NUM "
            + "FROM COMPANY "
            + "WHERE C_NAME = ?" ;
      
      Object[] param = new Object[] {c_name};
      jdbcUtil.setSqlAndParameters(query, param);
      
      try {
         ResultSet result = jdbcUtil.executeQuery();
         while (result.next()) {
            c_num = result.getInt("C_NUM");
         }
         return c_num;
      }catch(Exception ex) {
         ex.printStackTrace();
      }finally{
         jdbcUtil.close();
      }
      return null;
   }
   
public String getC_NAMEByC_NUM(Integer c_num) {
      
      String c_name = null;
      String query = "SELECT C_NAME "
            + "FROM COMPANY "
            + "WHERE C_NUM = ?" ;
      
      Object[] param = new Object[] {c_num};
      jdbcUtil.setSqlAndParameters(query, param);
      
      try {
         ResultSet result = jdbcUtil.executeQuery();
         while (result.next()) {
            c_name = result.getString("C_NAME");
         }
         return c_name;
      }catch(Exception ex) {
         ex.printStackTrace();
      }finally{
         jdbcUtil.close();
      }
      return null;
   }
   
   public List<CompanyDTO> getCompanyList(){
      
      String query = " SELECT C_NUM, C_NAME FROM COMPANY; ";
      jdbcUtil.setSql(query);
      try {
         ResultSet rs = jdbcUtil.executeQuery();
         List<CompanyDTO> list = new ArrayList<CompanyDTO>();
         while(rs.next()) {
            CompanyDTO dto = new CompanyDTO(
                  rs.getInt("C_NUM"),
                  rs.getString("C_NAME")
            );
            list.add(dto);
         }
         return list;
      }catch(Exception ex) {
         ex.printStackTrace();
      }finally{
         jdbcUtil.close();
      }return null;
      
   }
}