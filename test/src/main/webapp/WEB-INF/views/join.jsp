<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    
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
			<input type="text" name="id" placeholder="아이디" id="id" required="required" > <br>
				<span class="idchk" style="color: red; display: none;">5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.</span>
				<span class="id_ok" style="color: green; display: none;">사용 가능한 아이디 입니다.</span>
				<span class="id_already" style="color: red; display: none;">중복된 아이디 입니다.</span>
				
			<input type="password" name="pw" placeholder="비밀번호" id="pw" required="required"> <br>
				<span class="pwchk" style="color : red; display: none;">8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.</span>
				
			<input type="email" name="email"placeholder="이메일" id="email" required="required"> <br>
				<span class="emailchk" style="color: red; display: none">이메일 주소가 정확한지 확인해 주세요.</span>
				
			<input type="text" name="name"placeholder="이름" id="name" required="required"> <br>
				<span class="namechk" style="color: red; display: none">한글, 영문 대/소문자를 사용해 주세요. (특수기호, 공백 사용 불가)</span>
				
			<input type="text" name="birthday" placeholder="생년월일" id="birthday" required="required"> <br>
				<span class="birthdaychk" style="color: red; display: none; ">생년월일은 8자리 숫자로 입력해 주세요.</span>
			
			<input type="radio" name="gender" value="남" >남
			<input type="radio" name="gender" value="여" >여 <br>
			<input type="text" name="phoneNumber" placeholder="휴대폰"> <br>
			
			<input type="submit" value="회원가입">
			
			<a href="/login">로그인</a>
			
		</form>
	</fieldset>

<script type="text/javascript">
	$(document).ready(function() {
			
		$('#id').blur(function() {
			var RegId = /^[a-z0-9_-]{5,20}$/;
				
			if(!RegId.test($.trim($("#id").val()))){
				$('.idchk').css({'color': 'red', 'display': 'block'}); 
				$('.id_ok').css({"display":"none"});
      			$('.id_already').css({"display":"none"});
			}else{
				$('.idchk').css({'display': 'none'});
				var id = $('#id').val();
				
			    $.ajax({
		  			url : './ConfirmId',
		    		data : {
		       			id : id
		    		},
		       		type : 'post',
		       	
		     		success : function(cnt) {
			    		if(cnt != 1 && id.length > 0 ){
			       			$('.id_ok').css({"display":"block"});
			  				$('.id_already').css({"display":"none"});
		    			}else if(cnt == 1 && id.length > 0 ){
			   				$('.id_already').css({"display":"block"});
		    				$('.id_ok').css({"display":"none"});
			 			}else{
			  				$('.id_ok').css({"display":"none"});
			 				$('.id_already').css({"display":"none"});
		    			}
		   			},
				}); // ajax 끝
			}; 	
			 
		}); // id 끝
		
		$("#pw").blur(function(){
			var RegPw = /^[a-zA-Z0-9!@#$%^&*]{8,16}$/;
			
			if(!RegPw.test($.trim($("#pw").val()))){
				$(".pwchk").css({'color':'red', 'display':'block'});
			}else{
				$(".pwchk").css({'display':'none'});
			}
		}); //pw 끝 
		
		$("#email").blur(function(){
			var RegEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
			
			if(!RegEmail.test($.trim($("#email").val()))){
				$(".emailchk").css({'color':'red','display':'block'});
			}else{
				$(".emailchk").css({'display':'none'});
			}
		}); //email 끝
		
		$("#name").blur(function(){
			var RegName = /^[ㄱ-힣a-zA-Z]+$/;
			
			if(!RegName.test($.trim($("#name").val()))){
				$('.namechk').css({'color':'red','display':'block'});
			}else{
				$('.namechk').css({'display':'none'});
			}
		}); //name 끝
		
		$("#birthday").blur(function(){
			var RegBirthday = /^\d{8}$/;
			
			if(!RegBirthday.test($.trim($("#birthday").val()))){
				$(".birthdaychk").css({'display':'block'});
			}else{
				$(".birthdaychk").css({'display':'none'});
			}
			
		}); //birthday 끝
		
		
	});
</script>

</body>
</html>