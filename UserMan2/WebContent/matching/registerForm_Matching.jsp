<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>스펙 입력</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
function specAdd() {
	if (form.certification.value == "") {
		alert("자격증을 입력하십시오.");
		form.certification.focus();
		return false;
	} 
	if (form.grade.value == "") {
		alert("학점을 입력하십시오.");
		form.grade.focus();
		return false;
	}else{
		if (form.grade.value > 4.5 || form.grade.value < 0) {
			alert("학점이 부적합합니다.");
			form.grade.focus();
			return false;
		}
	}
	if (form.internship.value == "") {
		alert("인턴경험을 입력하십시오.");
		form.internship.focus();
		return false;
	}
	if (form.toeic.value == "") {
		alert("토익점수를 입력하십시오.");
		form.toeic.focus();
		return false;
	}else{
		if (form.toeic.value > 990 || form.toeic.value < 0) {
			alert("토익점수가 부적합합니다.");
			form.toeic.focus();
			return false;
		}
	}
	
	if (form.contest.value == "") {
		alert("대외활동을 입력하십시오.");
		form.contest.focus();
		return false;
	}
	if (form.awards.value == "") {
		alert("수상경력을 입력하십시오.");
		form.awards.focus();
		return false;
	}
	if (form.study_abroad.value == "") {
		alert("해외연수경험을 입력하십시오.");
		form.study_abroad.focus();
		return false;
	}
	if (form.volun.value == "") {
		alert("봉사활동을 입력하십시오.");
		form.volun.focus();
		return false;
	}
	
	alert("스펙 작성이 완료되었습니다.\n");
	
	form.submit();
}
function userList(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body>
<form name="form" method="POST" action="<c:url value='/matching/register' />">

<table style="background-color: YellowGreen">

		<tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">사용자 타입</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
			<input type = "text" name="userType" value='${curUserType}' readonly>
			</td>
		</tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">사용자 ID</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
			<input type = "text" name="userId" value='${curUserId}' readonly>
			</td>
			
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">자격증</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="certification"
				<c:if test="${registerFailed}">value="${user.certification}"</c:if>>
				</td>
		  </tr>
	  	  
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">학점</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="number" min="0.00" step="0.01" style="width: 240" name="grade" 
				 	<c:if test="${registerFailed}">value="${user.grade}"</c:if>>
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">인턴경험</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="internship" 
				 	<c:if test="${registerFailed}">value="${user.internship}"</c:if>>
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">토익(TOEIC)</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
			<input type="number" min="1" max="990" style="width: 240" name="toeic" 
			<c:if test="${registerFailed}">value="${user.toeic}"</c:if>>
				 
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">토익 스피킹</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				 <c:if test="${registerFailed}">value="${user.toeic_speaking}"</c:if>
				 <div id="toeic_speaking">
	               <select id="toeic_speaking" name="toeic_speaking">
	               	  <option value="L8">Level 8 (190~200)</option>
	                  <option value="L7">Level 7 (160~180)</option>
	                  <option value="L6">Level 6 (130~150)</option>
	                  <option value="L5">Level 5 (110~120)</option>
	                  <option value="L4">Level 4 (80~100)</option>
	                  <option value="L3">Level 3 (60~70)</option>
	                  <option value="L2">Level 2 (40~50)</option>
	                  <option value="L1">Level 1 (0~30)</option>
	               </select>
	            </div>
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">오픽(OPIC)</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
			<c:if test="${registerFailed}">value="${user.opic}"</c:if>
				<div id="opic">
	               <select id="opic" name="opic">
	               	  <option value="AL">Advanced LOW</option>
	                  <option value="IH">Intermediate HIGH</option>
	                  <option value="IM">Intermediate MID</option>
	                  <option value="IL">Intermediate LOW</option>
	                  <option value="NH">Novice HIGH</option>
	                  <option value="NM">Novice MID</option>
	                  <option value="NL">Novice LOW</option>
	               </select>
	            </div>
			</td>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">대외활동</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="contest" 
				 	<c:if test="${registerFailed}">value="${user.contest}"</c:if>>
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">수상</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="awards" 
				 	<c:if test="${registerFailed}">value="${user.awards}"</c:if>>
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">유학경험</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="study_abroad" 
				 	<c:if test="${registerFailed}">value="${user.study_abroad}"</c:if>>
			</td>
		  </tr>
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">봉사활동</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="volun" 
				 	<c:if test="${registerFailed}">value="${user.volun}"</c:if>>
			</td>
		  </tr>
		  
	    </table>
	    <br>	  
	    <table style="width: 100%">
		  <tr>
			<td align="left">
			<input type="button" value="완료" onClick="specAdd()" /> &nbsp;
			<input type="button" value="홈화면" onClick="userList('<c:url value='/user/main/form' />')">
			</td>
		  </tr>
	    </table>
	 </form>
</body>
</html>