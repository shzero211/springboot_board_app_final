package com.board;



import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
public class MapperTest {
	
	@Autowired 
	private BoardMapper boardMapper;
	
@Test
public void testOfInsert() {
		BoardDTO params=new BoardDTO();
		params.setTitle("1번 게시글제목");
		params.setContent("1번 게시글 내용");
		params.setWriter("테스터");
		int result=boardMapper.insertBoard(params);
		System.out.println("결과는"+result+"입니다");
}
@Test
public void  testOfSelectDetail() {
	BoardDTO board=boardMapper.selectBoardDetail((long)1);
	try {
		//DTO객체를 json방식으로 만들어줌
		String boardjson=new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
		System.out.println("==========================");
		System.out.println(boardjson);
		System.out.println("=======================");
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
@Test
public void testOfUpdate() {
	BoardDTO params=new BoardDTO();
	params.setTitle("1번 게시글 제목을 수정합니다.");
	params.setContent("1번 게시글 내용을 수정합니다.");
	params.setWriter("홍길동");
	params.setIdx((long)1);
	
	int result =boardMapper.updateBoard(params);
	if(result==1) {
		BoardDTO board=boardMapper.selectBoardDetail((long)1);
		try {
			String boardJson=new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
			System.out.println("====================");
			System.out.println(boardJson);
			System.out.println("====================");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
@Test
public void testOfDelete() {
	int result=boardMapper.deleteBoard((long)1);
	if(result==1) {
		try {
			BoardDTO board=boardMapper.selectBoardDetail((long)1);
			String boardJson=new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
			System.out.println("====================");
			System.out.println(boardJson);
			System.out.println("====================");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

@Test
public void testMultipleInsert() {
	for(int i=2;i<=50;i++) {
		BoardDTO params=new BoardDTO();
		params.setTitle(i+"번 게시글 제목");
		params.setContent(i+"번 게시글 내용");
		params.setWriter(i+"번 게시글 작성자");
		boardMapper.insertBoard(params);
	}
}
@Test
public void testSelectList() {
	int boardTotalCount=boardMapper.selectBoardTotalCount();
	if(boardTotalCount>0) {
		List<BoardDTO> list=boardMapper.selectBoardList();
		if(CollectionUtils.isEmpty(list)==false) {
			for(BoardDTO board:list) {
				System.out.println("====================");
				System.out.println(board.getTitle());
				System.out.println(board.getContent());
				System.out.println(board.getWriter());
				System.out.println("===========================");
			}
		}
	}
}
}
