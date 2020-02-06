<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.kosta.project.UserDAO"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<!-- 디바이스 화면에 맞춰, 자동 크기 조절 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- bootstrap.css 불러오기 -->
<link rel="stylesheet" href="./css/bootstrap.css">
<title>이벤트</title>
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
		if (session.getAttribute("userId") != null)
			userId = (String) session.getAttribute("userId");
		UserDAO ud = new UserDAO();
		String saup_num = ud.saup_num(userId);
	%>
	<!-- navbar의 색상, 배경색을 지정한다 -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand col" href="./page/camoaMain.jsp"><img
			src="./image/logo.png" width="50" height="50" class="d-inline-block">
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
					href="./page/camoaMain.jsp"><font color="orange">Home</font></a></li>
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
				onclick="location.href='./member/login.jsp'">Login</button>
			&nbsp;
			<button class="btn btn-outline-dark btn-sm" type="button"
				onclick="location.href='./member/choose.jsp'">JoinUs</button>
			<%
				} else {
			%>
			<button class="btn btn-outline-dark btn-sm" type="button"
				onclick="location.href='./member/logoutAction.jsp'">LogOut</button>
			&nbsp;
			<button class="btn btn-outline-dark btn-sm" type="button"
				onclick="location.href='./member/MyInforForm.jsp'">MyInfo</button>
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
				<table class="table">
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>날짜</td>
						<td>이름</td>
						<td>조회수</td>
						<td>추천</td>
					</tr>
					<c:forEach items="${list}" var="hv">
						<tr>
							<td>${hv.hong_num}</td>
							<td><a href="h_view.ho?hong_num=${hv.hong_num }">${hv.hong_subject}</a></td>
							<td>${hv.hong_date}</td>
							<td>${hv.hong_name}</td>
							<td>${hv.hong_count}</td>
							<td>${hv.hong_hit }</td>
						</tr>
					</c:forEach>
					<tr>
						<%
							if (saup_num != null) {
						%>
						<td><a href="h_write.ho"><button>글작성</button></a></td>
						<%
							}
						%>
					</tr>


				</table>
				<div class="text-center">
					<c:if test="${ pcount > 0 }">
						<!--  전체 페이지의 수를 연산 -->
						<c:set var="pageCount"
							value="${ pcount / pageSize + (pcount % pageSize ==0 ? 0 : 1) }" />
						<c:set var="startPage" value="${ 1 }" />
						<c:set var="pageBlock" value="${ 5 }" />
						<!-- 페이지 수 제한 -->


						<fmt:parseNumber var="result" value="${ currentPage / pageBlock }"
							integerOnly="true" />
						<!-- 정수값으로만 받도록 설정 -->
						<c:if test="${ currentPage % pageBlock != 0 }">
							<c:set var="startPage" value="${ result * pageBlock + 1 }" />
						</c:if>

						<c:if test="${ currentPage % pageBlock == 0 }">
							<c:set var="startPage" value="${ (result - 1) * pageBlock + 1 }" />
						</c:if>

						<c:set var="endPage" value="${ startPage + pageBlock -1 }" />

						<c:if test="${ endPage > pageCount }">
							<c:set var="endPage" value="${ pageCount }" />
						</c:if>

						<c:if test="${startPage >5 }">
							<a href="h_list.ho?pageNum=${ startPage-5  }">[이전] </a>
						</c:if>

						<c:forEach var="i" begin="${startPage }" end="${ endPage }">
							<a href="h_list.ho?pageNum=${i}">[${ i }] </a>
						</c:forEach>

						<c:if test="${ endPage < pageCount }">
							<a href="h_list.ho?pageNum=${ startPage+5 }">[다음] </a>
						</c:if>
					</c:if>
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