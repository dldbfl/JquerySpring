package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;
import com.jsp.dto.PdsVO;
import com.jsp.request.BoardRequest;
import com.jsp.request.MemberRegistRequest;
import com.jsp.request.ModifyBoardRequest;
import com.jsp.request.SearchCriteria;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;
import com.jsp.service.MemberServiceImpl;
import com.jsp.service.PdsService;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;

public class ModifyPdsAction implements Action {

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		String url = null;
					
		try {
			/*pdsVO.setAttachList(null);
			
			saveFile(request,response);
			System.out.println(pdsVO);
			pdsService.modify(pdsVO);*/
			
			PdsVO pds = saveFile(request);
			
			pdsService.modify(pds);
			
		}catch(Exception e) {
			e.printStackTrace();
			url = "error/500_error";
		}		
		
		// 화면결정
		//url = "redirect:detail.do?pno="+pno;	
				
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		out.println("<script>");
		out.println("alert(\"자료 수정이 정상적으로 완료 되었소\");");
		out.println("window.opener.location.reload(true);");
		out.println("window.close();");
		out.println("</script>");
				
		return url;
	}
	
	//	업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;             //
	private static final int MAX_FILE_SIZE    = 1024 * 1024 * 40;        //
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;        //


	private PdsVO saveFile(HttpServletRequest request) throws Exception {

		PdsVO pds = new PdsVO();
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		List<Integer> deleteAnoList = new ArrayList<>();
		//업로드를 위한 환경 설정 적용
		DiskFileItemFactory factroy = new DiskFileItemFactory();
		//저장을 위한 threshold memory 적용
		factroy.setSizeThreshold(MEMORY_THRESHOLD);
		//임시 저장 위치 결정
		factroy.setRepository(new File(System.getProperty("java.io.tmpdir")));		
		ServletFileUpload upload = new ServletFileUpload(factroy);
		
		//업로드 파일의 크기 적용.
		upload.setFileSizeMax(MAX_FILE_SIZE);
		
		//업로드 request 크기 적용.
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		//실제 저장 경로를 설정. <---- 여기서 요일별로 나눠야해				
		String uploadPath = GetUploadPath.getUploadPath("pds.upload");
		
		File file = new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath+"가 이미 존재하거나 실패했습니다.");
		};
		
		//request로부터 받는 파일을 추출해서 저장
	try {
		List<FileItem> formItems = upload.parseRequest(request);			
		
		String writer = null; // request.getParameter("writer")
		String title = null; // request.getParameter("title");
		String content = null;// request.getParameter("content");
		int pno = -1; // Integer.parseInt(request.getParameter("pno"))
		int ano = -1;
		
		
		for (FileItem item : formItems) {
			//	1.1	필드
			if(item.isFormField()) {
				//	파라메터 구분 (파라메터 이름)
				switch (item.getFieldName()) {
				case "title":	
					title = item.getString("utf-8");
				break;				
				
				case "writer":	
					writer = item.getString("utf-8");
				break;
				
				case "content":	
					content = item.getString("utf-8");
				break;	
				
				case "pno":
					pno = Integer.parseInt(item.getString("utf-8"));
				break;
				
				case "deleteFile" : 
					ano = Integer.parseInt(item.getString("utf-8"));
					//attachList.addAll(pdsService.getPds(pno).getAttachList());	
					deleteAnoList.add(ano);
				break;
				}

				
			}else {
				//	1.2	파일
				//	summernote의 input의 name인 files를 제외함
				if(!item.getFieldName().equals("uploadFile")) continue;
				
				String fileName = new File(item.getName()).getName();
				fileName = MakeFileName.toUUIDFileName(fileName, "$$");
				String filePath = uploadPath + File.separator + fileName;
				File storeFile = new File(filePath);
				
				//	1.5	파일저장
				//	local HDD에 저장.
				try {
					item.write(storeFile);
				} catch (Exception e) {						
					e.printStackTrace();
					throw e;
				}
				
				//	AttachVO 생성
				AttachVO attach = new AttachVO();
				
				//	DB에 저장할 attach에 file 내용 추가.
				attach.setFileName(fileName);
				attach.setUploadPath(uploadPath);
				attach.setFileType(fileName.substring(fileName.lastIndexOf(".")+1));
				
				//	List<AttachVO>  내용 추가
				attachList.add(attach);
				System.out.println("upload file : " + attach);
				//	원래 파일들 날려먹기
				
			}
		}
		
		
		List<AttachVO> innerAttachList = pdsService.getPds(pno).getAttachList();
		
		for(AttachVO allAttach : innerAttachList) {
			int allAno= allAttach.getAno();
			for(int deleteAno : deleteAnoList) {
				if(allAno == deleteAno) {
					//삭제할거
				}else {
					//남길거
					attachList.add(allAttach);
				}
				System.out.println("deleteAno : "+deleteAno);
			}if(attachList.size()==0) {
				//안삭제한거
				attachList.add(allAttach);
			}
			System.out.println("allAno : "+allAno);
		}
		
		
		//pds.setAttachList(pdsService.getPds(pds.getPno()).getAttachList());
		pds.setPno(pno);
		pds.setAttachList(attachList);
		pds.setWriter(writer);
		pds.setContent(content);
		pds.setTitle(title);
		//pds.setPno(pno);	
		
	} catch(Exception e) {
		e.printStackTrace(); //개발 중 에러 확인
	}
	
		
		return pds;
		
/*		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.href='list.do';window.close();");
		out.println("</script>");
*/		
		
		
	}

}









