package model.dao;

import java.util.List;
import model.DepartmentDTO;
import java.util.ArrayList;
import java.sql.*;
import model.dao.DepartmentDAO;
import model.dao.FieldDAO;

public class DepartmentDAOImpl implements DepartmentDAO{

   JDBCUtil jdbcUtil = null;
   
   public DepartmentDAOImpl(){
      jdbcUtil = new JDBCUtil();
   }
   
   /*회사마다 DEPARTMENT를 만드는 것인가,,,,?이상하다..
    private Integer CFD_NUM = null;
   private Integer CF_NUM = null;
   private String CFD_NAME = null;
   private Integer ANNULAL_INCOME = null;....?
    */
   @Override
   public List<DepartmentDTO> getDepartmentList() {
      String query = "SELECT CFD_NUM, CF_NUM, CFD_NAME FROM DEPARTMENT; ";
      jdbcUtil.setSqlAndParameters(query, null);
      try {
         ResultSet rs = jdbcUtil.executeQuery();
         List<DepartmentDTO> list = new ArrayList<DepartmentDTO>();
         while(rs.next()) {
            DepartmentDTO dto = new DepartmentDTO();
            dto.setCFD_NUM(rs.getInt("CFD_NUM"));
            dto.setCF_NUM(rs.getInt("CF_NUM"));
            dto.setCFD_NAME(rs.getString("CFD_NAME"));
            list.add(dto);
         }
         return list;
      }catch(Exception ex) {
         ex.printStackTrace();
      }finally{
         jdbcUtil.close();
      }
      return null;
   }
   public String getCFD_NAMEByCFD_NUM(Integer cfd_num) {
      
      String cfd_name = null;
      String query = "SELECT CFD_NAME "
            + "FROM DEPARTMENT "
            + "WHERE CFD_NUM = ?" ;
      
      Object[] param = new Object[] {cfd_num};
      jdbcUtil.setSqlAndParameters(query, param);
      
      try {
         ResultSet result = jdbcUtil.executeQuery();
         while (result.next()) {
            cfd_name = result.getString("CFD_NAME");
         }
         return cfd_name;
      }catch(Exception ex) {
         ex.printStackTrace();
      }finally{
         jdbcUtil.close();
      }
      return null;
   }
   @Override
   public Integer getCFD_NUMByCFD_NAME(String cfd_name) throws SQLException{
      Integer cfd_num = null;//Integer 타입이라 null로 초기화함.
      String query = "SELECT CFD_NUM "
            + "FROM DEPARTMENT "
            + "WHERE CFD_NAME = ?" ;
      
      Object[] param = new Object[] {cfd_name};
      jdbcUtil.setSqlAndParameters(query, param);
      
      try {
         ResultSet result = jdbcUtil.executeQuery();
         while (result.next()) {
            cfd_num = result.getInt("CFD_NUM");
         }
         return cfd_num;
      }catch(Exception ex) {
         ex.printStackTrace();
      }finally{
         jdbcUtil.close();
      }
      return null;
   }

   //구현하기
   public List<String> findDepartmentListByCf_name(String cf_name) throws SQLException {
      String query = "SELECT cfd_name FROM department WHERE cf_num = ? ";
      
      DAOFactory factory = new DAOFactory();      // 취준생정보와 cf(필드)정보를 알아오기 위해 DAO 객체를 생성하는 factory 객체 생성
      
      // JobSeekerDAO 객체를 생성하여 취준생 에 포함되어 있는 field의 cf_num을 알아옴
      //***********CfDAO -> 이름 확인 필요, getCfById메소드 존재하는지 확인 필요
      FieldDAO cfDAO = factory.getFieldDAO();      
      Integer cf_num = cfDAO.getCF_NUMByCF_NAME(cf_name);   // cf_num을 설정
      
      Object[] param = new Object[] {cf_num};
      jdbcUtil.setSqlAndParameters(query, param);
      
      try { 
         ResultSet rs = jdbcUtil.executeQuery();      // query 문 실행         
         List<String> list = new ArrayList<String>();      // JobSeekerDTO 객체들을 담기위한 list 객체
         while (rs.next()) {   
            list.add(rs.getString("cfd_name"));      // list 객체에 정보를 설정한 JobSeekerDTO 객체 저장
         }
         return list;      // 취준생정보를 저장한 dto 들의 목록을 반환
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
      }      
      return null;   
   }
}