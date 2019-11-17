<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 타입 선택</title>
<meta http-equiv="Content-Type" content="text/html;">
<link rel=stylesheet href="<c:url value='/css/user.css' />" type="text/css">
<!-- <script>
    function btn_click(str){                             
        if(str=="update"){                                 
            frm1.action="<c:url value='/user/registerJS/form' />";      
        } else if(str=="delete"){      
            <!--frm1.action="/user/registerJS/form";    
        }  else {
            //...
        }
    }
</script>-->
</head>
<body>
<a href="<c:url value='/user/registerJS/form' />">취준생</a> 	
<!-- <form method="post" name="frm1">
<tr height="40">
			<td width="150" align="center" bgcolor="E6ECDE">사용자 ID</td>
			<td width="250" bgcolor="ffffff" style="padding-left:10">
				<input type="text" style="width:240" name="userId">
			</td>
		  </tr>
<input type="submit" value="수정" onclick='btn_click("update");'>
<input type="submit" value="삭제" onclick='btn_click("delete");'>
</form> -->
</body>
</html>