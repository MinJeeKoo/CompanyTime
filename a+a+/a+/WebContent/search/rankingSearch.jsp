<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
	
</style>
</head>
<body>
<script>
	(function fnCngList() {
		var f = document.form;
		list = ${departmentList};
		
		if (list != null) {
			var len = list.length;
			for (var i = 0; i < len; i++) {
				f.department.options[i] = null;
				f.department.options[i] = new Option(list[i], list[i]);
			}
		}	
	})();  
</script>
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
	<select id="field" name="cf_name" onchange='location.href="<c:url value ='/search/rankingSearch' />"'>
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
	<select name="department">
		<option value="">�μ�</option>
	</select>
</div>
</form>
</body>
</html>