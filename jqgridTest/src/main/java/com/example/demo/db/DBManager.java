package com.example.demo.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BookVO;

public class DBManager {
	private static SqlSessionFactory factory;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/example/demo/db/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
	
	public static List<BookVO> findAll(HashMap map){
		SqlSession session = factory.openSession();
		List<BookVO> list = session.selectList("book.findAll",map);
		session.close();
		return list;
	}
	
	public static int insert(BookVO b) {
		SqlSession session = factory.openSession();
		int re = session.insert("book.insert",b);
		session.commit();
		session.close();
		return re;
	}
	
	public static int update(BookVO b) {
		SqlSession session = factory.openSession();
		int re = session.update("book.update",b);
		session.commit();
		session.close();
		return re;
	}
	
	public static int delete(int bookid) {
		SqlSession session = factory.openSession();
		int re = session.delete("book.delete",bookid);
		session.commit();
		session.close();
		return re;
	}
	
	public static int getTotalCount() {
		SqlSession session = factory.openSession();
		int re = session.selectOne("book.getTotalCount");
		session.commit();
		session.close();
		return re;
	}
	
}
















