package com.board.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.constant.Method;
import com.board.domain.BoardDTO;
import com.board.paging.Criteria;
import com.board.service.BoardService;
import com.board.util.UiUtils;
@Controller
public class BoardController extends UiUtils {
	@Autowired
	private BoardService boardService;
	
	// 작성페이지열기
	@GetMapping(value="/board/write.do")
	public String openBoardWrite(@ModelAttribute("params")BoardDTO params,@RequestParam(value="idx",required = false)Long idx,Model model) {
		if(idx==null) {
			model.addAttribute("board",new BoardDTO());
		}else {
			BoardDTO board=boardService.getBoardDetail(idx);
			if(board==null) {
				return showMessageWithRedirect("없는 게시글이거나 이미 삭제된게시글입니다.","/board/list.do",Method.GET, null, model);
			}
			model.addAttribute("board",board);
		}
		return "board/write";
	}
	
	//입력을 등록하기
	@PostMapping(value="/board/register.do")
	public String registerBoard(@ModelAttribute("params")final BoardDTO params,Model model) {
		Map<String,Object> pagingParams=getPagingParams(params);
		try {
			boolean isRegistered=boardService.registerBoard(params);
			if(isRegistered==false) {
				return showMessageWithRedirect("게시글 등록에 실패했습니다","/board/list.do",Method.GET,pagingParams,model);
			}
		}catch (DataAccessException e) {
			return showMessageWithRedirect("데이터 베이스 처리 과정에 문제가 발생했습니다.","/board/list.do", Method.GET,pagingParams, model);
		}catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생했습니다.","/board/list.do",Method.GET, pagingParams, model);
		}
		return showMessageWithRedirect("게시글 등록이 완료 되었습니다.","/board/list.do", Method.GET,pagingParams, model);
	}
	
	//입력 리스트 열기
	//ModelAttribute는 파라미터로 전달받은 객체를 자동으로 뷰로 전달해준다
	@GetMapping(value="/board/list.do")
	public String openBoardList(@ModelAttribute("params")BoardDTO params,Model model) {
		List<BoardDTO> boardList=boardService.getBoardList(params);
		model.addAttribute("boardList",boardList);
		return "board/list";
	}
	
	//리스트 상세페이지 열기
	@GetMapping(value="/board/view.do")
	public String openBoardDetail(@ModelAttribute("params")BoardDTO params,@RequestParam(value="idx",required = false)Long idx,Model model) {
		if(idx==null) {
			return showMessageWithRedirect("올바르지 않는 접근입니다.","/board/list.do",Method.GET, null, model);
		}
		BoardDTO board=boardService.getBoardDetail(idx);
		if(board==null||"Y".equals(board.getDeleteYn())) {
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.","/board/list.do",Method.GET, null, model);
		}
		model.addAttribute("board",board);
		
		return "board/view";
		
	}
	
	//deleteYn을 추가해주기
	@PostMapping(value="/board/delete.do")
	public String deleteBoard(@ModelAttribute("params")BoardDTO params,@RequestParam(value="idx",required = false)Long idx,Model model) {
			if(idx==null) {
				return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do",Method.GET, null, model);
			}
			Map<String,Object> pagingParams=getPagingParams(params);
			try {
				boolean isDeleted=boardService.deleteBoard(idx);
				if(isDeleted==false) {
					return showMessageWithRedirect("게시글 삭제에 실패했습니다.","/board/list.do",Method.GET, pagingParams, model);
				}
				
			}catch (DataException e) {
				return showMessageWithRedirect("데이터 베이스 처리과정에 문제가 발생했습니다.","/board/list.do",Method.GET, pagingParams, model);
			}catch(Exception e) {
				return showMessageWithRedirect("시스템에 문제가 발생하였습니다.","/board/list.do",Method.GET,pagingParams, model);
			}
			return showMessageWithRedirect("게시글 삭제가 완료되었습니다.","/board/list.do",Method.GET,pagingParams, model);
	}

}
