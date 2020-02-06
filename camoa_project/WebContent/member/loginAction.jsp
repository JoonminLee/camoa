<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.io.PrintWriter"%>
<%@ page import="com.kosta.project.UserDAO"%>
<% request.setCharacterEncoding("utf-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="user" class="com.kosta.project.UserBean" scope="page"/>
<jsp:setProperty name="user" property="cus_id" param="userId" />
<jsp:setProperty name="user" property="cus_pwd1" param="userPwd" />

<jsp:useBean id="DAO" class="com.kosta.project.UserDAO" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>가입완료</title>
</head>
<body>
	<%
			PrintWriter script = response.getWriter();
			int result = DAO.login(user.getCus_id(), user.getCus_pwd1());
			if (result == 1) {
				 session.setAttribute("userId", user.getCus_id());
				script.println("<script>");
				script.println("location.href='../page/camoaMain.jsp'");
				script.println("</script>");
			} else if(result ==0 || result ==-1){
				script.println("<script>");
				script.println("alert('아이디 혹은 패스워드가 틀립니다.')");
				script.println("history.back()");
				script.println("</script>");
			}
			else if(result==-2){
				script.println("<script>");
				script.println("alert('데이터베이스 오류가 발생했습니다.')");
				script.println("history.back()");
				script.println("</script>");
			}
	%>
</body>
</html>