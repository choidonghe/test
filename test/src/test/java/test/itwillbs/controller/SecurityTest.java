package test.itwillbs.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.itwillbs.domain.BoardVO;
import test.itwillbs.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml"
		}
		)
public class SecurityTest {

	private static final Logger logger = LoggerFactory.getLogger(SecurityTest.class);
	
	// 디비 접속DataSource 객체
	@Inject
	private DataSource ds;
	
	// 암호화 처리 PWEncoder 객체
	@Inject
	private PasswordEncoder pwEncoder;
	
	@Inject
	private BoardDAO bdao;
	
	//@Test
	public void test_회원가입() throws Exception{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql ="";
		
		for(int i=0; i<100; i++) {
			
		
		
		// 1. 디비연결
		con = ds.getConnection();
		
		// 2. sql 구문 작성 & pstmt 객체
		sql = "insert into test_tbl(id,pw,name,email) values(?,?,?,?)";
		
		pstmt = con.prepareStatement(sql);
		// ???
		
		// 3. sql 구문 실행 - for
		
		// 비밀번호 암호화 
		pstmt.setString(2, pwEncoder.encode("pw"+i));
		
		if(i < 80 ) {
			pstmt.setString(1, "user"+i);
			pstmt.setString(3, "일반사용자"+i);
			pstmt.setString(4, "user"+i+"@itwillbs.com");
		}else if(i < 90) {
			pstmt.setString(1, "manager"+i);
			pstmt.setString(3, "운영자"+i);
			pstmt.setString(4, "manager"+i+"@itwillbs.com");
		}else {
			pstmt.setString(1, "admin"+i);
			pstmt.setString(3, "관리자"+i);
			pstmt.setString(4, "admin"+i+"@itwillbs.com");
			
		}
		
		// 4.sql 구문 실행
		pstmt.executeUpdate();
		
		// 자원해제(히카리 10개만 사용)
		pstmt.close();
		con.close();
		
		}//for
	}//회원가입()
	
	//@Test
	public void test_회원인증정보_가입() throws Exception{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql ="";
		
		for(int i=0; i<100; i++) {
			
		
		
		// 1. 디비연결
		con = ds.getConnection();
		
		// 2. sql 구문 작성 & pstmt 객체
		sql = "insert into auth(id,AUTHORITY) values(?,?)";
		
		pstmt = con.prepareStatement(sql);
		// ???
		
		// 3. sql 구문 실행 - for
		
		
		if(i < 80 ) {
			pstmt.setString(1, "user"+i);
			pstmt.setString(2, "ROLE_USER");
		}else if(i < 90) {
			pstmt.setString(1, "manager"+i);
			pstmt.setString(2, "ROLE_MANAGER");
		}else {
			pstmt.setString(1, "admin"+i);
			pstmt.setString(2, "ROLE_ADMIN");
		}
		
		// 4.sql 구문 실행
		pstmt.executeUpdate();
		
		// 자원해제(히카리 10개만 사용)
		pstmt.close();
		con.close();
		
		}//for
	}
	//@Test
	public void testEncode() {
		System.out.println(pwEncoder.encode("1234"));
		System.out.println(pwEncoder.encode("1234"));
		
	}
	/*
	 * @Test public void test_회원정보조회() throws Exception{
	 * logger.info("test_회원정보조회() 실행");
	 * 
	 * 
	 * 
	 * BoardVO vo = bdao.readMember("ADMIN99");
	 * 
	 * logger.info(" vo :"+ vo); }
	 */
	
	@Test
	public void test_회원정보조회() throws Exception{
		logger.info("test_회원정보조회() 실행");
		
		
		
		BoardVO vo = bdao.read("ADMIN99");
		
		logger.info(" vo :"+ vo);
	}
}



