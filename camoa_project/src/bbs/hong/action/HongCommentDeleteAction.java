package bbs.hong.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.hong.db.HongDAO;

public class HongCommentDeleteAction implements HongAction{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int c_num = Integer.parseInt(req.getParameter("c_num"));
		HongDAO hd = new HongDAO();
		hd.cdelete(c_num);
	}
	
}
