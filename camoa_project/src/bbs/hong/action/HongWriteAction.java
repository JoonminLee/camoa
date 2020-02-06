package bbs.hong.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import bbs.hong.db.HongDAO;

public class HongWriteAction implements HongAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String hong_subject = req.getParameter("hong_subject");
		String hong_name = req.getParameter("hong_name");
		String hong_content = req.getParameter("hong_content");
		HongDAO hd = new HongDAO();
		hd.write(hong_subject, hong_name, hong_content);
		
	}
}
