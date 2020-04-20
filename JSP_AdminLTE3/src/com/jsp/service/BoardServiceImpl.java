package com.jsp.service;

import java.sql.SQLException;
import java.util.Map;

import com.jsp.dto.BoardVO;
import com.jsp.request.SearchCriteria;

public class BoardServiceImpl implements BoardService {

	@Override
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO getBoardForModify(int bno) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(BoardVO board) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modify(BoardVO board) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(int bno) throws SQLException {
		// TODO Auto-generated method stub

	}

}
