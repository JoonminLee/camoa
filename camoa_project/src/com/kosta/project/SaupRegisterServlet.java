package com.kosta.project;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaupRegisterServlet extends HttpServlet{
	private static final long serialVersionUI = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		String userId=req.getParameter("userId");
		String userPwd1=req.getParameter("userPwd1");
		String userPwd2=req.getParameter("userPwd2");
		String userName=req.getParameter("userName");
		String email =req.getParameter("email");
		String userNum=req.getParameter("userNum");
		String saupNum=req.getParameter("saupNum");
		String cafeName=req.getParameter("cafeName");
		System.out.println("Aa");
		if(userId==null || userId.equals("") || userPwd1==null || userPwd1.equals("") ||
				userPwd2==null || userPwd2.equals("") || userName==null || userName.equals("") ||
						userNum==null || userNum.equals("") || email==null || email.equals("") 
						|| saupNum==null || saupNum.equals("")
						|| cafeName==null || cafeName.equals("")) {
			req.getSession().setAttribute("messageType", "오류메세지");
			req.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			resp.sendRedirect("member/SjoinForm.jsp");
			return;
		}
		if(!userPwd1.equals(userPwd2)) {
			req.getSession().setAttribute("messageType", "오류메세지");
			req.getSession().setAttribute("messageContent", "비밀번호가 서로 일치하지 않습니다.");
			resp.sendRedirect("member/SjoinForm.jsp");
			return;
		}
			int result= new UserDAO().join(userId, userPwd1, userPwd2, userName,email, userNum, saupNum, cafeName);
			if(result ==1) {
				req.getSession().setAttribute("messageType", "성공메세지");
				req.getSession().setAttribute("messageContent", "회원가입에 성공했습니다.");
				resp.sendRedirect("page/camoaMain.jsp");
				
				return;
			}else {
				req.getSession().setAttribute("messageType", "오류메세지");
				req.getSession().setAttribute("messageContent", "이미 존재하는 회원입니다.");
				resp.sendRedirect("member/SjoinForm.jsp");
				
				return;
			}
	
	}
	
	
}
