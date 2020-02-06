package com.kosta.pboardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFormAction implements CommandAction {

	@Override
	public String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int cf_num = Integer.parseInt(request.getParameter("cf_num"));
		String pageNum = request.getParameter("pageNum");
		
		request.setAttribute("cf_num", cf_num);
		request.setAttribute("pageNum", pageNum);
		
		return "/pboard/DeleteForm.jsp" ;
	}

	
	
	
	
	
	
}
