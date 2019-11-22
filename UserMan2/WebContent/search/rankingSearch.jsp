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
</script>
</head>
<body>
<center><h3>랭킹 검색</h3></center>
<form action="" id="form" name="form">
<div id="cat">
	<h5>카테고리</h5>
	<select id="category">
		<option value="annual_income">연봉</option>
		<option value="mood">부서 분위기</option>
		<option value="satisfaction">직업 만족도</option>
		<option value="traffic_convenience">교통 편의성</option>
	</select>
</div>
<div id="fld">
	<h5>분야</h5>
	<select id="field" name="cf_name">
		<option value="bussiness">경영/사무</option>
		<option value="sales">영업/고객상담</option>
		<option value="IT">IT/인터넷</option>
		<option value="design">디자인</option>
		<option value="service">서비스</option>
		<option value="professional">전문직</option>
		<option value="medical">의료</option>
		<option value="manufacture">생산/제조</option>
		<option value="construction">건설</option>
		<option value="trade">유통/무역</option>
		<option value="media">미디어</option>
		<option value="education">교육</option>
		<option value="public/specialized">특수계층/공공</option>
	</select>
</div>
<div id="dept">
	<h5>부서</h5>
	<select name="cfd_name" id="department">
		<option value="">부서</option>
	</select>
</div>
</form>
</body>
</html>