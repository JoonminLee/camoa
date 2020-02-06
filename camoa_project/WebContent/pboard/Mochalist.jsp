<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.kosta.project.UserDAO"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카페 소개 게시판</title>
</head>
<link rel="stylesheet" type="text/css" href="./css/camoaCategory.css">
<body>
<%
		String userId = null;
		if (session.getAttribute("userId") != null){
			userId = (String) session.getAttribute("userId");
		}
		UserDAO dao = new UserDAO();
		String sa_num = dao.saup_num(userId);
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
	<left>
		<b>전체 글 : ${ count }
		</b>
</left>
<center>
<c:if test="${ count == 0 }">	
	<table width="700" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center">게시판에 저장된 글이 없습니다.</td>
		</tr>
	</table>	
</c:if>
	
<c:if test="${ count > 0 }">	
	<table border = "1" width="600" cellpadding="0" cellspacing="0" align="center">
		<br />
	<c:forEach var="list"  items="${ list }">    		
	<tr>
		<td colspan="2" rowspan="3"><a href="content.do?cf_num=${list.cf_num}&pageNum=${ currentPage }"><center><img
							src="./image/noimage2.png" /></center></a></td>
		<td colspan="4"><center><h2>${list.cf_name }</h2></center></td>
		
	</tr>
		<td colspan="2"><center>카페모카 가격 </center></td><td><center>${list.cf_mocha }</center></td>

	<tr>
		<td colspan="2"><center>추천수 : ${list.cf_recom }  <c:if test="${userId ne null }">
<input type ="button" value ="추천" onclick="document.location.href='MochaRecom.do?cf_num=${list.cf_num }&pageNum=${ pageNum }'">
</c:if></center></td>
		<td colspan="2"><center>조회수 : ${list.cf_readcount }</center></td>
	</tr>
	</c:forEach>
	</table>
</c:if>

 <c:if test="${ count > 0 }"> <!--  전체 페이지의 수를 연산 -->
	    <c:set  var="pageCount"  value="${ count / pageSize + (count % pageSize ==0 ? 0 : 1) }" />
		<c:set  var="startPage"  value="${ 1 }" />  
		<c:set  var="pageBlock"  value="${ 5 }" />
		
		
		<fmt:parseNumber var="result"  value="${ currentPage / pageBlock }" integerOnly="true" />
		<c:if  test="${ currentPage % pageBlock != 0 }" > 
			<c:set var="startPage" value="${ result * pageBlock + 1 }" />
		</c:if>
		
		<c:if  test="${ currentPage % pageBlock == 0 }" > 
			<c:set var="startPage" value="${ (result - 1) * pageBlock + 1 }" />
		</c:if>
	
		<c:set  var="endPage"  value="${ startPage + pageBlock -1 }" />

		<c:if test="${ endPage > pageCount }" >
			<c:set  var="endPage"  value="${ pageCount }" />
		</c:if>
		
		<c:if test="${startPage >5 }" >
			<a href="list.do?pageNum=${ startPage-5  }">[이전] </a>
		</c:if>

		<c:forEach  var="i" begin="${startPage }" end="${ endPage }">
			<a href="list.do?pageNum=${i}">[${ i }] </a>
	   </c:forEach>
	
	<c:if test="${ endPage < pageCount }" >
		<a href="list.do?pageNum=${ startPage+5 }">[다음] </a>
	</c:if>
</c:if>
<% if(sa_num != null) {%>
<center><input type="button" value="글작성" onclick="window.location='writeForm.do'"></center>
<%}%>
   </div>
</body>
</html>