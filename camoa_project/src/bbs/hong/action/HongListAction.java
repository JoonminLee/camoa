package bbs.hong.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.hong.db.HongDAO;
import bbs.hong.db.HongVO;

public class HongListAction implements HongAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int pageSize = 5; // 한 페이지에 출력할 게시글 수
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1"; // 페이지가 없다면, 무조건 1페이지 고정
		int currentPage = Integer.parseInt(pageNum);
		int startPage = (currentPage * pageSize) - 4; // 첫번째페이지 값
		int endPage = currentPage * pageSize; // 마지막페이지 값
		int pcount = 0, pnumber = 0;
		HongDAO hd = new HongDAO();
		ArrayList<HongVO> hvl = null;
		pcount = hd.all_list(); // 전체 페이지 리턴
		if (pcount > 0) {
			hvl = hd.list(startPage, endPage); //
		} else {
			System.out.println("글이 없습니다.");
		}
		// 9 = 9 - ( 1 - 1 ) * 7;
		pnumber = pcount - (currentPage - 1) * pageSize;
		req.setAttribute("pageSize", new Integer(pageSize));
		req.setAttribute("currentPage", new Integer(currentPage));
		req.setAttribute("startPage", new Integer(startPage));
		req.setAttribute("endPage", new Integer(endPage));
		req.setAttribute("pcount", pcount);
		req.setAttribute("pnumber", new Integer(pnumber));
		req.setAttribute("list", hvl);

	}

}
