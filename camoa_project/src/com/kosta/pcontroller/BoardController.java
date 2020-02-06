package com.kosta.pcontroller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosta.pboardAction.CommandAction;

public class BoardController extends HttpServlet {
	
	private Map commandMap = new HashMap();

	// 초기화 - 1회 호출됨
	// 명령어와 명령어 처리 클래스가 매핑되어 있는 properties  파일을 읽어 들이는 역할
	@Override
	public void init(ServletConfig config) throws ServletException {
		String props = config.getInitParameter("PboardConfig");
		Properties pr = new Properties();
		FileInputStream  f = null;
		
		try {
			// commandBoard.properties 파일의 내용을 읽어옴
			f = new FileInputStream(props);
			//commandBoard.properties 파일의 정보를 Properties 객체에 저장
			pr.load(f);	 // key=value
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if( f != null ) try{ f.close(); }catch(Exception e) { e.printStackTrace(); }
		} //end try
		
		Iterator  key = pr.keySet().iterator();  // key 값 추출
		
		while( key.hasNext() ) {
			String command = (String)key.next();
			String value = pr.getProperty(command);  // value  -  edu.kosta.boardAction.WriteFormAction
			
			try {
				Class className = Class.forName(value);  // forName() 문자열을 클래스로 변환.
				// 클래스로 변환시켰기 때문에 객체 생성함.
				Object instance = className.newInstance(); 

				commandMap.put(command, instance);   // put(key, value)
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} // end while
		
	} // end init(~~~) 

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
			doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
			doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			
		String view = null;
		CommandAction  action = null;
		
		try {
			String command = request.getRequestURI();
			System.out.println("command : " + command );	// command : /web06_boardMVC/writeForm.do		
			System.out.println("request.getContextPath() : " + request.getContextPath());   //   /web06_boardMVC
			
			if( command.indexOf(request.getContextPath()) == 0 ) {  // 경로가 없다면,...
				command = command.substring(request.getContextPath().length() + 1);  //  /web06_boardMVC
				System.out.println("if command : " + command);
			} // if end
			
			action = (CommandAction)commandMap.get(command);
			System.out.println("action : " + action);  //
			view = action.process(request, response); 
			System.out.println("view : " + view);   //
			
		} catch (Exception e) {
			e.printStackTrace();
		} // try end
		
		request.setAttribute("CONTENT", view);
				
		RequestDispatcher  dp = request.getRequestDispatcher(view);
		dp.forward(request, response);
	}
	
}




