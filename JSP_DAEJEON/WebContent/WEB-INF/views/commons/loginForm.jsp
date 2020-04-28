<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Font Awesome -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- icheck bootstrap -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
<style>
body.login-page{
	background-image:url('<%=request.getContextPath()%>/resources/images/css/daejeon.png');
	background-position:center;
	background-size:cover;
	background-repeat:no-repeat;
}
</style>

</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="#"><b style="color:white;"></b></a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
		 <div class="card-body login-card-body">
			<p class="login-box-msg"></p>

			<form action="<%=request.getContextPath() %>/commons/login.do"	method="post">
				<div class="form-group has-feedback">
					<input type="text" class="form-control" name="id" 
					onkeyup="this.value=this.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, &#39;&#39;);"
					placeholder="아이디를 입력하세요." value="${param.id}" id="id" value="mimi"/>
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
					<span id="spaceid" style="color:red"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name="pwd" id="pwd" placeholder="패스워드를 입력하세요." value="mimi">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
					<span id="error" style="color:red"></span>
				</div>
				<div class="row">
					<div class="col-sm-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox"> Remember Me
							</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-sm-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">로그인</button>
					</div>
					<!-- /.col -->
				</div>
			</form>

			

			<a href="#" style="font-weight:bold;">아이디/패스워드 찾기</a><br> 
			

		</div>
		<!-- /.login-box-body -->
		
	  </div>	
	</div>
	<!-- /.login-box -->

<!-- jQuery -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>
<script>	
	
	var message="${msg}";
	if(message!=""){		
		$("#error").text(message);
	}
	
</script>
	<% session.removeAttribute("msg"); %>
</body>
</html>








