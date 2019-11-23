<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
</style>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
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

function search() {
   form.submit();
}
</script>
</head>
<body>
<center><h3>랭킹 검색</h3></center>
<form action="<c:url value='/search/rankingSearchResult' />" id="form" name="form">
<div id="cat">
   <h5>카테고리</h5>
   <select id="category" name="category">
	  <option value="카테고리 선택">카테고리 선택</option>
      <option value="annual_income">연봉</option>
      <option value="department_mood">부서 분위기</option>
      <option value="job_satisfaction_r">직업 만족도</option>
      <option value="traffic_convenience">교통 편의성</option>
      <option value="cafeteria">구내식당</option>
      <option value="employee_wellfare">직원복지</option>
   </select>
</div>
<div id="fld">
   <h5>분야</h5>
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
   <h5>부서</h5>
   <select name="cfd_name" id="department">
      <option value="">부서</option>
   </select>
<input type="button" value="검색" onclick="search()" />
</div>
</form>
</body>
</html>