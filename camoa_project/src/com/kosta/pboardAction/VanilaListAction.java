package com.kosta.pboardAction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.pboardModel.P_bbsDAO;
import com.kosta.pboardModel.P_bbsVO;

public class VanilaListAction implements CommandAction {

	@Override
	public String process(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		int pageSize =5; //화면에 출력 레코드 수 

		String pageNum = request.getParameter("pageNum");
		
		if( pageNum == null ) pageNum = "1";
		
		int currentPage = Integer.parseInt(pageNum);  // 1
		int startRow = (currentPage * pageSize) - 4 ;  // 1
		int endRow = currentPage * pageSize ;  // 5
		int count = 0, number = 0;
		
		ArrayList<P_bbsVO> list = null;
		P_bbsDAO  dao = P_bbsDAO.getInstance();
		count = dao.getListAllCount(); //전체 페이지 리턴...  //ex) 9
																//1            7
		if( count > 0 ) {
			list = dao.vanilalist(startRow, endRow);  //레코드 목록 보기
			System.out.println(count);
		} else {

			System.out.println("글이 없습니다.");
			System.out.println(count);
		}
		System.out.println(list);
		  //  9       	=		9   -  (1 - 1 ) * 7
		// 글목록에 표시 할 글번호 
		number = count - (currentPage - 1) * pageSize ;		 // ex) 9
		
		//해당 뷰에서 사용할 속성(저장)
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", count);
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("list", list);
		request.setAttribute("pageNum", pageNum);
		return "/pboard/Vanilalist.jsp";
	}

}






