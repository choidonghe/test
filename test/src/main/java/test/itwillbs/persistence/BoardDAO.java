package test.itwillbs.persistence;

import test.itwillbs.domain.BoardVO;

public interface BoardDAO {

	public void joinBoard(BoardVO vo) throws Exception;
	
	public BoardVO loginBoard(BoardVO vo) throws Exception;
	
	public int checkIdBoard(String id) throws Exception;
}
