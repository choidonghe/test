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
		<form action="" method="POST" onsubmit="return checkAll()" id="myForm">
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
        $('#myForm').submit(function(event) {
            event.preventDefault(); // 폼 제출을 막음
            validateId();
        });
        $('#id').blur(validateId);
        $('#pw').blur(validatePw);
        $('#email').blur(validateEmail);
        $('#name').blur(validateName);
        $('#birthday').blur(validateBirthday);
    });

    function validateId() {
        var RegId = /^[a-z0-9_-]{5,20}$/;
        var id = $('#id').val();

        if (!RegId.test($.trim(id))) {
            $('.idchk').css({'display': 'block'}); 
            $('.id_ok').css({"display":"none"});
            $('.id_already').css({"display":"none"});
            return false;
        } else {
            $('.idchk').css({'display': 'none'});
            $.ajax({
                url: './ConfirmId',
                data: { id: id },
                type: 'post',
                success: function(cnt) {
                    if (cnt != 1 && id.length > 0) {
                        $('.id_ok').css({"display":"block"});
                        $('.id_already').css({"display":"none"});
                    } else if (cnt == 1 && id.length > 0) {
                        $('.id_already').css({"display":"block"});
                        $('.id_ok').css({"display":"none"});
                    } else {
                        $('.id_ok').css({"display":"none"});
                        $('.id_already').css({"display":"none"});
                    }
                }
            });
        }
    }


    function validatePw() {
        var RegPw = /^[a-zA-Z0-9!@#$%^&*]{8,16}$/;
        var pw = $('#pw').val();

        if (!RegPw.test($.trim(pw))) {
            $(".pwchk").css({'display':'block'});
            return false;
        } else {
            $(".pwchk").css({'display':'none'});
            return true;
        }
    }

    function validateEmail() {
        var RegEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        var email = $('#email').val();

        if (!RegEmail.test($.trim(email))) {
            $(".emailchk").css({'display':'block'});
            return false;
        } else {
            $(".emailchk").css({'display':'none'});
            return true;
        }
    }

    function validateName() {
        var RegName = /^[가-힣a-zA-Z]+$/;
        var name = $('#name').val();

        if (!RegName.test($.trim(name))) {
            $('.namechk').css({'display':'block'});
            return false;
        } else {
            $('.namechk').css({'display':'none'});
            return true;
        }
    }

    function validateBirthday() {
        var RegBirthday = /^\d{8}$/;
        var birthday = $('#birthday').val();

        if (!RegBirthday.test($.trim(birthday))) {
            $(".birthdaychk").css({'display':'block'});
            return false;
        } else {
            $(".birthdaychk").css({'display':'none'});
            return true;
        }
    }

    function validateAll() {
        return validateId() && validatePw() && validateEmail() && validateName() && validateBirthday();
    }
</script>

</body>
</html>
