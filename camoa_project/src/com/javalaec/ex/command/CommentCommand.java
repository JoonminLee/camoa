package com.javalaec.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalaec.ex.dao.BDao;

public class CommentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String bId  = request.getParameter("bId");
		String cContent = request.getParameter("cContent");
		String cName = request.getParameter("cName");	
		
		BDao dao = new BDao();
		System.out.println(cContent);
		System.out.println(cName);
		System.out.println(bId);
		dao.write_comment(cContent,cName,bId);
		
	
	
	}

}
