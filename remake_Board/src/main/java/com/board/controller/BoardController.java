package com.board.controller;

import java.util.List;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardDTO;
import com.board.service.BoardService;
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value="/board/write.do")
	public String openBoardWrite(@RequestParam(value="idx",required = false)Long idx,Model model) {
		if(idx==null) {
			model.addAttribute("board",new BoardDTO());
		}else {
			BoardDTO board=boardService.getBoardDetail(idx);
			if(board==null) {
				return "redirect:/board/list.do";
			}
			model.addAttribute("board",board);
		}
		return "board/write";
	}
	@PostMapping(value="/board/register.do")
	public String registerBoard(final BoardDTO params) {
		try {
			boolean isRegistered=boardService.registerBoard(params);
			if(isRegistered==false) {
				
			}
		}catch (DataAccessException e) {
			// TODO: handle exception
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/board/list.do";
	}
	@GetMapping(value="/board/list.do")
	public String openBoardList(Model model) {
		List<BoardDTO> boardList=boardService.getBoardList();
		model.addAttribute("boardList",boardList);
		return "board/list";
	}
	
	@GetMapping(value="/board/view.do")
	public String openBoardDetail(@RequestParam(value="idx",required = false)Long idx,Model model) {
		if(idx==null) {
			return "redirect:/board/list.do";
		}
		BoardDTO board=boardService.getBoardDetail(idx);
		if(board==null||"Y".equals(board.getDeleteYn())) {
			return "redirect:/board/list.do";
		}
		model.addAttribute("board",board);
		
		return "board/view";
		
	}
	@PostMapping(value="/board/delete.do")
	public String deleteBoard(@RequestParam(value="idx",required = false)Long idx) {
			if(idx==null) {
				return "redirect:/board/list.do";
			}
			try {
				boolean isDeleted=boardService.deleteBoard(idx);
				if(isDeleted==false) {
					
				}
				
			}catch (DataException e) {
				// TODO: handle exception
			}catch(Exception e) {
				
			}
			return "redirect:/board/list.do";
	}

}
