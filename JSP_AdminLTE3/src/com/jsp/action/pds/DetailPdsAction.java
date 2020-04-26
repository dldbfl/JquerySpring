package com.jsp.action.pds;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.dto.PdsVO;
import com.jsp.request.SearchCriteria;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;
import com.jsp.service.PdsService;

public class DetailPdsAction implements Action {
	
	private PdsService pdsService;// = BoardServiceImpl.getInstance();
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "pds/detailPds";
		String state=request.getParameter("state");
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		PdsVO pds =new PdsVO();
		
		try {
			
			pds = pdsService.read(pno);
			request.setAttribute("pds", pds);
						
		} catch (SQLException e) {
			e.printStackTrace();
			url="error/500_error";
		} 
		
		return url;
	}

}









