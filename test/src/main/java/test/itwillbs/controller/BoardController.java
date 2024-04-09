package test.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import test.itwillbs.persistence.BoardDAO;

@Controller
public class BoardController {

	@Inject
	private BoardDAO bDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// http://localhost:8088/list
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list() throws Exception{
		logger.debug("list() 호출");
	}
	
}
