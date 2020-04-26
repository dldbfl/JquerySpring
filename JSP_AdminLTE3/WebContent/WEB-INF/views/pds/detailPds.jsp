<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<title>자료보기</title>
<body>
  <!-- Content Wrapper. Contains page content -->
  <div>
    <jsp:include page="content_header.jsp">
    	<jsp:param value="자료실" name="subject"/>
		<jsp:param value="상세보기" name="item"/>
		<jsp:param value="list.do" name="url"/>    	
    </jsp:include>
    <!-- Main content -->
    <section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header">
						<h3 class="card-title">상세보기</h3>
					</div><!--end card-header  -->
					<div class="card-body">
						<!-- <form role="form" class="form-horizontal" action="regist.do" method="post"> -->
							
							<div class="form-group col-sm-12">
								<label for="title">제 목</label> 
								<input type="text" id="title" readonly
									name='title' class="form-control" value="${pds.title }">
							</div>
							
							<div class="row">
								<div class="form-group col-sm-4">
									<label for="writer">작성자</label> 
									<input type="text" id="writer" readonly
										name="writer" class="form-control" value="${pds.writer }">
								</div>
								<div class="form-group col-sm-4" >
									<label for="regDate">작성일</label>
									<input type="text" class="form-control" id="regDate" 
									value="<fmt:formatDate value="${pds.regDate }" pattern="yyyy-MM-dd" />" readonly />
							
								</div>
								<div class="form-group col-sm-4" >
									<label for="viewcnt">조회수</label>
									<input type="text" class="form-control" id="viewcnt" 
									value="${pds.viewcnt }" readonly />
								</div>									
							</div>
							
							
							<div class="form-group col-sm-12">
								<label for="content">내 용</label>
								<div id="content">${pds.content }</div>	
							</div>
							
							<div class="card-footer">
								<c:if test="${loginUser.id eq pds.writer }">
								<button type="button" id="modifyBtn" class="btn btn-warning">MODIFY</button>						
							    <button type="button" id="removeBtn" class="btn btn-danger">REMOVE</button>
							    </c:if>
							    <button type="button" id="listBtn" class="btn btn-primary">CLOSE</button>
							</div>	
							
							
							<div class="form-group">								
								<div class="card card-outline card-success">
									<div class="card-header">
										<h5 style="display:inline;line-height:40px;">첨부파일  </h5>
									</div>									
									<div class="card-footer fileInput">
										<span class="inputRow">
											<c:forEach items="${pds.attachList }" var="AttachVO">
												<input type=button name="downloadFile" value="다운로드" >${AttachVO.fileName }<br/>
											</c:forEach>
										</span>								
									</div>
								</div>
							</div>
						<!-- </form> -->
					</div><!--end card-body  -->
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<form role="form">
  	<input type='hidden' name='pno' value ="${pds.pno}">
  </form>

<jsp:include page="detailPds_js.jsp" /> 
<%-- <jsp:include page="./attach_js.jsp" /> --%>

</body>






  
  