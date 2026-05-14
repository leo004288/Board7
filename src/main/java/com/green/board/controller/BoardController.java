package com.green.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.board.mapper.BoardMapper;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Board")
public class BoardController {
		
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	// /Board/List?menu_id=MENU01
	@RequestMapping("/List") 
	public ModelAndView list(MenuDTO menudto) {
		
		// 메뉴목록 조회 - menu.jsp
		List<MenuDTO> menuList = menuMapper.getMenuList();
//		log.info("menuList:" + menuList);
		 
		// 게시물 목록 조회 - list.jsp
		List<BoardDto> boardList = boardMapper.getBoardList(menudto);
//		log.info("boardList:" + boardList);
		
		// 넘어온 menu_id
		String menu_id   = menudto.getMenu_id();
		
		String menu_name = menuMapper.getMenu_name(menudto);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/list");
		mv.addObject("menuList", menuList);
		mv.addObject("menu_name", menu_name);
		mv.addObject("menu_id", menu_id);
		mv.addObject("boardList", boardList);
		
		return mv; 
	}
	
	//	/Borad/View?idx=1&menu_id=MENU01
	@RequestMapping("/View")
	public ModelAndView view (BoardDto boarddto, MenuDTO menudto) {
		
		// 메뉴목록 조회 - menu.jsp
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// idx 글의 조회수 1 증가
		boardMapper.incHit(boarddto);
		
		// idx 로 조회한 게시글 
		BoardDto board = boardMapper.getBoard(boarddto);
//		log.debug("board:" + board);
//		System.out.println("board:" + board);
		
		// content 안에 있는 엔터 \n 을 <br> 변경 -> content
		if(board != null && board.getContent() != null)
		board.setContent(board.getContent().replace("\n", "<br>"));
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/view");
		mv.addObject("menuList", menuList);
		mv.addObject("board", board);
		mv.addObject("menu_id", board.getMenu_id());
		return mv;
	}
	
	// /Board/WriteForm?menu_id=${board.menu_id}
	@RequestMapping("/WriteForm")
	public ModelAndView writeform(BoardDto boarddto, MenuDTO menudto) {
//		System.out.println("/Board/WriteFrom boarddto:" + boarddto);
		
		// 메뉴목록
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		String menu_id   = boarddto.getMenu_id();
		
		String menu_name = menuMapper.getMenu_name(menudto);		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/write");
		mv.addObject("menu_id", menu_id);
		mv.addObject("menuList", menuList);
		mv.addObject("menu_name", menu_name);
		return mv;
	}
	
	// /Board/Write?menu_id=MENU01&title=a&content=a&writer=a
	@RequestMapping("/Write")
	public ModelAndView write(BoardDto boarddto) {
		System.out.println("write boarddto" + boarddto);
		
		// db 저장
		boardMapper.insertBoard(boarddto);
		
		String menu_id   = boarddto.getMenu_id();
				
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return mv;
	}
	
	
	// /Board/Delete?idx=${board.idx}&menu_id=${menu_id}
	//                   삭제번호             삭제후 돌아올번호
	@RequestMapping("/Delete")
	public ModelAndView delete(BoardDto boarddto) {
		
		// 삭제
		boardMapper.deleteBoard(boarddto);
		
		String menu_id = boarddto.getMenu_id();
		
		// 목록으로 돌아감
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return mv;
	}
	
	// /Board/UpdateForm?idx=8&menu_id=MENU01
	@RequestMapping("/UpdateForm")
	public ModelAndView updateForm (BoardDto boarddto, MenuDTO menudto) {
		
		// 메뮤목록
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 넘어온 데이터(idx)로 수정할 정보(board) 조회
		BoardDto board = boardMapper.getBoard(boarddto);
		
		String       menu_id   = boarddto.getMenu_id();
		String       menu_name = menuMapper.getMenu_name(menudto);
		ModelAndView mv        = new ModelAndView();
		mv.setViewName("/board/update");
		mv.addObject("board", board);
		mv.addObject("menuList", menuList);
		mv.addObject("menu_id", menu_id);
		mv.addObject("menu_name", menu_name);
		return mv;
	}
	
	//
	@RequestMapping("/Update")
	public ModelAndView update(BoardDto boarddto) {
		
		boardMapper.updateBoard(boarddto);
		
		String       menu_id = boarddto.getMenu_id();
		ModelAndView mv      = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return mv;
	}
	
}
