package com.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

//게시판 테이블(tb_board) 구체화역활 클래스
@Getter
@Setter
public class BoardDTO extends CommonDTO {

	/** 번호 (PK) */
	private Long idx;

	/** 제목 */
	private String title;

	/** 내용 */
	private String content;

	/** 작성자 */
	private String writer;

	/** 조회 수 */
	private int viewCnt;

	/** 공지 여부 */
	private String noticeYn;

	/** 비밀 여부 */
	private String secretYn;

}
