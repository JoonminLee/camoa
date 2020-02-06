package com.kosta.pboardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kosta.pboardModel.P_bbsDAO;
import com.kosta.pboardModel.P_bbsVO;
public class WriteFormAction implements CommandAction {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/pboard/WriteForm.jsp";
	}

}
