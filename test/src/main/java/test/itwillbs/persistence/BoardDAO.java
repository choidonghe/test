package test.itwillbs.persistence;

import java.util.List;

import test.itwillbs.domain.BoardVO;

public interface BoardDAO {

	public void joinBoard(BoardVO vo) throws Exception;
	
	public BoardVO loginBoard(BoardVO vo) throws Exception;
	
	public int checkIdBoard(String id) throws Exception;
	
	public List<BoardVO> findIdBoard(BoardVO vo) throws Exception;
	
	public List<BoardVO> findPwBoard(BoardVO vo) throws Exception;
	
	
	
}
