<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="model.P_TurnoverDTO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
   P_TurnoverDTO user = (P_TurnoverDTO)request.getAttribute("user");
%>
<html>
<head>
<title>내 정보 수정</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<script>
function userModify() {
   if (form.password.value == "") {
      alert("비밀번호를 입력하십시오.");
      form.password.focus();
      return false;
   }
   if (form.password.value != form.password2.value) {
      alert("비밀번호가 일치하지 않습니다.");
      form.name.focus();
      return false;
   }
   if (form.name.value == "") {
      alert("이름을 입력하십시오.");
      form.name.focus();
      return false;
   }
   var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
   if(emailExp.test(form.email.value)==false) {
      alert("이메일 형식이 올바르지 않습니다.");
      form.email.focus();
      return false;
   }
   if (form.company.value == "") {
      alert("회사를 입력하십시오.");
      form.empno.focus();
      return false;
   }
   if (form.dept.value == "") {
      alert("희망 분야를 입력하십시오.");
      form.empno.focus();
      return false;
   }
   if (form.field.value == "") {
      alert("부서를 입력하십시오.");
      form.empno.focus();
      return false;
   }
   form.submit();
}

function userList(targetUri) {
   form.action = targetUri;
   form.submit();
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<form name="form" method="POST" action="<c:url value='/user/updatePT' />">
  <input type="hidden" name="userId" value="<%=user.getP_id()%>"/>     
  <table style="width: 100%">
   <tr>
     <td width="20"></td>
     <td>
       <table>
        <tr>
          <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>내 정보 수정</b>&nbsp;&nbsp;</td>
        </tr>
       </table> 
       <br>     
       <table style="background-color: YellowGreen">
           <tr height="40">
         <td width="150" align="center" bgcolor="E6ECDE">사용자 ID</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10">
            <%= user.getP_id() %>
         </td>
        </tr>
          <tr height="40">
         <td width="150" align="center" bgcolor="E6ECDE">비밀번호</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10">
            <input type="password" style="width: 240" name="password" value="<%=user.getPw()%>">
         </td>
        </tr>
          <tr height="40">
         <td width="150" align="center" bgcolor="E6ECDE">비밀번호 확인</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10">
            <input type="password" style="width: 240" name="password2" value="<%=user.getPw()%>">
         </td>
        </tr>
          <tr height="40">
         <td width="150" align="center" bgcolor="E6ECDE">이름</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10">
            <input type="text" style="width: 240" name="name" value="<%=user.getName()%>">
         </td>
        </tr>
          <tr height="40">
         <td width="150" align="center" bgcolor="E6ECDE">이메일 주소</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10">
            <input type="text" style="width: 240" name="email" value="${user.company_email}">
         </td>
        </tr>         
        <!-- 회사 정보 -->        
        <tr height="40">
         <td width="150" align="center" bgcolor="E6ECDE">회사</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10">
            <input type="text" style="width: 240" name="company" value="<%=user.getC_num()%>">
         </td>
        </tr>   
              
        <tr height="40">
         <td width="150" align="center" bgcolor="E6ECDE">희망분야</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10">
            <input type="text" style="width: 240" name="field" value="<%=user.getCf_num()%>">
         </td>
        </tr>     
        <tr height="40">
         <td width="150" align="center" bgcolor="E6ECDE">부서</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10">
            <input type="text" style="width: 240" name="dept" value="<%=user.getCfd_num()%>">
         </td>
        </tr> 
        </table>
       <br>     
       <table style="width: 100%">
        <tr>
         <td align="left">
         <input type="button" value="수정" onClick="userModify()"> &nbsp;
         <input type="button" value="목록" onClick="userList('<c:url value='/user/list_pt' />')">
         </td>
        </tr>
       </table>
     </td>
   </tr>
  </table>  
</form>
</body>
</html>