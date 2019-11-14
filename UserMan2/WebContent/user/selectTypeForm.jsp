<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 타입 선택</title>
<meta http-equiv="Content-Type" content="text/html;">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<script>
function userCreate() {
	<!--if (body.radio.radioTxt.value[1] == true) {
		alert("취준생입니다.");
		return false;
	}-->
	
	<!--
		var obj = document.getElementsByName("radioTxt");
		var checked_index = -1;
		var checked_value = "";
		for(var i=0; i<obj.length; i++ ) {
			if(obj[i].checked) {
				alert( "선택된 항목 인덱스: "+checked_index+"\n선택된 항목 값: "+checked_value );
				checked_index = i;
				checked_value = obj[i].value;
			}
		}
		alert( "선택된 항목 인덱스: "+checked_index+"\n선택된 항목 값: "+checked_value );-->
		
	form.submit();
}

function userLogin(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body>
<!-- registration form  -->
<form name="form" method="POST" action="<c:url value='/user/register/form' />">
  <table style="width: 100%">
    <tr>
      <td width="20"></td>
	  <td>
	    <table>
		  <tr>
		    <td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>회원 타입 선택</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	 
	    <input type="radio" name="radioTxt" value="Worker" checked>현직자
	    <input type="radio" name="radioTxt" value="PreparationForTurnover">이직준비자(이직자)
	    <input type="radio" name="radioTxt" value="Student">취업준비생(취준생)
	    <br>	  
	    <table style="width: 100%">
		  <tr>
			<td align="left">
			<input type="button" value="다음" onclick="userCreate()" /> &nbsp;
			<input type="button" value="로그인창으로 돌아가기" onClick="userLogin('<c:url value='/user/login/form' />')">
			</td>
		  </tr>
	    </table>
	  </td>
    </tr>
  </table>  
 </form>
 </body>
</html>