package bbs.hong.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.hong.db.HongDAO;

public class HongCommentWriteAction implements HongAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String c_hong_name = req.getParameter("c_hong_name");
		String c_hong_content = req.getParameter("c_hong_content");
		String c_hong_num = req.getParameter("c_hong_num");
		HongDAO hd = new HongDAO();
		hd.cwrite(c_hong_name, c_hong_content, c_hong_num);

	}

}
