package com.green.paging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.board.dto.BoardDto;
import com.green.config.WebMvcConfig;
import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.paging.dto.Pagination;
import com.green.paging.dto.SearchDto;
import com.green.paging.mapper.BoardPagingMapper;

@Controller
@RequestMapping("/BoardPaging")
public class BoardPagingController {

    private final WebMvcConfig webMvcConfig;

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private BoardPagingMapper boardPagingMapper;

    BoardPagingController(WebMvcConfig webMvcConfig) {
        this.webMvcConfig = webMvcConfig;
    }

	// /BoardPaging/List?menu_id=MENU01&nowpage=1
	@RequestMapping("/List")
	public ModelAndView list(BoardDto boarddto, int nowpage, String searchType, String keyword, MenuDTO menudto) {
		
		// 메뉴목록 : menus.jsp
		List<MenuDTO> menuList  = menuMapper.getMenuList();
		
		String        menu_name = menuMapper.getMenu_name(menudto);
		
		// 게시물 목록 조회 (페이징해서)
		// 해당 메뉴의 자료갯수 : 
		int totalcount = boardPagingMapper.count(boarddto, searchType, keyword);  // menu_id
		System.out.println("totcount:" + totalcount);
		
//		PagingResponse<BoardDto> response = null;
//		if(totalcount < 1) {  // 현재 menu_id로 조회한 자료가 없다면
//			response = new PagingResponse<>(
//					Collections.emptyList(), null);
//					//Collections.emptyList() : 자료가 없는 빈 리스트를 채운다
//		}
		
		// 페이징을 위한 초기설정
		SearchDto searchdto = new SearchDto();
		searchdto.setPageNo(nowpage); // 현재페이지 정보
		searchdto.setNumOfRows(10);   // 한페이지의 자료수
		searchdto.setPageSize(10);    // paging.jsp 에 한줄에 출력될 페이지 번호 수
		
		//  pagination 설정
		Pagination pagination = new Pagination(totalcount, searchdto);
		searchdto.setPagination(pagination);
		
		// 검색 조건 추가
		// 추가된 검색 조건
		String menu_id = boarddto.getMenu_id();
//		String title   = boarddto.getTitle();
//		String writer  = boarddto.getWriter();
//		String content = boarddto.getContent();
		
		int offset    = searchdto.getOffset();
		int numOfRows = searchdto.getNumOfRows();
		
		// 검색조건추가
//		List<BoardDto> list = boardPagingMapper.getBoardPagingList(
//				menu_id, title, writer, content, offset, numOfRows);
		List<BoardDto> list = boardPagingMapper.getBoardPagingList(
				menu_id, searchType, keyword, offset, numOfRows);
		
//		response = new PagingResponse<>(list, pagination);
//		System.out.println(response);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/list");
		mv.addObject("menuList", menuList);

		mv.addObject("nowpage", nowpage);
		mv.addObject("menu_id", menu_id);        // 현재 메뉴정보
		mv.addObject("menu_name", menu_name);
		
		mv.addObject("boardList", list);
		mv.addObject("searchdto", searchdto);

		mv.addObject("searchType", searchType);
		mv.addObject("keyword", keyword);
		
		return mv;
	} 
	
	// /BoardPaging/View?idx=213&menu_id=MENU01&nowpage=1
	@RequestMapping("/View")
	public ModelAndView view(BoardDto boarddto, int nowpage) {
		
		// 메뉴목록 조회
		List<MenuDTO> menuList = menuMapper.getMenuList(); 
		
		// 조회수 1증가
		boardPagingMapper.incHit(boarddto);
		
		// idx 로 게시글 한개 조회
		BoardDto board = boardPagingMapper.getBoard(boarddto);
		
		// 조회된 content 안에 있는 엔터 \n 을 <br> 변경
		String content = board.getContent();
		if(content != null )
		board.setContent(content.replace("\n", "<br>"));
		
//		// 변경방법2
//		if(board != null && board.getContent() != null)
//		board.setContent(board.getContent().replace("\n", "<br>"));
		
		String menu_id = boarddto.getMenu_id();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/view");
		mv.addObject("menuList", menuList);
		
		mv.addObject("menu_id", menu_id);
		mv.addObject("nowpage", nowpage);
		
		mv.addObject("board", board);
		return mv;
	} 
	
	// /BoardPaging/WriteForm?menu_id=MENU01&nowpage=1
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm(BoardDto boarddto, int nowpage) {
		
		// 메뉴목록조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		String menu_id = boarddto.getMenu_id();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/boardpaging/write");
		mv.addObject("menuList", menuList);
		mv.addObject("menu_id", menu_id);
		
		mv.addObject("nowpage", nowpage);
		return mv;
	}
	
	// /BoardPaging/Write
	// 넘어온 값 : menu_id=MENU01, title=제목, writer=aaa, content=내용
	// 돌아가기 위해 필요한 변수 : menu_id, nowpage
	@RequestMapping("/Write")
	public ModelAndView write(BoardDto boarddto, int nowpage) {
		
		// db에 저장
		boardPagingMapper.insertBoard(boarddto);
		
		ModelAndView mv      = new ModelAndView();
		String       menu_id = boarddto.getMenu_id();
 		String       fmt     = "redirect:/BoardPaging/List?menu_id=%s&nowpage=%d";
		String       loc     = String.format(fmt, menu_id, nowpage);
		mv.setViewName(loc);
		return mv;
	}
	
    // /BoardPaging/Delete?idx=1815&menu_id=MENU01&nowpage=1
	@RequestMapping("/Delete")
	public ModelAndView delete(BoardDto boarddto, int nowpage) {
		
		// idx로 board를 삭제
		boardPagingMapper.deleteBoard(boarddto);
		
		ModelAndView mv      = new ModelAndView();
		String       menu_id = boarddto.getMenu_id();
		String       loc     = """
				redirect:/BoardPaging/List?menu_id=%s&nowpage=%d
				""".formatted(menu_id, nowpage);
		mv.setViewName(loc);
		return mv;
	}
	 
    // /BoardPaging/UpdateForm?idx=1814&menu_id=MENU01&nowpage=1
	@RequestMapping("/UpdateForm")
	public ModelAndView updateForm(BoardDto boarddto, int nowpage) {
		
		// 메뉴목록조회 
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 수정할 자료 조회
		BoardDto board = boardPagingMapper.getBoard(boarddto);
		
		// 수정페이지 이동
		String       menu_id = boarddto.getMenu_id(); 
		ModelAndView mv      = new ModelAndView();
		mv.setViewName("/boardpaging/update");
		mv.addObject("menuList", menuList);
		mv.addObject("menu_id", menu_id);
		
		mv.addObject("board", board);
		mv.addObject("nowpage", nowpage);

		return mv;
	}
	
	// /BoardPaging/Update
	@RequestMapping("/Update")
	public ModelAndView update(BoardDto boarddto, int nowpage) {
		
		// db로 넘김
		boardPagingMapper.updateBoard(boarddto);
		
		// List 돌아감
		String       menu_id = boarddto.getMenu_id(); 
		ModelAndView mv      = new ModelAndView();
		String       loc     = """
				redirect:/BoardPaging/List?menu_id=%s&nowpage=%d
				""".formatted(menu_id, nowpage);
		mv.setViewName(loc);
		return mv;
	}
	
}
