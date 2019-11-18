<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>main_page</title>

<script type="text/javascript">

 function keyword_check(){

  if(document.search.keyword.value==''){ //검색어가 없을 경우  

  alert('검색어를 입력하세요'); //경고창 띄움 

  document.sear
  ch.keyword.focus(); //다시 검색창으로 돌아감 

  return false; 

  }

  else return true;

 }
 
 function userList(targetUri) {
		form.action = targetUri;
		form.submit();
	}
</script>


<style>
  td.search {
    border: 1px solid #444444;
  }
</style>


</head>
<body>
	<h1 align="CENTER">우리사이</h1>

<!-- 로그인클릭시 로그인화면으로 넘어감 -->
<form name="login" method="get" action="<c:url value='/user/login/form' />">
<input type="submit" value="로그인">
</form>

<form name="search" style="margin-right:70px;" method = "get"  
		action ="NextFile.jsp" onsubmit="return keyword_check()">
<!-- 일단 NextFile.jsp로 넘어감 -->

	<div>
 	 	<input type="text" name="keyword"> 
		<input type="submit" value="search">
	</div>  
</form>

	
<table style="width:100%">
	<tr>
		<td>
			<button type="button" style="font-size:2em; border-radius:0.5em; padding:5px 20px" onclick="" >
			회원정보
			</button>
		</td>
		
		<td>
			<button type="button" style="font-size:2em; border-radius:0.5em; padding:5px 20px" onclick="" >
			랭킹검색
			</button>
		</td>
	</tr>
	
	<tr>
		<td id="search">
			<form action="" method="get">
				회사<input type="text" name="company"><br>
				부서<input type="text" name="department"><br>
				<button type="submit">검색</button>
			</form>
		</td>
		<td>
			<button type="button" style="font-size:2em; border-radius:0.5em; padding:5px 20px" onclick="" >
			매칭
			</button>
		</td>
	
	</tr>
</table>
	
</body>
</html>