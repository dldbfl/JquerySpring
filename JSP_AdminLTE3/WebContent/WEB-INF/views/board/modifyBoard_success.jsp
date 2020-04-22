<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	alert("게시글 수정이 정상적으로 완료 되었소")
	location.href="detail.do?bno=${param.bno}&state=modify"; 
	/* window.close(); */
	window.opener.location.reload(true);
</script>

