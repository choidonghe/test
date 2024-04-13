<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>

<html>
<head>
    <title>네이버 로그인</title>
</head>
<body>

<h1>login.jsp</h1>
	
	<fieldset>
		<legend>로그인</legend>
		<form action="" method="POST">
			bno : <input type="number" name="bno"> <br>
			아이디 : <input type="text" name="id"> <br>
			비밀번호 : <input type="password" name="pw"> <br>
			
			<input type="submit" value="로그인"> <br>
			
			<a href="/join">회원가입</a>
			
		</form>
	</fieldset>


<%
    String clientId = "sXbQ_pWcYls10ur3KgKR";//애플리케이션 클라이언트 아이디값";
    String clientSecret = "ZkvRJpXOqY";//애플리케이션 클라이언트 시크릿값";
    String redirectURI = URLEncoder.encode("http://localhost:8088/", "UTF-8");

    // 네이버 로그인 페이지 생성
    String state = new BigInteger(130, new SecureRandom()).toString();
    String loginURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    loginURL += "&client_id=" + clientId;
    loginURL += "&redirect_uri=" + redirectURI;
    loginURL += "&state=" + state;
    session.setAttribute("state", state);
 %>
<a href="<%=loginURL%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>

<%
    // 콜백 처리
    String code = request.getParameter("code");
    String receivedState = request.getParameter("state");
    if (code != null && receivedState != null && receivedState.equals(session.getAttribute("state"))) {
        String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + clientId;
        apiURL += "&client_secret=" + clientSecret;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&code=" + code;
        apiURL += "&state=" + receivedState;

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if (responseCode == 200) {
                out.println(res.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>

</body>
</html>