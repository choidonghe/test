package test.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import test.itwillbs.service.BoardService;

@RestController
public class BoardRestController {

	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	
	@Inject
	private BoardService bService;
	
	
	
	
	@PostMapping("/ConfirmId")
    public ResponseEntity<Integer> confirmId(@RequestParam("id") String id) throws Exception {
        int cnt = bService.boardIdCheck(id);
        return ResponseEntity.ok().body(cnt);
    }
}
	
	

