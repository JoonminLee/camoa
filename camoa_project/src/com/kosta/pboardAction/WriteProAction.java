package com.kosta.pboardAction;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.pboardModel.P_bbsDAO;
import com.kosta.pboardModel.P_bbsVO;


public class WriteProAction implements CommandAction {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		P_bbsVO vo = new P_bbsVO();
		vo.setCf_id(request.getParameter("cf_id"));
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
		vo.setCf_reg_date(new Timestamp(System.currentTimeMillis()));
		


		P_bbsDAO dao = P_bbsDAO.getInstance();
		dao.insert(vo);
		
		return "/pboard/WritePro.jsp";
	}

}
