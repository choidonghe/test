package test.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import test.itwillbs.domain.AuthVO;
import test.itwillbs.domain.BoardVO;
import test.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardDAO bDao;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Override
	public void boardJoin(BoardVO vo) throws Exception {
		logger.debug("boardJoin(BoardVO vo) 호출");
		logger.debug("vo==="+vo);
		
		bDao.joinBoard(vo);
	}
	
	@Override
	public void boardAuthJoin(AuthVO avo) throws Exception {
		logger.debug("boardAuthJoin(AuthVO avo) 호출");
		
		bDao.authJoin(avo);
		
	}



	@Override
	public BoardVO boardLogin(BoardVO vo) throws Exception {
		logger.debug("boardLogin(BoardVO vo) 호출");
		
		return bDao.loginBoard(vo);
	}

	@Override
	public int boardIdCheck(String id) throws Exception {
		logger.debug("boardIdCheck(String id) 호출");
		logger.debug("Ser id==>"+id);
		int cnt = bDao.checkIdBoard(id);
		return cnt;
		
	}

	@Override
	public List<BoardVO> boardIdFind(BoardVO vo) throws Exception {
		logger.debug("boardIdFind(BoardVO vo) 호출");
		
		return bDao.findIdBoard(vo);
		
	}

	@Override
	public List<BoardVO> boardPwFind(BoardVO vo) throws Exception {
		logger.debug("boardPwFind(BoardVO vo) 호출");
		
		return bDao.findPwBoard(vo);
	}

	@Override
	public BoardVO read(String id) throws Exception {
		// TODO Auto-generated method stub
		return bDao.read(id);
		
	}
	
	
	
	
	
	
	
	
	
	
}
