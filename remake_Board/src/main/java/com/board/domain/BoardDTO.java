package com.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

//게시판 테이블(tb_board) 구체화역활 클래스
@Getter
@Setter
public class BoardDTO {

		private Long idx;
		
		private String title;
		
		private String content;
		
		private String writer;
		
		private int viewCnt;
		
		private String noticeYn;
		
		private String secretYn;
		
		private String deleteYn;
		
		private LocalDateTime insertTime;
		private LocalDateTime updateTime;
		private LocalDateTime deleteTime;
}
