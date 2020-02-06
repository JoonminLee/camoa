package bbs.hong.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.hong.action.HongAction;
import bbs.hong.action.HongCommentDeleteAction;
import bbs.hong.action.HongCommentViewAction;
import bbs.hong.action.HongCommentWriteAction;
import bbs.hong.action.HongDeleteAction;
import bbs.hong.action.HongHitAction;
import bbs.hong.action.HongListAction;
import bbs.hong.action.HongModifyAction;
import bbs.hong.action.HongModifyViewAction;
import bbs.hong.action.HongViewAction;
import bbs.hong.action.HongWriteAction;

@WebServlet("*.ho")
public class HongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HongController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String viewPage = null;
		HongAction action = null;

		String uri = req.getRequestURI();
		String conPath = req.getContextPath();
		String command = uri.substring(conPath.length());
		System.out.println("===========================");
		System.out.println("command : " + command);

		if (command.equals("/h_list.ho")) {
			System.out.println("list");
			action = new HongListAction();
			action.execute(req, resp);
			viewPage = "/hboard/h_list.jsp";

		} else if (command.equals("/h_write_insert.ho")) {
			System.out.println("write insert");
			action = new HongWriteAction();
			action.execute(req, resp);
			viewPage = "/h_list.ho";
			
		} else if (command.equals("/h_modify_insert.ho")) {
			System.out.println("modify");
			action = new HongModifyAction();
			action.execute(req, resp);
			viewPage = "/h_list.ho";

		} else if (command.equals("/h_comment_write_insert.ho")) {
			System.out.println("comment insert");
			action = new HongCommentWriteAction();
			action.execute(req, resp);
			viewPage = "/h_view.ho";

		} else if (command.equals("/h_comment_delete.ho")) {
			System.out.println("comment delete");
			action = new HongCommentDeleteAction();
			action.execute(req, resp);
			viewPage = "/h_view.ho";

		} else if (command.equals("/h_delete.ho")) {
			System.out.println("delete");
			action = new HongDeleteAction();
			action.execute(req, resp);
			viewPage = "/h_list.ho";

		} else if (command.equals("/h_view.ho")) {
			System.out.println("view");
			action = new HongViewAction();
			action.execute(req, resp);
			System.out.println("comment view");
			action = new HongCommentViewAction();
			action.execute(req, resp);
			viewPage = "/hboard/h_view.jsp";
	
		} else if (command.equals("/h_hit.ho")) {
			System.out.println("hit");
			action = new HongHitAction();
			action.execute(req, resp);
			viewPage = "/h_view.ho";

		} else if (command.equals("/h_write.ho")) {
			System.out.println("write");
			viewPage = "/hboard/h_write.jsp";

		} else if (command.equals("/h_modify.ho")) {
			System.out.println("modify");
			action = new HongModifyViewAction();
			action.execute(req, resp);
			viewPage = "/hboard/h_modify.jsp";
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
		System.out.println("viewPage : " + viewPage);
		dispatcher.forward(req, resp);
	}

}
