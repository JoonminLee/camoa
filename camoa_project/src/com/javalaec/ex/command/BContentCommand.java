package com.javalaec.ex.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalaec.ex.dao.BDao;
import com.javalaec.ex.dto.BDto;
import com.javalaec.ex.dto.CDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String bId  = request.getParameter("bId");
		BDao dao = new BDao();
		System.out.println("bId"+bId);
		BDto dto = dao.contentView(bId);
		request.setAttribute("content_view", dto);
		
		ArrayList<CDto> cdtos = dao.commentList(bId); //댓글 list로
		request.setAttribute("clist", cdtos);
		 
		
		
	}

}
