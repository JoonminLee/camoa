<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.kosta.project.UserBean"%>
<%@page import="com.kosta.project.UserDAO"%>
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
</style>
</head>
<body class="bg-light">
	<%
		String userId = null;
		UserDAO dao = new UserDAO();
		if (session.getAttribute("userId") != null)
			userId = (String) session.getAttribute("userId");
		Boolean SaupTest = dao.test(userId); //사업자번호로 사업자 인지 판별

		String Email = dao.getIn("Email", userId);
		String Name = dao.getIn("Name", userId);
		String Phone = dao.getIn("Phone", userId);
		String Snum = dao.getIn("SaupNum", userId);
		String Cname = dao.getIn("CafeName", userId);
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
				<form method="post" action="MyInforAction.jsp">
					<h3 style="text-align: center;">개인 정보수정</h3>
					<div class="form-group text-left">
						<input type="text" class="form-control" readonly="readonly" value="${userId}">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호"
							name="userPwd1" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호재입력"
							name="userPwd2" maxlength="20">
					</div>
					<div class="form-group">
						<%-- <c:set var="em">${Email }</c:set>
					<c:out>em</c:out> --%>
						<input type="email" class="form-control" placeholder="이메일"
							value="<%=Email%>" name="email" maxlength="40">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름"
							value="<%=Name%>" name="userName" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="번호"
							value="<%=Phone%>" name="userNum" maxlength="20">
					</div>
					<%
						if (SaupTest) {
					%>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="사업자번호"
							value="<%=Snum%>" name="saupNum" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="점포명"
							value="<%=Cname%>" name="cafeName" maxlength="20">
					</div>
					<%
						}
					%>
					<input type="submit" class="btn btn-info form-control" value="정보수정">
				</form>
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