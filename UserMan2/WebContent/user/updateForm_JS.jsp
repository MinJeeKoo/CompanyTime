<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="model.JobSeekerDTO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	JobSeekerDTO user = (JobSeekerDTO)request.getAttribute("user");
%>
<html>
<head>
<title>내 정보 수정(취업준비생)</title>
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
	if (form.school.value == "") {
		alert("학교를 입력하십시오.");
	    form.empno.focus();
	    return false;
	}
	if (form.cf_name_hope.value == "") {
	    alert("희망 분야를 입력하십시오.");
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
<!-- Update Form  -->
<form name="form" method="POST" action="<c:url value='/user/updateJS' />">
  <input type="hidden" name="userId" value="<%=user.getUserId()%>"/>	  
  <table style="width: 100%">
	<tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>내 정보 수정(현직자)</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	  
	    <table style="background-color: YellowGreen">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">사용자 ID</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<%= user.getUserId() %>
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">비밀번호</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="password" style="width: 240" name="password" value="<%=user.getPassword()%>">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">비밀번호 확인</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="password" style="width: 240" name="password2" value="<%=user.getPassword()%>">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">이름</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="name" value="<%=user.getName()%>">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">학교</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="school" value="${user.school}">
			</td>
		  </tr><tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">전공</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="major" value="${user.major}">
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">이메일 주소</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="email" value="${user.email}">
			</td>
		  </tr>	
		  <tr height="40">
        <td width="150" align="center" bgcolor="E6ECDE">희망분야</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10">
            <c:if test="${updateFailed}">value="${cf_name}"</c:if>
            
            <div id="fld_hope">
               <select id="field_hope" name="cf_name_hope">
               	  <option value="분야 선택">분야 선택</option>
                  <option value="경영/사무">경영/사무</option>
                  <option value="영업/고객상담">영업/고객상담</option>
                  <option value="IT/인터넷">IT/인터넷</option>
                  <option value="디자인">디자인</option>
                  <option value="서비스">서비스</option>
                  <option value="전문직">전문직</option>
                  <option value="의료">의료</option>
                  <option value="생산/제조">생산/제조</option>
                  <option value="건설">건설</option>
                  <option value="유통/무역">유통/무역</option>
                  <option value="미디어">미디어</option>
                  <option value="교육">교육</option>
                  <option value="특수계층/공공">특수계층/공공</option>
               </select>
            </div>
         
         </td>
        </tr>     
	    </table>
	    <br>	  
	    <table style="width: 100%">
		  <tr>
			<td align="left">
			<input type="button" value="수정" onClick="userModify()"> &nbsp;
			<input type="button" value="목록" onClick="userList('<c:url value='/user/list' />')">
			</td>
		  </tr>
	    </table>
	  </td>
	</tr>
  </table>  
</form>
</body>
</html>