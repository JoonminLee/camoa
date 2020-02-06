<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그아웃</title>
</head>
<body>
	<%
	session.invalidate();
	%>
	<script>
	location.href='../page/camoaMain.jsp';
	</script>
</body>
</html>