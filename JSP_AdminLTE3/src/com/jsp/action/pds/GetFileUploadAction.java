package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.MemberVO;
import com.jsp.dto.PdsVO;
import com.jsp.request.MemberRegistRequest;
import com.jsp.service.PdsService;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;
import com.jsp.utils.MakeLogForException;

public class GetFileUploadAction implements Action {
	
//	업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500;             //
	private static final int MAX_FILE_SIZE    = 1024 * 1024 * 1;        //
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 5;        //

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PdsVO fileName = null;
		try {
			fileName = saveFile(request,response);
		}  catch (Exception e) {
			e.printStackTrace(); //개발 중 에러 확인
			MakeLogForException.makeLog(request,e);
			throw new IOException("파일 업로드 실패");// 사용자 화면 용 (ajax)
		}
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(fileName);
		
		return null;
	}

	private PdsVO saveFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {

		PdsVO pdsVO = new PdsVO();
		
		//request 파잏 첨부 확인.
		if(!ServletFileUpload.isMultipartContent(request)) {
			 throw new Exception(); // throw Exception 처리
		}
		
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
		String uploadPath=GetUploadPath.getUploadPath("pds.upload");
		File file = new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath+"가 이미 존재하거나 실패했습니다.");
		};
		
		//request로부터 받는 파일을 추출해서 저장
	
		List<FileItem> formItems = upload.parseRequest(request);
		
		//파일 개수 확인
		if(formItems != null && formItems.size() > 0) {
			for(FileItem item : formItems) {//form items 반복해서 꺼내는 구문
				if(!item.isFormField()) {//파일일 경우 해당-업로드된 파일 저장
					
					
					//작업시작
					List<AttachVO> attachList = null;
												
					String fileName = item.getFieldName(); //파라미터이름
					String uploadsPath = uploadPath + File.separator + MakeFileName.toUUIDFileName(".txt", "");
					String fileType = item.getContentType(); //파일타입
					String attacher = item.getFieldName("writer");
					
					
					
					
					
					
					for(int i=0; i<5; i++) {
						
						attachList.set(i, item);
					}
					
					pdsVO.setAttachList(attachList);
					
					
					//String fileName = item.getName(); //원본 파일명
					
				  	//File file = new File("C://Users/Administrator/Downloads/s/"+fileName); 
					
				
				  	/*MemberRegistRequest memberReq = new MemberRegistRequest(id,pwd,authority,email,phone,picture,name);
				       
			        MemberVO member = memberReq.toMemberVO();
				      
				      	try {
				      		memberService.regist(member);
					*/
					
					
					
					
					
					
					//local HDD에 저장.
					item.write(storeFile);
					
					
				}else {//old Picture 삭제
					switch (item.getFieldName()) {
					case "oldPicture":
						String oldFileName = item.getString("utf-8");
						File oldFile=new File(uploadPath+File.separator+oldFileName);
						if(oldFile.exists()) {
							oldFile.delete();
						}
						break;
					}
				}
			}
		}
		
		
		
		return pdsVO;
	}

	
}
