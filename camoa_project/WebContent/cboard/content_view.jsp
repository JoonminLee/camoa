<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/content_view.css" />
<title>내용(Content) 확인</title>
<style type="text/css">
body {
	background: #fff;
}

.blueone {
	border-collapse: collapse;
}

.blueone th {
	padding: 10px;
	color: #168;
	border-bottom: 3px solid #168;
	text-align: left;
}

.blueone td {
	color: #669;
	padding: 10px;
	border-bottom: 1px solid #ddd;
}

.blueone tr:hover td {
	color: #005;
}
</style>

<script type="text/javascript">
	function fnDeleteAction(bId) {
		if (confirm("정말 삭제하시겠습니까?")) {
			location.href = "delete.co?bId=" + bId;
		} else {
			return;
		}
	}
</script>
</head>
<link rel="stylesheet" type="text/css" href="./css/camoaCategory.css">
<body>
	<%
		String userId = null;
		if (session.getAttribute("userId") != null)
			userId = (String) session.getAttribute("userId");
	%>

	<div id="menubar">
		<div id="Camoa">
			<b>Camoa</b>
		</div>
		<div>
			<center>
				<ul style="text-decoration: none;">
					<li><a style="color: orange" href="./page/camoaMain.jsp">홈</a></li>
					<li><a href="list.do" name="cafeintro">카페소개</a></li>
					<li><a href="list.co">커뮤니티</a></li>
					<li><a href="h_list.ho">이벤트/홍보</a></li>
					<li><a class="btn btn-default" data-target="#modal"
						data-toggle="modal"><img src="./image/main_phone.png" /></a></li>
					<li><a href="camoaMap.jsp"><img src="./image/main_map.png" /></a></li>
				</ul>
			</center>
		</div>
		<div id="LoginJoin">
			<%
				if (userId == null) {
			%>
			<b><a href="./member/login.jsp">LogIn</a> &nbsp; <a
				href="./member/choose.jsp">JoinUs</a></b> &nbsp;
			<%
				} else {
			%>
			<b><a href="./member/logoutAction.jsp">LogOut</a> &nbsp; <a
				href="./member/MyInforForm.jsp">MyInfor</a></b> &nbsp;
			<%
				}
			%>
		</div>
	</div>
	<hr style="border: solid 1px #F2F2F2;">
	<div id="nav"></div>
	<div id="content">
		<h2 align="center">내용 보기</h2>
		<!-- <table class="contentTB" width = "700" cellpadding = "0" cellspacing = "0" border = "1"> -->
		<table class="blueone" width="90%" align="center">
			<form action="modify_view.co" method="post">
				<input type="hidden" name="bId" value="${content_view.bId}">
				<tr>
					<th>번호</th>
					<td>${content_view.bId}</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${content_view.bHit}</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${content_view.bName}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${content_view.bTitle}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="10" width="90%" name="bContent" size="80"
							style="height: 200px; width: 600px" readonly="readonly"> ${content_view.bContent}</textarea></td>
				</tr>
				<tr>
					<td colspan="2"><c:if test="${userId eq content_view.bName}">
							<button type="submit" class="btnGoModify">수정</button>&nbsp;&nbsp; 	
				<input type="button" value="삭제"
								onclick="javascript:fnDeleteAction('${content_view.bId}')">&nbsp;&nbsp;
				</c:if> <input type="button" value="목록보기"
						onclick="window.location='list.co'">&nbsp;&nbsp;</td>
				</tr>
			</form>
		</table>
		<br> <br> <br>


		<table class="table">
			<tr>
				<c:forEach items="${clist}" var="cdto">
					<tr>
						<%-- <td>${cdto.cNum}</td> --%>
						<td>댓글작성자</td>
						<td>${cdto.cName}</td>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>댓글작성시간</td>
						<td>${cdto.cDateFmt}</td>
					</tr>
					<tr>
						<td colspan='5' height=50px>${cdto.cContent}</td>
					</tr>
				</c:forEach>
		</table>
		<!--  //cNum, cContent, cName, cStep, cLevel, cMom -->
		<h2 align="center">댓글 입력</h2>
		<table class="blueone" width="90%" align="center" bgcolor="#F1FEFF">
			<form action="write_comment.co" method="post">
				<input type="hidden" name="bId" value="${content_view.bId}">
				<!--dao.contentView에서 받아오는 값  -->
				<tr>
					<td>이름</td>
					<!-- <td><input type="text" name="cName" size="50"placeholder="이름을 입력하세요" ></td> -->
					<td><input type="text" name="cName" value="${userId}"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea width="500" name="cContent" rows="10"
							style="width:80%" placeholder="댓글을 입력하세요"></textarea></td>
				</tr>
				<tr>
					<%
						if (userId != null) {
					%>
					<td colspan="2"><input type="submit" value="입  력">
						&nbsp;&nbsp; <%
 	} else {
 %>
					<td colspan="2"><input type="submit" value="입  력"
						readonly="readonly"> &nbsp;&nbsp; <%
 	}
 %>
				</tr>
			</form>
		</table>
	</div>
</body>
</html>
