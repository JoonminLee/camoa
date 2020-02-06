package bbs.hong.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.hong.db.HongDAO;
import bbs.hong.db.HongVO;

public class HongModifyAction implements HongAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int hong_num = Integer.parseInt(req.getParameter("hong_num"));
		String hong_subject = req.getParameter("hong_subject");
		String hong_content = req.getParameter("hong_content");
		HongDAO hd = new HongDAO();
		hd.modify(hong_subject, hong_content, hong_num);
	}

}
