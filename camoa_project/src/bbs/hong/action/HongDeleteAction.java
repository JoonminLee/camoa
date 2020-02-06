package bbs.hong.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.hong.db.HongDAO;

public class HongDeleteAction implements HongAction{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int hong_num = Integer.parseInt(req.getParameter("hong_num"));
		HongDAO hd = new HongDAO();
		hd.delete(hong_num);
	}
	
}
