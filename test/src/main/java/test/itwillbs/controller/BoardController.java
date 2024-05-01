package test.itwillbs.controller;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import test.itwillbs.domain.AuthVO;
import test.itwillbs.domain.BoardVO;
import test.itwillbs.domain.PasswordGenerator;
import test.itwillbs.persistence.BoardDAO;
import test.itwillbs.service.BoardService;
import test.itwillbs.service.MailService;

@Controller
@EnableAsync
public class BoardController {

	@Inject
	private BoardService bService;
	
	@Inject
	private MailService mailService;

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	// http://localhost:8088/login
	
	@Autowired
	private PasswordEncoder pwEncoder;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void join() throws Exception {
		logger.debug("join() 호출");

	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(BoardVO vo,AuthVO avo, String pw,String id) throws Exception{
		logger.debug("joinPOST()");
		logger.debug("id==>"+id);
		logger.debug("pw"+pw);
		logger.debug("vo==>"+vo);
		
		vo.setPw(pwEncoder.encode(vo.getPw()));

		
		bService.boardJoin(vo);
		bService.boardAuthJoin(avo);
		
		

		return "redirect:/login";
	}

	@GetMapping("/login")
	public String loginForm(@RequestParam(value = "error", required = false) String error, 
				@RequestParam(value = "exception", required = false) String exception,
				Model model) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "/login";
	}

	
	/*
	 * @RequestMapping(value = "/login",method = RequestMethod.POST) public String
	 * loginPOST(String id,String pw,BoardVO vo,HttpSession session)throws Exception
	 * { logger.debug("loginPOST() 호출"); logger.debug("id:"+id);
	 * logger.debug("pw:"+pw);
	 * 
	 * BoardVO result = bService.boardLogin(vo);
	 * 
	 * if (result != null) { logger.debug("result=>"+result);
	 * session.setAttribute("result", result); return "redirect:/"; }else { return
	 * "login"; } }
	 */
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String main(Model model,String id,Principal principal) throws Exception {
		logger.debug("main() 호출");
		
		/*
		 * // 현재 사용자의 인증 정보 가져오기 Authentication authentication =
		 * SecurityContextHolder.getContext().getAuthentication();
		 * 
		 * // 사용자 이름 가져오기 String username = authentication.getName();
		 * 
		 * BoardVO result = bService.read(id);
		 * 
		 * // 모델에 사용자 이름 추가하기 model.addAttribute("username", username);
		 * model.addAttribute("result", result);
		 */
		
		String userid = principal.getName();
        BoardVO vo = bService.read(userid);
        model.addAttribute("user", vo);
		
		
        // home.jsp로 이동
        return "home";
		
	}
	 
	@RequestMapping(value = "/findId", method = RequestMethod.GET)
	public void findId() throws Exception {
		logger.debug("findId() 호출");
	}

	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	public String findIdPOST(String name, String email, Model model, BoardVO vo) throws Exception {
		logger.debug("findIdPOST() 호출");
		logger.debug("name:" + name);
		logger.debug("email:" + email);

		List<BoardVO> list = bService.boardIdFind(vo);

		for (BoardVO result : list) {
			if (result.getName().equals(name) && result.getEmail().equals(email)) {
				logger.debug("result=====>" + result);

				model.addAttribute("result", result);

				return "showId";
			}

		}
		return "showId";
	}

	@RequestMapping(value = "/showId", method = RequestMethod.GET)
	public void showId() {
		logger.debug("showId() 호출");
	}

	@RequestMapping(value = "/findPw", method = RequestMethod.GET)
	public void findPw() throws Exception {
		logger.debug("findPw() 호출");

	}

	@RequestMapping(value = "/findPw", method = RequestMethod.POST)
	public String findPwPOST(String id, String email, Model model, BoardVO vo) throws Exception {
		logger.debug("findPwPOST() 호출");
		logger.debug("id:" + id);
		logger.debug("email:" + email);

		
		  BoardVO result = bService.boardPwFind(vo);		  
		  model.addAttribute("result", result);
		 
		
		 // 사용자가 존재하는 경우에만 처리
	    if (result != null) {
	        // 임시 비밀번호 생성
	        String temporaryPassword = PasswordGenerator.generateRandomPassword();
	        // 암호화된 임시 비밀번호 생성
	        String pw = pwEncoder.encode(temporaryPassword);
	        // 사용자의 비밀번호를 암호화된 임시 비밀번호로 변경
	        result.setPw(pw);
	        // 변경된 비밀번호를 데이터베이스에 업데이트
	        bService.updatePw(result);
	        
	        // 이메일로 임시 비밀번호 보내기
	        StringBuffer sb = new StringBuffer();
	        sb.append(" <html><head></head><body> ");
	        sb.append(" <h1> 안녕하세요 *** 입니다. </h1> ");
	        sb.append(" <p>임시 비밀번호: " + temporaryPassword + "</p> ");
	        sb.append(" </body></html> ");
	        
	        mailService.sendMail(email, "임시 비밀번호 발급 안내", sb.toString());
	        
	        // 비밀번호 변경 후 메시지를 모델에 추가
	        model.addAttribute("message", "임시 비밀번호가 이메일로 발송되었습니다. 새로운 비밀번호로 로그인해주세요.");
	    } else {
	        // 사용자가 존재하지 않는 경우에는 메시지를 모델에 추가
	        model.addAttribute("message", "입력한 정보와 일치하는 사용자가 없습니다.");
	    }

	    return "showPw";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public void doAll() {
		logger.info(" doAll() 호출");
	}

	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public void doMember() {
		logger.info(" doMember() 호출");
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public void doAdmin() {
		logger.info(" doAdmin() 호출");
	}

	@RequestMapping(value = "accessError", method = RequestMethod.GET)
	public void accessDenied(Authentication auth) throws Exception {
		logger.debug("accessDenied() 호출");
		logger.debug("접근 권한없는 접근이 발생");
		logger.debug("auth:"+auth);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout() throws Exception {
		logger.debug("logout() 호출");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping(value = "/annoMember")
	public void doMember2() {
		
		logger.debug("logined annotation member");
	
	}
	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/annoAdmin")
	public void doAdmin2() {
		logger.debug("admin annotation only");
	}
	
	@RequestMapping(value = "/mypage",method = RequestMethod.GET)
	public void mypage(Principal principal,Model model) throws Exception {
		logger.debug("mypage() 호출");
		
		String userid = principal.getName();
        BoardVO vo = bService.read(userid);
        model.addAttribute("user", vo);
		
	}
	
}
