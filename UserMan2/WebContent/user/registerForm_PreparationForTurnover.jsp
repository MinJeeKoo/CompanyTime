<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>사용자 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
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
	if (form.company.value == "") {
		alert("회사를 입력하십시오.");
		form.company.focus();
		return false;
	}
	if (form.cf_name_hope.value == "") {
		alert("희망 분야를 입력하십시오.");
		form.cf_name_hope.focus();
		return false;
	}
	if (form.cfd_name.value == "") {
		alert("부서를 입력하십시오.");
		form.cfd_name.focus();
		return false;
	}
	
	if (form.annual_income.value == "") {
		alert("연봉을 입력하십시오.");
		form.annual_income.focus();
		return false;
	}
	if (form.department_mood.value == "") {
		alert("부서분위기를 입력하십시오.");
		form.department_mood.focus();
		return false;
	}
	if (form.job_satisfaction.value == "") {
		alert("직업만족도를 입력하십시오.");
		form.job_satisfaction.focus();
		return false;
	}
	if (form.cafeteria.value == "") {
		alert("구내식당에 대한 점수를 입력하십시오..");
		form.cafeteria.focus();
		return false;
	}
	if (form.traffic_convenience.value == "") {
		alert("교통편의성에 대한 점수를 입력하십시오.");
		form.traffic_convenience.focus();
		return false;
	}
	if (form.employee_wellfare.value == "") {
		alert("직원복지에 대한 점수를 입력하십시오.");
		form.employee_wellfare.focus();
		return false;
	}
	form.submit();
}

function userList(targetUri) {
	form.action = targetUri;
	form.submit();
}

$(document).ready(function() {
    $("#field").on("change", function() {
       $.ajax({
          type: "GET",
          url: "<c:url value='/search/rankingSearch/json' />" + "?cf_name=" + $("#field option:selected").text(),
          cache: false,
          dataType: "json",
          success: function(departmentList) {
             
             var f = document.form;
             var len = departmentList.length;
             var opt = $("#field option").length;
             
             for (var i = 0; i < opt; i++) {
                f.department.options[0] = null;
             }
             
             for (var i = 0; i < len; i++) {
                f.department.options[i] = new Option(departmentList[i], departmentList[i]);
             }
          }
       });
    });
 });

</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<!-- registration form  -->
<form name="form" method="POST" action="<c:url value='/user/register_pt' />">
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
				<input type="text" style="width: 240" name="name">
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
		  <!-- 회사 정보 -->		  
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">회사</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="text" style="width: 240" name="company" >
				<c:if test="${registerFailed}">value="${user.company}"</c:if>
		
			</td>
		  </tr>	
				  
		   <tr height="40">
         <td width="150" align="center" bgcolor="E6ECDE">희망분야</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10">
            <c:if test="${registerFailed}">value="${user.field}"</c:if>
            
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
             
        <tr height="40">
         <td width="150" align="center" bgcolor="E6ECDE">부서</td>
         <td width="250" bgcolor="ffffff" style="padding-left: 10">
            <c:if test="${registerFailed}">value="${user.dept}"</c:if>
            
             <div id="fld">
               <select id="field" name="cf_name">
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
            <div id="dept">
               <select id="department" name="cfd_name">
                  <option value="">부서 선택</option>
               </select>
            </div>
         </td>
        </tr>   
		  
		  
		  
		  <!-- Info 정보 -->
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">연봉</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="radio" name="annual_income" value="0">매우불만
				<input type="radio" name="annual_income" value="1">불만
				<input type="radio" name="annual_income" value="2">보통
				<input type="radio" name="annual_income" value="3">좋음
				<input type="radio" name="annual_income" value="4">매우좋음
				<c:if test="${registerFailed}">value="${user.annual_income}"</c:if>
		
			</td>
		  </tr>		 
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">부서 분위기</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="radio" name="department_mood" value="0">매우불만
				<input type="radio" name="department_mood" value="1">불만
				<input type="radio" name="department_mood" value="2">보통
				<input type="radio" name="department_mood" value="3">좋음
				<input type="radio" name="department_mood" value="4">매우좋음
				<c:if test="${registerFailed}">value="${user.department_mood}"</c:if>
		
			</td>
		  </tr>		 
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">직업만족도</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="radio" name="job_satisfaction" value="0">매우불만
				<input type="radio" name="job_satisfaction" value="1">불만
				<input type="radio" name="job_satisfaction" value="2">보통
				<input type="radio" name="job_satisfaction" value="3">좋음
				<input type="radio" name="job_satisfaction" value="4">매우좋음
				<c:if test="${registerFailed}">value="${user.job_satisfaction}"</c:if>
		
			</td>
		  </tr>		 
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">구내식당</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="radio" name="cafeteria" value="0">매우불만
				<input type="radio" name="cafeteria" value="1">불만
				<input type="radio" name="cafeteria" value="2">보통
				<input type="radio" name="cafeteria" value="3">좋음
				<input type="radio" name="cafeteria" value="4">매우좋음
				<c:if test="${registerFailed}">value="${user.cafeteria}"</c:if>
		
			</td>
		  </tr>		 
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">교통편의성 </td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="radio" name="traffic_convenience" value="0">매우불만
				<input type="radio" name="traffic_convenience" value="1">불만
				<input type="radio" name="traffic_convenience" value="2">보통
				<input type="radio" name="traffic_convenience" value="3">좋음
				<input type="radio" name="traffic_convenience" value="4">매우좋음
				<c:if test="${registerFailed}">value="${user.traffic_convenience}"</c:if>
		
			</td>
		  </tr>		 
		  <tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">직원복지</td>
			<td width="250" bgcolor="ffffff" style="padding-left: 10">
				<input type="radio" name="employee_wellfare" value="0">매우불만
				<input type="radio" name="employee_wellfare" value="1">불만
				<input type="radio" name="employee_wellfare" value="2">보통
				<input type="radio" name="employee_wellfare" value="3">좋음
				<input type="radio" name="employee_wellfare" value="4">매우좋음
				<c:if test="${registerFailed}">value="${user.employee_wellfare}"</c:if>
		
			</td>
		  </tr>		 
		  
	    </table>

	    <br>	  
	    <table style="width: 100%">
		  <tr>
			<td align="left">
			<input type="button" value="회원 가입" onClick="userCreate()"> &nbsp;
			<input type="button" value="홈화면" onClick="userList('<c:url value='/user/main/form' />')">
			</td>
		  </tr>
	    </table>
	  </td>
    </tr>
  </table>  
</form>
</body>
</html>