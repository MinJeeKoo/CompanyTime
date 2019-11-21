<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
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
				alert("hello");
				
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
	/* function fnCngList() {
		console.log("hi");
		alert("hello");
		
		var f = document.form;
		
		var cf = $("#field option:selected").text();
		console.log(cf);
		
		list = $(cf);
		var len = list.length;
		for (var i = 0; i < len; i++) {
			f.department.options[i] = null;
			f.department.options[i] = new Option(list[i], list[i]);
		}
		console.log('hi');
	} */
</script>
</head>
<body>
<center><h3>��ŷ �˻�</h3></center>
<form action="" id="form" name="form">
<div id="cat">
	<h5>ī�װ�</h5>
	<select id="category">
		<option value="annual_income">����</option>
		<option value="mood">�μ� ������</option>
		<option value="satisfaction">���� ������</option>
		<option value="traffic_convenience">���� ���Ǽ�</option>
	</select>
</div>
<div id="fld">
	<h5>�о�</h5>
	<select id="field" name="cf_name">
		<option value="bussiness">�濵/�繫</option>
		<option value="sales">����/�����</option>
		<option value="IT">IT/���ͳ�</option>
		<option value="design">������</option>
		<option value="service">����</option>
		<option value="professional">������</option>
		<option value="medical">�Ƿ�</option>
		<option value="manufacture">����/����</option>
		<option value="construction">�Ǽ�</option>
		<option value="trade">����/����</option>
		<option value="media">�̵��</option>
		<option value="education">����</option>
		<option value="public/specialized">Ư������/����</option>
	</select>
</div>
<div id="dept">
	<h5>�μ�</h5>
	<select name="cfd_name" id="department">
		<option value="">�μ�</option>
	</select>
</div>
</form>
</body>
</html>