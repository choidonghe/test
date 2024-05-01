<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="include/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .btn-group-vertical {
        position: fixed;
        top: 50%; /* 필요에 따라 조절하세요 */
        transform: translateY(-50%);
        left: 20px; /* 필요에 따라 조절하세요 */
    }
    
    .container1{
    	width : 1500px;
    	height : 200px;
    	margin : 20px auto;
    	border: 1px solid black;
    }
    
    .sss{
    	width : 100px;
    	height : 50px;
    	border: 1px solid black;
    	border-radius: 80%;
    	
    }
    
</style>
</head>
<body>

<h1>mypage.jsp</h1>
    
user = ${user} <br>

<div class="btn-group-vertical">
  <button type="button" class="btn btn-default">Top</button>
  <button type="button" class="btn btn-default">Middle</button>
  <button type="button" class="btn btn-default">Bottom</button>
</div>

<div class="container1">
	<div class="sss">
		dsadas
	</div>
</div>
<%@include file="include/footer.jsp" %>
</body>
</html>