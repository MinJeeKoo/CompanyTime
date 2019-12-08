<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 타입 선택</title>
<meta http-equiv="Content-Type" content="text/html;">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<script>
function userList(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body>
<form name="form" method="POST" action="<c:url value='/user/registerJS/form' />">
	<input type="submit" value="취준생" /> &nbsp;
	<input type="button" value="이직준비자" onClick="userList('<c:url value='/user/registerPT/form' />')">&nbsp;
	<input type="button" value="현직자" onClick="userList('<c:url value='/user/registerW/form' />')">
</form>

</body>
</html>