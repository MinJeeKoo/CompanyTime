<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<title>매칭 결과</title>
<script>

</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<table style="width:100%">
	<tr>
		<td width="20"></td>
		<td>
			<table>
				<tr>
					<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>매칭 결과</b>&nbsp;&nbsp;</td>
				</tr>
			</table>
			<br>
			<c:if test="${empty mt}">
				<table>
					<tr>
						<td style="font-size:50px" height="22">&nbsp;&nbsp;<b>${userId}님은 아직 매칭중입니다.</b>&nbsp;&nbsp;</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${userType == 'pt' || userType == 'js'}">
				<table>
					<tr>
						<td style="font-size:50px" height="22">&nbsp;&nbsp;<b>${userId}님은 ${mt.getW_id()}님과 매칭 되셨습니다.</b>&nbsp;&nbsp;</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${userType == 'w'}">
				<table>
					<tr>
						<c:if test="${!empty js}">
							<td style="font-size:50px" height="22">&nbsp;&nbsp;<b>${userId}님은 ${js.getJS_id()}님과 매칭 되셨습니다.</b>&nbsp;&nbsp;</td>
						</c:if>
						<c:if test="${!empty pt}">
							<td style="font-size:50px" height="22">&nbsp;&nbsp;<b>${userId}님은 ${pt.getP_id()}님과 매칭 되셨습니다.</b>&nbsp;&nbsp;</td>
						</c:if>
					</tr>
				</table>
			</c:if>
			 <%-- <table style="background-color: YellowGreen">
				<tr>
					<td width="  --%>
		</td>
	</tr>
</table>
</body>
</html>