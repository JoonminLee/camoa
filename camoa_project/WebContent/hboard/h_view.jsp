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
<link rel="stylesheet" href="./css/bootstrap.css">
<title>홍보글 상세보기</title>
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
	<script type="text/javascript">
		$(document).ready(function() {
			$("#hitbtn").click(function() {
				$("#hit").attr("action", "h_hit.ho")
			});
		});
	</script>
	<%
		String userId = null;
		if (session.getAttribute("userId") != null)
			userId = (String) session.getAttribute("userId");
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
						<td>${view.hong_num }</td>
					</tr>
					<tr>
						<td>조회수</td>
						<td>${view.hong_count }</td>
					</tr>
					<tr>
						<td>추천수</td>
						<td>${view.hong_hit }</td>
					<tr>
						<td>작성자</td>
						<td>${view.hong_name }</td>
					</tr>
					<tr>
						<td>제목</td>
						<td>${view.hong_subject }</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>${view.hong_content }</td>
					</tr>
					<tr>
						<c:if test="${userId eq view.hong_name }">
							<td><a href="h_modify.ho?hong_num=${view.hong_num }">수정</a></td>
						</c:if>
						<c:if test="${userId ne null}">
							<td><a href="h_hit.ho?hong_num=${view.hong_num }">추천</a></td>
						</c:if>
						<td><a href="h_list.ho">목록보기</a></td>
					</tr>
				</table>
				<table class="table">
					<form action="h_comment_write_insert.ho?hong_num=${view.hong_num }"
						method="post">
						<input type="hidden" name="c_hong_num" value="${view.hong_num }">
						<tr class="row">
							<td class="col-2"><input type="text" name="c_hong_name"
								readonly="readonly" value="${userId}" size="15"></td>
							<td class="col-8"><textarea name="c_hong_content" style="width:100%; height:100%"
									placeholder="comment....."></textarea></td>
							<td class="col-2"><input type="submit" value="댓글입력"></td>
						</tr>
					</form>
				</table>
				<table class="table">
					<c:forEach items="${clist}" var="hcv">
						<form
							action="h_comment_delete.ho?c_num=${hcv.c_num }&hong_num=${view.hong_num}"
							method="post">
							<tr>
								<td>${hcv.c_num }</td>
								<td>${hcv.c_hong_name}</td>
								<td>${hcv.c_hong_content}</td>
								<td>${hcv.c_hong_date}</td>
								<td><input type="submit" value="삭제"></td>
							</tr>
						</form>
					</c:forEach>
				</table>
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