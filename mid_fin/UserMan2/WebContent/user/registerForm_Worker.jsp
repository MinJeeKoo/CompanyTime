<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">

<style>
.starR{
	  background: url('http://miuu227.godohosting.com/images/icon/ico_review.png') no-repeat right 0;
	  background-size: auto 100%;
	  width: 30px;
	  height: 30px;
	  display: inline-block;
	  text-indent: -9999px;
	  cursor: pointer;
}
.starR.on{background-position:0 0;}

</style>

<script>
function userCreate() {
	if (form.userId.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.userId.focus();
		return false;
	} 
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
	var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
	if(phoneExp.test(form.phone.value)==false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		form.phone.focus();
		return false;
	}
	if (form.empno.value == "") {
		alert("사원번호를 입력하십시오.");
		form.empno.focus();
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
	
	
	if (form.annual_income.value == "") {
		alert("연봉을 입력하십시오.");
		form.empno.focus();
		return false;
	}
	if (form.department_mood.value == "") {
		alert("부서분위기를 입력하십시오.");
		form.empno.focus();
		return false;
	}
	if (form.job_satisfaction.value == "") {
		alert("직업만족도를 입력하십시오.");
		form.empno.focus();
		return false;
	}
	if (form.cafeteria.value == "") {
		alert("구내식당에 대한 점수를 입력하십시오..");
		form.empno.focus();
		return false;
	}
	if (form.traffic_convenience.value == "") {
		alert("교통편의성에 대한 점수를 입력하십시오.");
		form.empno.focus();
		return false;
	}
	if (form.employee_wellfare.value == "") {
		alert("직원복지에 대한 점수를 입력하십시오.");
		form.empno.focus();
		return false;
	}
	form.submit();
}

function userList(targetUri) {
	form.action = targetUri;
	form.submit();
}

$('.starRev span').click(function(){
	  $(this).parent().children('span').removeClass('on');
	  $(this).addClass('on').prevAll('span').addClass('on');
	  return false;
	});
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<!-- registration form  -->
<form name="form" method="POST" action="<c:url value='/user/register' />">
  <table style="width: 100%">
    <tr>
      <td width="20"></td>
	  <td>
	    <table>
		  <tr>
		    <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>사용자 관리 - 회원 가입</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	 
	    <!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
     	<c:if test="${registerFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>
     
	    <br>	  
	    <table style="background-color: YellowGreen">
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">사용자 ID</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240;" name="userId">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">비밀번호</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="password" style="width: 240" name="password">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">비밀번호 확인</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="password" style="width: 240" name="password2">
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">이름</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="name" >
				<c:if test="${registerFailed}">value="${user.name}"</c:if>
		
			</td>
		  </tr>
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">이메일 주소</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="email" >
				<c:if test="${registerFailed}">value="${user.emailExp}"</c:if>
		
			</td>
		  </tr>	
	  	  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">전화번호</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="phone" >
				<c:if test="${registerFailed}">value="${user.phoneExp}"</c:if>
		
			</td>
		  </tr>		  
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">사원번호</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="empno" >
				<c:if test="${registerFailed}">value="${user.empno}"</c:if>
		
			</td>
		  </tr>		 
		  <!-- 회사 정보 -->		  
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">회사</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="company" >
				<c:if test="${registerFailed}">value="${user.company}"</c:if>
		
			</td>
		  </tr>	
				  
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">분야</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="field" >
				<c:if test="${registerFailed}">value="${user.dept}"</c:if>
		
			</td>
		  </tr>	
		  	  
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">부서</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="dept" >
				<c:if test="${registerFailed}">value="${user.field}"</c:if>
		
			</td>
		  </tr>	
		  
		  
		  <!-- Info 정보 -->
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">연봉</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<select name="income">
				    <option value="">연봉</option>
				    <option value="level1">2000~2500</option>
				    <option value="level2">2500~3000</option>
				    <option value="level3">3000~3500</option>
				    <option value="level4">3500~4000</option>
				    <option value="level5">4000~4500</option>
				    <option value="level6">4500~5000</option>
				    <option value="level7">5000이상</option>
				</select>
				<c:if test="${registerFailed}">value="${user.annual_income}"</c:if>
		
			</td>
		  </tr>		 
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">부서 분위기</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<c:if test="${registerFailed}">value="${user.department_mood}"</c:if>
		
				<div class="starRev">
				  <span class="starR on">별1</span>
				  <span class="starR">별2</span>
				  <span class="starR">별3</span>
				  <span class="starR">별4</span>
				  <span class="starR">별5</span>
				  <span class="starR">별6</span>
				  <span class="starR">별7</span>
				  <span class="starR">별8</span>
				  <span class="starR">별9</span>
				  <span class="starR">별10</span>
				</div>
				
			</td>
		  </tr>		 
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">직업만족도</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="number" style="width: 240" name="job_satisfaction" min="1" max="5" >
				<c:if test="${registerFailed}">value="${user.job_satisfaction}"</c:if>
		
			</td>
		  </tr>		 
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">구내식당</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="number" style="width: 240" name="cafeteria" min="1" max="5" >
				<c:if test="${registerFailed}">value="${user.cafeteria}"</c:if>
		
			</td>
		  </tr>		 
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">교통편의성</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="number" style="width: 240" name="traffic_convenience" min="1" max="5" >
				<c:if test="${registerFailed}">value="${user.traffic_convenience}"</c:if>
		
			</td>
		  </tr>		 
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">직원복지</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="number" style="width: 240" name="employee_wellfare" min="1" max="5" >
				<c:if test="${registerFailed}">value="${user.employee_wellfare}"</c:if>
		
			</td>
		  </tr>		 
		  
	    </table>
	    <br>	  
	    <table style="width: 100%">
		  <tr>
			<td align="left">
			<input type="button" value="회원 가입" onClick=""> &nbsp;
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