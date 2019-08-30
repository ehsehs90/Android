package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.service.BookService;

/**
 * Servlet implementation class BookSearchTitleServlet
 */
@WebServlet("/searchTitle")
public class BookSearchTitleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearchTitleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		////입력받고////
		
		
		String keyword = request.getParameter("keyword");
		//유저 키워드라는 키값으로 클라이언트가 입력 해줄거여.
	
		////로직처리////로직처리를 Service에게 위임(만들고왔움니당 ㅎ)
					
		//Service를 생성해서 Service에게 일을 시켜요!! 결과를
		//받아와요 !
		BookService service = new BookService(); //일을 할 수 있는 객체가 생겨요
		//일을 시켜요 -> 서비스가 갖고 있는 메서드를 호출해요
		List<String> list = service.getBooksTitle(keyword);  //키워드 입력받아서 뒤져바@ //리턴으로 (나는 그결과를) String배열로 받을거야
		//키워드 갖고 책 제목 얻어오면 그게 문자열이자나 -> 그걸 문자열 배열로 받겠다는 뜻
		
		
		
		////출력처리(JSON)////
		response.setContentType("text/plain; charset=UTF8");
		PrintWriter out = response.getWriter();
		// 잭슨 라이브러리 포함시키고 ObjectMapper라는 듣도보도 못한 객체를 만들어요
		
		ObjectMapper mapper = new ObjectMapper();
		//list를 writeValueAsString 머시기로 바꾼대요..
		String json = mapper.writeValueAsString(list);
		
		out.print("list의 JSON 문자열을 보내요!!" + json);
		out.flush();
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
