<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Camoa</title>
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
					<li><a href="camoaMap.jsp"><img
							src="./image/main_map.png" /></a></li>
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
   <div id="nav">
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
   <div id="content">
<meta http-equiv="Refresh" content="0; url=/camoa_project/content.do?cf_num=${cf_num }&pageNum=${pageNum}">
<script>
	alert("수정성공")
</script>
   </div>
</body>
</html>
