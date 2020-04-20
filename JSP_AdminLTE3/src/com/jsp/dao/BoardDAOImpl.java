package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;
import com.jsp.request.SearchCriteria;

public class BoardDAOImpl implements BoardDAO {

	
	// SqlSessionFactory
			private SqlSessionFactory sessionFactory;
			//= OracleMyBatisSqlSessionFactoryBuilder.getSqlSessionFactory();
			public void setSessionFactory(SqlSessionFactory sessionFactory) {
				this.sessionFactory = sessionFactory;
			}	
	
	@Override
	public BoardVO selectBoardByBno(int bno) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertBoard(BoardVO board) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBoard(BoardVO board) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBoard(int bno) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void increaseViewCnt(int bno) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int selectBoardSeqNext() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> selectBoardCriteria(SearchCriteria cri) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectBoardCriteria",null);
		session.close();
		return null;
	}

	@Override
	public int selectBoardCriteriaTotalCount(SearchCriteria cri) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
