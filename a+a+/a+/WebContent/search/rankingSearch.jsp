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
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	function fnCngList() {
		console.log("hi");
		alert("hello");
		
		var f = document.form;
		
		
		var cf = $("#field option:selected").text();
		console.log(cf);
		
		list = new Array();
		list = <%= request.getAttribute( %> cf <%= ) %> ;
		var len = list.length;
		for (var i = 0; i < len; i++) {
			f.department.options[i] = null;
			f.department.options[i] = new Option(list[i], list[i]);
		}
		console.log('hi');
	}
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
	<select id="field" name="cf_name" onchange="fnCngList();">
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