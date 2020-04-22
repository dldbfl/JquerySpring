package com.jsp.action.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;
import com.jsp.request.BoardRequest;
import com.jsp.request.MemberRegistRequest;
import com.jsp.request.SearchCriteria;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;
import com.jsp.service.MemberServiceImpl;

public class BoardRegistAction implements Action {
	
	private BoardService boardService;// = BoardServiceImpl.getInstance();
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		  String   url        = "board/registBoard_success";
		   
		  String   title         = request.getParameter("title");
		  String   writer        = request.getParameter("writer"); 
		  String   content       = request.getParameter("content"); 
		  int      bno			 = 0;
		  
	      BoardRequest boardReq = new BoardRequest(title, writer, content, bno);
	    		  
	      BoardVO board = boardReq.toBoardVO();
	      
	      	try {
	      		boardService.write(board);;
			} catch (SQLException e) {
				e.printStackTrace();
				url="board/regist_fail";
			}		
		return url;
	}

}









