package com.kosta.pboardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.pboardModel.P_bbsDAO;
import com.kosta.pboardModel.P_bbsVO;

public class DeleteProAction implements CommandAction {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		

		int cf_num = Integer.parseInt(request.getParameter("cf_num"));
		String pageNum = request.getParameter("pageNum");
		P_bbsDAO  dao = P_bbsDAO.getInstance();
		
		int check = dao.delete(cf_num); 
		System.out.println(check);
		//저장
		request.setAttribute("cf_num", cf_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		return "/pboard/DeletePro.jsp" ;
	}

}





