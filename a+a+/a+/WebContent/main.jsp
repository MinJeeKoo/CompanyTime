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

			<button type="button" style="font-size:2em; border-radius:0.5em; padding:5px 20px;" onclick="location.href='./search/rankingSearch' ">
			랭킹검색
			</button>
	
</body>
</html>