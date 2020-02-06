package com.javalaec.ex.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalaec.ex.dao.BDao;
import com.javalaec.ex.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();  //
		request.setAttribute("list", dtos);
		
		int count = dao.getListAllCount();
		request.setAttribute("count",count);
	}	
}
