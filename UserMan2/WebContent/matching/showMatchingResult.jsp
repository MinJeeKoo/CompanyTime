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
			<c:if test="${userType == 'pt' || userType == 'js'}">
				<c:if test="${empty mento}">
					<table>
						<tr>
							<td style="font-size:30px" height="22">&nbsp;&nbsp;<b>${userId}님은 아직 매칭중입니다.</b>&nbsp;&nbsp;</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${!empty mento}">
					<table>
						<tr>
							<td style="font-size:30px" height="22">&nbsp;&nbsp;<b>${userId}님은 ${mento.getW_id()}님과 매칭 되셨습니다.</b>&nbsp;&nbsp;<br></td>
						</tr>
						<tr>
							<td>
								<br><br>
								<details>
									<summary>매칭된 멘토 정보 보기</summary>
									<table style="background-color: LightCoral">
										<tr height="40">
											<td width="150" align="center" bgcolor="FDEDEC">이름</td>
											<td width="250" bgcolor="ffffff" style="padding-left: 10">
											<input type = "text" name="userType" value='${mento.name}' readonly>
											</td>
										</tr>
										<tr height="40">
											<td width="150" align="center" bgcolor="FDEDEC">회사</td>
											<td width="250" bgcolor="ffffff" style="padding-left: 10">
											<input type = "text" name="userType" value='${mentoC}' readonly>
											</td>
										</tr>
										<tr height="40">
											<td width="150" align="center" bgcolor="FDEDEC">부서</td>
											<td width="250" bgcolor="ffffff" style="padding-left: 10">
											<input type = "text" name="userType" value='${mentoCfd}' readonly>
											</td>
										</tr>
										<tr height="40">
											<td width="150" align="center" bgcolor="FDEDEC">이메일</td>
											<td width="250" bgcolor="ffffff" style="padding-left: 10">
											<input type = "text" name="userType" value='${mento.company_email}' readonly>
											</td>
										</tr>
									</table>
								</details>
							</td>
						</tr>
					</table>
				</c:if>
			</c:if>
			<c:if test="${userType == 'w'}">
				<c:if test="${empty mentee}">
					<table>
						<tr>
							<td style="font-size:30px" height="22">&nbsp;&nbsp;<b>${userId}님은 아직 매칭중입니다.</b>&nbsp;&nbsp;</td>
						</tr>
					</table>
				</c:if>
				<table>
					<c:if test="${!empty js}">
						<tr>
							<td style="font-size:30px" height="22">&nbsp;&nbsp;<b>${userId}님은 ${js.userId}님과 매칭 되셨습니다.</b>&nbsp;&nbsp;<br></td>
						</tr>
						<tr>
							<td>
								<br><br>
								<details>
									<summary>매칭된 멘티 정보 보기</summary>
									<table style="background-color: LightCoral">
										<tr height="40">
											<td width="150" align="center" bgcolor="FDEDEC">이름</td>
											<td width="250" bgcolor="ffffff" style="padding-left: 10">
											<input type = "text" name="userType" value='${js.name}' readonly>
											</td>
										</tr>
										<tr height="40">
											<td width="150" align="center" bgcolor="FDEDEC">학교</td>
											<td width="250" bgcolor="ffffff" style="padding-left: 10">
											<input type = "text" name="userType" value='${js.school}' readonly>
											</td>
										</tr>
										<tr height="40">
											<td width="150" align="center" bgcolor="FDEDEC">전공</td>
											<td width="250" bgcolor="ffffff" style="padding-left: 10">
											<input type = "text" name="userType" value='${js.major}' readonly>
											</td>
										</tr>
										<tr height="40">
											<td width="150" align="center" bgcolor="FDEDEC">이메일</td>
											<td width="250" bgcolor="ffffff" style="padding-left: 10">
											<input type = "text" name="userType" value='${js.email}' readonly>
											</td>
										</tr>
									</table>
								</details>
							</td>
						</tr>
					</c:if>
					<c:if test="${!empty pt}">
						<tr>
							<td style="font-size:30px" height="22">&nbsp;&nbsp;<b>${userId}님은 ${pt.p_id}님과 매칭 되셨습니다.</b>&nbsp;&nbsp;<br></td>
						</tr>
						<tr>
							<td>
								<br><br>
								<details>
									<summary>매칭된 멘티 정보 보기</summary>
									<table style="background-color: LightCoral">
										<tr height="40">
											<td width="150" align="center" bgcolor="FDEDEC">이름</td>
											<td width="250" bgcolor="ffffff" style="padding-left: 10">
											<input type = "text" name="userType" value='${pt.name}' readonly>
											</td>
										</tr>
										<tr height="40">
											<td width="150" align="center" bgcolor="FDEDEC">회사</td>
											<td width="250" bgcolor="ffffff" style="padding-left: 10">
											<input type = "text" name="userType" value='${menteeC}' readonly>
											</td>
										</tr>
										<tr height="40">
											<td width="150" align="center" bgcolor="FDEDEC">부서</td>
											<td width="250" bgcolor="ffffff" style="padding-left: 10">
											<input type = "text" name="userType" value='${menteeCfd}' readonly>
											</td>
										</tr>
										<tr height="40">
											<td width="150" align="center" bgcolor="FDEDEC">이메일</td>
											<td width="250" bgcolor="ffffff" style="padding-left: 10">
											<input type = "text" name="userType" value='${pt.company_email}' readonly>
											</td>
										</tr>
									</table>
								</details>
							</td>
						</tr>
					</c:if>
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