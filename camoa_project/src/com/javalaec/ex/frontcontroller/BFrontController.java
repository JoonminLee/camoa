package com.javalaec.ex.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalaec.ex.command.*;

@WebServlet("*.co")	//확장자가 do이면 BFrontController로 온다
public class BFrontController extends HttpServlet{
		private static final long serialVersionUID = 1L;
	
	public BFrontController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("doGet");
		actionDo(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("doPost");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("actionDo");
		
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;	//어떤 뷰를 보여줄껀지
		BCommand command = null;	//DAO에게 어떤 로직을 수행할껀지
		
		String uri = request.getRequestURI();	//요청객체로부터 uri뽑아냄
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length()); //conPath 의 길이만큼  uri를 잘라내면 "/write_view.do"같은 것만 남음
		
		/*if(com.equals("/write_view.do")) {
			viewPage = "write_view.jsp";
			
		}*/ 
		if(com.equals("/list.co")) {
			command = new BListCommand();	//완료
			command.execute(request,response);
			viewPage = "/cboard/list.jsp";
		} else if(com.equals("/write.co")) {
			command = new BWriteCommand();	//완료
			command.execute(request,response);
			viewPage = "list.co";
		} else if(com.equals("/write_view.co")) {
			viewPage = "/cboard/write_view.jsp";
		}		
		/*else if(com.equals("/list.do")) {
			command = new BListCommand();	//완료
			command.execute(request,response);
			viewPage = "list.jsp";
		}*/ else if(com.equals("/content_view.co")) {	
			command = new BContentCommand();	//완료
			command.execute(request,response);
			viewPage = "/cboard/content_view.jsp";
		} 	else if(com.equals("/modify_view.co")) {
			command = new BContentCommand();	//완료
			command.execute(request,response);
			viewPage = "/cboard/modify_view.jsp";
		}	else if(com.equals("/modify.co")) {
			command = new BModifyCommand();		//완료
			command.execute(request,response);
			viewPage = "list.co";
		} else if(com.equals("/delete.co")) {
			command = new BDeleteCommand();		//완료
			command.execute(request,response);
			viewPage = "list.co";
		} else if(com.equals("/write_comment.co")) {	//작업
			command = new CommentCommand();
			command.execute(request,response);
			viewPage = "list.co";
		} 
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		System.out.println("viewPage  "+viewPage);
		request.setAttribute("viewPage", viewPage);
//		dispatcher=request.getRequestDispatcher("/tle.jsp");
		dispatcher.forward(request,response);
	
	
	}
}
