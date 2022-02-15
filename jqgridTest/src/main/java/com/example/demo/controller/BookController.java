package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BookDAO;
import com.example.demo.vo.BookVO;

import lombok.Setter;

@Controller
@Setter
public class BookController {
	
	@Autowired
	private BookDAO dao;
	
	@RequestMapping(value = "/listBook", produces = "application/xml")
	@ResponseBody
	public String listBook(int page, int rows, String sidx, String sord) {
		System.out.println("page:"+page);
		System.out.println("rows:"+rows);
		
		if(sidx == null) {
			System.out.println("null입니다");
		}
		
		if(sidx.equals("")) {
			System.out.println("''입니다.");
		}
		System.out.println("sidx:"+sidx);
		System.out.println("sord:"+sord);
		//정렬이 되도록 기능을 추가 해 봅니다.
		//완성이되면 "3"

		
		int records = dao.getTotalCount();
		int total =  (int)Math.ceil(records / (double)rows);
		System.out.println("전체레코드수 : "+records);
		System.out.println("전체페이지수 : "+total);
		String r = "";
		r += "<rows>";
		r += "<page>"+page+"</page>";		   //매개변수로 전달받은 페이지를 설정
		r += "<total>"+total+"</total>";	   //전체레코드수에 따른 페이지수를 설정
		r += "<records>"+records+"</records>";  //진짜로 db연동하여 전체레코드 수를 설정
		// 페이징과 관련한 응답을 완성 해 봅니다.
		// 완성하면 "1"
		
		
		//만약에 한화면에 10개씩 보여주고 
		//현재페이지가 1페이지라면  보여줘야 할 시작레코는 			1,  마지막레코드는 10
		//현제페이지가 2페이지라면  보여줘야 할 시작레코는			11	마지락레코드는 20
		
		int start = (page-1)*rows + 1;
		int end = start + rows - 1;
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("sidx", sidx);
		map.put("sord", sord);
		
		List<BookVO> list = dao.findAll(map);
		//현재페이지에 보여줄 레코드만 반환하도록 코드를 수정해 봅니다.
		//완성하면 "1"
		
		for(BookVO b:list) {
			r += "<row>";
			r += "<cell>"+b.getBookid()+"</cell>";
			r += "<cell>"+b.getBookname()+"</cell>";
			r += "<cell>"+b.getPublisher()+"</cell>";
			r += "<cell>"+b.getPrice()+"</cell>";
			r += "</row>";
		}
		
		r += "</rows>";
		return r;
	}
	
	
	@RequestMapping("/editBook")
	@ResponseBody
	public String editBook(String oper, BookVO b) {
		System.out.println(oper);
		// 도서등록에 대한 기능을 구현해 봅니다.
		// 완성하면 "1"
		
		if(oper.equals("add")) {
			dao.insert(b);
		}else if(oper.equals("edit")) {
			dao.update(b);
		}else {
			dao.delete(b.getBookid());
		}
		
	
		return "OK";
	}
}
















