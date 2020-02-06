package bbs.hong.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.hong.action.HongAction;
import bbs.hong.db.HongDAO;

public class HongHitAction implements HongAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String hong_num = req.getParameter("hong_num");
		HongDAO hd = new HongDAO();
		hd.hit(hong_num);
	}

}
