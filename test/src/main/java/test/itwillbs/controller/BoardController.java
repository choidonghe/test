package test.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import test.itwillbs.domain.BoardVO;
import test.itwillbs.persistence.BoardDAO;
import test.itwillbs.service.BoardService;

@Controller
public class BoardController {

	@Inject
	private BoardService bService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// http://localhost:8088/login
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void join() throws Exception{
		logger.debug("join() 호출");
		
	}
	
	@RequestMapping(value = "/join",method = RequestMethod.POST)
	public String joinPOST(BoardVO vo) throws Exception {
		logger.debug("joinPOST()");
		
		bService.boardJoin(vo);
		
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public void login() {
		logger.debug("login() 호출");
	}
	
	/*
	 * @RequestMapping(value = "/login",method = RequestMethod.POST) public String
	 * loginPOST(String id,String pw,BoardVO vo,HttpSession session)throws
	 * Exception{ logger.debug("loginPOST() 호출"); logger.debug("id:"+id);
	 * logger.debug("pw:"+pw);
	 * 
	 * BoardVO result = bService.boardLogin(vo);
	 * 
	 * if (result != null) { session.setAttribute("result", vo);
	 * logger.debug("result=>"+result); return "redirect:/"; }else { return "login";
	 * }
	 * 
	 * 
	 * 
	 * }
	 */
	@RequestMapping(value = "/findId",method = RequestMethod.GET)
	public void findId() throws Exception{
		logger.debug("findId() 호출");
	}
	
	@RequestMapping(value = "/findId",method = RequestMethod.POST)
	public String findIdPOST(String name, String email,Model model,BoardVO vo) throws Exception{
		logger.debug("findIdPOST() 호출");
		logger.debug("name:"+name);
		logger.debug("email:"+email);
		
		List<BoardVO> list = bService.boardIdFind(vo);
		
		
		for(BoardVO result : list) {
			 if (result.getName().equals(name) && result.getEmail().equals(email)) {
				logger.debug("result=====>"+result);
				
				model.addAttribute("result", result);
				
				return "showId";
			}
			
		}
		return "showId";
	}
	
	@RequestMapping(value = "/showId",method = RequestMethod.GET)
	public void showId() {
		logger.debug("showId() 호출");
	}
	
	@RequestMapping(value = "/findPw",method = RequestMethod.GET)
	public void findPw() throws Exception{
		logger.debug("findPw() 호출");
		
	}
	
	@RequestMapping(value = "/findPw",method = RequestMethod.POST)
	public String findPwPOST(String id, String email,Model model,BoardVO vo) throws Exception{
		logger.debug("findPwPOST() 호출");
		logger.debug("id:"+id);
		logger.debug("email:"+email);
		
		List<BoardVO> result = bService.boardPwFind(vo);
		
		model.addAttribute("result", result);
		
		return "showPw";
	}
	
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	public void doAll() {
		logger.info(" doAll() 호출");
	}
	
	@RequestMapping(value = "/member",method = RequestMethod.GET)
	public void doMember() {
		logger.info(" doMember() 호출");
	}
	
	@RequestMapping(value = "/admin",method = RequestMethod.GET)
	public void doAdmin() {
		logger.info(" doAdmin() 호출");
	}
	
	@RequestMapping(value = "accessError",method = RequestMethod.GET)
	public void accessDenied() throws Exception{
		logger.debug("accessDenied() 호출");
		logger.debug("접근 권한없는 접근이 발생");
	}
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public void logout() throws Exception{
		logger.debug("logout() 호출");
	}
}
