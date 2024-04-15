<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

    <title>네이버 로그인</title>
    
    
</head>
<body>

<h1>login.jsp</h1>
	
	<fieldset>
		<legend>로그인</legend>
		<form action="" method="POST">
		
			아이디 : <input type="text" name="id"> <br>
			비밀번호 : <input type="password" name="pw"> <br>
			
			<input type="submit" value="로그인"> <a href="/findId">아이디 찾기</a> 
			<br>
			
			<a href="/join">회원가입</a>
			
		</form>
	</fieldset>

<!-- 네이버 로그인 버튼 노출 영역 -->
    <div id="naver_id_login"></div>
    <!-- //네이버 로그인 버튼 노출 영역 -->
    <script type="text/javascript">
        var naver_id_login = new naver_id_login("sXbQ_pWcYls10ur3KgKR", "http://localhost:8088/");
        var state = naver_id_login.getUniqState();
        naver_id_login.setButton("green", 10,40);
        naver_id_login.setDomain("http://localhost:8088/login");
        naver_id_login.setState(state);
        naver_id_login.setPopup();
        naver_id_login.init_naver_id_login();
    </script>
    
    <script type="text/javascript">
  var naver_id_login = new naver_id_login("sXbQ_pWcYls10ur3KgKR", "http://localhost:8088/");
  // 접근 토큰 값 출력
  alert(naver_id_login.oauthParams.access_token);
  // 네이버 사용자 프로필 조회
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  function naverSignInCallback() {
    alert(naver_id_login.getProfileData('email'));
    alert(naver_id_login.getProfileData('nickname'));
    alert(naver_id_login.getProfileData('age'));
  }
</script>

</body>
</html>