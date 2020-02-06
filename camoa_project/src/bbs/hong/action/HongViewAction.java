package bbs.hong.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.hong.db.HongDAO;
import bbs.hong.db.HongVO;

public class HongViewAction implements HongAction{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String hong_num = req.getParameter("hong_num");
		HongDAO hd = new HongDAO();
		HongVO hv = hd.view(hong_num);
		  
		req.setAttribute("view", hv);
	}
	
}
