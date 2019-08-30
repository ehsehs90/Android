package com.test.service;

import java.util.List;

import com.test.dao.BookDAO;

//Service 객체를 만들기 위한 class

public class BookService {

	
	
	   //Business Logic(Transactiopn)에 대한
	   //method만 나와요!
	   //하나의 Transaction(기능)당 1개의 method가 이용.
	
	
	public List<String> getBooksTitle(String keyword) {
		// 로직처리(더하기, 빼기, for, if, etc...)
		// 로직처리 코드가 많이 나와요!
		// Database처리가 나와야 해요!
		// DAO를 만들어서 database처리를 한 후 결과를 가져와요!
		//--dao class 만든 후 --
		BookDAO dao = new BookDAO();
		//얘는 crud 를 대표하는? (ㅅㅂ) db에 데이터 select하는 놈이니까 get~ set~ 이케 안씀.
		List<String> list = dao.selectTitle(keyword);  //키워드를 넘겨줘야 db 뒤지든 말든 할거아냐 //결과는 String[] list로 리턴
		
		
		return list; //리턴은 list
	}

}
