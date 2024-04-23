package test.itwillbs.service;

import java.util.List;

import test.itwillbs.domain.BoardVO;

public interface BoardService {
	
	public void boardJoin(BoardVO vo) throws Exception;

	public BoardVO boardLogin(BoardVO vo) throws Exception;
	
	public int boardIdCheck(String id) throws Exception;
	
	public List<BoardVO> boardIdFind(BoardVO vo) throws Exception;
	
	public List<BoardVO> boardPwFind(BoardVO vo) throws Exception;
	
	
	
}
