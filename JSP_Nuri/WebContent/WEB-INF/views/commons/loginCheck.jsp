<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	if(window.opener){
		alert("필터 카드 발동!");	
		window.close();
		window.opener.location.href="<%=request.getContextPath()%>/index.jsp";
	}else{
		alert("필터 카드 발동!");
		location.href="<%=request.getContextPath()%>/index.jsp";
	}
</script>   
