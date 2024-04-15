package test.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String loginPOST(BoardVO vo)throws Exception{
		logger.debug("loginPOST() 호출");
		
		bService.boardLogin(vo);
		
		return "redirect:/";
		
	}
	
}
