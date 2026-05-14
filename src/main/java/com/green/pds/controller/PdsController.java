package com.green.pds.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import com.green.pds.mapper.PdsMapper;

@Controller
@RequestMapping("/Pds")
public class PdsController {

	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private PdsMapper pdsMapper;
	
	// /Pds/List?menu_id=MENU01&nowpage=1
	// /Pds/List?menu_id=MENU01&nowpage=1&searchType=title&keyword=11
	@RequestMapping("/List")
	public ModelAndView list(@RequestParam HashMap<String, Object> map) {
		
		// 메뉴목록조회
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		// 자료실 목록 조회 (10개씩)
		// 해당 메뉴의 전체 자료수
		int totalCount = pdsMapper.count(map); // menus_id, searchType, keyword
		System.out.println("totalCount:" + totalCount);		
		
		// 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/pds/list");
				
		mv.addObject("menuList", menuList);
		
		mv.addObject("map", map);
		return mv;
	}
	
	
}
