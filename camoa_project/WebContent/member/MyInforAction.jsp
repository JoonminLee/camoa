<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="user" class="com.kosta.project.UserBean" scope="page" />
<jsp:setProperty name="user" property="cus_pwd1" param="userPwd1" />
<jsp:setProperty name="user" property="cus_pwd2" param="userPwd2" />
<jsp:setProperty name="user" property="cus_email" param="email" />
<jsp:setProperty name="user" property="cus_name" param="userName" />
<jsp:setProperty name="user" property="cus_phone" param="userNum" />
<jsp:setProperty name="user" property="saup_num" param="saupNum" />
<jsp:setProperty name="user" property="saup_cafe" param="cafeName" />
<jsp:useBean id="DAO" class="com.kosta.project.UserDAO" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원정보수정</title>
</head>
<body>
	<%
		String userId = null;
		if (session.getAttribute("userId") != null)
			userId = (String) session.getAttribute("userId");
		
		PrintWriter script = response.getWriter();
		/* if(((String)session.getAttribute("Snum"))!=null){	//사업번호로 사업자 개인회원.입력 안된사항 구분
			if (user.getSaup_cafe()==null || user.getSaup_num()==null ) {
				script.println("<script>");
				script.println("alert('입력이 안 된 사항이 있습니다.')");
				script.println("history.back()");
				script.println("</script>");
			}
		} */
		if (user.getCus_pwd1() == null || user.getCus_pwd2() == null || user.getCus_name() == null
				|| user.getCus_phone() == null || user.getCus_email() == null ) {
			script.println("<script>");
			script.println("alert('입력이 안 된 사항이 있습니다.')");
			script.println("history.back()");
			script.println("</script>");
		} else if (!(user.getCus_pwd1().equals(user.getCus_pwd2()))) {
			script.println("<script>");
			script.println("alert('비밀번호가 불일치합니다.')");
			script.println("history.back()");
			script.println("</script>");
		} else {

			int result = DAO.update(user, userId);
			if (result == -1) {
				script.println("<script>");
				script.println("alert('오류')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				/* session.setAttribute("userId", user.getCus_id()); */
				script.println("<script>");
				script.println("alert('회원정보 수정이 완료되었습니다.')");
			/* 	script.println("session.invalidate()");	//재로그인 */
				script.println("location.href='../page/camoaMain.jsp'");
				script.println("</script>");
			}
		}
	%>
</body>
</html>