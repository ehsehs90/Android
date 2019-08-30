package com.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;




public class BookDAO {

	public List<String> selectTitle(String keyword) {
		
		//keyword를 입력받아서 Database검색해서
		//String[] 만들어서 return 해주는 DB처리
		//JDBC를 이용한 DB처리
		
		//List<String> 싹다 바꾸고 왔어요 헤엑 힘들어라
		List<String> list = new ArrayList<String>(); //결과데이터가 저장될 ArrayList만들어요.
		//결과데이터 갖고와서 어레이 어레리 어레이 하꺼에여
		 try {
			// 1. Driver Loading - 사용하려는 db에 맞는 자바 class (Driver라 불리는)
			// MySQL을 위한 JDBC Driver class를 로딩.
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("로딩성공");
			 
			
			 //2. Connection 단계
			String id = "android";
			String pw = "android";
			String jdbcUrl = "jdbc:mysql://localhost:3306"
								+"/library?characterEncoding=UTF8";
			Connection con = DriverManager.getConnection(jdbcUrl,id,pw);
			System.out.println("연결성공!");
			
			//3. Statement 작성                                                          //여기물음표에넣어
			String sql = "select btitle from book where btitle like ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			//keyword 가 포함된 모든 문자열과 매핑되는 것들 다 땡 겨 와
			pstmt.setString(1, "%" + keyword+ "%"); 
			
			//4.Query 실행
			ResultSet rs = pstmt.executeQuery();
			//5. 결과처리
			while(rs.next()) {
				list.add(rs.getString("btitle"));
			}
			// 6. 사용한 리소스 해제.
			//리소스 해제 안하면 db죽어요. 다을땐 거꾸로 닫아요
			rs.close();
			pstmt.close();
			con.close();
			
		 }catch(Exception e){
			 System.out.println(e);
			 
		 }
		 
		return list;
	}

}
