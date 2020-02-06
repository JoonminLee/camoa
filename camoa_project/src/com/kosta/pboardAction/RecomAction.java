package com.kosta.pboardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.pboardModel.P_bbsDAO;

public class RecomAction implements CommandAction {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		int cf_num = Integer.parseInt(request.getParameter("cf_num"));
		String pageNum = request.getParameter("pageNum");
		
		P_bbsDAO dao = P_bbsDAO.getInstance();
		int check2 = dao.recom(cf_num);
		
		request.setAttribute("cf_num", cf_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check2);
		
		System.out.println(cf_num);
		System.out.println(pageNum);
		return "/pboard/Recom.jsp";
	}

}
