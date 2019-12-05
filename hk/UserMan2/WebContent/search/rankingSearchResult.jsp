<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*, model.*" %>
<%@page import="model.ForSearchList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   @SuppressWarnings("unchecked") 
   List<ForSearchList> searchList = (List<ForSearchList>)request.getAttribute("getSearchList");
%>
<html>
<head>
<title>search_result_List</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<table style="width:100%">
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  <tr>
   <td width="20"></td>
   <td>
     <table>
      <tr>
        <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>search_result_List</b>&nbsp;&nbsp;</td>
      </tr>
      <tr>
      <td>
      <% String category = (String)request.getAttribute("category");
      String field = (String)request.getAttribute("field");
      String dept = (String)request.getAttribute("dept");%>
      <%="<사용자가 검색한 값>" %>
      </td>
      </tr>
      <tr>
      <td>
      <%
      if(category.equals("annual_income"))
    		  out.println(" 카테고리: 연봉");
    	else if(category.equals("department_mood"))
    		   out.println(" 카테고리: 부서 분위기");
    	else if(category.equals("job_satisfaction_r"))
 		   out.println(" 카테고리: 직업 만족도");
    	else if(category.equals("traffic_convenience"))
 		   out.println(" 카테고리: 교통편의성");
    	else if(category.equals("cafeteria"))
  		   out.println(" 카테고리: 구내식당");
    	else 
   		   out.println(" 카테고리: 직원복지");
      %>
      </td>
      </tr>
      
      <tr>
      <td>
      <%= " 분야: " + field%>
      </td>
      </tr>
      
      <tr>
      <td>
      <%=" 부서: " + dept%>
      </td>
      </tr>
     </table>  
     <br>        
     <table style="background-color: YellowGreen">
      <tr>
        <td width="200" align="center" bgcolor="E6ECDE">순위</td>
        <td width="190" align="center" bgcolor="E6ECDE" height="22">회사명</td>
        <td width="200" align="center" bgcolor="E6ECDE">부서명</td>
        <td width="200" align="center" bgcolor="E6ECDE">평균점수</td>
       
      </tr>
<%
   
   // 주의: 아래와 같은 scriptlet은 생략하고 <c:for-each>와 EL로 구현하는 것이 바람직함 (list2.jsp 참조)
   if (searchList != null) {   
     Iterator<ForSearchList> userIter = searchList.iterator();
   
     int i = 0;
     int compare = -1;
     //사용자 리스트를 클라이언트에게 보여주기 위하여 출력.
     while ( userIter.hasNext() ) {
        ForSearchList user = (ForSearchList)userIter.next();
   
%>           
      <tr>
      <td width="190" align="center" bgcolor="ffffff" height="20">
         <%if(compare != user.getSearch_category())
         i++;%>
         <%=i%>
        </td>
        <td width="190" align="center" bgcolor="ffffff" height="20">
         <%=user.getC_name()%>
        </td>
        <td width="200" bgcolor="ffffff" style="padding-left: 10">
           <%=user.getCfd_name()%>
        </td>
        <td width="200" align="center" bgcolor="ffffff" height="20">
           <%=user.getSearch_category()%>
           
        </td>
      </tr>
<%
   ForSearchList before_user = user;
   compare = before_user.getSearch_category();
     }
   }
%>   
     </table>         
   </td>
  </tr>
</table> 
<br>
     <a href="<c:url value='/search/rankingSearch' />">뒤로가기</a>
     </td> 
</body>
</html>