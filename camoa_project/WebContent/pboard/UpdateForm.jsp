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
	<center><h1>카페소개 수정하기</h1></center>
	<form action = "updatePro.do?cf_num=${cf_num }&pageNum=${pageNum }" method="post" name="updateform" onsubmit="return updateSave()">
		
		<table width='500' border='1' cellspacing='1' cellpadding='0' align="center">
				<td width="100" align="center">카페명</td>
				<td width="400">&nbsp;&nbsp;<input type="text" size = "40" maxlength="50" value="${vo.cf_name }" name = "cf_name"></td>
			</tr>
			<tr>
				<td width="100" align="center">카페위치</td>
				<td width="400">&nbsp;&nbsp;<input type="text" size = "40" maxlength="50" value="${vo.cf_address }" name = "cf_address"></td>
			</tr>
			
			<tr>
				<td width="100" align="center">카페연락처</td>
				<td width="400">&nbsp;&nbsp;<input type="text" size = "40" maxlength="50" value="${vo.cf_phone }" name = "cf_phone"></td>
			</tr>
			<tr>
				<td width="100" align="center">아메리카노 가격</td>
				<td width="400">&nbsp;&nbsp;<input type="text" size = "40" maxlength="50" value="${vo.cf_ame }" name = "cf_ame"></td>
			</tr>
			<tr>
				<td width="100" align="center">카페라떼 가격</td>
				<td width="400">&nbsp;&nbsp;<input type="text" size = "40" maxlength="50" value="${vo.cf_latte }" name = "cf_latte"></td>
			</tr>
			<tr>
				<td width="100" align="center">카라멜마끼아또 가격</td>
				<td width="400">&nbsp;&nbsp;<input type="text" size = "40" maxlength="50" value="${vo.cf_caramel }" name = "cf_caramel"></td>
			</tr>
			<tr>
				<td width="100" align="center">카페모카 가격</td>
				<td width="400">&nbsp;&nbsp;<input type="text" size = "40" maxlength="50" value="${vo.cf_mocha }" name = "cf_mocha"></td>
			</tr>
			<tr>
				<td width="100" align="center">바닐라라떼 가격</td>
				<td width="400">&nbsp;&nbsp;<input type="text" size = "40" maxlength="50" value="${vo.cf_vanila }" name = "cf_vanila"></td>
			</tr>
			<tr>
				<td width="100" align="center">영업시간</td>
				<td width="400">&nbsp;&nbsp;<input type="text" size = "40" maxlength="50" value="${vo.cf_workhour }" name = "cf_workhour"></td>
			</tr>
			<tr>
				<td width="100" align="center">주차정보</td>
				<td width="400">&nbsp;&nbsp;<input type="text" size = "40" maxlength="50" value="${vo.cf_park }" name = "cf_park"></td>
			</tr>
			<tr>
				<td width="100" align="center">카페소개</td>
				<td width="400">&nbsp;&nbsp;<textarea rows="20" cols="40" name="cf_intro">${vo.cf_intro }</textarea></td>
			</tr>
			<tr>
				<td width="100" align="center">사진 올리기</td>
				<td><input type="file" name="cf_file"></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				<input type="submit" value="수정">
				<input type="reset" value="다시작성">
				<input type="button" value="목록보기" onclick="window.location='list.do?pageNum=${ pageNum }'"></td>
			</tr>
		</table>
		</form>
   </div>
</body>
</html>