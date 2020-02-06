<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<!-- 디바이스 화면에 맞춰, 자동 크기 조절 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- bootstrap.css 불러오기 -->
<link rel="stylesheet" href="../css/bootstrap.css">
<title>회원선택</title>
<style type="text/css">
.jumbotron {
	color: black;
	border-radius: 0;
}

footer {
	font-size: 12px;
}

body#LoginForm {
	background-image:
		url("https://hdwallsource.com/img/2014/9/blur-26347-27038-hd-wallpapers.jpg");
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
	padding: 10px;
}

.form-heading {
	color: #fff;
	font-size: 23px;
}

.panel h2 {
	color: #444444;
	font-size: 18px;
	margin: 0 0 8px 0;
}

.panel p {
	color: #777777;
	font-size: 14px;
	margin-bottom: 30px;
	line-height: 24px;
}

.login-form .form-control {
	background: #f7f7f7 none repeat scroll 0 0;
	border: 1px solid #d4d4d4;
	border-radius: 4px;
	font-size: 14px;
	height: 50px;
	line-height: 50px;
}

.main-div {
	background: #ffffff none repeat scroll 0 0;
	border-radius: 2px;
	margin: 10px auto 30px;
	max-width: 38%;
	padding: 50px 70px 70px 71px;
}

.login-form .form-group {
	margin-bottom: 10px;
}

.login-form {
	text-align: center;
}

.forgot a {
	color: #777777;
	font-size: 14px;
	text-decoration: underline;
}

.login-form  .btn.btn-primary {
	background: #f0ad4e none repeat scroll 0 0;
	border-color: #f0ad4e;
	color: #ffffff;
	font-size: 14px;
	width: 100%;
	height: 50px;
	line-height: 50px;
	padding: 0;
}

.forgot {
	text-align: left;
	margin-bottom: 30px;
}

.botto-text {
	color: #ffffff;
	font-size: 14px;
	margin: auto;
}

.login-form .btn.btn-primary.reset {
	background: #ff9900 none repeat scroll 0 0;
}

.back {
	text-align: left;
	margin-top: 10px;
}

.back a {
	color: #444444;
	font-size: 13px;
	text-decoration: none;
}
</style>
</head>
<body class="bg-light">
	<%
		String userId = null;
		if (session.getAttribute("userId") != null)
			userId = (String) session.getAttribute("userId");
	%>
	<!-- navbar의 색상, 배경색을 지정한다 -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand col" href="../page/camoaMain.jsp"><img
			src="../image/logo.png" width="50" height="50" class="d-inline-block">
			<h>Camoa</h></a>
		<!-- 디바이스가 줄어들었을 때, 접혀있던(data-toggle="collapse") 목록을 불러오는 버튼 생성 -->
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<!-- 토글 아이콘 (작대기 세개) -->
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse col" id="navbarSupportedContent">
			<ul class="navbar-nav mx-auto">
				<li class="nav-item"><a class="nav-link"
					href="../page/camoaMain.jsp"><font color="orange">Home</font></a></li>
				<li class="nav-item"><a class="nav-link" href="./list.do">카페소개</a></li>
				<li class="nav-item"><a class="nav-link" href="./list.co">커뮤니티</a></li>
				<li class="nav-item"><a class="nav-link" href="./h_list.ho">이벤트</a></li>
				<li class="nav-item"><a class="nav-link" href="#"
					data-target="#modal" data-toggle="modal">문의전화</a></li>
			</ul>
			<%
				if (userId == null) {
			%>
			<button class="btn btn-outline-dark btn-sm" type="button"
				onclick="location.href='../member/login.jsp'">Login</button>
			&nbsp;
			<button class="btn btn-outline-dark btn-sm" type="button"
				onclick="location.href='../member/choose.jsp'">JoinUs</button>
			<%
				} else {
			%>
			<button class="btn btn-outline-dark btn-sm" type="button"
				onclick="location.href='../member/logoutAction.jsp'">LogOut</button>
			&nbsp;
			<button class="btn btn-outline-dark btn-sm" type="button"
				onclick="location.href='../member/MyInforForm.jsp'">MyInfo</button>
			<%
				}
			%>
		</div>
	</nav>
	<div class="row">
		<div class="modal" id="modal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h3 style="text-align: center;">문의전화</h3>
						<button class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" style="text-align: center;">

						<table class="table">
							<tr>
								<td>신경도</td>
								<td><img src="./image/main_phone.png" id="imagepreview"
									style="width: 10px; height: 10px;">010-1111-1111</td>
							</tr>
							<tr>
								<td>김대민</td>
								<td><img src="./image/main_phone.png" id="imagepreview"
									style="width: 10px; height: 10px;">010-1111-1111</td>
							</tr>
							<tr>
								<td>이준민</td>
								<td><img src="./image/main_phone.png" id="imagepreview"
									style="width: 10px; height: 10px;">010-1111-1111</td>
							</tr>
							<tr>
								<td>안태회</td>
								<td><img src="./image/main_phone.png" id="imagepreview"
									style="width: 10px; height: 10px;">010-1111-1111</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="content-fluid mt-2">
		<div class="jumbotron text-center">
			<div class="container mx-auto">
				<div class="container">
					<div class="login-form">
						<div class="main-div">
							<div class="panel">
								<h2>Login</h2>
								<p>Please enter your Id and password</p>
							</div>
							<form id="Login" method="post" action="loginAction.jsp">

								<div class="form-group">


									<input type="text" class="form-control" id="inputEmail"
										placeholder="Id" name="userId" maxlength="20">

								</div>

								<div class="form-group">

									<input type="password" class="form-control" id="inputPassword"
										name="userPwd" maxlength="20" placeholder="Password">

								</div>
								<input type="submit" class="btn btn-primary" value="Login">
								</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<footer class="footer text-center">
		<div class="container mt-auto">
			<span class="text-muted">
				<p>Copyright &copy; by Camoa. All right reserved</p>
				<p>신경도 김대민 이준민 안태회</p>
			</span>
		</div>
	</footer>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>