package model.dao;

import model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.DispatcherServlet;

public class InfoDAOImpl implements InfoDAO {
   private final static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
   
   private JDBCUtil jdbcUtil = null;
   
   public InfoDAOImpl() {
      jdbcUtil = new JDBCUtil();
   }

   public int insertInfo(InfoDTO Info) throws SQLException {
         // TODO Auto-g66enerated method stub
         String insertQuery = "INSERT INTO INFO(I_NUM, P_ID, W_ID, ANNUAL_INCOME, DEPARTMENT_MOOD, JOB_SATISFACTION_R, "
               + "CAFETERIA, TRAFFIC_CONVENIENCE, EMPLOYEE_WELLFARE, CFD_NAME, C_NAME) " + 
               "VALUES(sequence_info.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
         
         String p_id = Info.getpId();
         String w_id = Info.getwId();
         Integer annual_Income = Info.getAnnual_Income();//1
         Integer InfoMood = Info.getInfoMood();//2
         Integer jobSat_R = Info.getJobSat_R();//3
//         String jobSat_H = Info.getJobSat_H();
         Integer cafeteria = Info.getCafeteria();//4
         Integer trafficConven = Info.getTrafficConven();//5
         Integer empWellfare = Info.getEmpWellfare();//6
         String cfdName = Info.getCfdName();
         String cName = Info.getCName();
         
         Object[] param = new Object[] {p_id, w_id, annual_Income, InfoMood, jobSat_R, cafeteria, trafficConven, empWellfare, cfdName, cName};
         
         jdbcUtil.setSqlAndParameters(insertQuery, param);
         
         try {            
            int result = jdbcUtil.executeUpdate();      // insert 문 실행
            return result;
         } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
         } finally {      
            jdbcUtil.commit();
            jdbcUtil.close();      // ResultSet, PreparedStatement, Connection 반환
         }      
         return 0;
      }

      public Integer updateInfo(InfoDTO Info) {
         String sql = "UPDATE"
               + "SET ANNUAL_INCOME =? , DEPARTEMENT_MOOD =? , JOB_SATISFACTION_R = ?, JOB_SATISFACTION_H =?,  CAFETERIA = ?, TRAFFIC_CONVENIENCE = ? , EMPLOYEE_WELLFARE= ?, CFD_NAME = ?, C_NAME = ?" 
               + "WHERE I_NUM=?";
      Object[] param = new Object[] {Info.getAnnual_Income(), Info.getInfoMood(), Info.getJobSat_R(), Info.getJobSat_R(), Info.getCafeteria(), Info.getTrafficConven(), Info.getEmpWellfare(), Info.getCfdName(), Info.getCName() };            
      jdbcUtil.setSqlAndParameters(sql,param);   
      
      try {            
         int result = jdbcUtil.executeUpdate();   // update 문 실행
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();   // resource 반환
      }      
      return 0;
         
         
      }//2

      @Override
      public Integer deleteInfo(Integer iCode) {
         int result = 0;
         String deleteQuery = "DELETE FROM INFO WHERE I_NUM = iCode";
      
         jdbcUtil.setSql(deleteQuery);
         
         try {
            result =jdbcUtil.executeUpdate();
            result = 1;//성공할 경우 1
         }catch(Exception ex) {
            ex.printStackTrace();
         }finally{
            jdbcUtil.close();
         }
         return result;
      }//3

   //�����ؾ���
   @Override
   public InfoDTO getInfoByName(String iName) {
      // TODO Auto-generated method stub
//      String searchQuery = query + "";
//      Object[] param = new Object[] {iName};
//
//      jdbcUtil.setSql(searchQuery);
//      jdbcUtil.setParameters(param);
//   
//      try {
//         ResultSet rs = jdbcUtil.executeQuery();
//         InfoDTO dto = new InfoDTO();
//         while (rs.next()) {
//            dto.setiNo(rs.getInt("Info_INO"));
//            dto.setpNo(rs.getInt("Info_PNO"));
//            dto.setwNo(rs.getInt("Info_WNO"));
//            dto.setCfdNo(rs.getInt("Info_CFDNO"));
//            dto.setAnnual_Income(rs.getInt("Info_ANNUALINCOME"));
//            dto.setInfoMood(rs.getInt("Info_MOOD"));
//            dto.setJobSat(rs.getInt("Info_JOBSAT"));
//            dto.setCafeteria(rs.getInt("Info_CAFETERIA"));
//            dto.setTrafficConven(rs.getInt("Info_TRAFFIC"));
//            dto.setEmpWellfare(rs.getInt("Info_EMPWELL"));
//
//         }
//         return dto;
//      } catch (Exception ex) {
//         ex.printStackTrace();
//      } finally {
//         jdbcUtil.close();
//      }
      return null;
   }

   /* select by id. 
    * error: not constructor in infoDTO */
//   @Override
//   public InfoDTO getInfoByP_id(String p_id) {
//      // TODO Auto-generated method stub
//      String query = "SELECT i_num AS i_num, p_id AS p_id, w_id AS w_id, annual_income AS annual_income, "
//            + "department_mood AS department_mood, job_satisfaction_r AS job_satisfaction_r, cafeteria AS cafeteria, traffic_convenience AS traffic_convenience, "
//            + "employee_wellfare AS employee_wellfare, cfd_num AS cfd_num, job_satisfaction_h AS job_satisfaction_h "
//            + "FROM info "
//            + "WHERE p_id = ? ";
//      
//      Object[] param = new Object[] { p_id };
//      jdbcUtil.setSqlAndParameters(query, param);
//      
//      try {
//         ResultSet rs = jdbcUtil.executeQuery();
//         InfoDTO info = null;
//         if (rs.next()) {
//            info = new InfoDTO(
//            rs.getInt("i_num"), 
//            rs.getString("p_id"), 
//            rs.getString("w_id"), 
//            rs.getInt("annual_income"), 
//            rs.getInt("department_mood"), 
//            rs.getInt("job_satisfaction_r"), 
//            rs.getInt("cafeteria"), 
//            rs.getInt("traffic_convenience"), 
//            rs.getInt("employee_wellfare")
//            );
//         }
//         return info;
//      } catch (Exception ex) {
//         ex.printStackTrace();
//      } finally {
//         jdbcUtil.close();
//      }
//      
//      return null;
//   }
//
//   @Override
//   public InfoDTO getInfoByW_id(String w_id) {
//      // TODO Auto-generated method stub
//      String query = "SELECT i_num AS i_num, p_id AS p_id, w_id AS w_id, annual_income AS annual_income, "
//            + "department_mood AS department_mood, job_satisfaction_r AS job_satisfaction_r, cafeteria AS cafeteria, traffic_convenience AS traffic_convenience, "
//            + "employee_wellfare AS employee_wellfare, cfd_num AS cfd_num, job_satisfaction_h AS job_satisfaction_h "
//            + "FROM info "
//            + "WHERE w_id = ? ";
//      
//      Object[] param = new Object[] { w_id };
//      jdbcUtil.setSqlAndParameters(query, param);
//      
//      try {
//         ResultSet rs = jdbcUtil.executeQuery();
//         InfoDTO info = null;
//         if (rs.next()) {
//            info = new InfoDTO(
//               rs.getInt("i_num"), 
//               rs.getString("p_id"), 
//               rs.getString("w_id"), 
//               rs.getInt("annual_income"), 
//               rs.getInt("department_mood"), 
//               rs.getInt("job_satisfaction_r"), 
//               rs.getInt("cafeteria"), 
//               rs.getInt("traffic_convenience"), 
//               rs.getInt("employee_wellfare")
//            );
//         }
//         return info;
//      } catch (Exception ex) {
//         ex.printStackTrace();
//      } finally {
//         jdbcUtil.close();
//      }
//      
//      return null;
//   }

   @Override
   public Integer getI_numByP_id(String p_id) {
      // TODO Auto-generated method stub
      String query = "SELECT i_num AS i_num, "
            + "FROM info "
            + "WHERE p_id = ? ";
      
      Object[] param = new Object[] { p_id };
      jdbcUtil.setSqlAndParameters(query, param);
      
      try {
         ResultSet rs = jdbcUtil.executeQuery();
         if (rs.next()) {
            return rs.getInt("i_num");
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();
      }
      
      return null;
   }

   @Override
   public Integer getI_numByW_id(String w_id) {
      // TODO Auto-generated method stub
      String query = "SELECT i_num AS i_num, "
            + "FROM info "
            + "WHERE w_id = ? ";
      
      Object[] param = new Object[] { w_id };
      jdbcUtil.setSqlAndParameters(query, param);
      
      try {
         ResultSet rs = jdbcUtil.executeQuery();
         if (rs.next()) {
            return rs.getInt("i_num");
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();
      }
      
      return null;
   }
   
    public boolean existingInfo(Integer infoId) throws SQLException {
         String sql = "SELECT count(*) FROM Info WHERE i_num=?";      
         jdbcUtil.setSqlAndParameters(sql, new Object[] {infoId});   // JDBCUtil�� query���� �Ű� ���� ����

         try {
            ResultSet rs = jdbcUtil.executeQuery();      // query ����
            if (rs.next()) {
               int count = rs.getInt(1);
               return (count == 1 ? true : false);
            }
         } catch (Exception ex) {
            ex.printStackTrace();
         } finally {
            jdbcUtil.close();      // resource ��ȯ
         }
         return false;
      }
    
    // TODO: : change 
    
//    public List<InfoDTO> findUserList() throws SQLException {
//           String sql = "SELECT p_id, pw, name, c_num, cf_num, cfd_num, company_email, matching_result " 
//                 + "FROM preparation_for_turnover "
//                 + "ORDER BY p_id";
//         jdbcUtil.setSqlAndParameters(sql, null);      // JDBCUtil�� query�� ����
//                  
//         try {
//            ResultSet rs = jdbcUtil.executeQuery();         // query ����         
//            List<InfoDTO> userList = new ArrayList<InfoDTO>();   // User���� ����Ʈ ����
//            while (rs.next()) {
//               InfoDTO user = new InfoDTO(         // User ��ü�� �����Ͽ� ���� ���� ������ ����
//                     rs.getString("p_id"),
//                     rs.getString("pw"),
//                     rs.getString("name"),
//                     rs.getInt("c_num"),
//                     rs.getInt("cf_num"),
//                     rs.getInt("cfd_num"),
//                     rs.getString("company_email"),
//                     Integer.valueOf(0)
//                     );   
//               userList.add(user);            // List�� User ��ü ����
//            }
//            return userList;               
//            
//         } catch (Exception ex) {
//            ex.printStackTrace();
//         } finally {
//            jdbcUtil.close();      // resource ��ȯ
//         }
//         return null;
//      }
//      
//      /**
//       * ��ü ����� ������ �˻��� �� ���� �������� �������� ����� ����� ���� �̿��Ͽ�
//       * �ش��ϴ� ����� �������� List�� �����Ͽ� ��ȯ.
//       */
//      public List<InfoDTO> findUserList(int currentPage, int countPerPage) throws SQLException {
//           String sql = "SELECT p_id, pw, name, c_num, cf_num, cfd_num, company_email, matching_result " 
//                    + "FROM preparation_for_turnover "
//                    + "ORDER BY p_id";
//         jdbcUtil.setSqlAndParameters(sql, null,               // JDBCUtil�� query�� ����
//               ResultSet.TYPE_SCROLL_INSENSITIVE,            // cursor scroll ����
//               ResultSet.CONCUR_READ_ONLY);                  
//         
//         try {
//            ResultSet rs = jdbcUtil.executeQuery();            // query ����         
//            int start = ((currentPage-1) * countPerPage) + 1;   // ����� ������ �� ��ȣ ���
//            if ((start >= 0) && rs.absolute(start)) {         // Ŀ���� ���� ������ �̵�
//               List<InfoDTO> userList = new ArrayList<InfoDTO>();   // User���� ����Ʈ ����
//               do {
//                  InfoDTO user = new InfoDTO(      // User ��ü�� �����Ͽ� ���� ���� ������ ����
//                     rs.getString("p_id"),
//                     rs.getString("pw"),
//                     rs.getString("name"),
//                     rs.getInt("c_num"),
//                     rs.getInt("cf_num"),
//                     rs.getInt("cfd_num"),
//                     rs.getString("company_email"),
//                     rs.getInt("matching_result")
//                     );   
//                  userList.add(user);                     // ����Ʈ�� User ��ü ����
//               } while ((rs.next()) && (--countPerPage > 0));      
//               return userList;                     
//            }
//         } catch (Exception ex) {
//            ex.printStackTrace();
//         } finally {
//            jdbcUtil.close();      // resource ��ȯ
//         }
//         return null;
//      }
//      /**
//       * �־��� ����� ID�� �ش��ϴ� ����� ������ �����ͺ��̽����� ã�� User ������ Ŭ������ 
//       * �����Ͽ� ��ȯ.
//       */
//      public InfoDTO findUser(String p_id) throws SQLException {
//           String sql = "SELECT p_id, c_num, cf_num, cfd_num, name, company_email, pw, matching_result "
//                    + "FROM preparation_for_turnover "
//                    + "WHERE p_id=? ";
//         jdbcUtil.setSqlAndParameters(sql, new Object[] {p_id});   // JDBCUtil�� query���� �Ű� ���� ����
//
//         try {
//            ResultSet rs = jdbcUtil.executeQuery();      // query ����
//            if (rs.next()) {                  // �л� ���� �߰�
//               InfoDTO user = new InfoDTO(      // User ��ü�� �����Ͽ� �л� ������ ����
//                     rs.getString("p_id"),
//                     rs.getString("pw"),
//                     rs.getString("name"),
//                     rs.getInt("c_num"),
//                     rs.getInt("cf_num"),
//                     rs.getInt("cfd_num"),
//                     rs.getString("company_email"),
//                     rs.getInt("matching_result")
//               );
//               return user;
//            }
//         } catch (Exception ex) {
//            ex.printStackTrace();
//         } finally {
//            jdbcUtil.close();      // resource ��ȯ
//         }
//         return null;
//      }
   @Override
   public InfoDTO getInfoByP_id(String p_id) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public InfoDTO getInfoByW_id(String w_id) {
      // TODO Auto-generated method stub
      return null;
   }
   
   @Override
   public List<ForSearchList> getSearchList(String cfd_name, String category) throws SQLException{
      if(category.equals("annual_income")){
         String query = "SELECT C_NAME, CFD_NAME, avg(ANNUAL_INCOME) AS INCOME FROM INFO WHERE CFD_NAME = ? GROUP BY C_NAME, CFD_NAME ORDER BY INCOME DESC";
         Object[] param = new Object[] {cfd_name};
         jdbcUtil.setSqlAndParameters(query, param);
         try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<ForSearchList> return_list = new ArrayList<ForSearchList>();
            while(rs.next()) {
               ForSearchList search_list = new ForSearchList(
                     rs.getString("C_NAME"), 
                     rs.getString("CFD_NAME"),
                     rs.getInt("INCOME"));
               logger.debug("search_list : {}", search_list);
               return_list.add(search_list);
            }
            logger.debug("return_list : {}", return_list);
            return return_list;
         }catch(Exception ex) {
            ex.printStackTrace();
         }finally{
            jdbcUtil.close();
         }
      }
      else if(category.equals("department_mood")) {
         String query = "SELECT C_NAME ,CFD_NAME, avg(DEPARTMENT_MOOD) AS DEPT_MOOD FROM INFO WHERE CFD_NAME = ? GROUP BY C_NAME, CFD_NAME ORDER BY DEPT_MOOD DESC";
         Object[] param = new Object[] {cfd_name};
         jdbcUtil.setSqlAndParameters(query, param);
         try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<ForSearchList> return_list = new ArrayList<ForSearchList>();
            while(rs.next()) {
               ForSearchList search_list = new ForSearchList(
                     rs.getString("C_NAME"), 
                     rs.getString("CFD_NAME"),
                     rs.getInt("DEPT_MOOD"));
               return_list.add(search_list);
            }
            return return_list;
         }catch(Exception ex) {
            ex.printStackTrace();
         }finally{
            jdbcUtil.close();
         }
      }
      else if(category.equals("job_satisfaction_r")) {
         String query = "SELECT C_NAME ,CFD_NAME, avg(JOB_SATISFACTION_R) AS JOB_SATISFACTION FROM INFO WHERE CFD_NAME = ? GROUP BY C_NAME, CFD_NAME ORDER BY JOB_SATISFACTION DESC";
         Object[] param = new Object[] {cfd_name};
         jdbcUtil.setSqlAndParameters(query, param);
         try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<ForSearchList> return_list = new ArrayList<ForSearchList>();
            while(rs.next()) {
               ForSearchList search_list = new ForSearchList(
                     rs.getString("C_NAME"), 
                     rs.getString("CFD_NAME"),
                     rs.getInt("JOB_SATISFACTION"));
               return_list.add(search_list);
            }
            return return_list;
         }catch(Exception ex) {
            ex.printStackTrace();
         }finally{
            jdbcUtil.close();
         }
      }
      else if(category.equals("traffic_convenience")) {
         String query = "SELECT C_NAME ,CFD_NAME, agv(TRAFFIC_CONVENIENCE) AS TRAFFIC_CONVEN FROM INFO WHERE CFD_NAME = ? GROUP BY C_NAME, CFD_NAME ORDER BY TRAFFIC_CONVEN DESC";
         Object[] param = new Object[] {cfd_name};
         jdbcUtil.setSqlAndParameters(query, param);
         try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<ForSearchList> return_list = new ArrayList<ForSearchList>();
            while(rs.next()) {
               ForSearchList search_list = new ForSearchList(
                     rs.getString("C_NAME"), 
                     rs.getString("CFD_NAME"),
                     rs.getInt("TRAFFIC_CONVEN"));
               return_list.add(search_list);
            }
            return return_list;
         }catch(Exception ex) {
            ex.printStackTrace();
         }finally{
            jdbcUtil.close();
         }
      }
      else if(category.equals("cafeteria")) {
         String query = "SELECT C_NAME ,CFD_NAME, avg(CAFETERIA) AS CAFETERIA FROM INFO WHERE CFD_NAME = ? GROUP BY C_NAME, CFD_NAME ORDER BY CAFETERIA DESC";
         Object[] param = new Object[] {cfd_name};
         jdbcUtil.setSqlAndParameters(query, param);
         try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<ForSearchList> return_list = new ArrayList<ForSearchList>();
            while(rs.next()) {
               ForSearchList search_list = new ForSearchList(
                     rs.getString("C_NAME"), 
                     rs.getString("CFD_NAME"),
                     rs.getInt("CAFETERIA"));
               return_list.add(search_list);
            }
            return return_list;
         }catch(Exception ex) {
            ex.printStackTrace();
         }finally{
            jdbcUtil.close();
         }
      }
      else {
         String query = "SELECT C_NAME ,CFD_NAME, avg(EMPLOYEE_WELLFARE) AS WELLFARE FROM INFO WHERE CFD_NAME = ? GROUP BY C_NAME, CFD_NAME ORDER BY WELLFARE DESC";
         Object[] param = new Object[] {cfd_name};
         jdbcUtil.setSqlAndParameters(query, param);
         try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<ForSearchList> return_list = new ArrayList<ForSearchList>();
            while(rs.next()) {
               ForSearchList search_list = new ForSearchList(
                     rs.getString("C_NAME"), 
                     rs.getString("CFD_NAME"),
                     rs.getInt("WELLFARE"));
               return_list.add(search_list);
            }
            return return_list;
         }catch(Exception ex) {
            ex.printStackTrace();
         }finally{
            jdbcUtil.close();
         }
      }
      return null;
   }
   
}