package test.itwillbs.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
		
		bDao.joinBoard(vo);
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
	public BoardVO boardIdFind(BoardVO vo) throws Exception {
		logger.debug("boardIdFind(BoardVO vo) 호출");
		
		return bDao.findIdBoard(vo);
		
	}
	
	
	
	
	
	
	
	
}
