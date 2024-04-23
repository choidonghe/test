package test.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import test.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE="com.itwillbs.mapper.TestMapper";

	@Override
	public void joinBoard(BoardVO vo) throws Exception {
		logger.debug("joinBoard(BoardVO vo) 호출");
		
		sqlSession.insert(NAMESPACE+".join", vo);
		
	}

	@Override
	public BoardVO loginBoard(BoardVO vo) throws Exception {
		logger.debug("loginBoard(BoardVO vo) 호출");
		
		return sqlSession.selectOne(NAMESPACE+".login", vo);
	}

	@Override
	public int checkIdBoard(String id) throws Exception {
	    logger.debug("checkIdBoard(String id) 호출");
	    logger.debug("DAO id==>"+id);

	    int cnt =  sqlSession.selectOne(NAMESPACE + ".checkId", id);
	    return cnt;
	}

	@Override
	public List<BoardVO> findIdBoard(BoardVO vo) throws Exception {
		logger.debug("findIdBoard(BoardVO vo)호출");
		
		return sqlSession.selectList(NAMESPACE+".findId", vo);
		
	}

	@Override
	public List<BoardVO> findPwBoard(BoardVO vo) throws Exception {
		logger.debug("findPwBoard(BoardVO vo) 호출");
		
		return sqlSession.selectOne(NAMESPACE+".findPw", vo);
	}
	
	
	
	
	
	
	
}
