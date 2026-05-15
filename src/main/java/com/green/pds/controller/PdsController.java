package com.green.pds.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.paging.dto.Pagination;
import com.green.paging.dto.SearchDto;
import com.green.pds.dto.PdsDto;
import com.green.pds.mapper.PdsMapper;
import com.green.pds.service.PdsService;

@Controller
@RequestMapping("/Pds")
public class PdsController {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private PdsMapper pdsMapper;
	
	@Autowired
	private PdsService pdsService;
	
	// /Pds/List?menu_id=MENU01&nowpage=1
	// /Pds/List?menu_id=MENU01&nowpage=1&searchType=title&keyword=11
	@RequestMapping("/List")
	public ModelAndView list(@RequestParam HashMap<String, Object> map) {
		
		// 메뉴목록조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 페이징 시작
		// 자료실 목록 조회 (10개씩)
		// 해당 메뉴의 전체 자료수
		int totalCount = pdsMapper.count(map); // menus_id, searchType, keyword
		System.out.println("totalCount:" + totalCount);		
		
		// 현재 페이지 정보 : map{nowpage=1}  Object -> String -> int
		int nowpage = Integer.parseInt( String.valueOf(map.get("nowpage")) );
		
		// 페이징을 위한 설정
		SearchDto searchDto = new SearchDto();
		searchDto.setPageNo(nowpage);   // 현재페이지 설정
		searchDto.setPageSize(10);      // 한페이지에 10줄의 자료
		searchDto.setNumOfRows(10);     // 페이지 번호 목록
		
		// pagination 설정
		Pagination pagination = new Pagination(totalCount,searchDto);
		searchDto.setPagination(pagination);
		
		int offset = searchDto.getOffset();
		int numOfRows = searchDto.getNumOfRows();
		map.put("offset", offset);
		map.put("numOfRows", numOfRows);
		// 페이징 끝
		
		// 자료조회
		List<PdsDto> pdsList = pdsService.getPdsList(map);
		
		// 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/pds/list");
				
		mv.addObject("menuList", menuList);
		mv.addObject("searchdto", searchDto);
		mv.addObject("pdsList", pdsList);
		
		mv.addObject("map", map);
		return mv;
	}
	
	// /Pds/View?idx=1818&menu_id=MENU01&nowpage=1
	@RequestMapping("/View")
	public ModelAndView view(@RequestParam HashMap<String, Object> map) {
		
		// 메뉴목록조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 넘겨줄 pdsDto 정보를 조회 idx
		List<PdsDto> pdsList = pdsService.getPds(map);
		
		// 넘겨줄 filesDto 정보를 조회 idx
		
		//
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("/pds/view");
		
		mv.addObject("menuList", menuList);
		mv.addObject("pdsList", pdsList);
		
		mv.addObject("map", map);		
		return mv;
	}
	
	// /Pds/WriteForm?menu_id=MENU01&nowpage=1
	@RequestMapping("/WriteForm")
	public ModelAndView writeform(@RequestParam HashMap<String, Object> map) {
		
		// 메뉴목록조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		//
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/pds/write");
		
		mv.addObject("menuList", menuList);
		
		mv.addObject("map", map);
		return mv;
	}
	
	// /Pds/Write
	// text   : menu_id=MENU01, nowpage=1, title=aaa, writer=aaa, content=aaa -> map
	// binary : upfile=(binary) -> uploadfiles
	@RequestMapping("/Write")
	public ModelAndView write(
			@RequestParam                 HashMap<String, Object> map,
			@RequestParam(value="upfile") MultipartFile []        uploadfiles
			) {
		System.out.println("map:" + map);
		System.out.println("uploadfiles:" + uploadfiles);
		
		// db로 넘기기
		pdsService.setWriter(map, uploadfiles);
		
		// 
		ModelAndView mv  = new ModelAndView();
		String       loc = """
				redirect:/Pds/List?menu_id=%s&nowpage=%d
				""".formatted(map.get("menu_id"), map.get("nowpage"));
		mv.setViewName(loc);
		return mv;
	}
	
}
