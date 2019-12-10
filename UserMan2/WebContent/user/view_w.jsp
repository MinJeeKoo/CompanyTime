<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="model.WorkerDTO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   WorkerDTO user = (WorkerDTO)request.getAttribute("user");
%>
<html>
<head>
<title>내 정보</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<script>
function userRemove() {
   return confirm("정말 삭제하시겠습니까?");      
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
  <br>
  <table style="width:100%">
    <tr>
     <td width="20"></td>
     <td>
       <table>
        <tr>
         <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>내 정보 보기</b>&nbsp;&nbsp;</td>
        </tr>
       </table>  
       <br>            
        <table style="background-color: YellowGreen">
          <tr>
         <td width="120" align="center" bgcolor="E6ECDE" height="22">ID</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10">
            ${user.w_id}
         </td>
        </tr>
        <tr>
         <td width="120" align="center" bgcolor="E6ECDE" height="22">패스워드</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10">
           ${user.pw}
         </td>
        </tr>
        <tr>
         <td width="120" align="center" bgcolor="E6ECDE" height="22">이름</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10">
           ${user.name}
         </td>
        </tr>
        <tr>
         <td width="120" align="center" bgcolor="E6ECDE" height="22">회사</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10">
            ${c_name}
         </td>
        </tr>
        <tr>
         <td width="120" align="center" bgcolor="E6ECDE" height="22">희망매칭분야(field)</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10">
            ${cf_name}
         </td>
        </tr>
        <tr>
         <td width="120" align="center" bgcolor="E6ECDE" height="22">부서</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10">
            ${cfd_name}
         </td>
        </tr>
        <tr>
         <td width="120" align="center" bgcolor="E6ECDE" height="22">이메일 주소</td>
         <td width="470" bgcolor="ffffff" style="padding-left: 10">
            ${user.company_email} <%-- <%=user.getCompany_email()%> --%>
         </td>
        </tr>      
       </table>
       <br>
       <a href="<c:url value='/user/updatePT/form'>
                 <c:param name='userId' value='<%=user.getW_id()%>'/>
              </c:url>">수정</a> &nbsp;
        <%--<a href="<c:url value='/user/delete'>
               <c:param name='userId' value='<%=user.getUserId()%>'/>
              </c:url>" onclick="return userRemove();">삭제</a> &nbsp;  --%>
        <a href="<c:url value='/user/main_login/form'>
              <c:param name='userId' value='<%=user.getW_id()%>'/> 
              </c:url>">홈화면</a>        
        <br><br>      
        
        <%-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력--%>
        <c:if test="${updateFailed || deleteFailed}">
         <font color="red"><c:out value="${exception.getMessage()}" /></font>
       </c:if>    
     </td>
   </tr>
  </table>  
</body>
</html>