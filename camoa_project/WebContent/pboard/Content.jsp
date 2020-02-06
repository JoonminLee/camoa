<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="com.kosta.project.UserDAO"%>
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
		if (session.getAttribute("userId") != null){
			userId = (String) session.getAttribute("userId");
		}
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
<center><br/><br/>

<table border = "1" width="500px" padding="10"  align="center">
	<tr>
		<td colspan="2">추천수  ${vo.cf_recom }</td>
		<td colspan="2">조회수  ${vo.cf_readcount }</td>
	<tr>
		<td colspan="4" rowspan="2"><center><h2>${vo.cf_name }</h2></center></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<td colspan="4" background-color="#bbdefb"><center>카페 위치</center></td>
	</tr>
	<tr>
		<td colspan="4"><center>${vo.cf_address }</center></td>
	</tr>
	<tr>
		<td colspan="4" background-color="#bbdefb"><center>카페연락처</center></td>
	</tr>
	<tr>
		<td colspan="4"><center>${vo.cf_phone }</center></td>
	</tr>
	<tr><td colspan="4" background-color="#bbdefb"><center>가격정보</center></td>
	</tr>
	<tr><td colspan="2">아메리카노 가격</td><td colspan="2"><center>${vo.cf_ame }</center></td>
	</tr>
	<tr><td colspan="2">카페라떼 가격</td><td colspan="2"><center>${vo.cf_latte }</center></td>
	</tr>
	<tr><td colspan="2">카라멜마끼아또 가격</td><td colspan="2"><center>${vo.cf_caramel }</center></td>
	</tr>
	<tr><td colspan="2">카페모카 가격</td><td colspan="2"><center>${vo.cf_mocha }</center></td>
	</tr>
	<tr><td colspan="2">바닐라라떼 가격</td><td colspan="2"><center>${vo.cf_vanila }</center></td>
	</tr>
	<tr>
		<td colspan="4" background-color="#bbdefb"><center>영업시간</center></td>
	</tr>
	<tr>
		<td colspan="4"><center>${vo.cf_workhour }</center></td>
	</tr>
	<tr>
		<td colspan="4" background-color="#bbdefb"><center>주차정보</center></td>
	</tr>
	<tr>
		<td colspan="4"><center>${vo.cf_park }</center></td>
	</tr>
	<tr>
		<td colspan="4" background-color="#bbdefb"><center>카페소개</center></td>
	</tr>
	<tr>
		<td colspan="4"><center>${vo.cf_intro }</center></td>
	</tr>
<tr height ="30">
	<td colspan="4" align="right">
<c:if test="${userId ne null }">
<input type ="button" value ="추천" onclick="document.location.href='Recom.do?cf_num=${ cf_num }&pageNum=${ pageNum }'">
</c:if>
<c:if test="${userId eq vo.cf_id }"><input type="button" value="글수정" onclick="document.location.href='updateForm.do?cf_num=${ cf_num }&pageNum=${ pageNum }'"> 
	&nbsp;&nbsp;
	<input type ="button" value ="글삭제" onclick="document.location.href='deleteForm.do?cf_num=${ cf_num }&pageNum=${ pageNum }'">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
	<input type = "button" value ="목록 보기" onclick="document.location.href='list.do?pageNum=${ pageNum }'"> 
	</td>
</tr>
</table>

</center>
   </div>
</body>
</html>