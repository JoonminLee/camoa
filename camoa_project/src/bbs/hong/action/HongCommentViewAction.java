package bbs.hong.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.hong.db.HongCommentVO;
import bbs.hong.db.HongDAO;

public class HongCommentViewAction implements HongAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String c_hong_num = req.getParameter("hong_num");
		HongDAO hd = new HongDAO();
		ArrayList<HongCommentVO> hcvl = hd.clist(c_hong_num);
		req.setAttribute("clist", hcvl);
	}

}
