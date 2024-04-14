<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>join.jsp</h1>

	<fieldset>
		<legend>회원가입</legend>
		<form action="" method="POST">
			<div class="form_group">
				<input type="text" name="id" placeholder="아이디" id="id" required="required" oninput="checkId()"> <br>
				<span class="id_ok" style="color: green; display: none;">사용 가능한 아이디 입니다.</span>
				<span class="id_already" style="color: red; display: none;">중복된 아이디 입니다.</span>
			</div>
			<input type="password" name="pw" placeholder="비밀번호"> <br>
			<input type="email" name="email"placeholder="이메일"> <br>
			<input type="text" name="name"placeholder="이름"> <br>
			<input type="text" name="birthday" placeholder="생년월일"> <br>
			<input type="radio" name="gender" value="남">남
			<input type="radio" name="gender" value="여">여 <br>
			<input type="text" name="phoneNumber" placeholder="휴대폰"> <br>
			
			<input type="submit" value="회원가입">
			<a href="/login">로그인</a>
			
		</form>
	</fieldset>

<script type="text/javascript">
   /*  $(document).ready(function() {
    	//ID 중복 확인
    	//id를 입력할 수 있는 input text 영역을 벗어나면 동작한다.
    	$("#id").on("focusout", function() { */
    		function checkId(){
    		var id = $('#id').val();
    		
        	//Ajax로 전송
        	$.ajax({
        		url : './ConfirmId',
        		data : {
        			id : id
        		},
        		type : 'post',
        	
        		success : function(cnt) {
        			if(cnt != 1 && id.length > 0 ){
        				$('.id_ok').css("display","inline-block");
        				$('.id_already').css("display","none");
        			}else if(cnt == 1 && id.length > 0 ){
        				$('.id_already').css("display","inline-block");
        				$('.id_ok').css("display","none");
        			}else{
        				$('.id_ok').css("display","none");
        				$('.id_already').css("display","none");
        			}
        		},
        });
    	};

</script>




</body>
</html>