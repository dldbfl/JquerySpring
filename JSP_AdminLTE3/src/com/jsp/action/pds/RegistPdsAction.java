package com.jsp.action.pds;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.PdsService;
import com.jsp.utils.GetUploadPath;

public class RegistPdsAction implements Action{
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	
	
	//	업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500;             //
	private static final int MAX_FILE_SIZE    = 1024 * 1024 * 1;        //
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2;        //

	
	
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url="null";
		
		
		int pno;
		String title;
		String content;
		String writer;
		int viewcnt;
		Date regDate;
		Date updatedate;
		
		
		
		
		
		
		
		
		
		String fileName = multi.getParameter("picture");
		String savedPath = GetUploadPath.getUploadPath("pds.upload");
		
		String filePath = savedPath+fileName;
		
		sendFile(request, response, filePath);
		
		
		
		
		
		
		try {
		
		pdsService.regist(pds);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.href='list.do';window.close();");
		out.println("</script>");
		
		
		return url;
	}
	
	
	
	
	
	
	
	
	
	private void sendFile(HttpServletRequest request, HttpServletResponse response, String filePath) throws ServletException, IOException {
		
		// 보낼 파일 설정.
		File downloadFile = new File(filePath);
		FileInputStream inStream = new FileInputStream(downloadFile);
		
		ServletContext context = request.getSession().getServletContext();
		
		// 파일 포맷으로 MIME를 결정한다.
		String mimeType = context.getMimeType(filePath);
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: "+ mimeType);
		
		// response 수정.
		response.setContentType(mimeType);
		response.setContentLength((int)downloadFile.length());
		
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);
		
		// 파일 내보내기
		OutputStream outputStream = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		
		while ((bytesRead = inStream.read(buffer)) != -1) {
			outputStream.write(buffer,0,bytesRead);
		}
		
		if(inStream != null ) inStream.close();
		if(outputStream != null ) outputStream.close();
		
	}

	
	
	
	
}




