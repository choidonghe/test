package test.itwillbs.service;

import java.util.List;

import test.itwillbs.domain.AuthVO;
import test.itwillbs.domain.BoardVO;

public interface BoardService {
	
	public void boardJoin(BoardVO vo) throws Exception;
	
	public void boardAuthJoin(AuthVO avo) throws Exception;

	public BoardVO boardLogin(BoardVO vo) throws Exception;
	
	public int boardIdCheck(String id) throws Exception;
	
	public List<BoardVO> boardIdFind(BoardVO vo) throws Exception;
	
	public BoardVO boardPwFind(BoardVO vo) throws Exception;
	
	public void updatePw(BoardVO vo) throws Exception;
	
	public BoardVO read(String id) throws Exception;
	
	
	
}
