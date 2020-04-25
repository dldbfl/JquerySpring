package com.jsp.action.pds;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;
import com.jsp.utils.MakeLogForException;

public class RegistPdsAction implements Action{
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	
	
	//	업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500;             //
	private static final int MAX_FILE_SIZE    = 1024 * 1024 * 1;        //
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 5;        //

	
		
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url="null";

		PdsVO pdsVO = new PdsVO();
		
		
		String title;
		String content;
		String writer;
		int viewcnt;
		Date regDate;
		Date updatedate;
		
		
		
		pdsService.regist(pdsVO);		

		
		
		return url;
		
		
		
		
/*		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.href='list.do';window.close();");
		out.println("</script>");
*/		
		
		
	}
	
	
	
	
	
	
	

	
	
	
	
}




