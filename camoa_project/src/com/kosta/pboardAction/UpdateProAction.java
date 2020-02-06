package com.kosta.pboardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.pboardModel.P_bbsDAO;
import com.kosta.pboardModel.P_bbsVO;

public class UpdateProAction implements CommandAction {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");   // 업데이트시 한글 깨짐처리 
		
		int cf_num = Integer.parseInt(request.getParameter("cf_num"));
		String pageNum = request.getParameter("pageNum");
		P_bbsVO vo = new P_bbsVO();
		
		vo.setCf_name(request.getParameter("cf_name"));
		vo.setCf_address(request.getParameter("cf_address"));
		vo.setCf_phone(request.getParameter("cf_phone"));
		vo.setCf_ame(Integer.parseInt(request.getParameter("cf_ame")));
		vo.setCf_latte(Integer.parseInt(request.getParameter("cf_latte")));
		vo.setCf_caramel(Integer.parseInt(request.getParameter("cf_caramel")));
		vo.setCf_mocha(Integer.parseInt(request.getParameter("cf_mocha")));
		vo.setCf_vanila(Integer.parseInt(request.getParameter("cf_vanila")));
		vo.setCf_workhour(request.getParameter("cf_workhour"));
		vo.setCf_park(request.getParameter("cf_park"));
		vo.setCf_intro(request.getParameter("cf_intro"));
		vo.setCf_num(cf_num);
		
		P_bbsDAO dao = P_bbsDAO.getInstance();
		int check = dao.update(vo);  //실제 변경 내용 반영 함수 호출
		request.setAttribute("cf_num", cf_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);	 //jsp페이지로 pageNum이랑 check값들구가
			
		return "/pboard/UpdatePro.jsp";
	}

}
