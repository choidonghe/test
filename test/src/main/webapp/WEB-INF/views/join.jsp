<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>join.jsp</h1>

	<fieldset>
		<legend>회원가입</legend>
		<form action="" method="POST">
			bno : <input type="number" name="bno"> <br>
			아이디 : <input type="text" name="id"> <br>
			비밀번호 : <input type="password" name="pw"> <br>
			
			<input type="submit" value="회원가입">
			<a href="/login">로그인</a>
			
		</form>
	</fieldset>
	
</body>
</html>