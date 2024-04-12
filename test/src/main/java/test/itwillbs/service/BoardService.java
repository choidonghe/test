package test.itwillbs.service;

import test.itwillbs.domain.BoardVO;

public interface BoardService {
	
	public void boardJoin(BoardVO vo) throws Exception;

	public BoardVO boardLogin(BoardVO vo) throws Exception;
}
