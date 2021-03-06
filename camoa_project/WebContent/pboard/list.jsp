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
		if (session.getAttribute("userId") != null) {
			userId = (String) session.getAttribute("userId");
		}
		UserDAO dao = new UserDAO();
		String sa_num = dao.saup_num(userId);
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
	<div class="content-fluid">
		<div class="jumbotron text-center row">
			<div class="container mx-auto col-2 mt-5">
				<p>
					<a href="list.do"> 카페소개 리스트 </a>
				</p>
				<p>
					<a href="amelist.do"> 아메리카노 가격순 </a>
				</p>
				<p>
					<a href="lattelist.do"> 카페라떼 가격순 </a>
				</p>
				<p>
					<a href="caramellist.do"> 카라멜마끼아또 가격순 </a>
				</p>
				<p>
					<a href="mochalist.do"> 카페모카 가격순 </a>
				</p>
				<p>
					<a href="vanilalist.do"> 바닐라라떼 가격순 </a>
				</p>
				<p>
					<a href="readcountlist.do"> 조회순 </a>
				</p>
				<p>
					<a href="recomlist.do"> 추천순 </a>
				</p>
			</div>
			<div class="container mx-auto col-10">

				<left> <b>전체 글 : ${ count } </b> </left>
				<center>
					<c:if test="${ count == 0 }">
						<table class="table">
							<tr>
								<td align="center">게시판에 저장된 글이 없습니다.</td>
							</tr>
						</table>
					</c:if>

					<c:if test="${ count > 0 }">
						<table class="table" style="width:80%">
							<br />
							<c:forEach var="list" items="${ list }">
								<tr>
									<td colspan="3" rowspan="3"><a
										href="content.do?cf_num=${list.cf_num}&pageNum=${ currentPage }"><center>
												<img src="./image/noimage2.png" />
											</center></a></td>
									<td colspan="2" rowspan="2"><center>
											<h2>${list.cf_name }</h2>
										</center></td>
								</tr>
								<tr>
								</tr>
								<tr>
									<td><center>
											추천수 ${list.cf_recom }
											<c:if test="${userId ne null }">
												<input type="button" value="추천"
													onclick="document.location.href='ListRecom.do?cf_num=${list.cf_num }&pageNum=${ pageNum }'">
											</c:if>
										</center></td>
									<td><center>조회수 ${list.cf_readcount }</center></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>

					<c:if test="${ count > 0 }">
						<!--  전체 페이지의 수를 연산 -->
						<c:set var="pageCount"
							value="${ count / pageSize + (count % pageSize ==0 ? 0 : 1) }" />
						<c:set var="startPage" value="${ 1 }" />
						<c:set var="pageBlock" value="${ 5 }" />


						<fmt:parseNumber var="result" value="${ currentPage / pageBlock }"
							integerOnly="true" />
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
							<a href="list.do?pageNum=${ startPage-5  }">[이전] </a>
						</c:if>

						<c:forEach var="i" begin="${startPage }" end="${ endPage }">
							<a href="list.do?pageNum=${i}">[${ i }] </a>
						</c:forEach>

						<c:if test="${ endPage < pageCount }">
							<a href="list.do?pageNum=${ startPage+5 }">[다음] </a>
						</c:if>
					</c:if>
					<%
						if (sa_num != null) {
					%>
					<center>
						<input type="button" value="글작성"
							onclick="window.location='writeForm.do'">
					</center>
					<%
						}
					%>
				
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
